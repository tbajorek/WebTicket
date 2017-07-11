package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	@NotNull
	private String name;
	
	@OneToMany(mappedBy = "department")
	private List<User> employees;
	
	@OneToMany(mappedBy = "department")
	private List<Ticket> tickets;
	
	@OneToMany(mappedBy = "department")
	private List<User> invitations;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the employees
	 */
	public List<User> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(List<User> employees) {
		this.employees = employees;
	}

	/**
	 * @return the tickets
	 */
	public List<Ticket> getTickets() {
		return tickets;
	}

	/**
	 * @param tickets the tickets to set
	 */
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	/**
	 * @return the invitations
	 */
	public List<User> getInvitations() {
		return invitations;
	}

	/**
	 * @param invitations the invitations to set
	 */
	public void setInvitations(List<User> invitations) {
		this.invitations = invitations;
	}

}
