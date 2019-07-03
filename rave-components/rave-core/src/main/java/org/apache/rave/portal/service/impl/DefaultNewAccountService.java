/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.rave.portal.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;
import org.apache.commons.lang.StringUtils;
import org.apache.rave.portal.model.User;
import org.apache.rave.portal.model.impl.UserImpl;
import org.apache.rave.portal.service.ActiveMQProducer;
import org.apache.rave.portal.service.AuthorityService;
import org.apache.rave.portal.service.CryptoService;
import org.apache.rave.portal.service.NewAccountService;
import org.apache.rave.portal.service.PageLayoutService;
import org.apache.rave.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ResourceBundle;

@Service
public class DefaultNewAccountService implements NewAccountService {

	private final UserService userService;
	private final PageLayoutService pageLayoutService;
	private final AuthorityService authorityService;
	private final CryptoService cryptoService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ActiveMQProducer activeMQ;

	private ResourceBundle bundle;

	@Autowired
	public DefaultNewAccountService(UserService userService,
			PageLayoutService pageLayoutService,
			AuthorityService authorityService, CryptoService cryptoService) {
		this.userService = userService;
		this.pageLayoutService = pageLayoutService;
		this.authorityService = authorityService;
		this.cryptoService = cryptoService;
	}

	@Override
	public void createNewAccount(User newUser) throws Exception {
		bundle = ResourceBundle.getBundle("jdbc");
		final String userName = newUser.getUsername();
		final String password = newUser.getPassword();
		final String defaultPageLayoutCode = newUser.getDefaultPageLayoutCode();
		final String email = newUser.getEmail();

		// capture optional fields
		final String givenName = newUser.getGivenName();
		final String familyName = newUser.getFamilyName();
		final String displayName = newUser.getDisplayName();
		final String status = newUser.getStatus();
		final String aboutMe = newUser.getAboutMe();
		String mobileNumber = "";
		mobileNumber = newUser.getMobileNumber();
		throwExceptionIfUserExists(userName, email);
		User user = new UserImpl();
		// set the required fields
		user.setUsername(userName);
		user.setEmail(email);
		// String hashedPassword = passwordEncoder.encode(password);
		user.setPassword(password);

		user.setExpired(false);
		user.setLocked(false);
		user.setEnabled(true);
		user.setDefaultPageLayout(pageLayoutService
				.getPageLayoutByCode(defaultPageLayoutCode));
		user.setAuthorities(authorityService.getDefaultAuthorities()
				.getResultSet());

		// set the optional fields
		user.setGivenName(givenName);
		user.setFamilyName(familyName);
		user.setDisplayName(givenName);
		user.setStatus(status);
		user.setMobileNumber(mobileNumber);

		user.setAboutMe(aboutMe);
		String encryptPassword = cryptoService.encrypt(password);
		user.setEncrypt(encryptPassword);
		if (bundle.getString("phone.creationFlag").equals("yes")) {
			String[] paramName2 = { "usrnm", "passwd" };
			String[] paramValue2 = { email, bundle.getString("phone.password") };
			String extension = callService(bundle.getString("phone.account"),
					paramName2, paramValue2);
			String[] splitString = extension.split(",");
			user.setExtensionNumber(splitString[1]);
		} else {
			user.setExtensionNumber("0000");
		}
		User managedUser = userService.registerNewUser(user);
		insert(managedUser);

		Map<String, String> propertyMap = new HashMap<String, String>();
		propertyMap.put("userId", userName);
		propertyMap.put("emailId", email);
		propertyMap.put("name", givenName);
		propertyMap.put("password", password);
		propertyMap.put("mobileNumber", mobileNumber);
		propertyMap.put("extension", managedUser.getExtensionNumber());
		propertyMap.put("entityId", managedUser.getId() + "");
		activeMQ.producerCall("UserCreationRave", propertyMap, "");

		// Below code is used to create profile in Sling and also creates
		// default pages in RAVE.
		// Now following code is moved in
		// ActiveMQConsumer(org.rave.context.ActiveMQConsumer).(7-December-2013)
		// Below code is uncommented now. (4-January-2014)

	
		String slingUrl = bundle.getString("sling.serverSpec")
				+ bundle.getString("sling.servletPostProfileURL");
		String[] slingParam = { "title", "email", "emailValue", "im", "imType",
				"name", "number", "numberType", "secondaryId", "status",
				"extension", "primaryEmail", "primaryMobile", "create" };
		String[] slingValue = { userName.replaceAll("@", "_"), email, "", "",
				"", givenName, mobileNumber, "Mobile",
				"u" + managedUser.getId(), "",
				managedUser.getExtensionNumber(), email, mobileNumber, "true" };
		callService(slingUrl, slingParam, slingValue);
		 
		
		if (bundle.getString("portal.defaultPageCreationFlag").equals("yes")) {
			String pageCreationUrl = bundle.getString("portal.serverSpec")
					+ bundle.getString("portal.defaultPageUrl");
			try {
				String[] pageParam = { "adminUserId", "userId" };
				String[] pageValue = {
						bundle.getString("portal.defaultPageCreationUser"),
						managedUser.getId() + "" };
				callService(pageCreationUrl, pageParam, pageValue);

			} catch (Exception e) {
			}
		}
		

	}

