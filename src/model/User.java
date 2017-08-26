package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import tool.Hasher;

/**
 * Entity of user
 * 
 * @author Tomasz Bajorek
 */
@Entity
@NamedQuery(
	name = "User.findByEP",
	query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password"
)
public class User implements Serializable {
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
	private String email;
	
	/**
	 * User password
	 */
	private String password;
	
	/**
	 * Name of user
	 */
	private String name;
	
	/**
	 * Surname of user
	 */
	private String surname;
	
	/**
	 * Position of user
	 */
	private String position;
	
	/**
	 * Department where user is assigned
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id")
	private Department department;
	
	/**
	 * Rate of user
	 */
	private Double rate;
	
	/**
	 * Number of rates got by user
	 */
	private Integer rateCounter;
	
	/**
	 * Account type
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="type_id")
	private AccountType type;
	
	/**
	 * List of tickets sent by the user
	 */
	@OneToMany(mappedBy = "author")
	private List<Ticket> ownTickets = new ArrayList<Ticket>();
	
	/**
	 * List of tickets adopted by the user
	 */
	@OneToMany(mappedBy = "recipient")
	private List<Ticket> adoptedTickets = new ArrayList<Ticket>();
	
	/**
	 * Session which belongs to the user
	 */
	@OneToOne(mappedBy = "user")
	private Session session;
	
	/**
	 * Initialization of empty user object
	 */
	public User() {}

	/**
	 * Return session which belongs to the user
	 * @return
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * Set session which belongs to the user
	 * @param session Session object
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * Return user identifier
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the given user identifier
	 * @param id User identifier
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
	 * Return password
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the given password. It converts input to hash, so it is impossible to recover original password after executing this method.
	 * @param password User password
	 */
	public void setPassword(String password) {
		this.password = Hasher.getHash(password);
	}

	/**
	 * Return name of user
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the given name of user
	 * @param name Name of user
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return surname of user
	 * @return
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Set the given surname of user
	 * @param surname Surname of user
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
	 * Return position of user
	 * @return
	 */
	public String getPosition() {
		return position;
	}
	
	/**
	 * Set the given position of user
	 * @param position Position of user
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * Return department to which the user is assigned
	 * @return
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the given department
	 * @param department Department to which the user is assigned
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Return rate of user
	 * @return
	 */
	public Double getRate() {
		return rate;
	}

	/**
	 * Set the given rate
	 * @param rate Rate value
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	/**
	 * Return number of times when user has been rated
	 * @return
	 */
	public Integer getRateCounter() {
		return rateCounter;
	}

	/**
	 * Set the given rate number
	 * @param rate Number of times when user has been rated
	 */
	public void setRateCounter(Integer rateCounter) {
		this.rateCounter = rateCounter;
	}

	/**
	 * Return account type of user
	 * @return
	 */
	public AccountType getType() {
		return type;
	}

	/**
	 * Set the given account type of user
	 * @param type Account type
	 */
	public void setType(AccountType type) {
		this.type = type;
	}

	/**
	 * Return list of tickets created by user
	 * @return
	 */
	public List<Ticket> getOwnTickets() {
		return ownTickets;
	}

	/**
	 * Set the given list of tickets created by user
	 * @param ownTickets List of tickets created by user
	 */
	public void setOwnTickets(List<Ticket> ownTickets) {
		this.ownTickets = ownTickets;
	}
	
	/**
	 * Add new ticket created by user to the list
	 * @param ownTicket Ticket created by user
	 */
	public void addOwnTicket(Ticket ownTicket) {
		this.ownTickets.add(ownTicket);
	}

	/**
	 * Return list of tickets adopted by user
	 * @return the adoptedTickets
	 */
	public List<Ticket> getAdoptedTickets() {
		return adoptedTickets;
	}

	/**
	 * Set the given list of tickets adopted by user
	 * @param adoptedTickets List of tickets adopted by user
	 */
	public void setAdoptedTickets(List<Ticket> adoptedTickets) {
		this.adoptedTickets = adoptedTickets;
	}
	
	/**
	 * Add new ticket adopted by user to the list
	 * @param adoptedTicket Ticket adopted by user
	 */
	public void addAdoptedTicket(Ticket adoptedTicket) {
		this.adoptedTickets.add(adoptedTicket);
	}
	
	/**
	 * Check if the given password is equal with password of user
	 * @param password Password given as normal text without hash operation yet
	 * @return
	 */
	public boolean equalPassword(String password) {
		return getPassword().equals(Hasher.getHash(password));
	}
	
	/**
	 * Update rate of user using new value
	 * @param rate New rate to add
	 */
	public void addNewRate(Integer rate) {
		int newCounter = getRateCounter()+1;
		double newRate = (getRate()*getRateCounter()+rate.doubleValue())/(double)newCounter;
		setRate(Math.round(newRate*100.0 )/100.0);
		setRateCounter(newCounter);
	}
}
