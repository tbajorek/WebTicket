package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class AccountType implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public enum Types {
		GUEST(0), WORKER(1), MANAGER(2), ADMIN(3);
		private Integer id;
		
		private Types(Integer id) {
			this.id = id;
		}
		public Integer getId() {
			return id;
		}
	};
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	private String name;
	
	@OneToMany(mappedBy = "type")
	private List<User> users;
	
	public AccountType() {}
	
	public AccountType(Integer id) {
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
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	
}
