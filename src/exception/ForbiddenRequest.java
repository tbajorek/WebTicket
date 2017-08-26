package exception;

import model.User;

/**
 * Exception thrown when access to a resource is forbidden
 * 
 * @author Tomasz Bajorek
 */
public class ForbiddenRequest extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * User object
	 */
	private User user;
	
	/**
	 * Initialize the exception with user for whom it is forbidden to access an end point
	 * @param user User object
	 */
	public ForbiddenRequest(User user) {
		this.user = user;
	}
	
	/**
	 * Return user for whom it is forbidden to access an end point
	 * @return
	 */
	public User getUser() {
		return user;
	}
}
