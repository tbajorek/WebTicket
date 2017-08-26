package response;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * Response class of department
 * 
 * @author Tomasz Bajorek
 */
public class Invitation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identifier of invitation
	 */
	private Integer id;
	
	/**
	 * Email address
	 */
	@NotNull
	private String email;
	
	/**
	 * Hash which is needed for registration
	 */
	@NotNull
	private String hash;
	
	/**
	 * Position of new user
	 */
	private String position;

	/**
	 * Department where new user will be assigned
	 */
	@NotNull
	private Department department;
	
	/**
	 * Account type of invited user
	 */
	@NotNull
	private AccountType type;

	/**
	 * Initialization of response invitation based on model invitation
	 * @param invitation Model invitation
	 */
	public Invitation(model.Invitation invitation) {
		setId(invitation.getId());
		setEmail(invitation.getEmail());
		setHash(invitation.getHash());
		setPosition(invitation.getPosition());
		setDepartment(new Department(invitation.getDepartment()));
		setType(new AccountType(invitation.getAccountType()));
	}

	/**
	 * Return invitation id
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the given invitation id
	 * @param id Invitation identifier
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Return email address
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the given email address
	 * @param email Email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Return invitation hash
	 * @return
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * Set the given invitation hash
	 * @param hash Invitation hash
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * Return department where the user will be assigned
	 * @return
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the given department
	 * @param department Department object
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	/**
	 * Return position of user
	 * @return
	 */
	public String getPosition() {
		return position;
	}
	
	/**
	 * Set the given position
	 * @param position Position
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * Return account type which will be set to user
	 * @return
	 */
	public AccountType getType() {
		return type;
	}

	/**
	 * Set the given account type
	 * @param type Account type
	 */
	public void setType(AccountType type) {
		this.type = type;
	}
	
}
