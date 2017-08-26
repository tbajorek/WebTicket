package model;

import java.io.Serializable;

/**
 * Model for new user registration
 * 
 * @author Tomasz Bajorek
 */
public class NewUser  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Registration hash
	 */
	private String hash;
	
	/**
	 * Password of new user
	 */
	private String password;
	
	/**
	 * Name of new user
	 */
	private String name;
	
	/**
	 * Surname of new user
	 */
	private String surname;
	
	public NewUser() {}

	/**
	 * Return registration hash
	 * @return
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * Set the given registration hash
	 * @param hash Registration hash
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * Return password
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the given password
	 * @param password Password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Return name of new user
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the given name of new user
	 * @param name Name of new user
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return surname of new user
	 * @return
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Set the given surname of new user
	 * @param surname Surname of new user
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}
