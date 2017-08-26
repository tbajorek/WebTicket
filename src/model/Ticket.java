package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity of single ticket
 * 
 * @author Tomasz Bajorek
 */
@Entity
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identifier of the entity
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Title of ticket
	 */
	@NotNull
	private String title;
	
	/**
	 * Department where the ticket is assigned
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id")
	private Department department;
	
	/**
	 * Author of ticket
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	private User author;
	
	/**
	 * Recipient of ticket
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="recipient_id")
	private User recipient;
	
	/**
	 * Rate of ticket
	 */
	private Integer rate;
	
	/**
	 * Date when ticket was created
	 */
	@NotNull
	private Date created;
	
	/**
	 * Date when ticket was modified last time
	 */
	@NotNull
	private Date lastModified;
	
	/**
	 * Date when ticket was closed
	 */
	private Date closed;
	
	/**
	 * Flag if ticket has new changes
	 */
	private Boolean newChanges;
	
	/**
	 * Messages assigned to ticket
	 */
	@OneToMany(mappedBy = "ticket")
	private List<Message> messages;
	
	/**
	 * Milestones assigned to ticket
	 */
	@OneToMany(mappedBy = "ticket")
	private List<Milestone> milestones;
	
	/**
	 * Initialization of empty ticket object
	 */
	public Ticket() {}
	
	/**
	 * Initialization of the object with given ticket id
	 * @param id Ticket identifier
	 */
	public Ticket(Integer id) {
		setId(id);
	}

	/**
	 * Return ticket id
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the given ticket id
	 * @param id Ticket id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Return ticket title
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the given ticket title
	 * @param title Ticket title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Return department associated with ticket
	 * @return
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the  given department as associated with ticket
	 * @param department Department object
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Return author of ticket
	 * @return
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * Set the given author of ticket
	 * @param author Author of ticket
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

	/**
	 * Return recipient of ticket
	 * @return
	 */
	public User getRecipient() {
		return recipient;
	}

	/**
	 * Set the given recipient of ticket
	 * @param recipient Recipient of ticket
	 */
	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	/**
	 * Return ticket rate
	 * @return
	 */
	public Integer getRate() {
		return rate;
	}

	/**
	 * Set the given rate to ticket
	 * @param rate Ticket rate
	 */
	public void setRate(Integer rate) {
		this.rate = rate;
	}

	/**
	 * Return date when ticket was created
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Set the given date when ticket was created
	 * @param created Creation date
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Return date of last modification of ticket
	 * @return
	 */
	public Date getLastModified() {
		return lastModified;
	}

	/**
	 * Set the given date of last modification
	 * @param lastModified Date of last modification of ticket
	 */
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * Return date when ticket was closed
	 * @return
	 */
	public Date getClosed() {
		return closed;
	}

	/**
	 * Set the given date of closing ticket
	 * @param closed Date of closing ticket
	 */
	public void setClosed(Date closed) {
		this.closed = closed;
	}

	/**
	 * Return flag if ticket has new changes
	 * @return
	 */
	public Boolean getNewChanges() {
		return newChanges;
	}

	/**
	 * Set the given flag of new changes in ticket
	 * @param newChanges Flag of new changes
	 */
	public void setNewChanges(Boolean newChanges) {
		this.newChanges = newChanges;
	}

	/**
	 * Return list of ticket messages
	 * @return
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * Set the given list of ticket messages
	 * @param messages List of ticket messages
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	/**
	 * Return list of ticket milestones
	 * @return
	 */
	public List<Milestone> getMilestones() {
		return milestones;
	}

	/**
	 * Set the given list of ticket milestones
	 * @param milestones List of ticket milestones
	 */
	public void setMilestones(List<Milestone> milestones) {
		this.milestones = milestones;
	}
	
	/**
	 * Report that ticket has some new changes
	 */
	public void setSomethingNew() {
		setLastModified(new Date());
		setNewChanges(true);
	}
	
	/**
	 * Check if the given user has permission to ticket
	 * @param user Requested user
	 * @return
	 */
	public boolean hasUserPermissions(User user) {
		return (getAuthor().getId().equals(user.getId()) ||
				getRecipient().getId().equals(user.getId()));
	}
}
