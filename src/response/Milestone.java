package response;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class Milestone implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String name;

	private Boolean done;
	
	public Milestone(model.Milestone dbMilestone) {
		setId(dbMilestone.getId());
		setName(dbMilestone.getName());
		setDone(dbMilestone.getDone());
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
	 * @return the done
	 */
	public Boolean getDone() {
		return done;
	}

	/**
	 * @param done the done to set
	 */
	public void setDone(Boolean done) {
		this.done = done;
	}
}
