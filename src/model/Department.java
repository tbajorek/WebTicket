package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
	@NamedQuery(
			name = "Department.findAvailable",
			query = "SELECT t FROM Ticket t WHERE t.department = :department AND t.recipient IS NULL"
		),
	@NamedQuery(
			name = "Department.findAll",
			query = "SELECT d FROM Department d"
		)
})
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@NotNull
	private String name;
	
	@OneToMany(mappedBy = "department")
	private List<User> users;
	
	@OneToMany(mappedBy = "department")
	private List<Ticket> tickets;
	
	@OneToMany(mappedBy = "department")
	private List<Invitation> invitations;
	
	public Department() {}
	
	public Department(Integer id) {
		setId(id);
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
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the employees to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
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
	public List<Invitation> getInvitations() {
		return invitations;
	}

	/**
	 * @param invitations the invitations to set
	 */
	public void setInvitations(List<Invitation> invitations) {
		this.invitations = invitations;
	}

}
