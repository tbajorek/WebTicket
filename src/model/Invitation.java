package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Invitation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@NotNull
	private String email;
	
	@NotNull
	private String hash;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id")
	private Department department;

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
	
}
