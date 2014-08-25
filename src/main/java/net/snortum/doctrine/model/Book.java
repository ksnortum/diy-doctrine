package net.snortum.doctrine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table( name = "books" )
public class Book {

	private int id;
	private String abbr;
	private String description;

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
	@SuppressWarnings( "unused" )
	// Hibernate can access private setters
	private void setId( int id ) {
		this.id = id;
	}

	/**
	 * @return the abbr
	 */
	@Column( name = "abbr", unique = true, length = 3 )
	@NotNull( message = "You must enter an abbreviation" )
	@Size( min = 2, max = 3,
			message = "Abbreviation must be two or three characters" )
	@Pattern(
			regexp = "^\\[A-Z0-9]{2,3}$",
			message = "Abbreviation must be capitol letters and numbers only" )
	public String getAbbr() {
		return abbr;
	}

	/**
	 * @param abbr
	 *            the abbr to set
	 */
	public void setAbbr( String abbr ) {
		this.abbr = abbr;
	}

	/**
	 * @return the description
	 */
	@Column( name = "descr", unique = true, length = 3 )
	@Size( min = 2, max = 30,
			message = "Abbreviation must be two to 30 characters" )
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription( String description ) {
		this.description = description;
	}

}
