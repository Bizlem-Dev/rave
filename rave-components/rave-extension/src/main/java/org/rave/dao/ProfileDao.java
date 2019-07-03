package org.rave.dao;


import java.util.List;


import org.rave.model.Company;
import org.rave.model.ImportedContacts;
import org.rave.model.RavePageUser;
import org.rave.model.RavePerson;
import org.rave.model.Profile;
import org.rave.model.ServiceProvider;

// TODO: Auto-generated Javadoc
/**
 * The Interface ProfileDao.
 */
public interface ProfileDao {
	
	
	/**
	 * Save profile.
	 *
	 * @param profile the profile
	 * @return the profile
	 */
	public Profile saveProfile(Profile profile);
	
	/**
	 * Edits the profile.
	 *
	 * @param id the id
	 * @return the profile
	 */
	public Profile editProfile(String id);
	
	/**
	 * Gets the person.
	 *
	 * @param id the id
	 * @return the person
	 */
	public List<RavePerson> getPerson(String id);
	
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
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public List<Profile> getUserName();
	
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

}
