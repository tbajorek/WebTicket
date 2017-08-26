package response;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Response class of single ticket
 * 
 * @author Tomasz Bajorek
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Identifier of the entity
	 */
	private Integer id;
	
	/**
	 * Email address
	 */
	@NotNull
	private String email;
	
	/**
	 * Name of user
	 */
	@NotNull
	private String name;
	
	/**
	 * Surname of user
	 */
	@NotNull
	private String surname;
	
	/**
	 * Position of user
	 */
	@NotNull
	private String position;

	/**
	 * Department where user is assigned
	 */
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
	private AccountType type;
	
	/**
	 * Initialization empty response user object
	 */
	public User() {}
	
	/**
	 * Initialization of single ticket based on model single ticket. If the flag is set as "false", then only the most important features are copied to the response object.
	 * @param dbUser Model ticket
	 * @param full Flag if the whole data should be transfered
	 */
	public User(model.User dbUser, boolean full) {
		setId(dbUser.getId());
		setEmail(dbUser.getEmail());
		setName(dbUser.getName());
		setSurname(dbUser.getSurname());
		if(full == true) {
			setPosition(dbUser.getPosition());
			setDepartment(new Department(dbUser.getDepartment()));
			setRate(dbUser.getRate());
			setRateCounter(dbUser.getRateCounter());
			setType(new AccountType(dbUser.getType()));
		}
	}
	
	/**
	 * Initialization with default full information using user model from database.
	 * @param dbUser
	 */
	public User(model.User dbUser) {
		this(dbUser, true);
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
	 * Set the given email address
	 * @param email Email address
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
}