	private void throwExceptionIfUserExists(String userName, String email) {
		User existingUser = userService.getUserByUsername(userName);
		if (existingUser != null) {
			throw new IllegalArgumentException(
					"A user already exists for username " + userName);
		}

		// Implementors who use an alternative store for profile data probably
		// wont be including email when creating new
		// Rave accounts -- they will likely only be setting username to create
		// a stub entry in the database just so they
		// have something to tie the rest of the Rave entities to. All the other
		// profile data (like email) will be looked
		// up elsewhere.
		if (StringUtils.isNotEmpty(email)) {
			existingUser = userService.getUserByEmail(email);
			if (existingUser != null) {
				throw new IllegalArgumentException(
						"A user already exists for email " + email);
			}
		}
	}

	private Logger logger = Logger.getLogger(DefaultNewAccountService.class);
	private Hashtable<String, String> env = new Hashtable<String, String>();

	private boolean insert(User user) {
		try {
			bundle = ResourceBundle.getBundle("jdbc");
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL,
					"ldap://" + bundle.getString("ldap.host") + ":389");
			env.put(Context.SECURITY_PRINCIPAL, bundle.getString("ldap.rootdn"));
			env.put(Context.SECURITY_CREDENTIALS,
					bundle.getString("ldap.password"));
			DirContext dctx = new InitialDirContext(env);
			Attributes matchAttrs = new BasicAttributes(true);
			matchAttrs.put(new BasicAttribute("uid", user.getUsername()));
			matchAttrs.put(new BasicAttribute("cn", user.getDisplayName()));
			matchAttrs.put(new BasicAttribute("title", "u" + user.getId()));
			matchAttrs.put(new BasicAttribute("sn", user.getDisplayName()));
			matchAttrs.put(new BasicAttribute("mail", user.getEmail()));
			matchAttrs.put(new BasicAttribute("userpassword",
					encryptLdapPassword("SHA", user.getPassword())));
			matchAttrs.put(new BasicAttribute("objectclass", "top"));
			matchAttrs.put(new BasicAttribute("objectclass", "person"));
			matchAttrs.put(new BasicAttribute("objectclass",
					"organizationalPerson"));
			matchAttrs.put(new BasicAttribute("objectclass", "inetorgperson"));

			String name = "uid=" + user.getUsername() + ","
					+ bundle.getString("ldap.basedn");
			InitialDirContext iniDirContext = (InitialDirContext) dctx;
			iniDirContext.bind(name, dctx, matchAttrs);
			logger.debug("success inserting " + user.getUsername());
			return true;
		} catch (Exception e) {
			logger.error(e, e);
			return false;
		}
	}

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
					logger.error(e, e);
				}
			}
		}
		return sEncrypted;
	}

	private String callService(String urlStr, String[] paramName,
			String[] paramValue) {

		StringBuilder response = new StringBuilder();
		URL url;
		try {
			url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setAllowUserInteraction(false);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			// Create the form content
			OutputStream out = conn.getOutputStream();
			Writer writer = new OutputStreamWriter(out, "UTF-8");
			for (int i = 0; i < paramName.length; i++) {
				writer.write(paramName[i]);
				writer.write("=");
				writer.write(URLEncoder.encode(paramValue[i], "UTF-8"));
				writer.write("&");
			}
			writer.close();
			out.close();
			if (conn.getResponseCode() != 200) {
				// throw new IOException(conn.getResponseMessage());
			}
			// Buffer the result into a string
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			response = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
			}
			rd.close();

			conn.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return response.toString();
	}
}