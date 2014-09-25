package net.snortum.doctrine.mvc;

import net.snortum.doctrine.util.SessionInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Control the home page
 * 
 * @author Knute Snortum
 * @version 0.2
 */
@Controller
public class HomeController {
	private static final Logger LOG = Logger.getLogger( HomeController.class );
	
	@Autowired
	private SessionInfo sessionInfo;

	/**
	 * Map HTTP root or "home" to home page
	 * 
	 * @param model
	 *            not used
	 * @return the name of the home page
	 */
	@RequestMapping( { "/", "/home" } )
	public String showHomePage( ModelMap model ) {
		if ( LOG.isInfoEnabled() ) {
			LOG.info( "In showHomePage()" );
		}

		if ( sessionInfo.isLoggedIn() ) {
			if ( LOG.isInfoEnabled() ) {
				LOG.info( "Logged in" );
			}
			return "main_logged_in";
		}
		
		return "main";
	}
}
