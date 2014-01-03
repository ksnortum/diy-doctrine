package net.snortum.doctrine.mvc;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Control the home page
 * 
 * @author Knute Snortum
 * @version 0.1
 */
@Controller
public class HomeController {
	private static final Logger LOG = Logger.getLogger( HomeController.class );

	/**
	 * Map HTTP root or "home" to home page
	 * 
	 * @param model
	 *            not used
	 * @return the name of the home page
	 */
	@RequestMapping( { "/", "/home" } )
	public String showHomePage( Map<String, Object> model ) {
		if ( LOG.isInfoEnabled() ) {
			LOG.info( "In showHomePage()" );
		}

		return "home";
	}
}
