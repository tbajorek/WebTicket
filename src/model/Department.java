package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity of department
 * 
 * @author Tomasz Bajorek
 */
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
	
	/**
	 * Identifier of the entity
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Name of the department
	 */
	@NotNull
	private String name;
	
	/**
	 * List of users who works in the department
	 */
	@OneToMany(mappedBy = "department")
	private List<User> users;
	
	/**
	 * List of ticket sent to the department
	 */
	@OneToMany(mappedBy = "department")
	private List<Ticket> tickets;
	
	/**
	 * List of invitations to the department
	 */
	@OneToMany(mappedBy = "department")
	private List<Invitation> invitations;
	
	/**
	 * Initialization of empty department object
	 */
	public Department() {}
	
	/**
	 * Initialization of the object with given account type id
	 * @param id Department identifier
	 */
	public Department(Integer id) {
		setId(id);
	}

	/**
	 * Return department id
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the given department id
	 * @param id Id of department
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Return department name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the given department name
	 * @param name Department name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return list of users assigned to department
	 * @return
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Set the given list of users assigned to department
	 * @param users List of users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Return list of ticket sent to department
	 * @return
	 */
	public List<Ticket> getTickets() {
		return tickets;
	}

	/**
	 * Set the given list of ticket sent to department
	 * @param tickets List of tickets
	 */
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	/**
	 * Return list of invitations to department
	 * @return
	 */
	public List<Invitation> getInvitations() {
		return invitations;
	}

	/**
	 * Set the given list of invitations to department
	 * @param invitations List of invitations
	 */
	public void setInvitations(List<Invitation> invitations) {
		this.invitations = invitations;
	}

}
