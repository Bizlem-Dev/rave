package org.rave.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.rave.model.Company;
import org.rave.model.Connection;
import org.rave.model.ImportedContacts;
import org.rave.model.RavePageUser;
import org.rave.model.RavePerson;
import org.rave.model.Profile;
import org.rave.model.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfileDaoImpl.
 */
@Repository("profileDao")
public class ProfileDaoImpl implements ProfileDao {
	
	
	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	/* (non-Javadoc)
	 * @see org.rave.dao.ProfileDao#saveProfile(org.rave.model.Profile)
	 */
	public Profile saveProfile(Profile profile)
	{
		
		 sessionFactory.getCurrentSession().saveOrUpdate(profile);
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see org.rave.dao.ProfileDao#editProfile(java.lang.String)
	 */
	public Profile editProfile(String id){
		
		Profile profile_dao=(Profile) sessionFactory.getCurrentSession().get(Profile.class,id);
		return profile_dao;
	}
	
	/* (non-Javadoc)
	 * @see org.rave.dao.ProfileDao#getPerson(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<RavePerson> getPerson(String id){

		//Person person=((List<Person>)) sessionFactory.getCurrentSession().getNamedQuery("getPerson").setString("username", id).list();
	
		return (List<RavePerson>) sessionFactory.getCurrentSession().createCriteria(RavePerson.class)
					.add(Restrictions.eq("username", id)).list();
	}
	
	/* (non-Javadoc)
	 * @see org.rave.dao.ProfileDao#saveDynamicAttribute(java.lang.String, java.lang.String)
	 */
	public void saveDynamicAttribute(String userId,String picture){
		
		   Query q = (Query) sessionFactory.getCurrentSession().createQuery("from Profile where userId = :userId ");
		   q.setParameter("userId", userId);
		   Profile profileUP = (Profile)q.list().get(0);	 
		   profileUP.setPicture(picture);
		   sessionFactory.getCurrentSession().update(profileUP);
		
	}
	
	/* (non-Javadoc)
	 * @see org.rave.dao.ProfileDao#getCountConnection(java.lang.String)
	 */
	public int getCountConnection(String userId){
		
	  return (Integer)	sessionFactory.getCurrentSession().createCriteria(Connection.class)
			  .add(Restrictions.like("status", "ACCEPTED")).add(Restrictions.eq("userId", userId)).list().size();
	}

	/* (non-Javadoc)
	 * @see org.rave.dao.ProfileDao#getCountRequest(java.lang.String)
	 */
	public int getCountRequest(String userId){
		
		return (Integer) sessionFactory.getCurrentSession().createCriteria(Connection.class)
				.add(Restrictions.like("status", "HOLD")).add(Restrictions.eq("followedId", userId)).list().size();
	}
	
	
	/* (non-Javadoc)
	 * @see org.rave.dao.ProfileDao#getUserName()
	 */
	@SuppressWarnings("unchecked")
	public List<Profile> getUserName(){

	
		return (List<Profile>) sessionFactory.getCurrentSession().createCriteria(Profile.class,"profile")
				.setProjection(Projections.projectionList().add(Projections.property("profile.userId"))).list();
	}
	
	/* (non-Javadoc)
	 * @see org.rave.dao.ProfileDao#saveCompanyProfile(org.rave.model.Company)
	 */
	public void saveCompanyProfile(Company company){
		sessionFactory.getCurrentSession().save(company);
	}
	
	/* (non-Javadoc)
	 * @see org.rave.dao.ProfileDao#getCompany(int)
	 */
	public Company getCompany(int id){
		
	Company company_dao=(Company) sessionFactory.getCurrentSession().get(Company.class,id);
		return company_dao;
	}
	
	/* (non-Javadoc)
	 * @see org.rave.dao.ProfileDao#savePerson(org.rave.model.RavePerson)
	 */
	public void savePerson(RavePerson person){
		String userId=person.getUsername();
		String password=person.getPlaintext_password();
		Query q = (Query) sessionFactory.getCurrentSession().createQuery("from RavePerson where username = :userId ");
		   q.setParameter("userId", userId);
		   RavePerson personUP = (RavePerson)q.list().get(0);	 
		   
		   personUP.setPlaintext_password(password);
		
		   sessionFactory.getCurrentSession().update(personUP);
	}
	
	/* (non-Javadoc)
	 * @see org.rave.dao.ProfileDao#changePassword(org.rave.model.RavePerson)
	 */
	public void changePassword(RavePerson person){
		String userId=person.getUsername();
		String password=person.getPassword();
		String plainText=person.getEncrypt_password();
		Query q = (Query) sessionFactory.getCurrentSession().createQuery("from RavePerson where username = :userId ");
		   q.setParameter("userId", userId);
		   RavePerson personUP = (RavePerson)q.list().get(0);	 
		   personUP.setPassword(password);
		   personUP.setEncrypt_password(plainText);
		  // personUP.setPlaintext_password(plainText);
		   sessionFactory.getCurrentSession().update(personUP);

	}
	
	/* (non-Javadoc)
	 * @see org.rave.dao.ProfileDao#saveServiceProvider(org.rave.model.ServiceProvider)
	 */
	public void saveServiceProvider(ServiceProvider provider)
	{
		
		 sessionFactory.getCurrentSession().saveOrUpdate(provider);
		
	}
	
	/* (non-Javadoc)
	 * @see org.rave.dao.ProfileDao#saveImportedContact(org.rave.model.ImportedContacts)
	 */
	public void saveImportedContact(ImportedContacts contact)
	{
		
		 sessionFactory.getCurrentSession().saveOrUpdate(contact);
		
	}
	
	/* (non-Javadoc)
	 * @see org.rave.dao.ProfileDao#getServiceProvider(java.lang.String, java.lang.String)
	 */
	public ServiceProvider getServiceProvider(String providerUserName,String providerId){
		
		return (ServiceProvider) sessionFactory.getCurrentSession().get(ServiceProvider.class,providerId+providerUserName);
	}
	

}
