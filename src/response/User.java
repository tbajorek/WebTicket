package response;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String email;
	private String name;
	private String surname;
	private String position;

	private Department department;
	
	private Double rate;
	
	private Integer rateCounter;

	private AccountType type;
	
	public User() {}
	
	public User(model.User dbUser, boolean full) {
		setId(dbUser.getId());
		setEmail(dbUser.getEmail());
		setName(dbUser.getName());
		setSurname(dbUser.getSurname());
		if(full == true) {
			setDepartment(new Department(dbUser.getDepartment()));
			setRate(dbUser.getRate());
			setRateCounter(dbUser.getRateCounter());
			setType(new AccountType(dbUser.getType()));
		}
	}
	
	public User(model.User dbUser) {
		this(dbUser, true);
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
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
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
	 * @return the rate counter
	 */
	public Integer getRateCounter() {
		return rateCounter;
	}

	/**
	 * @param rate the rate counter to set
	 */
	public void setRateCounter(Integer rateCounter) {
		this.rateCounter = rateCounter;
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
