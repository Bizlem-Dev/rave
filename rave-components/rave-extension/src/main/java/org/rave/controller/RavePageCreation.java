package org.rave.controller;

import org.rave.service.WidgetNewPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Class <class>RavePageCreation</class> is an API to create Default Page for new account from
 * respective Admin ID.
 * 
 * @author pranav.arya
 */
@Controller
@RequestMapping("/pageCreation")
public class RavePageCreation {

	@Autowired
	private WidgetNewPageService widget_service;

	/**
	 * Creates the default Page for new user.
	 * 
	 * @param adminUser
	 *            Contains the Admin userId
	 * @param userId
	 *            Contains the userId of new account
	 * @return the string
	 */
	@RequestMapping(value = "/createDefault", method = RequestMethod.POST)
	public @ResponseBody
	String createDefault(@RequestParam("adminUserId") String adminUser,
			@RequestParam("userId") String userId) {
		widget_service.setPages(userId, adminUser);

		return "done";
	}
	
	
}
