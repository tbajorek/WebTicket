package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(
	name = "User.findByEP",
	query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password"
)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String email;
	private String password;
	private String name;
	private String surname;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id")
	private Department department;
	private Double rate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="type_id")
	private AccountType type;
	
	@OneToMany(mappedBy = "author")
	private List<Ticket> ownTickets = new ArrayList<Ticket>();
	
	@OneToMany(mappedBy = "recipient")
	private List<Ticket> adoptedTickets = new ArrayList<Ticket>();
	
	@OneToOne(mappedBy = "user")
	private Session session;
	
	public User() {}

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
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
	 * @return the rate
	 */
	public Double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(Double rate) {
		this.rate = rate;
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

	/**
	 * @return the ownTickets
	 */
	public List<Ticket> getOwnTickets() {
		return ownTickets;
	}

	/**
	 * @param ownTickets the ownTickets to set
	 */
	public void setOwnTickets(List<Ticket> ownTickets) {
		this.ownTickets = ownTickets;
	}
	
	public void addOwnTicket(Ticket ownTicket) {
		this.ownTickets.add(ownTicket);
	}

	/**
	 * @return the adoptedTickets
	 */
	public List<Ticket> getAdoptedTickets() {
		return adoptedTickets;
	}

	/**
	 * @param adoptedTickets the adoptedTickets to set
	 */
	public void setAdoptedTickets(List<Ticket> adoptedTickets) {
		this.adoptedTickets = adoptedTickets;
	}
	
	public void addAdoptedTicket(Ticket adoptedTicket) {
		this.adoptedTickets.add(adoptedTicket);
	}
}
