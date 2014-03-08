package net.snortum.doctrine.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EditorDaoFactoryTest {

	@Test
	/** Test {@link EditorDaoFactory#getEditor} */
	public void testGetEditorDao() {
		EditorDao dao = EditorDaoFactory.getEditorDao();
		assertTrue( dao instanceof EditorDaoJava );
	}

}
