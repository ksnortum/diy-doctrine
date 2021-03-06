package net.snortum.doctrine.mvc;

import javax.validation.Valid;

import net.snortum.doctrine.dao.EditorDao;
import net.snortum.doctrine.model.Editor;
import net.snortum.doctrine.util.SessionInfo;
import net.snortum.doctrine.util.VersionsBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller that logs in a valid editor
 * 
 * @author Knute Snortum
 * @version 0.6
 */

@Controller
@RequestMapping( "/login" )
public class LoginController {
	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	private EditorDao editorDao;

	@Autowired
	private SessionInfo sessionInfo;

	@Autowired
	private VersionsBean versions;

	/**
	 * Validate a user ({@link Editor})
	 * 
	 * @param editor
	 *            the editor entity
	 * @param bindingResult
	 *            used to check for validation errors
	 * @param model
	 *            ModelMap used to hold logged in editor
	 * @return string to the next page
	 */
	@RequestMapping( value = "frame", method = RequestMethod.POST )
	public String validateEditor( @Valid Editor editor,
			BindingResult bindingResult, ModelMap model ) {

		if ( LOG.isInfoEnabled() ) {
			LOG.info( "In validateEditor()" );
		}

		// Form did not validate
		if ( bindingResult.hasErrors() ) {
			if ( LOG.isInfoEnabled() ) {
				LOG.info( "Login form did not validate" );
			}
			return "login/frame";
		}

		int editorId = editorDao.idByUsername( editor.getUsername() );

		if ( editorId != -1 ) {
			editor = editorDao.read( editorId );

			if ( editor == null ) {
				LOG.error( "Found editor id (" + editorId +
						") but cannot read editor from DB" );
				return "login/not_found";
			}
		}
		else {
			return "login/not_found";
		}

		if ( LOG.isInfoEnabled() ) {
			LOG.info( "Updating Model and SessionInfo" );
		}

		model.addAttribute( "editor", editor );
		sessionInfo.setEditor( editor );
		//List<Version> versionList = versions.getVersions();
		//sessionInfo.setVersions( versionList );

		if ( editor.editorCanAdd() ) {
			return "menu"; //
		}

		return "login/success";
	}

	/**
	 * Display an empty form to login
	 * 
	 * @param model
	 *            add a new editor to this
	 * @return string to next page
	 */
	@RequestMapping( value = "frame", method = RequestMethod.GET )
	public String displayForm( ModelMap model ) {
		if ( LOG.isInfoEnabled() ) {
			LOG.info( "In displayForm()" );
		}
		model.addAttribute( "editor", new Editor() );
		return "login/frame";
	}

	/**
	 * @return the editorDao
	 */
	public EditorDao getEditorDao() {
		return editorDao;
	}

	/**
	 * @param editorDao
	 *            the editorDao to set
	 */
	public void setEditorDao( EditorDao editorDao ) {
		this.editorDao = editorDao;
	}

	/**
	 * For testing only.
	 * 
	 * @param sessionInfo
	 *            the sessionInfo to set
	 */
	public void setSessionInfo( SessionInfo sessionInfo ) {
		this.sessionInfo = sessionInfo;
	}

	/**
	 * For testing only
	 * 
	 * @param versions
	 *            the versions bean to set
	 */
	public void setVersions( VersionsBean versions ) {
		this.versions = versions;
	}
}
