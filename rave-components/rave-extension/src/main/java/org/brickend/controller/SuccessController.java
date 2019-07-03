package org.brickend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.apache.rave.portal.service.ActiveMQProducer;
import org.apache.rave.portal.service.NewAccountService;
import javax.servlet.http.HttpServletRequest;
import org.rave.model.ImportedContacts;
import org.rave.model.Profile;
import org.rave.model.RavePerson;
import org.rave.model.ServiceProvider;
import org.rave.service.ProfileExportService;
import org.rave.service.ProfileService;
import org.apache.rave.portal.model.User;
import org.apache.rave.portal.model.impl.UserImpl;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.spring.bean.SocialAuthTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class <class>SuccessController</class> is an Action class. This class is used
 * for Social oAuth successful redirection. This controller creates the account,
 * import profile and import contacts from Different Social Networking sites.
 * 
 * @author pranav.arya
 */
@Controller
public class SuccessController {

	@Autowired
	private SocialAuthTemplate socialAuthTemplate;

	@Autowired
	private ProfileService profile_service;

	@Autowired
	private ProfileExportService export_service;

	private static final Logger logger = Logger
			.getLogger(SuccessController.class);

	@Autowired
	private NewAccountService newAccountService;
	
	@Autowired
	private ActiveMQProducer activeMQ;

	/**
	 * Gets the authentication from different Social Networking sites. If user
	 * exists, the user is logged in. If user doesnot exists, Map account screen
	 * will come where user will decide whether user want to map the account or
	 * creates the new account with respective social networking ID.
	 * 
	 * @param request
	 *            the request
	 * @param service
	 *            the service object of ServiceProvider API
	 * @return the authentication
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/authSuccess", method = RequestMethod.GET)
	public ModelAndView getAuthentication(final HttpServletRequest request,
			ServiceProvider service) {
		if (logger.isDebugEnabled()) {
			logger.debug("Method NAME -----> getAuthentication()");
			logger.debug("### User Is Authenticated with Social Provider ####");
		}
		ModelAndView mv = new ModelAndView();
		List<Contact> contactsList = new ArrayList<Contact>();

		SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
		AuthProvider provider = manager.getCurrentAuthProvider();

		try {
			System.out.println(provider.getUserProfile().getEmail()
					+ provider.getProviderId());
			service = profile_service.getServiceProvider(provider
					.getUserProfile().getEmail(), provider.getProviderId());
			if (service != null) {
				mv.addObject("auth",
						profile_service.getPerson(service.getUserId()));
				mv.setViewName("/oauth/authSuccess");
			} else {
				mv.addObject("profile", provider.getUserProfile());
				mv.setViewName("/oauth/authMapping");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return mv;
	}

	/**
	 * If user click on map the user account then this method creates the
	 * account after successful redirection from different social networking
	 * sites. Social Networking ID is mapped userId for account creation.
	 * 
	 * @param request
	 *            the request
	 * @param profile
	 *            Contains the profile Object
	 * @param person
	 *            Contains the RavePerson object
	 * @param service
	 *            Contains the object of ServiceProvider API
	 * @return the redirect url
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value = "/authCreateAccount", method = RequestMethod.POST)
	public ModelAndView getRedirectURL(final HttpServletRequest request,
			final Profile profile, final RavePerson person,
			ServiceProvider service) throws Exception {
		ModelAndView mv = new ModelAndView();
		SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
		AuthProvider provider = manager.getCurrentAuthProvider();
		System.out.println("----" + provider.getUserProfile().getEmail());
		// request.getParameter("newUserName");
		if (logger.isDebugEnabled()) {
			logger.debug("Method NAME -----> getRedirectURL()");
		}
		User user = new UserImpl();
		user.setUsername(request.getParameter("newUserName"));
		user.setPassword(export_service.gen8DigitPwd());
		user.setEmail(request.getParameter("newUserName"));
		user.setDefaultPageLayoutCode("columns_2");
		user.setGivenName(provider.getUserProfile().getFirstName());
		user.setFamilyName(provider.getUserProfile().getFirstName());
		user.setDisplayName(provider.getUserProfile().getFirstName());
		user.setStatus("");
		user.setAboutMe(provider.getUserProfile().getHeadline());
		user.setMobileNumber("");
		// user.set

		profile.setUserId(request.getParameter("newUserName"));
		profile.setEmail(request.getParameter("newUserName"));
		profile.setCity(provider.getUserProfile().getLocation());
		profile.setName(provider.getUserProfile().getFirstName());
		profile.setLastName(provider.getUserProfile().getLastName());
		service.setProviderId(provider.getProviderId());
		service.setUserId(user.getUsername());
		service.setId(provider.getProviderId()
				+ provider.getUserProfile().getEmail());
		service.setProviderUserName(provider.getUserProfile().getEmail());
		profile_service.saveServiceProvider(service);
		try {
			newAccountService.createNewAccount(user);

			// profile_service.saveProfile(profile);
			//export_service.sendEmail(user.getEmail(), user.getPassword());

			if (logger.isDebugEnabled()) {
				logger.debug("userID  ---->" + user.getUsername());
			}
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("-----------user Exit");

			if (logger.isDebugEnabled()) {
				logger.debug("-----------user Exit");
			}

		}
		Map<String, String> property = new HashMap<String, String>();
		property.put("userId", user.getUsername());
		property.put("providerId", provider.getProviderId());
		property.put("providerEmailId", provider.getUserProfile().getEmail());
		activeMQ.producerCall("AccountMapped", property, "");
		// mv.addObject("profile", provider.getUserProfile());
		// mv.addObject("contacts", contactsList);
		mv.addObject("auth",
				profile_service.getPerson(request.getParameter("newUserName")));
		mv.setViewName("/oauth/authSuccess");

		return mv;
	}

	/**
	 * Maps the account. If user click on map the user account then this method
	 * maps an userId with Social Networking ID.
	 * 
	 * @param request
	 *            the request
	 * @param service
	 *            Contains the object of ServiceProvider API
	 * @param person
	 *            Contains the RavePerson object
	 * @return the redirection page in ModelAndView object
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value = "/mapAccount", method = RequestMethod.POST)
	public ModelAndView mapAccount(final HttpServletRequest request,
			final ServiceProvider service, RavePerson person) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Method NAME -----> mapAccount()");
		}
		ModelAndView mv = new ModelAndView();
		SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
		AuthProvider provider = manager.getCurrentAuthProvider();
		person = profile_service.getPerson(request
				.getParameter("mappedUserName"));

		if (person.getPlaintext_password().equals(
				request.getParameter("mappedPassword"))) {
			service.setUserId(person.getUsername());
			service.setProviderId(provider.getProviderId());
			service.setId(provider.getProviderId()
					+ provider.getUserProfile().getEmail());
			service.setProviderUserName(provider.getUserProfile().getEmail());
			profile_service.saveServiceProvider(service);
		}
		Map<String, String> property = new HashMap<String, String>();
		property.put("userId", person.getUsername());
		property.put("providerId", provider.getProviderId());
		property.put("providerEmailId", provider.getUserProfile().getEmail());
		activeMQ.producerCall("AccountMapped", property, "");
		mv.addObject("auth", person);

		mv.setViewName("/oauth/authSuccess");

		return mv;
	}

	/**
	 * This method imports the profile information from different social
	 * networking sites.
	 * 
	 * @param request
	 *            the request
	 * @param service
	 *            Contains the object of ServiceProvider API
	 * @param contacts
	 *            Contains the object of ImportedContacts
	 * @return the profile
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value = "/authProfile", method = RequestMethod.GET)
	public ModelAndView getProfile(final HttpServletRequest request,
			ServiceProvider service, ImportedContacts contacts)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Method NAME -----> getProfile()");
		}
		String userName = "";
		try {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			userName = auth.getName();

		} catch (Exception e) {
			userName = "";
		}
		ModelAndView mv = new ModelAndView();
		Map<String, String> property = new HashMap<String, String>();
		
		SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
		AuthProvider provider = manager.getCurrentAuthProvider(); /*
																 * provider is
																 * the object
																 * which
																 * contains all
																 * the data of
																 * social
																 * network of
																 * logged in
																 * user
																 */

