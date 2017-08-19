package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import exception.ForbiddenRequest;
import model.AccountType;
import model.Department;
import model.Invitation;
import model.Milestone;
import model.User;
import model.AccountType.Types;
import response.Response;
import tool.Hasher;

@Path("/invitation")
public class InvitationApi extends AbstractEndpoint {
	private static final Types ADMIN = AccountType.Types.ADMIN;
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response invite(Invitation invitation, @HeaderParam("X-Token") String token) {
		Response responseStructure = new Response(0);
        try {
        	authorize(AccountType.Types.MANAGER, token);
        	User user = getSessionByToken(token).getUser();
        	Double randomNumber = new Double(Math.random());
        	invitation.setHash(Hasher.getHash(randomNumber.toString()));
        	if(user.getType().getId().equals(ADMIN.getId())) {
        		return inviteToAnyDepartment(invitation);
        	} else {
        		return inviteToMyDepartment(invitation, token);
        	}
        } catch (ForbiddenRequest e) {
        	responseStructure.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return responseStructure;
    }
	
	@DELETE
	@Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.ADMIN, token);
        	model.Invitation invitation = (model.Invitation) entityManager.find(model.Invitation.class, id);
        	if(invitation != null) {
        		entityTransaction.begin();
            	entityManager.remove(invitation);
            	entityTransaction.commit();
            	response.addSuccess("Zaproszenie zostało usunięte");
        	} else {
        		response.addError("Nie znaleziono zaproszenia o id "+id);
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	private Response inviteToMyDepartment(Invitation invitation, String token) {
		Response responseStructure = new Response(1);
        try {
        	Department department = getSessionByToken(token).getUser().getDepartment();
        	invitation.setDepartment(department);
            entityTransaction.begin();
            entityManager.persist(invitation);
            entityManager.flush();
            entityTransaction.commit();
            response.Invitation responseInvitation = new response.Invitation(invitation);
            responseStructure.addData("invitation", responseInvitation);
            responseStructure.addSuccess("Zaproszenie zostało dodane");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return responseStructure;
    }
	
	private Response inviteToAnyDepartment(Invitation invitation) {
		Response responseStructure = new Response(1);
        try {
        	Department department = (model.Department) entityManager.find(model.Department.class, invitation.getDepartment().getId());
        	if(department == null) {
        		responseStructure.addError("Nie znaleziono żądanego departamentu");
        		responseStructure.setResponse(0);
        		return responseStructure;
        	}
            entityTransaction.begin();
            entityManager.persist(invitation);
            entityManager.flush();
            entityTransaction.commit();
            response.Invitation responseInvitation = new response.Invitation(invitation);
            responseStructure.addData("invitation", responseInvitation);
            responseStructure.addSuccess("Zaproszenie zostało dodane");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return responseStructure;
    }
}