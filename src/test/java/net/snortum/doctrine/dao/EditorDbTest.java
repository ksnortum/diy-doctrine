package net.snortum.doctrine.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import net.snortum.doctrine.model.Editor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EditorDbTest {
	
	private static final Logger LOG = LogManager.getLogger();
	private static final String firstName = "Knute";
	private static final String lastName = "Snortum";
	private static final String userName = "ksnortum";
	private static final String password = "Acb12345";
	private static final String anotherPassword = "Xyzzy123";
	
	private static Validator validator;
	
	private EditorDao dao = new EditorDao();
	private Editor testEditor;
	private int editorId = -1;
	
	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Before
	public void initialize() {
		testEditor = new Editor();
		testEditor.setFirstName( firstName );
		testEditor.setLastName( lastName );
		testEditor.setUsername( userName );
		testEditor.setPassword( password );
		validateEditor( testEditor );
	}

	@Test
	public void createEditors() {
		removeEditor();
		editorId = dao.create( testEditor );
		assertNotNull( editorId );
	}
	
	@Test
	public void readEditor() {
		addEditor();
		Editor editor = dao.read( editorId );
		assertNotNull( editor );
		assertTrue( firstName.equals( editor.getFirstName() ) );
		assertTrue( lastName.equals( editor.getLastName() ) );
		assertTrue( userName.equals( editor.getUsername() ) );
		assertTrue( password.equals( editor.getPassword() ) );
	}
	
	@Test
	public void updateEditor() {
		addEditor();
		Editor editor = dao.read( editorId );
		editor.setPassword( anotherPassword );
		validateEditor( editor );
		dao.update( editor );
		Editor newEditor = dao.read( editorId );
		assertTrue( anotherPassword.equals( newEditor.getPassword() ) ) ;
	}
	
	@Test
	public void deleteEditor() {
		addEditor();
		Editor editor = dao.read( editorId );
		dao.delete( editor );
		Editor newEditor = dao.read( editorId );
		assertNull( newEditor );
	}
	
	@Test
	public void listEditors() {
		addEditor();
		List<Editor> editors = dao.list();
		boolean sawEditor = false;
		
		for ( Editor editor : editors ) {
			if ( editor.getId() == editorId ) {
				sawEditor = true;
			}
		}
		
		assertTrue( sawEditor );
	}
	
	@Test
	public void getIdByUnsername() {
		int myId = dao.idByUsername( "this username does not exist" );
		assertEquals( -1, myId );
		addEditor();
		myId = dao.idByUsername( userName );
		assertTrue( myId != -1 );
	}

	/**
	 * @param editor
	 */
	private void validateEditor( Editor editor ) {
		Set<ConstraintViolation<Editor>> violations = 
				validator.validate( editor );
		
		if ( violations.size() > 0 ) {
			for ( ConstraintViolation<Editor> violation : violations ) {
				LOG.error( violation.getMessage() );
			}
			
			fail();
		}
	}
	
	/**
	 * @return id of Editor with certain username
	 */
	private void findEditor() {
		editorId = -1;
		List<Editor> editors = dao.list();
		
		for ( Editor editor : editors ) {
			if ( editor.getUsername().equals( userName ) ) {
				editorId = editor.getId();
				break;
			}
		}
	}
	
	private void removeEditor() {
		findEditor();
		
		if ( editorId != -1 ) {
			testEditor = dao.read( editorId );
			dao.delete( testEditor );
		}
	}
	
	private void addEditor() {
		findEditor();
		
		if ( editorId == -1 ) {
			editorId = dao.create( testEditor );
		}
	}
}
