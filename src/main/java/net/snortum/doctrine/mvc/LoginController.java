package net.snortum.doctrine.mvc;

import java.io.IOException;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import net.snortum.doctrine.dao.EditorDao;
import net.snortum.doctrine.model.Editor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller that logs in a valid editor
 * 
 * @author Knute Snortum
 * @version 0.3
 */

@Controller
@RequestMapping( "/login" )
public class LoginController {
	private static final Logger LOG = Logger.getLogger( LoginController.class );

	@Autowired
	private EditorDao editorDao;
	
	@Autowired
	private Validator validator;
	
	public LoginController() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	/**
	 * Validate a user ({@link Editor})
	 * 
	 * @param editor
	 *            the editor entity
	 * @return string to the next page
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@RequestMapping( value = "frame", method = RequestMethod.POST )
	public String validateEditor( @Valid Editor editor,
			BindingResult bindingResult ) {

		if ( LOG.isInfoEnabled() ) {
			LOG.info( "In validateEditor()" );
		}

		// Form did not validate
		if ( bindingResult.hasErrors() ) {
			return "login/frame";
		}

		int editorId = getEditorDao().idByUsername( editor.getUsername() );
		
		if ( editorId != -1) {
			editor = getEditorDao().read( editorId );
		} else {
			return "login/not_found";
		}

		if ( editor.editorCanAdd() ) {
			return "add/menu"; // TODO: what is this URL
		} else {
			return "login/success"; 
		}
	}

	/**
	 * Display an empty form for new {@link Editor}
	 * 
	 * @param model
	 *            add a new editor to this
	 * @return string to next page
	 */
	@RequestMapping( value = "frame", method = RequestMethod.GET )
	public String displayForm( Model model ) {
		if ( LOG.isInfoEnabled() ) {
			LOG.info( "In displayForm()" );
		}
		model.addAttribute( new Editor() );
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
}
