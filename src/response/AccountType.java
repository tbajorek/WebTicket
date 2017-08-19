package response;

import java.io.Serializable;


public class AccountType implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;
	
	public AccountType(model.AccountType accountType) {
		setId(accountType.getId());
		setName(accountType.getName());
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
}
