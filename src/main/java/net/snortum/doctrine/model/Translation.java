package net.snortum.doctrine.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Bible translations.  These should match the script in WEB-INF/views/form.html
 * 
 * @author Knute
 * @version 0.1
 */
public enum Translation {
	KJVA ("King James Version With Apocrypha", "eng-KJVA"), 
	NASB ("New American Standard Bible",       "eng-NASB"), 
	NIV  ("New International Version",         "eng-NIV"), 
	NRSV ("New Revised Standard Version",      "eng-NRSV"), 
	RSV  ("Revised Standard Version",          "eng-RSV"); 
	
	{
		for ( Translation trans : values() ) {
			this.descriptions.put( trans, trans.getDescription() );
			this.versions.put( trans, trans.getVersion() );
		}
	}
	
	private String description;
	private String version;
	private Map<Translation, String> descriptions = new HashMap<>();
	private Map<Translation, String> versions = new HashMap<>();
	
	private Translation( String description, String version ) {
		this.description = description;
		this.version = version;
	}
	
	/**
	 * @return this translation's description
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * @return the bibles.org version of this translation
	 */
	public String getVersion() {
		return this.version;
	}
	
	/**
	 * @return a map of translations and their descriptions
	 */
	public Map<Translation, String> getDescriptions() {
		return this.descriptions;
	}

	/**
	 * @return a map of translations and their bible.org versions
	 */
	public Map<Translation, String> getVersions() {
		return this.versions;
	}

}
