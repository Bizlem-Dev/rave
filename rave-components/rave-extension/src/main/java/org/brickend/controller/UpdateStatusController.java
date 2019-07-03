package org.brickend.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.rave.portal.model.JpaUser;
import org.apache.rave.portal.model.User;
import org.apache.rave.portal.model.impl.UserImpl;
import org.apache.rave.portal.service.NewAccountService;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.exception.SocialAuthException;
import org.brickred.socialauth.spring.bean.SocialAuthTemplate;
import org.rave.service.ProfileExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * <class>UpdateStatusController</class> is used as a controller for multiple
 * purpose i.e. for getting the online list of user, to check whether the
 * respective user is online or not, for profile creation.
 * It is REST API in spring
 * 
 * @author pranav.arya
 */
@Controller
public class UpdateStatusController {

	@Autowired
	private SocialAuthTemplate socialAuthTemplate;

	@Autowired
	private SessionRegistryImpl sessionRegistry;

	@Autowired
	private NewAccountService newAccountService;

	@Autowired
	private ProfileExportService export_service;

	/**
	 * Gets the redirect url. Not is use Now
	 * 
	 * @param request
	 *            the request
	 * @return the redirect url
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public ModelAndView getRedirectURL(final HttpServletRequest request)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
		AuthProvider provider = manager.getCurrentAuthProvider();
		String statusMsg = request.getParameter("statusMessage");
		try {
			provider.updateStatus(statusMsg);
			mv.addObject("Message", "Status Updated successfully");
		} catch (SocialAuthException e) {
			mv.addObject("Message", e.getMessage());
			e.printStackTrace();
		}
		mv.setViewName("/jsp/statusSuccess.jsp");

		return mv;
	}

	/**
	 * Gets the online users. Return the list of online users in string
	 * 
	 * @param ownerId
	 *            contains the userId of loggedIn user
	 * @return the online users as String
	 */
	@RequestMapping(value = "/read/session/{ownerId}", method = RequestMethod.POST)
	public @ResponseBody
	String getOnlineUsers(@PathVariable("ownerId") String ownerId) {
		List<Object> userSessions = sessionRegistry.getAllPrincipals();

		String userName = "";
		ArrayList<String> users = new ArrayList<String>();
		for (Object principal : userSessions) {
			final JpaUser loggedUser = (JpaUser) principal;
			userName = loggedUser.getUsername();
			users.add(userName.replace("@", "_"));
		}

		if (users.contains(ownerId)) {
			System.out.println(users.toString());
			return users.toString();
		}
		return "invalid";
	}

	/**
	 * Checks whether the required user is online or not.
	 * 
	 * @param ownerId
	 *            contains the userId
	 * @return the flag i.e. online or offline
	 */
	@RequestMapping(value = "/read/singleSession/{ownerId}", method = RequestMethod.POST)
	public @ResponseBody
	String getOnlineUser(@PathVariable("ownerId") String ownerId) {
		List<Object> userSessions = sessionRegistry.getAllPrincipals();
		String userName = "";
		for (Object principal : userSessions) {
			final JpaUser loggedUser = (JpaUser) principal;
			userName = loggedUser.getUsername();
			if (userName.replace("@", "_").equals(ownerId)) {
				return "online";
			}
		}
		return "offline";
	}

	/**
	 * Profile Creation API
	 * 
	 * @param request
	 *            the request
	 * @return the flag i.e. success or fail
	 */
	@RequestMapping(value = "/create/profile/service", method = RequestMethod.POST)
	public @ResponseBody
	String profileCreationApi(final HttpServletRequest request) {
		User user = new UserImpl();
		user.setUsername(request.getParameter("username"));
		user.setPassword(export_service.gen8DigitPwd());
		user.setEmail(request.getParameter("username"));
		user.setDefaultPageLayoutCode("columns_2");
		user.setGivenName(request.getParameter("name"));
		user.setFamilyName(request.getParameter("name"));
		user.setDisplayName(request.getParameter("name"));
		user.setStatus("");
		user.setAboutMe("");
		user.setMobileNumber("");
		try {
			newAccountService.createNewAccount(user);
			export_service.sendEmail(user.getEmail(), user.getPassword());
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}

}
