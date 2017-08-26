package api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import exception.AuthenticateFailed;
import exception.BadSessionsNumber;
import exception.ForbiddenRequest;
import model.AccountType;
import model.Session;
import model.User;

/**
 * This end point provides universal functionality for all other end points eg. providing session for owner of the token.
 * 
 * @author Tomasz Bajorek
 */
public abstract class AbstractEndpoint {
	/**
	 * Factory of entity managers
	 */
	protected EntityManagerFactory managerFactory;
	/**
	 * Manager of entities
	 */
	@PersistenceContext
	protected EntityManager entityManager;
	/**
	 * Entity transaction which allows to manage transactions
	 */
	protected EntityTransaction entityTransaction;
    
	/**
	 * Initialization of basic fields
	 */
	public AbstractEndpoint() {
		managerFactory = Persistence.createEntityManagerFactory("WebTicket");
		entityManager = managerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}
	
	/**
	 * Authentication with the given data
	 * @param email Email of a user
	 * @param password Password of a user
	 * @return
	 * @throws AuthenticateFailed Thrown when authentication can't be finished successfully
	 */
	public User authenticate(String email, String password) throws AuthenticateFailed {
	    List<User> usersList = entityManager.createNamedQuery("User.findByEP",  User.class)
	    									.setParameter("email", email)
	    									.setParameter("password", password).getResultList();
	    if (usersList.size() > 0) {
	    	return usersList.get(0);
	    } else {
	    	throw new AuthenticateFailed();
	    }
	}
	
	/**
	 * Return session object for the given token if it exists
	 * @param token Session token
	 * @return
	 * @throws BadSessionsNumber Thrown when multiple sessions with the given token exist in database
	 */
	public Session getSessionByToken(String token) throws BadSessionsNumber {
		List<Session> sessionsList = getAllSessionsByToken(token);
		if (sessionsList.size() == 1) {
			return sessionsList.get(0);
		} else {
			throw new BadSessionsNumber();
		}
	}
	
	/**
	 * Authorize owner of the given token and compare his role with the given
	 * @param accountType
	 * @param token
	 * @throws ForbiddenRequest Thrown when user can't have access to an end point
	 */
	public void authorize(AccountType.Types accountType, String token) throws ForbiddenRequest {
		model.User user = null; 
		try {
			if(token == null && accountType.equals(AccountType.Types.GUEST)) {
				return;
			}
			Session session = getSessionByToken(token);
			user = session.getUser();
			if(session.getUser().getType().getId().compareTo(accountType.getId()) >= 0) {
				return;
			}
		} catch (BadSessionsNumber e) {
			List<Session> sessionsList = getAllSessionsByToken(token);
			entityTransaction.begin();
			for (Session session : sessionsList) {
				entityManager.remove(session);
	        }
            entityTransaction.commit();
		}
		throw new ForbiddenRequest(user);
	}
	
	/**
	 * Authorize user without token with the given role
	 * @param accountType
	 * @throws ForbiddenRequest Thrown when user can't have access to an end point
	 */
	public void authorize(AccountType.Types accountType) throws ForbiddenRequest {
		authorize(accountType, null);
	}
	
	/**
	 * Return all session objects associated with the given token
	 * @param token Session token
	 * @return
	 */
	private List<Session> getAllSessionsByToken(String token) {
		return entityManager.createNamedQuery("Session.findByToken",  Session.class)
				.setParameter("token", token).getResultList();
	}
	
	/**
	 * Convert list of tickets from database to tickets returned for output
	 * @param dbTicketList List of tickets from database
	 * @return
	 */
	protected List<response.Ticket> produceOutputTicketList(List<model.Ticket> dbTicketList) {
		List<response.Ticket> results = new ArrayList<response.Ticket>();
		for(model.Ticket ticket : dbTicketList) {
			results.add(new response.Ticket(ticket));
		}
		return results;
	}
}
