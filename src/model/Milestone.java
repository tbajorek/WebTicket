package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity of ticket milestone
 * 
 * @author Tomasz Bajorek
 */
@Entity
public class Milestone implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identifier of the entity
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Ticket to which the milestone belongs
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ticket_id")
	private Ticket ticket;
	
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
	 * Return associated ticket
	 * @return
	 */
	public Ticket getTicket() {
		return ticket;
	}

	/**
	 * Set the given ticket
	 * @param ticket Ticket object
	 */
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
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
