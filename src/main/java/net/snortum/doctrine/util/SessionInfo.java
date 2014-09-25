package net.snortum.doctrine.util;

import java.util.List;

import net.snortum.doctrine.model.Editor;
import net.snortum.doctrine.model.Version;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope( value = WebApplicationContext.SCOPE_SESSION,
		proxyMode = ScopedProxyMode.TARGET_CLASS )
public class SessionInfo {

	private boolean loggedIn;
	private Editor editor;
	private List<Version> versions;

	/**
	 * Create a default session object
	 */
	public SessionInfo() {
	}

	/**
	 * @return the loggedIn
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * @param loggedIn
	 *            the loggedIn to set
	 */
	public void setLoggedIn( boolean loggedIn ) {
		this.loggedIn = loggedIn;
	}

	/**
	 * @return the editor
	 */
	public Editor getEditor() {
		return editor;
	}

	/**
	 * @param editor
	 *            the editor to set
	 */
	public void setEditor( Editor editor ) {
		this.editor = editor;
		this.loggedIn = true;
	}

	/**
	 * @return the versions
	 */
	public List<Version> getVersions() {
		return versions;
	}

	/**
	 * @param versions
	 *            the versions to set
	 */
	public void setVersions( List<Version> versions ) {
		this.versions = versions;
	}

	/**
	 * @return whether this {@link Editor} can add a doctrine
	 */
	public boolean editorCanAdd() {
		if ( this.editor == null ) {
			return false;
		}
		else {
			return editor.editorCanAdd();
		}
	}

	public void logoff() {
		this.editor = null;
		this.loggedIn = false;
	}

}
