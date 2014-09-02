package net.snortum.doctrine.mvc;

import net.snortum.doctrine.model.SessionInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller that logs off the current editor
 * 
 * @author Knute Snortum
 * @version 0.2
 */

@Controller
@RequestMapping( "/logoff" )
public class LogoffController {
	private static final Logger LOG = Logger.getLogger( LogoffController.class );
	
	@Autowired
	private SessionInfo sessionInfo;

	/**
	 * Log off an editor
	 * 
	 * @param model Model to remove editor from
	 * @return page to display after logoff
	 */
	@RequestMapping( value = "frame" )
	public String logoffEditor( ModelMap model ) {

		if ( LOG.isInfoEnabled() ) {
			LOG.info( "In logoffEditor()" );
		}
		
		model.remove( "editor" );
		sessionInfo.logoff();
		
		return "logoff/success";
	}
}