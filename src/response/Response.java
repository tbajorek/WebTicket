package response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class describes universal design of response structure
 * 
 * @author Tomasz Bajorek
 */
public class Response implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * Code of response
     */
	private Integer response;
	
	/**
	 * List of messages divided into groups
	 */
	private HashMap<String, ArrayList<String>> messages;
	
	/**
	 * List of all data positions
	 */
	private HashMap<String, Object> data;
	
	/**
	 * Initialization of the response object
	 */
	public Response() {
		messages = new HashMap<String, ArrayList<String>>();
		this.messages.put("error", new ArrayList<String>());
		this.messages.put("warning", new ArrayList<String>());
		this.messages.put("info", new ArrayList<String>());
		this.messages.put("success", new ArrayList<String>());
		this.data = new HashMap<String, Object>();
	}
	
	/**
	 * Initialization with the given code
	 * @param response Response code
	 */
	public Response(int response) {
		this();
		setResponse(response);
	}

	/**
	 * Return response code
	 * @return
	 */
	public Integer getResponse() {
		return response;
	}
	
	/**
	 * Set the given response code
	 * @param response Response code
	 * @return
	 */
	public Response setResponse(Integer response) {
		this.response = response;
		return this;
	}

	/**
	 * Return list of all messages
	 * @return
	 */
	public HashMap<String, ArrayList<String>> getMessages() {
		return messages;
	}

	/**
	 * Set the given list of messages
	 * @param messages LIst of messages
	 * @return
	 */
	public Response setMessages(HashMap<String, ArrayList<String>> messages) {
		this.messages = messages;
		return this;
	}
	
	/**
	 * Add new error message
	 * @param message Content of error message
	 * @return
	 */
	public Response addError(String message) {
		this.messages.get("error").add(message);
		return this;
	}
	
	/**
	 * Add new warning message
	 * @param message Content of warning message
	 * @return
	 */
	public Response addWarning(String message) {
		this.messages.get("warning").add(message);
		return this;
	}
	
	/**
	 * Add new info message
	 * @param message Content of info message
	 * @return
	 */
	public Response addInfo(String message) {
		this.messages.get("info").add(message);
		return this;
	}
	
	/**
	 * Add new success message
	 * @param message Content of success message
	 * @return
	 */
	public Response addSuccess(String message) {
		this.messages.get("success").add(message);
		return this;
	}

	/**
	 * Return all data elements
	 * @return
	 */
	public HashMap<String, Object> getData() {
		return data;
	}

	/**
	 * Set the given data element
	 * @param data Data element
	 * @return
	 */
	public Response setData(HashMap<String, Object> data) {
		this.data = data;
		return this;
	}
	
	/**
	 * Add new data element
	 * @param name Name of data
	 * @param value Value of data
	 * @return
	 */
	public Response addData(String name, Object value) {
		this.data.put(name, value);
		return this;
	}
}
