package net.snortum.doctrine.model;

import java.net.URL;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "version" )
public class Version {

	private String id;
	private String name;
	private String lang;
	private String langCode;
	private String copyright;
	private String info;
	private URL contactUrl;
	private String langName;
	private String langNameEng;
	private String abbreviation;
	private Date updatedAt;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@XmlElement
	public void setId( String id ) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@XmlElement
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang
	 *            the lang to set
	 */
	@XmlElement
	public void setLang( String lang ) {
		this.lang = lang;
	}

	/**
	 * @return the langCode
	 */
	public String getLangCode() {
		return langCode;
	}

	/**
	 * @param langCode
	 *            the langCode to set
	 */
	@XmlElement
	public void setLangCode( String langCode ) {
		this.langCode = langCode;
	}

	/**
	 * @return the copyright
	 */
	public String getCopyright() {
		return copyright;
	}

	/**
	 * @param copyright
	 *            the copyright to set
	 */
	@XmlElement
	public void setCopyright( String copyright ) {
		this.copyright = copyright;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	@XmlElement
	public void setInfo( String info ) {
		this.info = info;
	}

	/**
	 * @return the contactUrl
	 */
	public URL getContactUrl() {
		return contactUrl;
	}

	/**
	 * @param contactUrl
	 *            the contactUrl to set
	 */
	@XmlElement
	public void setContactUrl( URL contactUrl ) {
		this.contactUrl = contactUrl;
	}

	/**
	 * @return the langName
	 */
	public String getLangName() {
		return langName;
	}

	/**
	 * @param langName
	 *            the langName to set
	 */
	@XmlElement
	public void setLangName( String langName ) {
		this.langName = langName;
	}

	/**
	 * @return the langNameEng
	 */
	public String getLangNameEng() {
		return langNameEng;
	}

	/**
	 * @param langNameEng
	 *            the langNameEng to set
	 */
	@XmlElement
	public void setLangNameEng( String langNameEng ) {
		this.langNameEng = langNameEng;
	}

	/**
	 * @return the abbreviation
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * @param abbreviation
	 *            the abbreviation to set
	 */
	@XmlElement
	public void setAbbreviation( String abbreviation ) {
		this.abbreviation = abbreviation;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	@XmlElement
	public void setUpdatedAt( Date updatedAt ) {
		this.updatedAt = updatedAt;
	}
	
	@Override
	public String toString() {
		return "Id: " +  getId()
				+ ", Name: " + getName()
				+ ", Lang: " + getLang()
				+ ", Abbr: " + getAbbreviation();
	}
}
