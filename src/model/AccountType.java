package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity of account type
 * 
 * @author Tomasz Bajorek
 */
@Entity
public class AccountType implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Enumeration contains all possible account types
	 */
	public enum Types {
		GUEST(0), WORKER(1), MANAGER(2), ADMIN(3);
		private Integer id;
		
		/**
		 * Initialize enumeration element with the given identifier
		 * @param id Account type id
		 */
		private Types(Integer id) {
			this.id = id;
		}
		
		/**
		 * Return account type id
		 * @return
		 */
		public Integer getId() {
			return id;
		}
	};
	
	/**
	 * Identifier of the entity
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Name of account type
	 */
	@NotNull
	private String name;
	
	/**
	 * List of users who has set the concrete account type
	 */
	@OneToMany(mappedBy = "type")
	private List<User> users;
	
	/**
	 * Initialization of empty account type object
	 */
	public AccountType() {}
	
	/**
	 * Initialization of the object with given account type id
	 * @param id Account type identifier
	 */
	public AccountType(Integer id) {
		setId(id);
	}

	/**
	 * Return account type identifier
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the given identifier
	 * @param id Identifier
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Return account type name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the given account type name
	 * @param name Account type name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return list of associated users
	 * @return
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Set the list of users as associated with the account type
	 * @param users List of users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	
}
