package net.snortum.doctrine.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;

import net.snortum.doctrine.model.Editor;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * This is the DAO for editors, implemented to save the Java object list of
 * {@link Editor}s to a local file. This is slow and not portable. It Should be
 * used to testing only.
 * 
 * @author Knute Snortum
 * @version 0.1
 * 
 */
@Component
public class EditorDaoJava implements EditorDao {

	private final static Logger LOG = Logger.getLogger( EditorDaoJava.class );
	private final static String EDITORS_FILE_NAME = "editors.ser";

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
	public void saveEditor( Editor editor ) throws IOException,
			ClassNotFoundException {

		List<Editor> editors = new ArrayList<Editor>();
		FileLock readLock = null;

		// Get current list of editors from disk, if present
		try (FileInputStream fis = new FileInputStream( getFilePathName() )) {
			readLock = fis.getChannel().lock();

			try (ObjectInputStream ois = new ObjectInputStream( fis )) {
				editors = (List<Editor>) ois.readObject();
			}
		}
		catch ( FileNotFoundException fnfe ) {
			if ( LOG.isInfoEnabled() ) {
				LOG.info( "Creating a new editors file" );
			}
		}

		editors.add( editor );

		// Write updated list of editors back to disk
		try (FileOutputStream fos = new FileOutputStream( getFilePathName() )) {
			try (ObjectOutputStream oos = new ObjectOutputStream( fos )) {
				oos.writeObject( editors );
			}
			finally {
				readLock.release();
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
		try (FileInputStream fis = new FileInputStream( getFilePathName() )) {
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

	/**
	 * @return a path and file name relative to the resource directory
	 */
	private String getFilePathName() {
		ClassPathResource file = new ClassPathResource( EDITORS_FILE_NAME );
		if ( LOG.isInfoEnabled() ) {
			LOG.info( "File path is " + file.getPath() );
		}
		return file.getPath();
	}
}
