package org.rave.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.rave.model.RavePerson;
import org.rave.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

// TODO: Auto-generated Javadoc
/**
 * </class>CasFilterController</class> is a controller which runs just after the
 * login. It contains the API login of CAS and generate different sessions.
 */
@Controller
@RequestMapping("/loginRedirect")
public class CasFilterController {

	@Autowired
	private ProfileService profile_service;

	private ResourceBundle bundle;

	/**
	 * Gets the information for loggedIn user. This method is run just after the
	 * login. This method will login into CAS through API. This method also
	 * gathers information for loggedIn user i.e. which services are provisioned
	 * to him/her and then those values are set in session.
	 * 
	 * @param request
	 *            the request
	 * @param person
	 *            the person
	 * @return the info
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getInfo(HttpServletRequest request, RavePerson person)
			throws Exception {
		bundle = ResourceBundle.getBundle("jdbc");
		String userName = "";
		try {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			userName = auth.getName();

		} catch (Exception e) {
			userName = "";
		}
		person = profile_service.getPerson(userName);
		Cookie cookies[] = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie c = cookies[i];
			if (c.getName().equals("visioninfo") && !c.getValue().equals("/")) {
				try {
					System.out.println("******" + c.getValue() + "******");
					String formatURL = c.getValue().replaceAll("%3A", ":");
					URL url = new URL(formatURL);
					System.out.println(url.getHost());
					if (url.getHost().equals(
							bundle.getString("offbiz.serverSpec"))) {
						String[] offbizKey = { "emailProductStoreId",
								"USER_TITLE", "USER_FIRST_NAME",
								"USER_LAST_NAME", "CUSTOMER_ADDRESS1",
								"CUSTOMER_CITY", "CUSTOMER_POSTAL_CODE",
								"CUSTOMER_COUNTRY", "CUSTOMER_STATE",
								"CUSTOMER_MOBILE_CONTACT", "CUSTOMER_EMAIL",
								"USERNAME", "PASSWORD", "CONFIRM_PASSWORD" };
						String[] offbizValue = { "9000", "Mr.",
								person.getGiven_name(), person.getGiven_name(),
								"Delhi", "Delhi", "000000", "IN", "IN-AN",
								"9999999999", person.getUsername(),
								person.getUsername(),
								person.getPlaintext_password(),
								person.getPlaintext_password() };
						httpPost(bundle.getString("offbiz.createUser"),
								offbizKey, offbizValue);
					}
				} catch (MalformedURLException e) {

					e.printStackTrace();
				}
			}

		}

		/*
		 * String[] serviceCheckKey = {"param", "userId"}; String[]
		 * serviceCheckValue = { "ox,wc,mm,log", userName }; String serviceCheck
		 * = httpPost( bundle.getString("sling.getServiceParam"),
		 * serviceCheckKey, serviceCheckValue);
		 */
		String[] setLoginKey = { "param", "userId" };
		String[] setLoginValue = {
				bundle.getString("sling.getActivatedServiceParam"),
				userName.replace("@", "_") };
		String serviceCheck = httpPost(bundle.getString("sling.serverSpec")
				+ bundle.getString("sling.setLoginStatus"), setLoginKey,
				setLoginValue);
		Map<String, Object> model_info = new HashMap<String, Object>();
		String server = "http://" + bundle.getString("cas.serverHost") + ":"
				+ bundle.getString("cas.serverPort") + "/"
				+ bundle.getString("cas.serverName") + "/v1/tickets";
		String username = person.getUsername();
		String password = person.getPlaintext_password();
		String service = bundle.getString("sling.serverSpec");
		String loginTicket = getTicket(server, username, password, service);
		model_info.put("key", loginTicket);
		model_info.put("serviceCheck", serviceCheck);
		return new ModelAndView("loginRedirect", model_info);
	}

	/**
	 * Gets the ticket.
	 * 
	 * @param server
	 *            the server
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @param service
	 *            the service
	 * @return the ticket
	 */
	public static String getTicket(final String server, final String username,
			final String password, final String service) {
		notNull(server, "server must not be null");
		notNull(username, "username must not be null");
		notNull(password, "password must not be null");
		notNull(service, "service must not be null");

		return getServiceTicket(server,
				getTicketGrantingTicket(server, username, password), service);
	}

	/**
	 * Gets the service ticket.
	 * 
	 * @param server
	 *            the server
	 * @param ticketGrantingTicket
	 *            the ticket granting ticket
	 * @param service
	 *            the service
	 * @return the service ticket
	 */
	@SuppressWarnings("unused")
	private static String getServiceTicket(final String server,
			final String ticketGrantingTicket, final String service) {
		if (ticketGrantingTicket == null)
			return null;

		final HttpClient client = new HttpClient();

		final PostMethod post = new PostMethod(server + "/"
				+ ticketGrantingTicket);

		post.setRequestBody(new NameValuePair[] { new NameValuePair("service",
				service) });

		try {
			client.executeMethod(post);

			final String response = post.getResponseBodyAsString();

			switch (post.getStatusCode()) {
			case 200:
				return ticketGrantingTicket;

			default:

				break;
			}
		}

		catch (final Exception e) {

		}

		finally {
			post.releaseConnection();
		}

		return null;
	}

	/**
	 * Gets the ticket granting ticket.
	 * 
	 * @param server
	 *            the server
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @return the ticket granting ticket
	 */
	private static String getTicketGrantingTicket(final String server,
			final String username, final String password) {
		final HttpClient client = new HttpClient();

		final PostMethod post = new PostMethod(server);

		post.setRequestBody(new NameValuePair[] {
				new NameValuePair("username", username),
				new NameValuePair("password", password) });

		try {
			client.executeMethod(post);

			final String response = post.getResponseBodyAsString();

			switch (post.getStatusCode()) {
			case 201: {
				final Matcher matcher = Pattern.compile(
						".*action=\".*/(.*?)\".*").matcher(response);

				if (matcher.matches())
					return matcher.group(1);

				break;
			}

			default:
				break;
			}
		}

		catch (final Exception e) {

		}

		finally {
			post.releaseConnection();
		}

		return null;
	}

	/**
	 * Https post.
	 * 
	 * @param urlStr
	 *            the url str
	 * @param paramName
	 *            the param name
	 * @param paramVal
	 *            the param val
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public static String httpsPost(String urlStr, String[] paramName,
			String[] paramVal) throws Exception {
		URL url = new URL(urlStr);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setHostnameVerifier(new HostnameVerifier() {

			@Override
			public boolean verify(String hostname, SSLSession session) {
				// TODO Auto-generated method stub
				return true;
			}

		});
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
			writer.write(URLEncoder.encode(paramVal[i], "UTF-8"));
			writer.write("&");

		}
		writer.close();
		out.close();

		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}

		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();

		conn.disconnect();

		return sb.toString();
	}

	/**
	 * Http post.
	 * 
	 * @param urlStr
	 *            the url str
	 * @param paramName
	 *            the param name
	 * @param paramVal
	 *            the param val
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	private String httpPost(String urlStr, String[] paramName, String[] paramVal)
			throws Exception {
		URL url = new URL(urlStr);
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
			writer.write(URLEncoder.encode(paramVal[i], "UTF-8"));
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
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();

		conn.disconnect();

		return sb.toString();
	}

	/**
	 * Not null.
	 * 
	 * @param object
	 *            the object
	 * @param message
	 *            the message
	 */
	private static void notNull(final Object object, final String message) {
		if (object == null)
			throw new IllegalArgumentException(message);
	}

}
