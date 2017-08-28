package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity of invitation
 * 
 * @author Tomasz Bajorek
 */
@Entity
@NamedQuery(
	name = "Invitation.findByHash",
	query = "SELECT i FROM Invitation i WHERE i.hash = :hash"
)
public class Invitation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identifier of the entity
	 */
	@Id
	@GeneratedValue
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
	@NotNull
	private String position;

	/**
	 * Department where new user will be assigned
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id")
	private Department department;
	
	/**
	 * Account type of invited user
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="type_id")
	private AccountType accountType;

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
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * Set the given account type
	 * @param accountType Account type
	 */
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
}
