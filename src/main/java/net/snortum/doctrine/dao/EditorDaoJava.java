package net.snortum.doctrine.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.snortum.doctrine.model.Editor;
import net.snortum.doctrine.model.PropFile;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * This is the DAO for editors, implemented to save the Java object list of
 * {@link Editor}s to a local file. This is slow and not portable. It should be
 * used to testing only.
 * 
 * @author Knute Snortum
 * @version 0.1
 */
@Component
public class EditorDaoJava implements EditorDao {

	private final static Logger LOG = Logger.getLogger( EditorDaoJava.class );
	private final static String EDITORS_FILE_NAME = "editors.ser";

	private PropFile propFile = PropFile.getInstance();

	/**
	 * Save an {@link Editor} to a Java object file.
	 * 
	 * @param editor
	 *            the editor to save
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public synchronized void saveEditor( Editor editor ) throws IOException,
			ClassNotFoundException {

		List<Editor> editors = new ArrayList<Editor>();
		boolean tryAgain = true;

		// Get current list of editors from disk, if present
		do {
			try (FileInputStream fis = new FileInputStream( getFileName() )) {
				tryAgain = false;

				try (ObjectInputStream ois = new ObjectInputStream( fis )) {
					editors = (List<Editor>) ois.readObject();
				}
				catch ( EOFException eofe ) {
					// This is okay the first time because nothing will be in
					// the file
				}
			}
			catch ( FileNotFoundException fnfe ) {
				if ( LOG.isInfoEnabled() ) {
					LOG.info( "Creating a new editors file" );
				}

				try (FileOutputStream fos =
						new FileOutputStream( getFileName() )) {
				}
				catch ( IOException ioe ) {
					LOG.error( "Could not create \"" + getFileName()
							+ "\" for the first time", ioe );
					return;
				}
			}
		}
		while ( tryAgain );

		editors.add( editor );

		// Write updated list of editors back to disk
		try (FileOutputStream fos = new FileOutputStream( getFileName() )) {
			try (ObjectOutputStream oos = new ObjectOutputStream( fos )) {
				oos.writeObject( editors );
			}
		}
	}

	/**
	 * Get an {@link Editor} based on a username
	 * 
	 * @param username
	 *            make a case-insensitive match to the editor
	 * @return the editor found or null if not found
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public Editor getEditor( String username ) throws IOException,
			ClassNotFoundException {

		List<Editor> editors = new ArrayList<Editor>();

		// Get current list of editors from disk, if present
		try (FileInputStream fis = new FileInputStream( getFileName() )) {
			try (ObjectInputStream ois = new ObjectInputStream( fis )) {
				editors = (List<Editor>) ois.readObject();
			}
		}

		Editor editor = null;
		for ( Editor thisEditor : editors ) {
			if ( thisEditor.getUsername().equalsIgnoreCase( username ) ) {
				editor = thisEditor;
				break;
			}
		}

		return editor;
	}

	/** @return the full path and name of the editors file */
	private String getFileName() {
		return getPropFile().getDatabaseDir() + EDITORS_FILE_NAME;
	}

	/** @return the propFile object */
	public PropFile getPropFile() {
		return propFile;
	}

	/**
	 * @param propFile
	 *            the propFile to set, mostly for testing
	 */
	public void setPropFile( PropFile propFile ) {
		this.propFile = propFile;
	}

	@Override
	public boolean validateEditor( String username, String password ) {
		// TODO Auto-generated method stub
		return false;
	}
}
