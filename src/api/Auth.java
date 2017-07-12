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
import model.Session;
import model.User;
import response.Response;
import tool.Hasher;

@Path("/auth")
public class Auth extends AbstractEndpoint {
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(User user) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.GUEST);
        	User dbUser = authenticate(user.getEmail(), Hasher.getHash(user.getPassword()));
        	Session session = new Session();
    		session.setToken(session.generateToken());
    		session.setUser(dbUser);
            response.addData("token", session.getToken());
            response.addData("userId", dbUser.getId());
            response.addSuccess("Zostałeś zalogowany pomyślnie");
            entityTransaction.begin();
            entityManager.persist(session);
            entityManager.flush();
            entityTransaction.commit();
        } catch (AuthenticateFailed e) {
        	response.setResponse(0);
        	response.addError("Podałeś błędne dane logowania");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
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
