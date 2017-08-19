package api;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import exception.AuthenticateFailed;
import exception.ForbiddenRequest;
import model.AccountType;
import model.AccountType.Types;
import model.Department;
import model.Session;
import model.Ticket;
import model.User;
import response.Response;

@Path("/ticket")
public class TicketApi extends AbstractEndpoint {
	private static final Types ADMIN = AccountType.Types.ADMIN;

	@GET
    @Path("/my")
    @Produces({MediaType.APPLICATION_JSON})
    public Response my(@HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	User user = getSessionByToken(token).getUser();
        	List<model.Ticket> myDbTickets = user.getOwnTickets();
        	if(myDbTickets.size() > 0) {
	    		List<response.Ticket> myTickets = produceOutputTicketList(myDbTickets);
	            response.addData("tickets", myTickets);
        	} else {
        		response.addWarning("Nie znaleziono przyjętych zgłoszeń");
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	@GET
    @Path("/adopted")
    @Produces({MediaType.APPLICATION_JSON})
    public Response adopted(@HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	User user = getSessionByToken(token).getUser();
        	List<model.Ticket> adoptedDbTickets = user.getAdoptedTickets();
        	if(adoptedDbTickets.size() > 0) {
	    		List<response.Ticket> acceptedTickets = produceOutputTicketList(adoptedDbTickets);
	            response.addData("tickets", acceptedTickets);
        	} else {
        		response.addWarning("Nie znaleziono przyjętych zgłoszeń");
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	@GET
    @Path("/available")
    @Produces({MediaType.APPLICATION_JSON})
    public Response available(@HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	Department department = getSessionByToken(token).getUser().getDepartment();
        	List<model.Ticket> availableDbTickets = entityManager.createNamedQuery("Department.findAvailable",  model.Ticket.class)
					.setParameter("department", department).getResultList();
        	if(availableDbTickets.size() > 0) {
	    		List<response.Ticket> availableTickets = produceOutputTicketList(availableDbTickets);
	            response.addData("tickets", availableTickets);
        	} else {
        		response.addWarning("Nie znaleziono dostępnych zgłoszeń");
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	@GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response details(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	model.Ticket ticket = (model.Ticket) entityManager.find(model.Ticket.class, id);
        	if(ticket != null) {
	        	response.addData("id", ticket.getId());
	        	response.addData("title", ticket.getTitle());
	            response.addData("department", new response.Department(ticket.getDepartment()));
	            response.addData("created", ticket.getCreated());
	            response.addData("author", new response.User(ticket.getAuthor()));
	            if(ticket.getRecipient() != null) {
	            	response.addData("recipient", new response.User(ticket.getRecipient()));
	            }
	            response.addData("newChanges", ticket.getNewChanges());
	            response.addData("lastModified", ticket.getLastModified());
	            response.addData("closed", ticket.getClosed());
        	} else {
        		response.addWarning("Nie znaleziono zgłoszenia o id "+id);
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(Ticket ticket, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	ticket.setSomethingNew();
        	ticket.setCreated(new Date());
        	ticket.setAuthor(getSessionByToken(token).getUser());
            entityTransaction.begin();
            entityManager.persist(ticket);
            entityManager.flush();
            entityTransaction.commit();
            response.addData("id", ticket.getId());
        	response.addData("title", ticket.getTitle());
            response.addData("department", new response.Department(ticket.getDepartment()));
            response.addData("created", ticket.getCreated());
            response.addData("author", new response.User(ticket.getAuthor()));
            if(ticket.getRecipient() != null) {
            	response.addData("recipient", new response.User(ticket.getRecipient()));
            }
            response.addData("newChanges", ticket.getNewChanges());
            response.addData("lastModified", ticket.getLastModified());
            response.addSuccess("Nowe zgłoszenie zostało zapisane");
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	@DELETE
	@Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.MANAGER, token);
        	User user = getSessionByToken(token).getUser();
        	model.Ticket ticket = (model.Ticket) entityManager.find(model.Ticket.class, id);
        	if(ticket != null) {
        		if(user.getDepartment().equals(ticket.getDepartment()) || user.getType().getId().equals(ADMIN.getId())) {
	        		entityTransaction.begin();
	            	entityManager.remove(ticket);
	            	entityTransaction.commit();
	            	response.addSuccess("Zgłoszenie zostało usunięte");
        		} else {
        			response.addError("Nie możesz usunąć zgłoszenia z innego departamentu");
        		}
        	} else {
        		response.addError("Nie znaleziono zgłoszenia o id "+id);
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	@PUT
	@Path("{id}/rate")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response rate(@PathParam("id") Integer id, @HeaderParam("X-Token") String token, model.Ticket ratedTicket) {
        Response response = new Response(0);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	User user = getSessionByToken(token).getUser();
        	model.Ticket ticket = (model.Ticket) entityManager.find(model.Ticket.class, id);
        	if(ticket != null) {
        		if(user.getId().equals(ticket.getAuthor().getId())) {
        			if(ticket.getClosed()!= null) {
	        			ticket.setRate(ratedTicket.getRate());
	        			user.addNewRate(ratedTicket.getRate());
	        			entityTransaction.begin();
	                    entityManager.persist(ticket);
	                    entityManager.persist(user);
	                    entityManager.flush();
	                    entityTransaction.commit();
	                    response.setResponse(1);
		            	response.addSuccess("Zgłoszenie zostało ocenione");
        			} else {
        				response.addError("Nie możesz ocenić zgłoszenia, wciąż jest ono niezamknięte");
        			}
        		} else {
        			response.addError("Nie możesz ocenić zgłoszenia, stworzonego przez innego użytkownika");
        		}
        	} else {
        		response.addError("Nie znaleziono zgłoszenia o id "+id);
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	@PUT
	@Path("{id}/close")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response close(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	User user = getSessionByToken(token).getUser();
        	model.Ticket ticket = (model.Ticket) entityManager.find(model.Ticket.class, id);
        	if(ticket != null) {
        		if(user.getId().equals(ticket.getAuthor().getId())) {
        			ticket.setClosed(new Date());
        			ticket.setSomethingNew();
        			entityTransaction.begin();
                    entityManager.persist(ticket);
                    entityManager.flush();
                    entityTransaction.commit();
	            	response.addSuccess("Zgłoszenie zostało zamknięte");
        		} else {
        			response.addError("Nie możesz ocenić zgłoszenia, stworzonego przez innego użytkownika");
        		}
        	} else {
        		response.addError("Nie znaleziono zgłoszenia o id "+id);
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	@GET
    @Path("{id}/messages")
    @Produces({MediaType.APPLICATION_JSON})
    public Response messages(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	User user = getSessionByToken(token).getUser();
        	Ticket ticket = (Ticket) entityManager.find(Ticket.class, id);
        	if(user.getId().equals(ticket.getAuthor().getId()) || user.getId().equals(ticket.getRecipient().getId())) {
        		List<model.Message> dbMessages = ticket.getMessages();
    			if(dbMessages.size() > 0) {
    	    		List<response.Message> messages = produceOutputMessageList(dbMessages);
    	            response.addData("messages", messages);
    			} else {
    				response.addWarning("Nie znaleziono wiaddomości dla zgłoszenia");
    			}
        	} else {
        		throw new ForbiddenRequest();
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	@GET
    @Path("{id}/milestones")
    @Produces({MediaType.APPLICATION_JSON})
    public Response milestones(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	User user = getSessionByToken(token).getUser();
        	Ticket ticket = (Ticket) entityManager.find(Ticket.class, id);
        	if(user.getId().equals(ticket.getAuthor().getId()) || user.getId().equals(ticket.getRecipient().getId())) {
        		List<model.Milestone> dbMilestones = ticket.getMilestones();
    			if(dbMilestones.size() > 0) {
    	    		List<response.Milestone> milestones = produceOutputMilestoneList(dbMilestones);
    	            response.addData("milestones", milestones);
    			} else {
    				response.addWarning("Nie znaleziono kroków milowych dla zgłoszenia");
    			}
        	} else {
        		throw new ForbiddenRequest();
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	protected List<response.Message> produceOutputMessageList(List<model.Message> dbMessageList) {
		List<response.Message> results = new ArrayList<response.Message>();
		for(model.Message message : dbMessageList) {
			results.add(new response.Message(message));
		}
		return results;
	}
	
	protected List<response.Milestone> produceOutputMilestoneList(List<model.Milestone> dbMilestoneList) {
		List<response.Milestone> results = new ArrayList<response.Milestone>();
		for(model.Milestone milestone : dbMilestoneList) {
			results.add(new response.Milestone(milestone));
		}
		return results;
	}
}