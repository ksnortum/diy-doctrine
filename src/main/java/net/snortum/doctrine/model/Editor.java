package net.snortum.doctrine.model;

import java.io.Serializable;

/**
 * Model of a person who edits (sets up) doctrines and Bible verses.
 * 
 * @author Knute Snortum
 * @version 0.1
 */
public class Editor implements Serializable {
	
	/** 
	 * {@link Editor}
	 * @version 0.1 
	 */
	private static final long serialVersionUID = 5450662604532514701L;
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private boolean canAdd;
	private boolean canDelete;
	private boolean deleteApproval;
	
	/** Create an editor with defaults set */
	public Editor() {
		canAdd = false;
		canDelete = false;
		deleteApproval = false;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername( String username ) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword( String password ) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the email address
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * @param email the email address
	 */
	public void setEmail( String email ) {
		this.email = email;
	}

	/**
	 * @return whether this editor can add to DB
	 */
	public boolean editorCanAdd() {
		return canAdd;
	}

	/**
	 * @param canAdd is this editor allowed to add to DB?
	 */
	public void setCanAdd( boolean canAdd ) {
		this.canAdd = canAdd;
	}

	/**
	 * @return whether this editor can delete from the DB
	 */
	public boolean editorCanDelete() {
		return canDelete;
	}

	/**
	 * @param canDelete is this editor allowed to delete from the DB?
	 */
	public void setCanDelete( boolean canDelete ) {
		this.canDelete = canDelete;
	}

	/**
	 * @return whether this editor needs approval to delete from the DB
	 */
	public boolean needsDeleteApproval() {
		return deleteApproval;
	}

	/**
	 * @param deleteApproval does this editor need approval to delete from the
	 *        database?
	 */
	public void setDeleteApproval( boolean deleteApproval ) {
		this.deleteApproval = deleteApproval;
	}

}
