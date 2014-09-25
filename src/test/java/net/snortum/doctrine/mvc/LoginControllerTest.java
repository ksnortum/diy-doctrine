package net.snortum.doctrine.mvc;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import net.snortum.doctrine.dao.EditorDao;
import net.snortum.doctrine.model.Editor;
import net.snortum.doctrine.util.SessionInfo;
import net.snortum.doctrine.util.VersionsBean;

import org.junit.Test;
import org.springframework.ui.ModelMap;

public class LoginControllerTest {

	private static final int EDITOR_ID = 1;

	private LoginController lc;
	private EditorDao editorDaoMock;
	private SessionInfo sessionInfo;
	private VersionsBean versions;
	private ModelMap modelMock;
	private Editor editor;
	private BindingResultForTest bindingResult;

	/**
	 * Test validateEditor(), pass
	 */
	@Test
	public void testValidateEditorPass() {
		setVariables();
		when( editorDaoMock.idByUsername( editor.getUsername() ) )
				.thenReturn( EDITOR_ID );
		when( editorDaoMock.read( EDITOR_ID ) ).thenReturn( editor );
		bindingResult.setErrors( false );
		String menu = lc.validateEditor( editor, bindingResult, modelMock );
		verify( editorDaoMock ).idByUsername( anyString() );
		verify( editorDaoMock ).read( anyInt() );
		verify( modelMock ).addAttribute( anyString(), any( Editor.class ) );
		verify( sessionInfo ).setEditor( any( Editor.class ) );
		assertTrue( "login/success".equals( menu ) );
	}

	/**
	 * Test validateEditor(), bad data in form
	 */
	@Test
	public void testValidateEditorFormFail() {
		setVariables();
		bindingResult.setErrors( true );
		String menu = lc.validateEditor( editor, bindingResult, modelMock );
		verify( editorDaoMock, never() ).idByUsername( anyString() );
		verify( editorDaoMock, never() ).read( anyInt() );
		verify( modelMock, never() ).addAttribute(
				anyString(), any( Editor.class ) );
		verify( sessionInfo, never() ).setEditor( any( Editor.class ) );
		assertTrue( "login/frame".equals( menu ) );
	}

	/**
	 * Test validateEditor(), editor (user) not found
	 */
	@Test
	public void testValidateEditorNoUsername() {
		setVariables();
		bindingResult.setErrors( false );
		when( editorDaoMock.idByUsername( editor.getUsername() ) )
				.thenReturn( -1 );
		String menu = lc.validateEditor( editor, bindingResult, modelMock );
		verify( editorDaoMock ).idByUsername( anyString() );
		verify( editorDaoMock, never() ).read( anyInt() );
		verify( modelMock, never() ).addAttribute(
				anyString(), any( Editor.class ) );
		assertTrue( "login/not_found".equals( menu ) );
	}

	/**
	 * Test validateEditor(), editor found but bad ID
	 */
	@Test
	public void testValidateEditorEntityNotFound() {
		setVariables();
		when( editorDaoMock.idByUsername( editor.getUsername() ) )
				.thenReturn( EDITOR_ID );
		when( editorDaoMock.read( EDITOR_ID ) ).thenReturn( null );
		bindingResult.setErrors( false );
		String menu = lc.validateEditor( editor, bindingResult, modelMock );
		verify( editorDaoMock ).idByUsername( anyString() );
		verify( editorDaoMock ).read( anyInt() );
		verify( modelMock, never() ).addAttribute(
				anyString(), any( Editor.class ) );
		assertTrue( "login/not_found".equals( menu ) );
	}

	/**
	 * Set and reset variables for testing
	 */
	private void setVariables() {
		editorDaoMock = mock( EditorDao.class );
		sessionInfo = mock( SessionInfo.class );
		versions = mock( VersionsBean.class );
		lc = new LoginController();
		lc.setEditorDao( editorDaoMock );
		lc.setSessionInfo( sessionInfo );
		lc.setVersions( versions );
		modelMock = mock( ModelMap.class );
		editor = new Editor();
		bindingResult = new BindingResultForTest();
	}
}
