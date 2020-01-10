package net.snortum.doctrine.util;

import java.util.ArrayList;
import java.util.List;

import net.snortum.doctrine.model.Version;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VersionsBean {
	
	private static Logger LOG = LogManager.getLogger();

	public List<Version> getVersions() {
		if ( LOG.isInfoEnabled() ) {
			LOG.info( "In getVersions()" );
		}
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> entity =
				template.getForEntity( Literals.VERSIONS_URI, String.class );
		if ( LOG.isInfoEnabled() ) {
			LOG.info( entity.toString() );
		}
		List<Version> versionList = new ArrayList<>();

		return versionList;
	}

}
