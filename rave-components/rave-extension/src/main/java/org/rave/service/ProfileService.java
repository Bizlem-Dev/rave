package org.rave.service;

import java.awt.image.BufferedImage;
import org.rave.model.Company;
import org.rave.model.ImportedContacts;
import org.rave.model.RavePerson;
import org.rave.model.Profile;
import org.rave.model.ServiceProvider;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Interface <class>ProfileService</class> is related to account info extraction and modification.
 * Change password and forgot password are also contained in this module. 
 */
public interface ProfileService {

	/**
	 * Save first profile.
	 *
	 * @param id the id
	 * @return the profile
	 */
	public Profile saveFirstProfile(String id);
	
	/**
	 * Edits the profile.
	 *
	 * @param id the id
	 * @return the profile
	 */
	public Profile editProfile(String id);
	
	/**
	 * Save profile.
	 *
	 * @param profile the profile
	 * @return the profile
	 */
	public Profile saveProfile(Profile profile);
	
	/**
	 * Gets the person.
	 *
	 * @param id the id
	 * @return the person
	 */
	public RavePerson getPerson(String id);
	
	/**
	 * Resize image.
	 *
	 * @param originalImage the original image
	 * @param type the type
	 * @return the buffered image
	 */
	public BufferedImage resizeImage(BufferedImage originalImage, int type);
	
	/**
	 * Upload pic.
	 *
	 * @param fileData the file data
	 * @param userName the user name
	 */
	public void uploadPic(CommonsMultipartFile fileData,String userName);
	
	/**
	 * Save dynamic attribute.
	 *
	 * @param userId the user id
	 * @param picture the picture
	 */
	public void saveDynamicAttribute(String userId,String picture);
	
	/**
	 * Gets the count connection.
	 *
	 * @param userId the user id
	 * @return the count connection
	 */
	public int getCountConnection(String userId);
	
	/**
	 * Gets the count request.
	 *
	 * @param userId the user id
	 * @return the count request
	 */
	public int getCountRequest(String userId);
	
	/**
	 * Save company profile.
	 *
	 * @param company the company
	 */
	public void saveCompanyProfile(Company company);
	
	/**
	 * Gets the company.
	 *
	 * @param id the id
	 * @return the company
	 */
	public Company getCompany(int id);
	
	/**
	 * Save person.
	 *
	 * @param person the person
	 */
	public void savePerson(RavePerson person);
	
	/**
	 * Change password.
	 *
	 * @param person the person
	 */
	public void changePassword(RavePerson person);
	
	/**
	 * Save service provider.
	 *
	 * @param provider the provider
	 */
	public void saveServiceProvider(ServiceProvider provider);
	
	/**
	 * Save imported contact.
	 *
	 * @param contact the contact
	 */
	public void saveImportedContact(ImportedContacts contact);
	
	/**
	 * Gets the service provider.
	 *
	 * @param providerUserName the provider user name
	 * @param providerId the provider id
	 * @return the service provider
	 */
	public ServiceProvider getServiceProvider(String providerUserName,String providerId);
	
	/**
	 * Ldap password change.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @return true, if successful
	 */
	boolean ldapEditPassword(String userName, String password);
}
