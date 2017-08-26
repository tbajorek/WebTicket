package response;

import java.io.Serializable;

/**
 * Response class of department
 * 
 * @author Tomasz Bajorek
 */
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identifier of department
	 */
	private Integer id;
	
	/**
	 * Name of the department
	 */
	private String name;
	
	/**
	 * Initialization of the object with given id and name
	 * @param id Department id
	 * @param name Department name
	 */
	public Department(Integer id, String name) {
		setId(id);
		setName(name);
	}
	
	/**
	 * Initialization of response department based on model department
	 * @param department
	 */
	public Department(model.Department department) {
		setId(department.getId());
		setName(department.getName());
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
}
