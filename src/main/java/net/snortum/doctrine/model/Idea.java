package net.snortum.doctrine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table( name = "ideas" )
public class Idea {
	
	private int id;
	private String description;
	private String text;

	/**
	 * @return the id
	 */
	@Column( name = "id", unique = true )
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
	
	@Column( name = "descr", unique = true, length = 60 )
	@NotNull( message = "The description cannot be blank" )
	@Size( min = 3, max = 60,
			message = "Abbreviation must be three to 60 characters" )
	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	@Column( name = "text", length = 300 )
	@Size( min = 3, max = 300,
			message = "Text must be three to 300 characters" )
	public String getText() {
		return text;
	}

	public void setText( String text ) {
		this.text = text;
	}

}
