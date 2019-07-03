package org.rave.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.spring.bean.SocialAuthTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfileExportServiceImpl.
 */
@Service("SlingExportService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProfileExportServiceImpl implements ProfileExportService {

	/** The bundle. */
	private ResourceBundle bundle;

	/** The Constant logger. */
	private static final Logger logger = Logger
			.getLogger(ProfileExportServiceImpl.class);

	/** The social auth template. */
	@Autowired
	private SocialAuthTemplate socialAuthTemplate;

	/** The map. */
	Map<String, Object> map;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rave.service.ProfileExportService#slingExportProfile(org.brickred
	 * .socialauth.AuthProvider)
	 */
	public void slingExportProfile(AuthProvider provider) {
		String userName = "";

		try {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			userName = auth.getName();

		} catch (Exception e) {
			userName = "";
		}

		bundle = ResourceBundle.getBundle("jdbc");
		String url = bundle.getString("sling.serverSpec")
				+ bundle.getString("sling.servletPostProfileURL");
		String userId = userName.replaceAll("@", "_");
		ArrayList<String> paramKey = new ArrayList<String>();
		ArrayList<String> paramValue = new ArrayList<String>();
		try {

			paramKey.add("title");
			paramValue.add(userId);

			if (provider.getUserProfile().getAddress() != null) {
				paramKey.add("address");
				paramValue.add(provider.getUserProfile().getAddress());
			}
			// "&birthDay="+URLEncoder.encode(provider.getUserProfile().getDob().toString())+
			if (provider.getUserProfile().getLocation() != null) {
				paramKey.add("city");
				paramValue.add(provider.getUserProfile().getLocation());
			}
			if (provider.getUserProfile().getDob() != null) {
				paramKey.add("birthDay");
				paramValue.add(provider.getUserProfile().getDob().getYear()
						+ "-" + provider.getUserProfile().getDob().getMonth()
						+ "-" + provider.getUserProfile().getDob().getDay());
			}
			if (provider.getUserProfile().getProfileImageURL() != null) {
				paramKey.add("profileImage");
				paramValue.add(provider.getUserProfile().getProfileImageURL());
			}
			if (provider.getUserProfile().getEmail() != null) {
				paramKey.add("email");
				paramValue.add(provider.getUserProfile().getEmail());
				paramKey.add("emailValue");
				paramValue.add("");
			}

			if (provider.getUserProfile().getHeadline() != null) {

				paramKey.add("headline");
				paramValue.add(provider.getUserProfile().getHeadline());
			}

			if (provider.getUserProfile().getIndustry() != null) {
				paramKey.add("industry");
				paramValue.add(provider.getUserProfile().getIndustry());
			}
			if (provider.getUserProfile().getLastName() != null) {
				paramKey.add("lastName");
				paramValue.add(provider.getUserProfile().getLastName());
			}
			if (provider.getUserProfile().getFirstName() != null) {
				paramKey.add("name");
				paramValue.add(provider.getUserProfile().getFirstName());
			}
			paramKey.add("status");
			paramValue.add("");
			// if(provider.getUserProfile().getIndustry()!=null){
			if (provider.getUserProfile().getPhoneName() != null) {
				String[] phoneArray;

				phoneArray = provider.getUserProfile().getPhoneName()
						.split(",");
				for (String phone : phoneArray) {
					paramKey.add("number");
					paramValue.add(phone);
					paramKey.add("numberType");
					paramValue.add("");
				}

			} else {
				paramKey.add("number");
				paramValue.add("");
				paramKey.add("numberType");
				paramValue.add("");

			}

			if (provider.getUserProfile().getImAccountName() != null) {
				String[] imArray = provider.getUserProfile().getImAccountName()
						.split(",");
				String[] imTypeArray = provider.getUserProfile()
						.getImAccountType().split(",");
				for (int i = 0; i < imArray.length; i++) {

					paramKey.add("im");
					paramValue.add(imArray[i]);
					paramKey.add("imType");
					if (imTypeArray.length == imArray.length) {
						paramValue.add(imTypeArray[i]);
					} else {
						paramValue.add("");
					}
				}

			} else {
				paramKey.add("im");
				paramValue.add("");
				paramKey.add("imType");
				paramValue.add("");
			}

			callService(url, paramKey.toArray(new String[paramKey.size()]),
					paramValue.toArray(new String[paramKey.size()]));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug("content profile---->" + e);
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rave.service.ProfileExportService#slingExportEducation(org.brickred
	 * .socialauth.AuthProvider, int)
	 */
	@SuppressWarnings("unchecked")
	public void slingExportEducation(AuthProvider provider, int count) {
		String userName = "";
		try {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			userName = auth.getName();

		} catch (Exception e) {
			userName = "";
		}
		bundle = ResourceBundle.getBundle("jdbc");
		String url = bundle.getString("sling.serverSpec")
				+ bundle.getString("sling.servletPostEducationURL");
		String userId = userName.replaceAll("@", "_");
		ArrayList<String> paramKey = new ArrayList<String>();
		ArrayList<String> paramValue = new ArrayList<String>();
		try {

			paramKey.add("redirect");
			paramValue.add(userId);

			map = new HashMap<String, Object>();
			map = (Map<String, Object>) provider.getUserProfile()
					.getEducation().get(count);

			String degree = (String) map.get("degree");
			if (!degree.equals(null)) {
				paramKey.add("degree");
				paramValue.add(degree);
			}
			if (map.get("activities") != null) {
				paramKey.add("activity");
				paramValue.add((String) map.get("activities"));
			}
			// "&birthDay="+URLEncoder.encode(provider.getUserProfile().getDob().toString())+
			if (map.get("notes") != null) {
				paramKey.add("additionalNotes");
				paramValue.add((String) map.get("notes"));
			}
			if (map.get("endDate") != null) {
				paramKey.add("endDate");
				paramValue.add((String) map.get("endDate"));
			}
			if (map.get("schoolName") != null) {
				paramKey.add("school");
				paramValue.add((String) map.get("schoolName"));
			}

			paramKey.add("location");
			paramValue.add("");

			if (map.get("startDate") != null) {
				paramKey.add("toDate");
				paramValue.add((String) map.get("startDate"));
			}

			paramKey.add("title");
			paramValue.add(degree.replaceAll("\\s+", ""));

			paramKey.add("grade");
			paramValue.add("");
			callService(url, paramKey.toArray(new String[paramKey.size()]),
					paramValue.toArray(new String[paramKey.size()]));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug("content school---->" + e);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rave.service.ProfileExportService#slingExportSummary(org.brickred
	 * .socialauth.AuthProvider)
	 */
	public void slingExportSummary(AuthProvider provider) {
		String userName = "";
		try {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			userName = auth.getName();

		} catch (Exception e) {
			userName = "";
		}
		bundle = ResourceBundle.getBundle("jdbc");
		String url = bundle.getString("sling.serverSpec")
				+ bundle.getString("sling.servletPostSummaryURL");
		String userId = userName.replaceAll("@", "_");
		ArrayList<String> paramKey = new ArrayList<String>();
		ArrayList<String> paramValue = new ArrayList<String>();
		try {

			paramKey.add("redirect");
			paramValue.add(userId);

			if (provider.getUserProfile().getSummary() != null) {
				paramKey.add("goals");
				paramValue.add(provider.getUserProfile().getSummary());
			}
			if (provider.getUserProfile().getDisplayName() != null) {
				paramKey.add("specialities");
				paramValue.add(provider.getUserProfile().getDisplayName());
			}
			paramKey.add("title");
			paramValue.add("ProfessionalSummary");
			if (provider.getUserProfile().getSkill() != null) {
				String[] skillArray;
				String[] levelArray = null;
				skillArray = provider.getUserProfile().getSkill().split(",");
				for (String skill : skillArray) {
					paramKey.add("skill");
					paramValue.add(skill);
					paramKey.add("level");
					paramValue.add("");
				}
				paramValue.add(levelArray + "");
			}
			callService(url, paramKey.toArray(new String[paramKey.size()]),
					paramValue.toArray(new String[paramKey.size()]));

		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug("content summary---->" + e);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rave.service.ProfileExportService#slingExportExperience(org.brickred
	 * .socialauth.AuthProvider, int)
	 */
	@SuppressWarnings("unchecked")
	public void slingExportExperience(AuthProvider provider, int count) {

		String userName = "";
		try {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			userName = auth.getName();

		} catch (Exception e) {
			userName = "";
		}
		map = new HashMap<String, Object>();

		ArrayList<String> paramKey = new ArrayList<String>();
		ArrayList<String> paramValue = new ArrayList<String>();
		try {
			map = (Map<String, Object>) provider.getUserProfile().getCompany()
					.get(count);
			bundle = ResourceBundle.getBundle("jdbc");
			String url = bundle.getString("sling.serverSpec")
					+ bundle.getString("sling.servletPostExperienceURL");
			String userId = userName.replaceAll("@", "_");

			paramKey.add("redirect");
			paramValue.add(userId);

			String companyName = (String) map.get("companyName");
			String jobName = (String) map.get("jobTitle");
			if (!companyName.equals(null)) {
				paramKey.add("companyName");
				paramValue.add(companyName.replaceAll("\\s+", ""));
				paramKey.add("title");
				paramValue.add(companyName.replaceAll("\\s+", ""));
			}
			if (!jobName.equals(null)) {
				paramKey.add("jobTitle");
				paramValue.add(jobName.replaceAll("\\s+", ""));
			}
			if (map.containsKey("current")) {
				paramKey.add("currentlyWork");
				paramValue.add((String) map.get("current"));

			}

			if (map.containsKey("startDate")) {

				paramKey.add("startDate");
				paramValue.add((String) map.get("startDate"));
			}
			if (map.containsKey("endDate")) {

				paramKey.add("endDate");
				paramValue.add((String) map.get("endDate"));
			}
			if (map.containsKey("jobDesc")) {

				paramKey.add("jobDesc");
				paramValue.add((String) map.get("jobDesc"));
			}
			callService(url, paramKey.toArray(new String[paramKey.size()]),
					paramValue.toArray(new String[paramKey.size()]));

		} catch (Exception e) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rave.service.ProfileExportService#slingExportInfo(org.brickred.socialauth
	 * .AuthProvider)
	 */
	public void slingExportInfo(AuthProvider provider) {
		String userName = "";
		try {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			userName = auth.getName();

		} catch (Exception e) {
			userName = "";
		}
		bundle = ResourceBundle.getBundle("jdbc");
		String url = bundle.getString("sling.serverSpec")
				+ bundle.getString("sling.servletPostInfoURL");
		String userId = userName.replaceAll("@", "_");
		ArrayList<String> paramKey = new ArrayList<String>();
		ArrayList<String> paramValue = new ArrayList<String>();
		try {
			paramKey.add("redirect");
			paramKey.add("interests");
			paramKey.add("honorsAwards");
			paramKey.add("websites");
			paramKey.add("webTypes");

			paramValue.add(userId);
			paramValue.add(provider.getUserProfile().getInterests());
			paramValue.add(provider.getUserProfile().getHonors());
			paramValue.add("");
			paramValue.add("");

			callService(url, paramKey.toArray(new String[paramKey.size()]),
					paramValue.toArray(new String[paramKey.size()]));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * public void slingProfileVisit(String userName,String id) throws
	 * IOException{
	 * 
	 * bundle=ResourceBundle.getBundle("jdbc");
	 * 
	 * URL url; // URLConnection urlConn; HttpURLConnection urlConn;
	 * DataOutputStream printout; DataInputStream input; // URL of CGI-Bin
	 * script. url = new URL (bundle.getString("sling.serverSpec")+
	 * bundle.getString("sling.servletProfileVisit")); // URL connection
	 * channel. urlConn = (HttpURLConnection) url.openConnection();
	 * urlConn.setRequestMethod("POST"); // Let the run-time system (RTS) know
	 * that we want input. urlConn.setDoInput (true); // Let the RTS know that
	 * we want to do output. urlConn.setDoOutput (true); // No caching, we want
	 * the real thing. urlConn.setUseCaches (false); // Specify the content
	 * type. urlConn.setRequestProperty ("Content-Type",
	 * "application/x-www-form-urlencoded"); // Send POST output.
	 * 
	 * printout = new DataOutputStream (urlConn.getOutputStream ());
	 * 
	 * String content =""; try { content ="userName="+URLEncoder.encode(id);
	 * content +="&visitor="+URLEncoder.encode(userName);
	 * 
	 * 
	 * 
	 * if(logger.isDebugEnabled()){ logger.debug("content info---->"+content); }
	 * 
	 * System.out.println("content info---->"+content); } catch (Exception e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * 
	 * printout.writeBytes (content); printout.flush (); printout.close (); //
	 * Get response data. input = new DataInputStream (urlConn.getInputStream
	 * ());
	 * 
	 * 
	 * input.close ();
	 * 
	 * }
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rave.service.ProfileExportService#slingExportContacts(org.rave.model
	 * .ImportedContacts, java.lang.String)
	 */
	public void slingExportContacts(String contactList, String userId) {

		bundle = ResourceBundle.getBundle("jdbc");
		String url = bundle.getString("sling.serverSpec")
				+ bundle.getString("sling.servletContactImport");
		String[] paramKey = { "userId", "contactList"};
		String[] paramValue = {userId.replaceAll("@", "_"),
				contactList };
		callService(url, paramKey, paramValue);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rave.service.ProfileExportService#callService(java.lang.String,
	 * java.lang.String[], java.lang.String[])
	 */
	public String callService(String urlStr, String[] paramName,
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

	/*
	 * public void sendEmail(String userName, String password) { bundle =
	 * ResourceBundle.getBundle("jdbc"); Properties props = new Properties();
	 * String mailType = bundle.getString("mail.type"); String mailUser="";
	 * String mailPass="";
	 * System.out.println("-->"+mailType+"**"+bundle.getString
	 * ("mail.host")+"##"+bundle.getString("mail.username")); if
	 * (mailType.equals("gmail")) { props.put("mail.smtp.host",
	 * bundle.getString("mail.host")); props.put("mail.smtp.socketFactory.port",
	 * "465"); props.put("mail.smtp.socketFactory.class",
	 * "javax.net.ssl.SSLSocketFactory"); props.put("mail.smtp.auth", "true");
	 * props.put("mail.smtp.port", "465"); mailUser=
	 * bundle.getString("mail.username"); mailPass=
	 * bundle.getString("mail.password");
	 * 
	 * } else { props.put("mail.smtp.host", bundle.getString("mail.host"));
	 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.port", "25");
	 * mailUser= bundle.getString("mail.username"); mailPass=
	 * bundle.getString("mail.password"); System.out.println("````Other"); }
	 * final String mailUserName=mailUser; final String mailPassword=mailPass;
	 * Session session = Session.getDefaultInstance(props, new
	 * javax.mail.Authenticator() { protected PasswordAuthentication
	 * getPasswordAuthentication() { return new
	 * PasswordAuthentication(mailUserName, mailPassword); } }); try {
	 * 
	 * Message message = new MimeMessage(session); message.setFrom(new
	 * InternetAddress("portal@socialaware.in"));
	 * message.setRecipients(Message.RecipientType.TO,
	 * InternetAddress.parse(userName));
	 * message.setSubject("Username & password");
	 * message.setText("Your UserName is: " + userName +
	 * "\n\n Your Password is :" + password);
	 * 
	 * Transport.send(message);
	 * 
	 * System.out.println("Done");
	 * 
	 * } catch (MessagingException e) {
	 * System.out.println("@@@-"+e.getMessage()); e.printStackTrace(); throw new
	 * RuntimeException(e); } }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rave.service.ProfileExportService#sendEmail(java.lang.String,
	 * java.lang.String)
	 */
	public void sendEmail(String userName, String password) {
		bundle = ResourceBundle.getBundle("jdbc");
		String message = bundle.getString("sendMail.body")
				.replace("{0}", userName).replace("{1}", password);
		String url = bundle.getString("sendMail.address");
		String[] paramName = { "emailto[]", "emailfrom[]", "emailsub[]",
				"emailmsg[]" };
		String[] paramValue = { userName, bundle.getString("sendMail.from"),
				bundle.getString("sendMail.subject"), message };
		callService(url, paramName, paramValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rave.service.ProfileExportService#gen8DigitPwd()
	 */
	public String gen8DigitPwd() {
		String strPassword = "";
		try {
			Random randomPassword = new Random();
			int i = randomPassword.nextInt(10);
			int j = randomPassword.nextInt(100);
			int k = randomPassword.nextInt(100);
			int l = randomPassword.nextInt(10);
			int m = randomPassword.nextInt(10);
			strPassword = i + "T" + j + k + "P" + l + m;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strPassword;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		ProfileExportServiceImpl a = new ProfileExportServiceImpl();
		a.sendEmail("pranav.arya@visioninfocon.com", "dfg");
	}
}
