package net.snortum.doctrine.mvc;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.isA;

import java.io.IOException;

import net.snortum.doctrine.dao.EditorDao;
import net.snortum.doctrine.model.Editor;

import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * Tests for {@link RegisterController}
 * 
 * @author Knute Snortum
 * @version 0.4
 */
public class RegisterControllerTest {
	private RegisterController rc;
	private EditorDao editorDaoMock;

	/**
	 * Test method for
	 * {@link RegisterController#addEditorFromForm(Editor, BindingResult)}, no
	 * errors.
	 */
	@Test
	public void testAddEditorFromFormNoErrors() {
		setupVariables();
		Editor editor = new Editor();
		editor.setUsername( "username" );
		editor.setPassword( "Abcd1234" );
		editor.setEmail( "someone@somewhere.com" );
		BindingResultForTest bindingResult = new BindingResultForTest();
		bindingResult.setErrors( false );
		String menu = rc.addEditorFromForm( editor, bindingResult );
		verify( editorDaoMock ).create( editor );
		assertTrue( "login/frame".equalsIgnoreCase( menu ) );
	}

	/**
	 * Test method for
	 * {@link RegisterController#addEditorFromForm(Editor, BindingResult)}, with
	 * errors.
	 */
	@Test
	public void testAddEditorFromFormErrors() throws ClassNotFoundException,
			IOException {

		setupVariables();
		Editor editor = new Editor();
		BindingResultForTest bindingResult = new BindingResultForTest();
		bindingResult.setErrors( true );
		String menu = rc.addEditorFromForm( editor, bindingResult );
		menu = rc.addEditorFromForm( editor, bindingResult );
		assertTrue( "register/frame".equalsIgnoreCase( menu ) );
	}

	/**
	 * Test method for {@link RegisterController#displayForm(Model)}
	 */
	@Test
	public void testDisplayForm() {
		rc = new RegisterController();
		Model modelMock = mock( Model.class );
		String menu = rc.displayForm( modelMock );
		verify( modelMock ).addAttribute( isA( Editor.class ) );
		assertTrue( "register/frame".equalsIgnoreCase( menu ) );
	}

	private void setupVariables() {
		editorDaoMock = mock( EditorDao.class );
		rc = new RegisterController();
		rc.setEditorDao( editorDaoMock );
	}
}
