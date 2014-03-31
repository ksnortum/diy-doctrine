package net.snortum.doctrine.mvc;

import java.io.IOException;

import javax.validation.Valid;

import net.snortum.doctrine.dao.EditorDao;
import net.snortum.doctrine.dao.EditorDaoFactory;
import net.snortum.doctrine.model.Editor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller that logs in a valid editor
 * 
 * @author Knute Snortum
 * @version 0.1
 */

@Controller
@RequestMapping( "/login" )
public class LoginController {
	private static final Logger LOG = Logger.getLogger( LoginController.class );

	private EditorDao editorDao;
	
	@Autowired
	private Validator validator;

	/**
	 * Create an LoginController object. Set {@link EditorDao} from factory.
	 */
	public LoginController() {
		editorDao = EditorDaoFactory.getEditorDao();
	}

	/**
	 * Validate a user ({@link Editor})
	 * 
	 * @param username
	 *            the editor's username
	 * @param password
	 *            the editor's password
	 * @return string to the next page
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@RequestMapping( value = "first_time", method = RequestMethod.POST )
	public String validateEditor( @Model( Editor editor ),
			BindingResult bindingResult ) throws IOException,
			ClassNotFoundException {

		if ( LOG.isInfoEnabled() ) {
			LOG.info( "In validateEditor()" );
		}

		// Form did not validate
		if ( bindingResult.hasErrors() ) {
			return "login/first_time";
		}

		Editor editor = new Editor();

		if ( getEditorDao().validateEditor( username, password ) ) {
			editor = getEditorDao().getEditor( username );
		}

		if ( editor.editorCanAdd() ) {
			return "add/menu"; // TODO: what is this URL
		}
		else {
			return "display/menu"; // TODO: where do we go? Display?
		}
	}

	/**
	 * Display an empty form for new {@link Editor}
	 * 
	 * @param model
	 *            add a new editor to this
	 * @return string to next page
	 */
	@RequestMapping( value = "first_time", method = RequestMethod.GET )
	public String displayForm( Model model ) {
		if ( LOG.isInfoEnabled() ) {
			LOG.info( "In displayForm()" );
		}
		model.addAttribute( new Editor() );
		return "login/first_time";
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
