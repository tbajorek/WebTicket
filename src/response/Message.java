package response;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 * Response class of ticket message
 * 
 * @author Tomasz Bajorek
 */
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Identifier of message
	 */
	@NotNull
	private Integer id;

	/**
	 * Author of the message
	 */
	@NotNull
	private User author;
	
	/**
	 * Message content
	 */
	private String content;
	
	/**
	 * Date when the message has been created
	 */
	@NotNull
	private Date created;
	
	/**
	 * Initialization of response message based on model message
	 * @param message Model message
	 */
	public Message(model.Message message) {
		setId(message.getId());
		setAuthor(new User(message.getAuthor(), false));
		setContent(message.getContent());
		setCreated(message.getCreated());
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
