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
 * Tests for {@link EditorController}
 * 
 * @author Knute Snortum
 * @version 0.2
 */
public class EditorControllerTest {
	private EditorController ec;
	private EditorDao editorDaoMock;

	/**
	 * Test method for
	 * {@link EditorController#addEditorFromForm(Editor, BindingResult)}, no
	 * errors.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testAddEditorFromFormNoErrors() {

		setupVariables();
		Editor editor = new Editor();
		BindingResultForTest bindingResult = new BindingResultForTest();
		bindingResult.setErrors( false );
		String menu = ec.addEditorFromForm( editor, bindingResult );
		verify( editorDaoMock ).update( editor );
		assertTrue( "home".equalsIgnoreCase( menu ) );
	}

	/**
	 * Test method for
	 * {@link EditorController#addEditorFromForm(Editor, BindingResult)}, with
	 * errors.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testAddEditorFromFormErrors() throws ClassNotFoundException,
			IOException {

		setupVariables();
		Editor editor = new Editor();
		BindingResultForTest bindingResult = new BindingResultForTest();
		bindingResult.setErrors( true );
		String menu = ec.addEditorFromForm( editor, bindingResult );
		menu = ec.addEditorFromForm( editor, bindingResult );
		assertTrue( "register/first_time".equalsIgnoreCase( menu ) );
	}

	/**
	 * Test method for {@link EditorController#displayForm(Model)}
	 */
	@Test
	public void testDisplayForm() {
		ec = new EditorController();
		Model modelMock = mock( Model.class );
		String menu = ec.displayForm( modelMock );
		verify( modelMock ).addAttribute( isA( Editor.class ) );
		assertTrue( "register/first_time".equalsIgnoreCase( menu ) );
	}

	private void setupVariables() {
		editorDaoMock = mock( EditorDao.class );
		ec = new EditorController();
		ec.setEditorDao( editorDaoMock );
	}
}
