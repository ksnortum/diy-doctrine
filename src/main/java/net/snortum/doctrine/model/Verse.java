package net.snortum.doctrine.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table( name = "verses" )
public class Verse {
	
	private int id;
	private String description;
	private String text;
	private Translation translation;
	private Book book;
	private int startingChapter;
	private int endingChapter;
	private int startingVerse;
	private int endingVerse;
	private Set<Idea> ideas;

	public Verse() {
		startingChapter = 1;
		endingChapter = 1;
		startingVerse = 1;
		endingVerse = 1;
		ideas = new HashSet<Idea>();
	}

	// Getters and Setters
	
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
	
	@Column( name = "text", length = 300 )
	@NotNull( message = "You must enter text for this passage" )
	public String getText() {
		return text;
	}

	public void setText( String text ) {
		this.text = text;
	}
	
	@Column( name = "descr", length = 60 )
	public String getDesc() {
		return description;
	}
	
	public void setDesc( String description ) {
		this.description = description;
	}
	
	@ManyToOne
	@JoinColumn( table = "translations" )
	public Translation getTranslation() {
		return translation;
	}

	public void setTranslation( Translation translation ) {
		this.translation = translation;
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook( Book book ) {
		this.book = book;
	}

	public int getStartingChapter() {
		return startingChapter;
	}

	public void setStartingChapter( int startingChapter ) {
		this.startingChapter = startingChapter;
	}

	public int getEndingChapter() {
		return endingChapter;
	}

	public void setEndingChapter( int endingChapter ) {
		this.endingChapter = endingChapter;
	}

	public int getStartingVerse() {
		return startingVerse;
	}

	public void setStartingVerse( int startingVerse ) {
		this.startingVerse = startingVerse;
	}

	public int getEndingVerse() {
		return endingVerse;
	}

	public void setEndingVerse( int endingVerse ) {
		this.endingVerse = endingVerse;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "verses_ideas", 
			joinColumns = { @JoinColumn(table = "ideas", name = "id") },
			inverseJoinColumns = { @JoinColumn(table = "verses", name = "id") })
	public Set<Idea> getIdeas() {
		return ideas;
	}
	
	public void setIdeas( Set<Idea> ideas ) {
		this.ideas = ideas;
	}
}
