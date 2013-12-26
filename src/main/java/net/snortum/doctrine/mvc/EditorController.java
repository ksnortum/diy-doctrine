package net.snortum.doctrine.mvc;

import java.io.IOException;

import javax.validation.Valid;

import net.snortum.doctrine.dao.EditorDao;
import net.snortum.doctrine.dao.EditorDaoFactory;
import net.snortum.doctrine.model.Editor;

import org.apache.log4j.Logger;
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
@RequestMapping("/register")
public class EditorController {
	private final static Logger LOG = Logger.getLogger(EditorController.class);

	private EditorDao editorDao;

	/**
	 * Create an EditorController object. Set {@link EditorDao} from factory.
	 */
	public EditorController() {
		editorDao = EditorDaoFactory.getEditorDao();
	}

	/**
	 * Save an {@link Editor}
	 * 
	 * @param editor
	 *            the editor to save
	 * @param bindingResult
	 *            tell if validation failed
	 * @return string to the next page
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String addEditorFromForm(@Valid Editor editor,
			BindingResult bindingResult) throws ClassNotFoundException,
			IOException {

		if (LOG.isInfoEnabled()) {
			LOG.info("In addEditorFromForm()");
		}

		// Form did not validate
		if (bindingResult.hasErrors()) {
			return "register/first_time";
		}

		getEditorDao().saveEditor(editor);

		// New editor still needs to login
		return "home";
	}

	/**
	 * Display an empty form for new {@link Editor}
	 * 
	 * @param model
	 *            add a new editor to this
	 * @return string to next page
	 */
	@RequestMapping(value = "first_time")
	public String displayForm(Model model) {
		if (LOG.isInfoEnabled()) {
			LOG.info("In displayForm()");
		}
		model.addAttribute(new Editor());
		return "register/first_time";
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
	public void setEditorDao(EditorDao editorDao) {
		this.editorDao = editorDao;
	}
}
