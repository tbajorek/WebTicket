package response;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class Invitation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String email;
	
	private String hash;

	private Department department;
	
	private AccountType type;

	public Invitation(model.Invitation invitation) {
		setId(invitation.getId());
		setEmail(invitation.getEmail());
		setHash(invitation.getHash());
		setDepartment(new Department(invitation.getDepartment()));
		setType(new AccountType(invitation.getAccountType()));
	}

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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	/**
	 * @return the type
	 */
	public AccountType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(AccountType type) {
		this.type = type;
	}
	
}
