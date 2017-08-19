package response;

import java.io.Serializable;
import java.util.Date;

import response.Department;
import response.User;

public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String title;
	
	private Department department;
	
	private User author;
	
	private User recipient;
	
	private Integer rate;
	
	private Date created;
	
	private Date lastModified;
	
	private Date closed;
	
	private Boolean newChanges;
	
	public Ticket(model.Ticket dbTicket) {
		setId(dbTicket.getId());
		setTitle(dbTicket.getTitle());
		setDepartment(new Department(dbTicket.getDepartment()));
		setAuthor(new User(dbTicket.getAuthor()));
		if(dbTicket.getRecipient() != null) {
			setRecipient(new User(dbTicket.getRecipient()));
		}
		setRate(dbTicket.getRate());
		setCreated(dbTicket.getCreated());
		setLastModified(dbTicket.getLastModified());
		setClosed(dbTicket.getClosed());
		setNewChanges(dbTicket.getNewChanges());
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
}
