package net.snortum.doctrine.mvc;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import net.snortum.doctrine.dao.EditorDao;
import net.snortum.doctrine.model.Editor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Control the {@link Editor} form, display and save
 * 
 * @author Knute Snortum
 * @version 0.1
 */
@Controller
@RequestMapping( "/register" )
public class RegisterController {
	private final static Logger LOG = LogManager.getLogger();
	private static Validator validator;

	@Autowired
	private EditorDao editorDao;

	public RegisterController() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	/**
	 * Save an {@link Editor}
	 * 
	 * @param editor
	 *            the editor to save
	 * @param bindingResult
	 *            tell if validation failed
	 * @return string to the next page
	 */
	@RequestMapping( value = "frame", method = RequestMethod.POST )
	public String addEditorFromForm( @Valid Editor editor,
			BindingResult bindingResult ) {

		if ( LOG.isInfoEnabled() ) {
			LOG.info( "In addEditorFromForm()" );
		}

		// Form did not validate
		if ( bindingResult.hasErrors() ) {
			if ( LOG.isInfoEnabled() ) {
				LOG.info( "Register form did not validate" );
			}
			return "register/frame";
		}

		if ( !validateEditor( editor ) ) {
			if ( LOG.isInfoEnabled() ) {
				LOG.info( "Editor object did not validate" );
			}
			// Warning user
			return "register/frame"; // or some other page
		}

		getEditorDao().create( editor );

		// New editor still needs to login
		return "login/frame";
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
		return "register/frame";
	}

	/**
	 * Validate this editor using constraint annotations in {@link Editor}.
	 * 
	 * @param editor
	 *            the editor to validate
	 * @return true for valid and false for invalid
	 */
	private boolean validateEditor( Editor editor ) {
		Set<ConstraintViolation<Editor>> violations =
				validator.validate( editor );

		if ( violations.size() > 0 ) {
			for ( ConstraintViolation<Editor> violation : violations ) {
				LOG.error( violation.getMessage() );
			}

			return false;
		}

		return true;
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
