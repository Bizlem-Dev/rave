package org.rave.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.rave.model.Education;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("educationDao")
public class EducationDaoImpl implements EducationDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Education> listEducation(String userId)
	{
		
		 return (List<Education>) sessionFactory.getCurrentSession().getNamedQuery("getEducation").setString("userId",userId).list();
	}
	
	public Education saveEducation(Education education){
		
	sessionFactory.getCurrentSession().saveOrUpdate(education);
	return null;
	}
	
	
	public Education editEducation(int id){
		
		Education education_dao=(Education) sessionFactory.getCurrentSession().get(Education.class,id);
		
		return education_dao;
	}
	
public Education removeEducation(int id){
		
		Education education_dao=(Education) sessionFactory.getCurrentSession().get(Education.class,id);
		sessionFactory.getCurrentSession().delete(education_dao);
		return null;
	}
}
