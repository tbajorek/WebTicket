package response;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import response.Department;
import response.User;

/**
 * Response class of single ticket
 * 
 * @author Tomasz Bajorek
 */
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Identifier of one ticket
	 */
	private Integer id;

	/**
	 * Title of ticket
	 */
	@NotNull
	private String title;
	
	/**
	 * Department where the ticket is assigned
	 */
	@NotNull
	private Department department;
	
	/**
	 * Author of ticket
	 */
	@NotNull
	private User author;
	
	/**
	 * Recipient of ticket
	 */
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
	 * Initialization of single ticket based on model single ticket
	 * @param dbTicket Model ticket
	 */
	public Ticket(model.Ticket dbTicket) {
		setId(dbTicket.getId());
		setTitle(dbTicket.getTitle());
		setDepartment(new Department(dbTicket.getDepartment()));
		setAuthor(new User(dbTicket.getAuthor()));
		if(dbTicket.getRecipient() != null) {
			setRecipient(new User(dbTicket.getRecipient()));
		}
		if(dbTicket.getRate()!= null) {
			setRate(dbTicket.getRate());
		}
		setCreated(dbTicket.getCreated());
		setLastModified(dbTicket.getLastModified());
		if(dbTicket.getClosed()!=null) {
			setClosed(dbTicket.getClosed());
		}
		setNewChanges(dbTicket.getNewChanges());
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
}
