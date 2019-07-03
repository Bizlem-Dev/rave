package org.rave.service;

import org.brickred.socialauth.AuthProvider;

// TODO: Auto-generated Javadoc
/**
 * Interface <class>ProfileExportService</class> is used to export all content
 * related to profile i.e. Profile, Education, Experience, ProfileSummary,
 * Additional information and contacts.
 * 
 * @author pranav.arya
 */
public interface ProfileExportService {

	/**
	 * Export user's profile in sling.
	 * 
	 * @param provider
	 *            the provider
	 */
	void slingExportProfile(AuthProvider provider);

	/**
	 * Export user's education in sling.
	 * 
	 * @param provider
	 *            the provider
	 * @param count
	 *            the count
	 */
	void slingExportEducation(AuthProvider provider, int count);

	/**
	 * Export summary in sling.
	 * 
	 * @param provider
	 *            the provider
	 */
	void slingExportSummary(AuthProvider provider);

	/**
	 * Export experience in sling.
	 * 
	 * @param provider
	 *            the provider
	 * @param count
	 *            the count
	 */
	void slingExportExperience(AuthProvider provider, int count);

	/**
	 * Export info in sling.
	 * 
	 * @param provider
	 *            the provider
	 */
	void slingExportInfo(AuthProvider provider);

	/**
	 * Export contacts in sling.
	 * 
	 * @param contacts
	 *            the contacts
	 * @param id
	 *            the id
	 */
	void slingExportContacts(String contacts, String id);

	/**
	 * Send email.
	 * 
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 */
	void sendEmail(String userName, String password);

	/**
	 * Generate 8 digit random password.
	 * 
	 * @return the string
	 */
	String gen8DigitPwd();

	/**
	 * Call service.
	 * 
	 * @param urlStr
	 *            the url str
	 * @param paramName
	 *            the param name
	 * @param paramValue
	 *            the param value
	 * @return the string
	 */
	String callService(String urlStr, String[] paramName, String[] paramValue);
}
