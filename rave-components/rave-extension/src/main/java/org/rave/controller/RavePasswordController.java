package org.rave.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.rave.portal.service.CryptoService;
import org.rave.model.RavePerson;
import org.rave.service.ProfileExportService;
import org.rave.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class RavePasswordController.
 */
@Controller
@RequestMapping("/secure")
public class RavePasswordController {

	@Autowired
	private CryptoService cryptoService;

	@Autowired
	private ProfileExportService profileExportService;

	@Autowired
	private ProfileService profileService;

	/**
	 * Call change password Page.
	 * 
	 * @return the model and view
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public ModelAndView callChangePassword() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("response", "new");
		mv.setViewName("/userPassword");
		return mv;
	}

	/**
	 * Sets the new password. This authenticates whether the old password is
	 * correct or not. If correct then, password is changed with new password.
	 * 
	 * @param request
	 *            the request
	 * @param person
	 *            contains the object RavePerson
	 * @return the model and view
	 */
	@RequestMapping(value = "/setPassword", method = RequestMethod.POST)
	public ModelAndView setPassword(HttpServletRequest request,
			RavePerson person) {
		ModelAndView mv = new ModelAndView();
		String userName = "";
		try {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			userName = auth.getName();

		} catch (Exception e) {
			userName = "";
		}
		person = profileService.getPerson(userName);
		if (person.getPlaintext_password().equals(
				request.getParameter("currentPassword"))) {
			person.setPassword(request.getParameter("newPassword"));
			try {
				person.setEncrypt_password(cryptoService.encrypt(request
						.getParameter("newPassword")));
				profileService.changePassword(person);
				profileService.ldapEditPassword(userName,
						request.getParameter("newPassword"));
			} catch (Exception e) {
				mv.addObject("response", "error");
			}
			mv.addObject("response", "success");

		} else {
			mv.addObject("response", "error");
		}
		mv.setViewName("/userPassword");
		return mv;
	}

	/**
	 * Calls Forgot Password Page
	 * 
	 * @param request
	 *            the request
	 * @param person
	 *            contains the object RavePerson
	 * @return the model and view
	 */
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public ModelAndView sendPasswordPage(HttpServletRequest request,
			RavePerson person) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("response", "new");
		mv.setViewName("/forgotPassword");
		return mv;
	}

	/**
	 * Sends the password. This method will send a password on respective
	 * emailId through mail.
	 * 
	 * @param request
	 *            the request
	 * @param person
	 *            contains the object RavePerson
	 * @return the model and view
	 */
	@RequestMapping(value = "/sendPassword", method = RequestMethod.POST)
	public ModelAndView sendPassword(HttpServletRequest request,
			RavePerson person) {
		ModelAndView mv = new ModelAndView();
		try {
			person = profileService.getPerson(request.getParameter("emailId"));
			profileExportService.sendEmail(person.getEmail(),
					person.getPlaintext_password());
			mv.addObject("response", "success");
			mv.addObject("email", person.getEmail());
		} catch (Exception e) {
			mv.addObject("response", "error");
		}
		mv.setViewName("/forgotPassword");
		return mv;
	}
}
