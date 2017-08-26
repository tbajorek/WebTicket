package model;

import java.io.Serializable;

/**
 * Model for change password operations
 * 
 * @author Tomasz Bajorek
 */
public class NewPassword  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Old password
	 */
	private String oldPassword;
	
	/**
	 * New password
	 */
	private String newPassword;
	
	/**
	 * Return old password
	 * @return
	 */
	public String getOldPassword() {
		return oldPassword;
	}
	/**
	 * Set the given old password
	 * @param oldPassword Old password
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	/**
	 * Return new password
	 * @return
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * Set the given new password
	 * @param newPassword New password
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
