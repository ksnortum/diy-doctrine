package net.snortum.doctrine.dao;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Get the correct DAO for an {@link Editor} based on a properties file
 * 
 * @author Knute Snortum
 * @version 0.1
 */
public class EditorDaoFactory {
	public static final Logger LOG = Logger.getLogger( EditorDaoFactory.class );
	private static final String PROPERTIES_FILE_NAME = "doctrine.properties";
	private static final String DATABASE_KEY = "database";
	private static final String DATABASE_DEFAULT = "java";

	/**
	 * Get the correct {@link EditorDao} based on a properties file.
	 * 
	 * @return an EditorDao
	 */
	public static EditorDao getEditorDao() {

		Properties properties = new Properties();
		try {
			properties.load( Thread.currentThread().getContextClassLoader()
					.getResourceAsStream( PROPERTIES_FILE_NAME ) );
		}
		catch ( IOException ioe ) {
			LOG.warn( "Cannot find properties file \"" + PROPERTIES_FILE_NAME
					+ "\"", ioe );
			properties.setProperty( DATABASE_KEY, DATABASE_DEFAULT );
		}

		EditorDao dao = null;
		switch ( properties.getProperty( DATABASE_KEY, DATABASE_DEFAULT ) ) {
			case "java":
				dao = new EditorDaoJava();
				break;
			case "postgres":
				dao = new EditorDaoPostGres();
				break;
			default:
				dao = new EditorDaoJava();
		}

		return dao;
	}
}
