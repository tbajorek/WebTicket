package api;

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

public abstract class AbstractEndpoint {
	
	protected EntityManagerFactory managerFactory;
	@PersistenceContext
	protected EntityManager entityManager;
	protected EntityTransaction entityTransaction;
    
	public AbstractEndpoint() {
		managerFactory = Persistence.createEntityManagerFactory("WebTicket");
		entityManager = managerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}
	
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
	
	public Session getSessionByToken(String token) throws BadSessionsNumber {
		List<Session> sessionsList = getAllSessionsByToken(token);
		if (sessionsList.size() == 1) {
			return sessionsList.get(0);
		} else {
			throw new BadSessionsNumber();
		}
	}
	
	public void authorize(AccountType.Types accountType, String token) throws ForbiddenRequest {
		try {
			if(token == null && accountType.equals(AccountType.Types.GUEST)) {
				return;
			}
			Session session = getSessionByToken(token);
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
		throw new ForbiddenRequest();
	}
	
	public void authorize(AccountType.Types accountType) throws ForbiddenRequest {
		authorize(accountType, null);
	}
	
	private List<Session> getAllSessionsByToken(String token) {
		return entityManager.createNamedQuery("Session.findByToken",  Session.class)
				.setParameter("token", token).getResultList();
	}
}
