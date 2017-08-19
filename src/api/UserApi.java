package api;

import java.security.SecureRandom;
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
import model.Department;
import model.Invitation;
import model.NewPassword;
import model.NewUser;
import model.Session;
import model.User;
import response.Response;
import tool.Hasher;

@Path("/user")
public class UserApi extends AbstractEndpoint {
	@GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response profile(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
			User user = (User) entityManager.find(User.class, id);
			if(user != null) {
	            response.addData("id", user.getId());
	            response.addData("email", user.getEmail());
	            response.addData("name", user.getName());
	            response.addData("surname", user.getSurname());
	            response.addData("department", new response.Department(user.getDepartment()));
	            response.addData("position", user.getPosition());
	            if(user.getType().getId().compareTo(AccountType.Types.MANAGER.getId()) >= 0) {
	            	response.addData("rate", user.getRate());
	            }
			} else {
				response.addWarning("Nie znaleziono użytkownika");
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
	@Path("{id}/password")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response changePassword(@PathParam("id") Integer id, @HeaderParam("X-Token") String token, NewPassword newPassword) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	User user = getSessionByToken(token).getUser();
        	if(!id.equals(user.getId())) {
        		throw new ForbiddenRequest();
        	}
        	if(user.equalPassword(newPassword.getOldPassword())) {
        		user.setPassword(newPassword.getNewPassword());
        		entityTransaction.begin();
                entityManager.persist(user);
                entityManager.flush();
                entityTransaction.commit();
                response.addSuccess("Twoje hasło zostało zmienione");
        	} else {
        		response.setResponse(0);
            	response.addError("Podałeś złe oryginalne hasło");
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
	@Path("{id}/profile")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response changeProfile(@PathParam("id") Integer id, @HeaderParam("X-Token") String token, User userData) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	User user = getSessionByToken(token).getUser();
        	if(!id.equals(user.getId())) {
        		throw new ForbiddenRequest();
        	}
        	if(userData.getName() != null) {
        		user.setName(userData.getName());
        	}
        	if(userData.getSurname() != null) {
        		user.setSurname(userData.getSurname());
        	}
        	if(userData.getEmail() != null) {
        		user.setEmail(userData.getEmail());
        	}
        	entityTransaction.begin();
            entityManager.merge(user);
            entityTransaction.commit();
        	response.addSuccess("Twoje dane zostały zmienione");
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	@PUT
	@Path("{id}/department")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response changeDepartment(@PathParam("id") Integer id, @HeaderParam("X-Token") String token, model.Department newDepartment) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.ADMIN, token);
        	User user = (User) entityManager.find(User.class, id);
        	if(user != null) {
	        	model.Department department = (model.Department) entityManager.find(model.Department.class, newDepartment.getId());
	        	if(department != null ) {
		        	user.setDepartment(department);
		        	entityTransaction.begin();
		            entityManager.merge(user);
		            entityTransaction.commit();
		            response.addSuccess("Departament użytkownika "+user.getName()+" "+user.getSurname()+" został zmieniony");
	        	} else {
	        		response.addError("Nie znaleziono departamentu o identyfikatorze "+newDepartment.getId());
	        	}
        	} else {
        		response.addError("Nie znaleziono użytkownika o identyfikatorze "+id);
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
	@Path("{id}/position")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response changeDepartment(@PathParam("id") Integer id, @HeaderParam("X-Token") String token, User newUser) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.MANAGER, token);
        	User user = (User) entityManager.find(User.class, id);
        	if(user != null) {
        		User manager = getSessionByToken(token).getUser();
        		if(!manager.getDepartment().getId().equals(user.getDepartment().getId())) {
        			throw new ForbiddenRequest();
        		}
	        	user.setPosition(newUser.getPosition());
	        	entityTransaction.begin();
	            entityManager.merge(user);
	            entityTransaction.commit();
	        	response.addSuccess("Stanowisko użytkownika "+user.getName()+" "+user.getSurname()+" zostało zmienione");
        	} else {
        		response.addError("Nie znaleziono użytkownika");
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
	@Path("{hash}")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response registerUser(@PathParam("hash") String hash, NewUser newUser) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.GUEST);
        	Invitation invitation = findInvitation(hash);
        	if(invitation != null) {
        		User dbUser = new User();
        		dbUser.setDepartment(invitation.getDepartment());
        		dbUser.setEmail(invitation.getEmail());
        		dbUser.setPassword(newUser.getPassword());
        		dbUser.setName(newUser.getName());
        		dbUser.setSurname(newUser.getSurname());
        		dbUser.setPosition(invitation.getPosition());
        		dbUser.setType(invitation.getAccountType());
        		dbUser.setRate(0.0);
        		dbUser.setRateCounter(0);
	        	entityTransaction.begin();
	        	entityManager.persist(dbUser);
	            entityManager.remove(invitation);
	            entityTransaction.commit();
	            response.addData("user", new response.User(dbUser));
	        	response.addSuccess("Użytkownik został zarejestrowany. Konto jest dostępne do logowania.");
        	} else {
        		response.setResponse(0);
        		response.addError("Nie znaleziono zaproszenia");
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	private Invitation findInvitation(String hash) {
		List<Invitation> invitations = entityManager.createNamedQuery("Invitation.findByHash",  model.Invitation.class)
				.setParameter("hash", hash)
				.getResultList();
		if(invitations.size() > 0) {
			return invitations.get(0);
		} else {
			return null;
		}
	}
}
