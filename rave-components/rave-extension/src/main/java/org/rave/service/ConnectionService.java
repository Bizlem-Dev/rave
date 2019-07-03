package org.rave.service;

import java.util.List;

import org.rave.model.Connection;
import org.rave.model.Profile;

public interface ConnectionService {
	public List<Connection> listAcceptedConnection(String userId);
	public Profile friendView(String id);
	public List<Profile> getPersonList(String keyword);
	public void sendRequest(Connection connection);
	public List<Connection> listAllConnection(String userId);
	public List<Connection> showRequest(String userId);
	public void acceptRequest(Connection connection);
	public void removeConnection(Connection connection);
}
