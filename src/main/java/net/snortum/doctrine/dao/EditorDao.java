package net.snortum.doctrine.dao;

import java.io.IOException;

import net.snortum.doctrine.model.Editor;

/**
 * All the persistence activity for an {@link Editor}.
 * 
 * @author Knute
 * @version 0.1
 */
public interface EditorDao {

	/**
	 * Add this {@link Editor} object to a list of editors and save.
	 * 
	 * @param editor
	 *            the editor to save
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void saveEditor( Editor editor ) throws IOException, ClassNotFoundException;

	/**
	 * Send in a username and return an {@link Editor}.
	 * 
	 * @param username
	 *            the username of the editor
	 * @return The matching editor or null if not found
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	Editor getEditor( String username ) throws IOException,
			ClassNotFoundException;
	
	boolean validateEditor( String username, String password );
}
