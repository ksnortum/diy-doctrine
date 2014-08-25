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
@Table( name = "translations" )
public class Translation {

	private int id;
	private String abbreviation;
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
	 * @return the abbreviation
	 */
	@Column( name = "abbr", unique = true, length = 5 )
	@NotNull( message = "You must enter an abbreviation" )
	@Size( min = 2, max = 5,
			message = "Abbreviation must be two to five characters" )
	@Pattern(
			regexp = "^\\[A-Z0-9]{2,3}$",
			message = "Abbreviation must be capitol letters and numbers only" )
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * @param abbreviation
	 *            the abbreviation to set
	 */
	public void setAbbreviation( String abbreviation ) {
		this.abbreviation = abbreviation;
	}

	/**
	 * @return the description
	 */
	@Column( name = "descr", unique = true, length = 30 )
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
