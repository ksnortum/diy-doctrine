package net.snortum.doctrine.dao;

import net.snortum.doctrine.model.Editor;
import net.snortum.doctrine.model.PropFile;

/**
 * Get the correct DAO for an {@link Editor} based on a properties file
 * 
 * @author Knute Snortum
 * @version 0.1
 */
public class EditorDaoFactory {

	/**
	 * Get the correct {@link EditorDao} based on a properties file.
	 * 
	 * @return an EditorDao
	 */
	public static EditorDao getEditorDao() {

		PropFile propFile = PropFile.getInstance();
		EditorDao dao = null;

		switch (propFile.getDatabaseType()) {
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
