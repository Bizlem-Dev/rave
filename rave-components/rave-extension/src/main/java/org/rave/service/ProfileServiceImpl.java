package org.rave.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.servlet.http.HttpServletRequest;
import org.rave.dao.ProfileDao;
import org.rave.model.Company;
import org.rave.model.ImportedContacts;
import org.rave.model.RavePerson;
import org.rave.model.Profile;
import org.rave.model.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import sun.misc.BASE64Encoder;

/**
 * Class <class>ProfileService</class> is related to account info extraction and
 * modification. Change password and forgot password are also contained in this
 * module.
 */
@SuppressWarnings("restriction")
@Service("profileService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	public ProfileDao profile_dao;

	@Autowired
	private HttpServletRequest request;

	private ResourceBundle bundle;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rave.service.ProfileService#saveFirstProfile(java.lang.String)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Profile saveFirstProfile(String id) {
		Profile profile = new Profile();
		profile = editProfile(id);
		RavePerson personGet = new RavePerson();
		personGet = getPerson(id);

		try {
			if (!personGet.getUsername().equals(profile.getUserId())) {
				profile.setEmail(personGet.getEmail());
				profile.setUserId(personGet.getUsername());
				profile.setName(personGet.getGiven_name());
				profile.setStatus(personGet.getStatus());
				profile.setPicture("css/images/photo.gif");

				saveProfile(profile);
			}

		} catch (Exception e) {

			Profile profile1 = new Profile();
			profile1.setEmail(personGet.getEmail());
			profile1.setUserId(personGet.getUsername());
			profile1.setName(personGet.getGiven_name());
			profile1.setStatus(personGet.getStatus());
			profile1.setPicture("css/images/photo.gif");

			saveProfile(profile1);

		}

		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rave.service.ProfileService#saveProfile(org.rave.model.Profile)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Profile saveProfile(Profile profile) {

		return profile_dao.saveProfile(profile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rave.service.ProfileService#editProfile(java.lang.String)
	 */
	public Profile editProfile(String id) {

		return profile_dao.editProfile(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rave.service.ProfileService#getPerson(java.lang.String)
	 */
	public RavePerson getPerson(String id) {

		RavePerson person_service = new RavePerson();
		List<RavePerson> person_list = profile_dao.getPerson(id);

		person_service.setEmail(person_list.get(0).getEmail());
		person_service.setGiven_name(person_list.get(0).getGiven_name());
		person_service.setStatus(person_list.get(0).getStatus());
		person_service.setUsername(person_list.get(0).getUsername());
		person_service.setPlaintext_password(person_list.get(0).getPassword());
		return person_service;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rave.service.ProfileService#resizeImage(java.awt.image.BufferedImage,
	 * int)
	 */
	public BufferedImage resizeImage(BufferedImage originalImage, int type) {
		BufferedImage resizedImage = new BufferedImage(125, 165, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 125, 165, null);
		g.dispose();

		return resizedImage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rave.service.ProfileService#saveDynamicAttribute(java.lang.String,
	 * java.lang.String)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveDynamicAttribute(String userId, String picture) {

		profile_dao.saveDynamicAttribute(userId, picture);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rave.service.ProfileService#uploadPic(org.springframework.web.multipart
	 * .commons.CommonsMultipartFile, java.lang.String)
	 */
	public void uploadPic(CommonsMultipartFile fileData, String userName) {

		try {
			MultipartFile file = fileData;
			String fileName = null;
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (file.getSize() > 0) {
				inputStream = file.getInputStream();

				System.out.println("size::" + file.getSize());
				fileName = request.getRealPath(File.separator)
						+ "css/profilePic/" + file.getOriginalFilename();
				outputStream = new FileOutputStream(fileName);

				System.out.println("fileName:" + file.getOriginalFilename());
				System.out.println("file path" + fileName);

				int readBytes = 0;
				byte[] buffer = new byte[10000];
				while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
			BufferedImage originalImage = ImageIO.read(new File(fileName));
			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
					: originalImage.getType();
			BufferedImage resizeImageJpg = resizeImage(originalImage, type);
			ImageIO.write(resizeImageJpg, "jpg",
					new File(request.getRealPath(File.separator)
							+ "css/profilePic/" + userName + ".jpg"));
			if (file.getSize() > 10000) {
				System.out.println("File Size::::::" + file.getSize());

			}

			// System.out.println(session.getAttribute("uploadFile")+"  file.getOriginalFilename() : "+
			// file.getOriginalFilename());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rave.service.ProfileService#getCountConnection(java.lang.String)
	 */
	public int getCountConnection(String userId) {

		return profile_dao.getCountConnection(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rave.service.ProfileService#getCountRequest(java.lang.String)
	 */
	public int getCountRequest(String userId) {

		return profile_dao.getCountRequest(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rave.service.ProfileService#saveCompanyProfile(org.rave.model.Company
	 * )
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveCompanyProfile(Company company) {
		profile_dao.saveCompanyProfile(company);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rave.service.ProfileService#getCompany(int)
	 */
	public Company getCompany(int id) {

		return profile_dao.getCompany(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rave.service.ProfileService#savePerson(org.rave.model.RavePerson)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void savePerson(RavePerson person) {
		profile_dao.savePerson(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rave.service.ProfileService#changePassword(org.rave.model.RavePerson)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void changePassword(RavePerson person) {

		profile_dao.changePassword(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rave.service.ProfileService#saveServiceProvider(org.rave.model.
	 * ServiceProvider)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveServiceProvider(ServiceProvider provider) {

		profile_dao.saveServiceProvider(provider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rave.service.ProfileService#saveImportedContact(org.rave.model.
	 * ImportedContacts)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveImportedContact(ImportedContacts contact) {
		profile_dao.saveImportedContact(contact);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rave.service.ProfileService#getServiceProvider(java.lang.String,
	 * java.lang.String)
	 */
	public ServiceProvider getServiceProvider(String providerUserName,
			String providerId) {

		return profile_dao.getServiceProvider(providerUserName, providerId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rave.service.ProfileService#ldapEditPassword(java.lang.String,
	 * java.lang.String)
	 */
	public boolean ldapEditPassword(String userName, String password) {
		Hashtable<String, String> env = new Hashtable<String, String>();
		try {
			bundle = ResourceBundle.getBundle("jdbc");
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL,
					"ldap://" + bundle.getString("ldap.host") + ":389");
			env.put(Context.SECURITY_PRINCIPAL, bundle.getString("ldap.rootdn"));
			env.put(Context.SECURITY_CREDENTIALS,
					bundle.getString("ldap.password"));
			DirContext ctx = new InitialDirContext(env);
			ModificationItem[] mods = new ModificationItem[1];
			Attribute mod0 = new BasicAttribute("userpassword",
					encryptLdapPassword("SHA", password));
			mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod0);
			ctx.modifyAttributes(
					"uid=" + userName + "," + bundle.getString("ldap.basedn"),
					mods);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Encrypt ldap password.
	 * 
	 * @param algorithm
	 *            the algorithm
	 * @param _password
	 *            the _password
	 * @return the string
	 */
	private String encryptLdapPassword(String algorithm, String _password) {
		String sEncrypted = _password;
		if ((_password != null) && (_password.length() > 0)) {
			boolean bMD5 = algorithm.equalsIgnoreCase("MD5");
			boolean bSHA = algorithm.equalsIgnoreCase("SHA")
					|| algorithm.equalsIgnoreCase("SHA1")
					|| algorithm.equalsIgnoreCase("SHA-1");
			if (bSHA || bMD5) {
				String sAlgorithm = "MD5";
				if (bSHA) {
					sAlgorithm = "SHA";
				}
				try {
					MessageDigest md = MessageDigest.getInstance(sAlgorithm);
					md.update(_password.getBytes("UTF-8"));
					sEncrypted = "{" + sAlgorithm + "}"
							+ (new BASE64Encoder()).encode(md.digest());
				} catch (Exception e) {
					sEncrypted = null;
				}
			}
		}
		return sEncrypted;
	}

}
