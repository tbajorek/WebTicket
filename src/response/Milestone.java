package response;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Response class of ticket milestone
 * 
 * @author Tomasz Bajorek
 */
public class Milestone implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identifier of milestone
	 */
	private Integer id;

	/*
	 * Name of the milestone
	 */
	@NotNull
	private String name;

	/**
	 * Flag if the milestone is done
	 */
	@NotNull
	private Boolean done;
	
	/**
	 * Initialization of response milestone based on model milestone
	 * @param milestone Model milestone
	 */
	public Milestone(model.Milestone dbMilestone) {
		setId(dbMilestone.getId());
		setName(dbMilestone.getName());
		setDone(dbMilestone.getDone());
	}

	/**
	 * Return milestone identifier
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the given milestone identifier
	 * @param id Milestone identifier
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Return name of the milestone
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the given milestone name
	 * @param name Milestone name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return a flag if the milestone is done
	 * @return
	 */
	public Boolean getDone() {
		return done;
	}

	/**
	 * Set the given flag
	 * @param done Flag if the milestone is done
	 */
	public void setDone(Boolean done) {
		this.done = done;
	}
}