		service.setProviderId(provider.getProviderId());
		service.setUserId(userName);
		service.setId(provider.getProviderId()
				+ provider.getUserProfile().getEmail());
		profile_service.saveServiceProvider(service);

		String userId = userName;

		export_service.slingExportProfile(provider);
		export_service.slingExportSummary(provider);
		export_service.slingExportInfo(provider);
		Map<String, Object> map;
		for (int i = 0; i < provider.getUserProfile().getCompany().size(); i++) {

			map = new HashMap<String, Object>();
			map = (Map<String, Object>) provider.getUserProfile().getCompany()
					.get(i);

			if (map.get("companyName") != null) {

				export_service.slingExportExperience(provider, i);
			}
		}
		for (int i = 0; i < provider.getUserProfile().getEducation().size(); i++) {

			map = new HashMap<String, Object>();
			map = (Map<String, Object>) provider.getUserProfile()
					.getEducation().get(i);

			if (map.get("degree") != null) {

				export_service.slingExportEducation(provider, i);
			}
		}
		
		property.put("userId", userName);
		property.put("providerId", provider.getProviderId());
		property.put("providerEmailId", provider.getUserProfile().getEmail());
		activeMQ.producerCall("ExtractProfile", property, "");
		
		// List<Contact> contactsList = new ArrayList<Contact>();
		// contactsList = provider.getContactList();
		/*
		 * if (contactsList != null && contactsList.size() > 0) { for (Contact p
		 * : contactsList) { contacts.setContactEmail(p.getEmail());
		 * contacts.setUserId(userName); if
		 * (!StringUtils.hasLength(p.getFirstName()) &&
		 * !StringUtils.hasLength(p.getLastName())) {
		 * contacts.setContactFirstname((p.getFirstName()));
		 * contacts.setContactLastName(p.getLastName()); }
		 * export_service.slingExportContacts
		 * (contacts,provider.getUserProfile().getProviderId()); } }
		 */
		// mv.addObject("profile", provider.getUserProfile());
		// mv.addObject("contacts", contactsList);
		/*
		 * if(logger.isDebugEnabled()){
		 * logger.debug("companyList--->"+provider.getUserProfile
		 * ().getCompany().toString()); } if(logger.isDebugEnabled()){
		 * logger.debug
		 * ("educationList--->"+provider.getUserProfile().getEducation
		 * ().toString()); } if(logger.isDebugEnabled()){
		 * logger.debug("contactList--->"+contactsList.toString()); }
		 */

