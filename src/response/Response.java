package response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Response implements Serializable {
    private static final long serialVersionUID = 1L;
	private Integer response;
	private HashMap<String, ArrayList<String>> messages;
	private HashMap<String, Object> data;
	private HashMap<String, HashMap<String, Object>> view;
	
	public Response(Integer response) {
		setResponse(response);
		messages = new HashMap<String, ArrayList<String>>();
		this.messages.put("error", new ArrayList<String>());
		this.messages.put("warning", new ArrayList<String>());
		this.messages.put("info", new ArrayList<String>());
		this.messages.put("success", new ArrayList<String>());
		this.data = new HashMap<String, Object>();
	}

	/**
	 * @return the response
	 */
	public Integer getResponse() {
		return response;
	}
	
	/**
	 * 
	 * @param response the response to set
	 * @return
	 */
	public Response setResponse(Integer response) {
		this.response = response;
		return this;
	}

	/**
	 * @return the messages
	 */
	public HashMap<String, ArrayList<String>> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 * @return
	 */
	public Response setMessages(HashMap<String, ArrayList<String>> messages) {
		this.messages = messages;
		return this;
	}
	
	public Response addError(String message) {
		this.messages.get("error").add(message);
		return this;
	}
	
	public Response addWarning(String message) {
		this.messages.get("warning").add(message);
		return this;
	}
	
	public Response addInfo(String message) {
		this.messages.get("info").add(message);
		return this;
	}
	
	public Response addSuccess(String message) {
		this.messages.get("success").add(message);
		return this;
	}

	/**
	 * @return the data
	 */
	public HashMap<String, Object> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 * @return
	 */
	public Response setData(HashMap<String, Object> data) {
		this.data = data;
		return this;
	}
	
	/**
	 * @param name name of the data field
	 * @param value of the data field
	 * @return
	 */
	public Response addData(String name, Object value) {
		this.data.put(name, value);
		return this;
	}

	/**
	 * @return the view
	 */
	public HashMap<String, HashMap<String, Object>> getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 * @return
	 */
	public Response setView(HashMap<String, HashMap<String, Object>> view) {
		this.view = view;
		return this;
	}
	
	
}
