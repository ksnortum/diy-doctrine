package net.snortum.doctrine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

/**
 * Model of a person who edits (sets up) doctrines and Bible verses.
 * 
 * @author Knute Snortum
 * @version 0.1
 */

@Entity
@DynamicUpdate
@Table( name = "editors" )
public class Editor implements Serializable {

	/**
	 * {@link Editor}
	 * 
	 * @version 0.1
	 */
	private static final long serialVersionUID = 5450662604532514701L;

	@Id
	@GeneratedValue
	private int id;
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
	 * @return the id
	 */
	@Column( name = "id", unique = true )
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@SuppressWarnings( "unused" ) // Hibernate can access private setters
	private void setId( int id ) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	@Column( name = "username", unique = true, length = 20 )
	@NotNull( message = "You must enter a username" )
	@Size( min = 3, max = 20,
			message = "Username must be between 3 and 30 characters" )
	@Pattern(
			regexp = "^\\w{3,20}$",
			message = "Username must be alphanumeric (underscore okay) with no spaces" )
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername( String username ) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	@Column( name = "password", nullable = false, length = 20 )
	@NotNull( message = "You must enter a password" )
	@Size( min = 8, max = 20, message = "Password must be between 8 and 20 "
			+ "characters" )
	@Pattern( regexp = ".*[A-Z].*",
			message = "Password must contain at least one capital letter" )
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword( String password ) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	@Column( name = "first_name", length = 20 )
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	@Column( name = "last_name", length = 40 )
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	/**
	 * @return the email address
	 */
	@Column( name = "email", length = 40 )
	@Pattern( regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$",
			message = "Invalid email address." )
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email
	 *            the email address
	 */
	public void setEmail( String email ) {
		this.email = email;
	}

	/**
	 * @return whether this editor can add to DB
	 */
	@Column( name = "can_add" )
	public boolean editorCanAdd() {
		return canAdd;
	}

	/**
	 * @param canAdd
	 *            is this editor allowed to add to DB?
	 */
	public void setCanAdd( boolean canAdd ) {
		this.canAdd = canAdd;
	}

	/**
	 * @return whether this editor can delete from the DB
	 */
	@Column( name = "can_delete" )
	public boolean editorCanDelete() {
		return canDelete;
	}

	/**
	 * @param canDelete
	 *            is this editor allowed to delete from the DB?
	 */
	public void setCanDelete( boolean canDelete ) {
		this.canDelete = canDelete;
	}

	/**
	 * @return whether this editor needs approval to delete from the DB
	 */
	@Column( name = "needs_approval" )
	public boolean needsDeleteApproval() {
		return deleteApproval;
	}

	/**
	 * @param deleteApproval
	 *            does this editor need approval to delete from the database?
	 */
	public void setDeleteApproval( boolean deleteApproval ) {
		this.deleteApproval = deleteApproval;
	}

	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}
}
