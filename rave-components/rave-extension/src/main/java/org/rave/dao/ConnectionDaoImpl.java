package org.rave.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.rave.model.Connection;
import org.rave.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("connectionDao")
public class ConnectionDaoImpl implements ConnectionDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Connection> listAcceptedConnection(String userId)
	{
		//return (List<Connection>) sessionFactory.getCurrentSession().createCriteria(Connection.class).add(Restrictions.like("status", "ACCEPTED")).add(Restrictions.eq("userId", userId)).list();
		
		return (List<Connection>) sessionFactory.getCurrentSession().createCriteria(Connection.class,"conn")
				.createCriteria("profileFollowedJoin", "profile")
				.add(Restrictions.like("conn.status", "ACCEPTED"))
				.add(Restrictions.eq("conn.userId", userId))
				.setProjection(Projections.projectionList()
						.add(Projections.property("profile.userId"),"userId")
						.add(Projections.property("profile.name"),"name")
						.add(Projections.property("profile.lastName"),"lastName")
						.add(Projections.property("profile.headline"),"headline")
						.add(Projections.property("profile.picture"),"picture")
						.add(Projections.property("conn.followedId"),"followedId")
						/*.add(Projections.property("conn.recommendForTitle"),"recommendForTitle")
						.add(Projections.property("conn.recommendByTitle"),"recommendByTitle")
						.add(Projections.property("conn.status"),"status")
						.add(Projections.property("conn.hide"),"hide")
						.add(Projections.property("recom.fk_key_by_exp"),"fk_key_by_exp")
						.add(Projections.property("recom.fk_key_for_exp"),"fk_key_for_exp")
						.add(Projections.property("recom.recommendType"),"recommendType")*/
						).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

		
	}
	
	public Profile friendView(String id){
		
		Profile connection_dao=(Profile) sessionFactory.getCurrentSession().get(Profile.class,id);
		return connection_dao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Profile> getPersonList(String keyword){
		
		return (List<Profile>) sessionFactory.getCurrentSession().createCriteria(Profile.class).add(Restrictions.like("name", "%"+keyword+"%")).list();
	}
	
	public void sendRequest(Connection connection){
		
		sessionFactory.getCurrentSession().saveOrUpdate(connection);
	}
	
	@SuppressWarnings("unchecked")
	public List<Connection> listAllConnection(String userId)
	{
		
		return (List<Connection>) sessionFactory.getCurrentSession().getNamedQuery("getSelectedConnection").setString("userId",userId).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Connection> showRequest(String userId)
	{
		
		//return (List<Connection>) sessionFactory.getCurrentSession().getNamedQuery("showRequest").setString("userId",userId).list();
		
		//return (List<Connection>) sessionFactory.getCurrentSession().createQuery("from Connection c left outer join c.profileJoin where c.status='HOLD'").list();
		
	 return (List<Connection>) sessionFactory.getCurrentSession().createCriteria(Connection.class).add(Restrictions.like("status", "HOLD")).add(Restrictions.eq("followedId", userId)).list();
	
	/* return (List<Connection>) sessionFactory.getCurrentSession().createCriteria(Connection.class,"con")
			.add(Restrictions.like("con.status", "HOLD")).add(Restrictions.eq("con.followedId", userId))
			.setProjection(Projections.projectionList().add(Projections.property("con.userId"))).list(); */
	}
	

	public void acceptRequest(Connection connection)
	{
		
		// sessionFactory.getCurrentSession().getNamedQuery("showRequest").setString("userId",userId).list();
		
		sessionFactory.getCurrentSession().saveOrUpdate(connection);
	}
	
	
	public void removeConnection(Connection connection){
		
		sessionFactory.getCurrentSession().delete(connection);
	}

}
