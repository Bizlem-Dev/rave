package org.rave.context;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSubscriber;
import javax.servlet.ServletContextEvent;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.web.context.ContextLoaderListener;

public class ActiveMQConsumer extends ContextLoaderListener {

	private ResourceBundle bundle = ResourceBundle.getBundle("jdbc");

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		accountcreationConsumer();
	}

	public void accountcreationConsumer() {
		try {
			TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					"tcp://" + bundle.getString("activeMQ.serverIP") + ":"
							+ bundle.getString("activeMQ.serverPort"));
			Connection connection = connectionFactory.createConnection();
			connection.setClientID(bundle.getString("activeMQ.clientId"));
			connection.start();

			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			Topic topic = session.createTopic("UserCreationRave");
			TopicSubscriber durableSubscriber = session
					.createDurableSubscriber(topic, "Test_Durable_Subscriber");
			MessageListener listner = new MessageListener() {
				public void onMessage(Message message) {
					try {

						if (message instanceof TextMessage) {
							TextMessage textMessage = (TextMessage) message;
							String entityId = textMessage.getStringProperty("entityId");	
							String userName = textMessage.getStringProperty("userId");
							String email = textMessage.getStringProperty("emailId");
							String givenName = textMessage.getStringProperty("name");
							String mobileNumber = textMessage.getStringProperty("mobileNumber");
							String extension = textMessage.getStringProperty("extension"); 
							createDeafultPages(entityId);
							createAccountInSling(userName, email, givenName, mobileNumber, extension, entityId);					
						}
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			};

			durableSubscriber.setMessageListener(listner);

		} catch (Exception e) {
			e.printStackTrace();
		}
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

	private void createDeafultPages(String userId) {
		if (bundle.getString("portal.defaultPageCreationFlag").equals("yes")) {
			String pageCreationUrl = bundle.getString("portal.serverSpec")
					+ bundle.getString("portal.defaultPageUrl");
			try {
				String[] pageParam = { "adminUserId", "userId" };
				String[] pageValue = {
						bundle.getString("portal.defaultPageCreationUser"),
						userId + "" };
				callService(pageCreationUrl, pageParam, pageValue);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void createAccountInSling(String userName, String email,
			String givenName, String mobileNumber, String extension,
			String entityId) {
		try {
			String slingUrl = bundle.getString("sling.serverSpec")
					+ bundle.getString("sling.servletPostProfileURL");
			String[] slingParam = { "title", "email", "emailValue", "im",
					"imType", "name", "number", "numberType", "secondaryId",
					"status", "extension", "primaryEmail", "primaryMobile" };
			String[] slingValue = { userName.replaceAll("@", "_"), email, "",
					"", "", givenName, mobileNumber, "Mobile", "u" + entityId,
					"", extension, email, mobileNumber };
			callService(slingUrl, slingParam, slingValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
