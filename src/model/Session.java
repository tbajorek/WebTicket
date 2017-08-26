package model;

import java.io.Serializable;
import java.security.SecureRandom;

import javax.persistence.*;

import tool.Hasher;

/**
 * Entity of session
 * 
 * @author Tomasz Bajorek
 */
@Entity
@NamedQuery(
	name = "Session.findByToken",
	query = "SELECT s FROM Session s WHERE s.token = :token"
)
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identifier of the entity
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Owner of session
	 */
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
	private User user;
	
	/**
	 * Session token
	 */
	@Column(nullable=false)
	private String token;
	
	/**
	 * Initialization of empty session object
	 */
	public Session() {}
	
	/**
	 * Return session id
	 * @return
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Set the given session id
	 * @param id Session id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Return a user who is owner of session
	 * @return
	 */
	public User getUser() {
		return user;
	}
	/**
	 * Set the given user as an owner
	 * @param user User
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * Return session token
	 * @return
	 */
	public String getToken() {
		return token;
	}
	/**
	 * Set the given session token
	 * @param token Session token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	/**
	 * Generate session token
	 * @return
	 */
	public String generateToken() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		return Hasher.getHash(bytes.toString());
	}
}
