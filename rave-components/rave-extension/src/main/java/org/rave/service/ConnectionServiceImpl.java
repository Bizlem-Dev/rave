package org.rave.service;

import java.util.List;
import org.rave.dao.ConnectionDao;
import org.rave.model.Connection;
import org.rave.model.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("connectionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ConnectionServiceImpl implements ConnectionService{
	
	
	@Autowired
	private ConnectionDao connection_dao;

	public List<Connection> listAcceptedConnection(String userId)
	{
		
		 return connection_dao.listAcceptedConnection(userId);
	}
	
	public Profile friendView(String id){
		
		
		return connection_dao.friendView(id);
	}
	
	public List<Profile> getPersonList(String keyword)
	{
		
		 return connection_dao.getPersonList(keyword);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void sendRequest(Connection connection){
		
		 connection_dao.sendRequest(connection);
	}
	public List<Connection> listAllConnection(String userId)
	{
		
		 return connection_dao.listAllConnection(userId);
	}
	public List<Connection> showRequest(String userId)
	{
		
		 return connection_dao.showRequest(userId);
	}
	public void acceptRequest(Connection connection)
	{
		
		  connection_dao.acceptRequest(connection);
	}
	
	
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void removeConnection(Connection connection)
	{
		connection_dao.removeConnection(connection);
		
	}
}
