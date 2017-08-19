package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@NotNull
	private String title;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id")
	private Department department;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	private User author;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="recipient_id")
	private User recipient;
	
	private Integer rate;
	
	@NotNull
	private Date created;
	
	@NotNull
	private Date lastModified;
	
	private Date closed;
	
	private Boolean newChanges;
	
	@OneToMany(mappedBy = "ticket")
	private List<Message> messages;
	
	@OneToMany(mappedBy = "ticket")
	private List<Milestone> milestones;
	
	public Ticket() {}
	
	public Ticket(Integer id) {
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

	/**
	 * @return the recipient
	 */
	public User getRecipient() {
		return recipient;
	}

	/**
	 * @param recipient the recipient to set
	 */
	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	/**
	 * @return the rate
	 */
	public Integer getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(Integer rate) {
		this.rate = rate;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the lastModified
	 */
	public Date getLastModified() {
		return lastModified;
	}

	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * @return the closed
	 */
	public Date getClosed() {
		return closed;
	}

	/**
	 * @param closed the closed to set
	 */
	public void setClosed(Date closed) {
		this.closed = closed;
	}

	/**
	 * @return the newChanges
	 */
	public Boolean getNewChanges() {
		return newChanges;
	}

	/**
	 * @param newChanges the newChanges to set
	 */
	public void setNewChanges(Boolean newChanges) {
		this.newChanges = newChanges;
	}

	/**
	 * @return the messages
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	/**
	 * @return the milestones
	 */
	public List<Milestone> getMilestones() {
		return milestones;
	}

	/**
	 * @param milestones the milestones to set
	 */
	public void setMilestones(List<Milestone> milestones) {
		this.milestones = milestones;
	}
	
	public void setSomethingNew() {
		setLastModified(new Date());
		setNewChanges(true);
	}
	
	public boolean hasUserPermissions(User user) {
		return (getAuthor().getId().equals(user.getId()) ||
				getRecipient().getId().equals(user.getId()));
	}
}
