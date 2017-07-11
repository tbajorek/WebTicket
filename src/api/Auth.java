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

import model.Session;
import model.User;
import response.Response;
import tool.Hasher;

@Path("/auth")
public class Auth {
	private EntityManagerFactory managerFactory;
	@PersistenceContext
    private EntityManager entityManager; // = managerFactory.createEntityManager();
    private EntityTransaction entityTransaction;
	
	public Auth() {
			managerFactory = Persistence.createEntityManagerFactory("WebTicket");
			entityManager = managerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction(); 
	 
	    }
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(User user) {
		User dbUser = authenticate(user.getEmail(), Hasher.getHash(user.getPassword()));
		//System.out.println(dbUser.getEmail());
		System.out.println(user.getEmail());
		/*Session session = new Session();
		session.setToken(session.generateToken());
		entityTransaction.begin();
        entityManager.persist(user);
        entityManager.flush();
        entityTransaction.commit();*/
		
        Response response = new Response(1);
        try {
            response.addData("token", "dfsdfsfsfsdfsd");
            response.addSuccess("Zostałeś zalogowany pomyślnie");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	@DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public Response logout(@HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        System.out.println(token);
        try {
            response.addSuccess("Zostałeś wylogowany pomyślnie");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	public User authenticate(String email, String password) {
	    List<User> usersList = entityManager.createNamedQuery("User.findUserByEP",  User.class)
	    									.setParameter("email", email)
	    									.setParameter("password", password).getResultList();
	    User firstUserFromList = usersList.get(0);
	    return firstUserFromList;
	}
}
