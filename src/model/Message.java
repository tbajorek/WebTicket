package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity of ticket message
 * 
 * @author Tomasz Bajorek
 */
@Entity
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identifier of the entity
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Ticket to which the message belongs
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ticket_id")
	private Ticket ticket;
	
	/**
	 * Author of the message
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	private User author;
	
	/**
	 * Message content
	 */
	@NotNull
	private String content;
	
	/**
	 * Date when the message has been created
	 */
	@NotNull
	private Date created;
	
	/**
	 * Initialization of empty message object
	 */
	public Message() {}
	
	/**
	 * Initialization of the object with given message id
	 * @param id Message identifier
	 */
	public Message(Integer id) {
		setId(id);
	}

	/**
	 * Return message identifier
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the given message identifier
	 * @param id Message identifier
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
	 * Return author of the message
	 * @return
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * Set the given author of message
	 * @param author Author of message
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

	/**
	 * Return message content
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Set the given content of the message
	 * @param content Message content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Return date of creation the message
	 * @return
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Set the given date of creation
	 * @param created Date of creation
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
}
