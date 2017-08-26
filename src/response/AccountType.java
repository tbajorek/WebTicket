package response;

import java.io.Serializable;

/**
 * Response class of account type
 * 
 * @author Tomasz Bajorek
 */
public class AccountType implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Identifier of account type
	 */
	private Integer id;
	
	/**
	 * Name of account type
	 */
	private String name;
	
	/**
	 * Initialization of response account type based on model account type
	 * @param accountType Model account type
	 */
	public AccountType(model.AccountType accountType) {
		setId(accountType.getId());
		setName(accountType.getName());
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
}
