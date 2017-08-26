package api;
 
import java.util.ArrayList;
import java.util.List;

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
import model.User;
import model.Department;
import response.Response;

/**
 * End point of activities about department
 * 
 * @author Tomasz Bajorek
 */
@Path("/department")
public class DepartmentApi extends AbstractEndpoint {
	/**
	 * Return list of all departments in response
	 * @param token Session token
	 * @return
	 */
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response list(@HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	List<model.Department> availableDbDepartments = entityManager.createNamedQuery("Department.findAll",  model.Department.class)
        													.getResultList();
        	if(availableDbDepartments.size() > 0) {
	    		List<response.Department> availableDepartments = produceOutputDepartmentList(availableDbDepartments);
	            response.addData("departments", availableDepartments);
			} else {
				response.addWarning("Nie znaleziono departamentów");
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
	 * Return details of concrete department in response
	 * @param id Department id
	 * @param token Session token
	 * @return
	 */
	@GET
	@Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response details(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	User user = getSessionByToken(token).getUser();
        	Department department = (Department) entityManager.find(Department.class, id);
        	if(department != null) {
        		if(user.getDepartment().equals(department) || user.getType().getId().equals(AccountType.Types.ADMIN.getId())) {
        			response.addData("id", department.getId());
        			response.addData("name", department.getName());
        		} else {
            		throw new ForbiddenRequest(user);
            	}
        	} else {
        		response.setResponse(0);
        		response.addError("Nie znaleziono departamentu");
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
	 * Return list of tickets associated to the concrete department in response
	 * @param id Department id
	 * @param token Session token
	 * @return
	 */
	@GET
	@Path("{id}/tickets")
    @Produces({MediaType.APPLICATION_JSON})
    public Response tickets(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	User user = getSessionByToken(token).getUser();
        	Department department = (Department) entityManager.find(Department.class, id);
        	if(user.getDepartment().equals(department) || user.getType().getId().equals(AccountType.Types.ADMIN.getId())) {
        		List<model.Ticket> departmentDbTickets = department.getTickets();
    			if(departmentDbTickets.size() > 0) {
    	    		List<response.Ticket> departmentTickets = produceOutputTicketList(departmentDbTickets);
    	            response.addData("tickets", departmentTickets);
    			} else {
    				response.addWarning("Nie znaleziono zgłoszeń w departamencie "+department.getName());
    			}
        	} else {
        		throw new ForbiddenRequest(user);
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
	 * Return all users who are members of the concrete department in response
	 * @param id Department id
	 * @param token Session token
	 * @return
	 */
	@GET
	@Path("{id}/users")
    @Produces({MediaType.APPLICATION_JSON})
    public Response users(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	User user = getSessionByToken(token).getUser();
        	Department department = (Department) entityManager.find(Department.class, id);
        	if(department != null ) {
        		if(user.getDepartment().equals(department) || user.getType().getId().equals(AccountType.Types.ADMIN.getId())) {
            		List<model.User> departmentDbUsers = department.getUsers();
        			if(departmentDbUsers.size() > 0) {
        	    		List<response.User> departmentUsers = produceOutputUserList(departmentDbUsers);
        	            response.addData("users", departmentUsers);
        			} else {
        				response.addWarning("Nie znaleziono użytkowników w departamencie "+department.getName());
        			}
            	} else {
            		throw new ForbiddenRequest(user);
            	}
        	} else {
        		response.setResponse(0);
            	response.addError("Departament, którego użytkowników chcesz pobrać, nie istnieje");
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
	 * Return list of invitations for the concrete department in advance
	 * @param id Department id
	 * @param token Session token
	 * @return
	 */
	@GET
	@Path("{id}/invitations")
    @Produces({MediaType.APPLICATION_JSON})
    public Response invitations(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.MANAGER, token);
        	User user = getSessionByToken(token).getUser();
        	Department department = (Department) entityManager.find(Department.class, id);
        	if(department != null ) {
        		if(user.getDepartment().equals(department) || user.getType().getId().equals(AccountType.Types.ADMIN.getId())) {
            		List<model.Invitation> departmentDbInvitations = department.getInvitations();
        			if(departmentDbInvitations.size() > 0) {
        	    		List<response.Invitation> departmentInvitations = produceOutputInvitationList(departmentDbInvitations);
        	            response.addData("invitations", departmentInvitations);
        			} else {
        				response.addWarning("Nie znaleziono zaproszeń w departamencie "+department.getName());
        			}
            	} else {
            		throw new ForbiddenRequest(user);
            	}
        	} else {
        		response.setResponse(0);
            	response.addError("Departament, do którego zaproszenia chcesz pobrać, nie istnieje");
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
	 * Add new department to the database. Id and name of it are returned in response
	 * @param department Department object with filled name data
	 * @param token Session token
	 * @return
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(Department department, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.ADMIN, token);
            entityTransaction.begin();
            entityManager.persist(department);
            entityManager.flush();
            entityTransaction.commit();
            response.addData("id", department.getId());
        	response.addData("name", department.getName());
            response.addSuccess("Nowy departament został dodany");
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	/**
	 * Change name of the concrete department. The new name is returned in response.
	 * @param id Department id
	 * @param token Session token
	 * @param inputDepartment Department object with filled name data
	 * @return
	 */
	@PUT
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response changeName(@PathParam("id") Integer id, @HeaderParam("X-Token") String token, model.Department inputDepartment) {
        Response response = new Response(0);
        try {
        	authorize(AccountType.Types.ADMIN, token);
        	model.Department department = (model.Department) entityManager.find(model.Department.class, id);
        	if(department != null) {
        		department.setName(inputDepartment.getName());
        		entityTransaction.begin();
                entityManager.persist(department);
                entityManager.flush();
                entityTransaction.commit();
                response.setResponse(1);
                response.addData("name", department.getName());
            	response.addSuccess("Nazwa departamentu została zmieniona");
        	} else {
        		response.addError("Nie znaleziono departamentu o id "+id);
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
	 * Delete the concrete department
	 * @param id Department id
	 * @param token Session token
	 * @return
	 */
	@DELETE
	@Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.ADMIN, token);
        	model.Department department = (model.Department) entityManager.find(model.Department.class, id);
        	if(department != null) {
        		entityTransaction.begin();
            	entityManager.remove(department);
            	entityTransaction.commit();
            	response.addSuccess("Departament został usunięty");
        	} else {
        		response.addError("Nie znaleziono departamentu o id "+id);
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
	 * Convert the given list to list of invitations allowed to send in response
	 * @param dbInvitationList List of invitations from database
	 * @return
	 */
	protected List<response.Invitation> produceOutputInvitationList(List<model.Invitation> dbInvitationList) {
		List<response.Invitation> results = new ArrayList<response.Invitation>();
		for(model.Invitation invitation : dbInvitationList) {
			results.add(new response.Invitation(invitation));
		}
		return results;
	}
	
	/**
	 * Convert the given list to list of departments allowed to send in response
	 * @param dbDepartmentList List of departments from database
	 * @return
	 */
	protected List<response.Department> produceOutputDepartmentList(List<model.Department> dbDepartmentList) {
		List<response.Department> results = new ArrayList<response.Department>();
		for(model.Department department : dbDepartmentList) {
			results.add(new response.Department(department));
		}
		return results;
	}
	
	/**
	 * Convert the given list to list of users allowed to send in response
	 * @param dbUserList List of users from database
	 * @return
	 */
	protected List<response.User> produceOutputUserList(List<model.User> dbUserList) {
		List<response.User> results = new ArrayList<response.User>();
		for(model.User user : dbUserList) {
			results.add(new response.User(user));
		}
		return results;
	}
}