		return new ModelAndView("redirect:/profile/getProfile.html?id="
				+ userId.replaceFirst("@", "_"));
	}

	/**
	 * Import the contacts from different Social Networking sites.
	 * 
	 * @param request
	 *            the request
	 * @param contacts
	 *            contains the object of ImportedContacts
	 * @return the contact
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/authContact", method = RequestMethod.GET)
	public ModelAndView getContact(final HttpServletRequest request,
			ImportedContacts contacts) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Method NAME -----> getContact()");
		}
		String userName = "";
		try {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			userName = auth.getName();

		} catch (Exception e) {
			userName = "";
		}
		
		List contactsList = new ArrayList();
		SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
		AuthProvider provider = manager.getCurrentAuthProvider();
		contactsList = provider.getContactList();
		export_service.slingExportContacts(contactsList.toString(),userName);
		/*
		 * ModelAndView mv = new ModelAndView();
		 * mv.addObject("profile", provider.getUserProfile());
		mv.addObject("contacts", contactsList);

		mv.setViewName("/jsp/authSuccess.jsp");
		return mv;*/
		return new ModelAndView("redirect:/profile/getProfile.html?id="
				+ userName.replaceFirst("@", "_"));
	}

	/**
	 * Redirect profile. This controller just redirect to profile page.
	 * 
	 * @param id
	 *            the id
	 * @param profile
	 *            the profile
	 * @return the model and view
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/redirectProfile", method = RequestMethod.GET)
	public ModelAndView redirectProfile(
			@RequestParam(value = "id", required = true) String id,
			Profile profile) {
		String userName = "";
		try {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			userName = auth.getName();

		} catch (Exception e) {
			userName = "";
		}
		String slingUserId = userName.replaceFirst("@", "_");
		Map<String, Object> model = new HashMap<String, Object>();
		if (id.equals(slingUserId)) {
			return new ModelAndView("redirect:/profile/getProfile.html?id="
					+ slingUserId);
		} else {
			try {
				// export_service.slingProfileVisit(slingUserId,id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ModelAndView("redirect:/profile/getProfile.html?id="
					+ id);
		}

	}

	/**
	 * Sends mail. This method is used to send mail with respective SMTP provider.
	 * This method is not in use now.
	 * 
	 * @param sendTo
	 *            the send to
	 * @param textMessage
	 *            the text message
	 * @param subject
	 *            the subject
	 */
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public void sendMail(
			@RequestParam(value = "sendTo", required = true) String sendTo,
			@RequestParam(value = "message", required = true) String textMessage,
			@RequestParam(value = "subject", required = true) String subject) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "10.36.76.3");

		Session session = Session.getDefaultInstance(props);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("verification@socialaware.in"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(sendTo));
			message.setSubject(subject);

			message.setContent(textMessage, "text/html");
			Transport.send(message);

			System.out.println("Done");
			if (logger.isDebugEnabled()) {
				logger.debug("Email Send to--->" + sendTo);
			}
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Gets the profile sling view.
	 * 
	 * @param id
	 *            Contains the userId of logged in user
	 * @return the profile sling view
	 */
	@RequestMapping(value = "/profile/getProfile", method = RequestMethod.GET)
	public ModelAndView getProfileSlingView(
			@RequestParam(value = "id", required = true) String id) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("profile", id);

		return new ModelAndView("profileView", model);

	}
	
	/*@RequestMapping(value = "/active/test", method = RequestMethod.GET)
	public ModelAndView rt(@RequestParam("adminUserId") String adminUser,
			@RequestParam("userId") String userId) {
		
		System.out.println("sdfsdf");
		activeMQ.accountCreationProducer(adminUser, userId, userId, adminUser, userId);
		ModelAndView p = new ModelAndView();
		return p;
	}*/

}
