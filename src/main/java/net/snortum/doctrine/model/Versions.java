package net.snortum.doctrine.model;

import java.util.HashMap;
import java.util.Map;

public enum Versions {
	NIV( "eng-NIV", "New International Version" ),
	NASB( "eng-NASB", "New American Standard Bible" ),
	NRSV( "eng-NRSV", "New Revised Standard Version" ),
	RSV( "eng-RSV", "Revised Standard Version" ),
	KJVA( "eng-KJVA", "King James Version" );

	private String apiName;
	private String descr;
	private Map<Versions, String> descriptions = new HashMap<>();
	
	{
		for ( Versions version: values() ) {
			String description = version.getDescr();
			descriptions.put( version, description );
		}
	}

	private Versions( String apiName, String descr ) {
		this.apiName = apiName;
	}

	/**
	 * @return the Bibles.org API version name
	 */
	public String getApiName() {
		return this.apiName;
	}

	/** @return the description of this version */
	public String getDescr() {
		return this.descr;
	}
	
	/** @return a map of all versions and their descriptions */
	public Map<Versions, String> getDescriptioins() {
		return this.descriptions;
	}
}
