package model;

import java.io.Serializable;
import java.security.SecureRandom;

import javax.persistence.*;

import tool.Hasher;

@Entity
@NamedQuery(
	name = "Session.findByToken",
	query = "SELECT s FROM Session s WHERE s.token = :token"
)
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
	private User user;
	
	@Column(nullable=false)
	private String token;
	
	public Session() {}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	public String generateToken() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		return Hasher.getHash(bytes.toString());
	}
}
