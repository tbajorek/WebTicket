package api;
 
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import exception.AuthenticateFailed;
import exception.ForbiddenRequest;
import model.AccountType;
import model.Session;
import model.User;
import response.Response;

/**
 * End point of authentication activities
 * 
 * @author Tomasz Bajorek
 */
@Path("/auth")
public class Auth extends AbstractEndpoint {
	
	/**
	 * Log in the user to the application and return session token and user datails in response
	 * @param request Request object
	 * @param user User object contains filled email and password fields
	 * @return
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Context HttpServletRequest request, User user) {
		System.out.println(request);
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.GUEST);
        	User dbUser = authenticate(user.getEmail(), user.getPassword());
        	Session session = new Session();
    		session.setToken(session.generateToken());
    		session.setUser(dbUser);
            response.addData("token", session.getToken());
            response.addData("user", new response.User(dbUser));
            entityTransaction.begin();
            entityManager.persist(session);
            entityManager.flush();
            entityTransaction.commit();
            response.addSuccess("Zostałeś zalogowany pomyślnie");
        } catch (AuthenticateFailed e) {
        	response.setResponse(0);
        	response.addError("Podałeś błędne dane logowania");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	/**
	 * Log out the user and remove his existing session of user
	 * @param token Session token
	 * @return
	 */
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public Response logout(@HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	Session session = getSessionByToken(token);
        	entityTransaction.begin();
        	entityManager.remove(session);
        	entityTransaction.commit();
            response.addSuccess("Zostałeś wylogowany pomyślnie");
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
}
