package net.snortum.doctrine.dao;

import java.io.Serializable;

/**
 * Basic CRUD behavior for an object
 * 
 * @author Knute Snortum
 * @version 0.1
 *
 * @param <T>
 *            The type of the object
 * @param <PK>
 *            The type of the primary key (ID)
 */
public interface GenericDao<T, PK extends Serializable> {

	/**
	 * Write the object to the database
	 * 
	 * @param newInstance
	 *            The new object to write
	 * @return The new primary key
	 */
	PK create( T newInstance );

	/**
	 * Read an object from the database using the ID
	 * 
	 * @param id
	 *            The primary key ID
	 * @return The found object, or null if not found
	 */
	T read( PK id );

	/**
	 * Save changes made to a object already in the database
	 * 
	 * @param transientObject
	 *            The changed object
	 */
	void update( T transientObject );

	/**
	 * Remove an object from the database
	 * 
	 * @param persistentObject
	 *            The object to remove
	 */
	void delete( T persistentObject );
}
