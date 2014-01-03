package net.snortum.doctrine.model;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropFile {
	private static final Logger LOG = Logger.getLogger( PropFile.class );
	private static final String PROPERTIES_FILE_NAME = "doctrine.properties";
	private static final String DATABASE_KEY = "database";
	private static final String DATABASE_DEFAULT = "java";
	private static final String DATABASE_DIR_KEY = "db_file_dir";
	private static final String DATABASE_DIR_DEFAULT = "/";

	private static PropFile instance = null;
	private static Properties properties;

	// Use getInstance() to instantiate
	private PropFile() {
	}

	/**
	 * Get exactly one instance of PropFile
	 * 
	 * @return an instance of PropFile
	 */
	public static synchronized PropFile getInstance() {
		if ( instance == null ) {
			instance = new PropFile();
			properties = new Properties();

			try {
				properties.load( Thread.currentThread().getContextClassLoader()
						.getResourceAsStream( PROPERTIES_FILE_NAME ) );
			}
			catch ( IOException ioe ) {
				LOG.warn( "Cannot find properties file \""
						+ PROPERTIES_FILE_NAME + "\"", ioe );
			}
		}
		return instance;
	}

	/** @return the database type (java, postgres) */
	public String getDatabaseType() {
		String dbType = properties.getProperty( DATABASE_KEY, DATABASE_DEFAULT );

		switch ( dbType ) {
			case "java":
			case "postgres":
				break;
			default:
				dbType = DATABASE_DEFAULT;
		}

		return dbType;
	}

	/** @return if the DB is "java", what directory do we write the files to */
	public String getDatabaseDir() {
		String dbDir = properties.getProperty( DATABASE_DIR_KEY,
				DATABASE_DIR_DEFAULT );
		dbDir.replaceAll( "\\\\", "/" );

		if ( !dbDir.endsWith( "/" ) ) {
			dbDir = dbDir + "/";
		}

		return dbDir;
	}
}
