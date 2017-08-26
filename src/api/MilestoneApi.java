package api;

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

import exception.ForbiddenRequest;
import model.AccountType;
import model.Ticket;
import model.Milestone;
import model.User;
import response.Response;

/**
 * End point of milestones
 * 
 * @author Tomasz Bajorek
 */
@Path("/milestone")
public class MilestoneApi extends AbstractEndpoint {
	/**
	 * Return information of the concrete milestone in response
	 * @param id Milestone id
	 * @param token Session token
	 * @return
	 */
	@GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response view(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
			Milestone milestone = (Milestone) entityManager.find(Milestone.class, id);
			if(milestone != null) {
				User user = getSessionByToken(token).getUser();
				if(!milestone.getTicket().hasUserPermissions(user)) {
					throw new ForbiddenRequest(user);
				}
				response.Milestone outputMilestone = new response.Milestone(milestone);
	            response.addData("id", outputMilestone.getId());
	            response.addData("name", outputMilestone.getName());
	            response.addData("done", outputMilestone.getDone());
			} else {
				response.addWarning("Nie znaleziono kroku milowego");
			}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	/**
	 * Create new milestone with the given data
	 * @param newMilestone Milestone object contains filled name and id of related ticket
	 * @param token Session token
	 * @return
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(Milestone newMilestone, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	Ticket dbTicket = (model.Ticket) entityManager.find(Ticket.class, newMilestone.getTicket().getId());
        	User user = getSessionByToken(token).getUser();
        	if(!dbTicket.hasUserPermissions(user)) {
        		throw new ForbiddenRequest(user);
        	}
            entityTransaction.begin();
            entityManager.persist(newMilestone);
            entityManager.flush();
            entityTransaction.commit();
            response.addData("id", newMilestone.getId());
            response.addSuccess("Krok milowy został zapisany");
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	/**
	 * Change information about the concrete milestone
	 * @param id Milestone id
	 * @param token Session token
	 * @param newMilestone Milestone object contains filled name flag if it is done
	 * @return
	 */
	@PUT
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response change(@PathParam("id") Integer id, @HeaderParam("X-Token") String token, Milestone newMilestone) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	User user = getSessionByToken(token).getUser();
        	Milestone dbMilestone = (Milestone) entityManager.find(Milestone.class, id);
        	if(dbMilestone != null) {
	        	if(!dbMilestone.getTicket().hasUserPermissions(user)) {
	        		throw new ForbiddenRequest(user);
	        	}
	        	if(newMilestone.getName() != null) {
	        		dbMilestone.setName(newMilestone.getName());
	        	}
	        	if(newMilestone.getDone() != null) {
	        		dbMilestone.setDone(newMilestone.getDone());
	        	}
	        	entityTransaction.begin();
	            entityManager.merge(dbMilestone);
	            entityTransaction.commit();
	        	response.addSuccess("Krok milowy został zmieniony");
        	} else {
        		response.setResponse(0);
        		response.addError("Nie znaleziono kroku milowego o id "+id);
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	/**
	 * Delete the concrete milestone
	 * @param id Milestone id
	 * @param token Session token
	 * @return
	 */
	@DELETE
	@Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.MANAGER, token);
        	User user = getSessionByToken(token).getUser();
        	Milestone milestone = (Milestone) entityManager.find(Milestone.class, id);
        	if(milestone != null) {
        		if(!milestone.getTicket().hasUserPermissions(user)) {
	        		throw new ForbiddenRequest(user);
	        	}
        		entityTransaction.begin();
            	entityManager.remove(milestone);
            	entityTransaction.commit();
            	response.addSuccess("Krok milowy został usunięty");
        	} else {
        		response.addError("Nie znaleziono kroku milowego o id "+id);
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
}
