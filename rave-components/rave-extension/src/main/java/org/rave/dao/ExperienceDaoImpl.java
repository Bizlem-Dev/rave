package org.rave.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import org.rave.model.Experience;
import org.rave.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("experienceDao")
public class ExperienceDaoImpl implements ExperienceDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Experience> listExperience(String userId,String referringPageId)
	{
		System.out.println("Doa layer UserId:"+userId);
		/*List<Experience> experience_list = (List<Experience>) sessionFactory.getCurrentSession().getNamedQuery("asd").setString("userId",userId).list();*/
		return (List<Experience>) sessionFactory.getCurrentSession().getNamedQuery("getExperience").setString("userId",userId).list();
	}
	
	public Experience saveExperience(Experience experience)
	{
		System.out.println(experience.getPkExperienceId());
		sessionFactory.getCurrentSession().saveOrUpdate(experience);
		
		return null;
	}
	
	
	public Experience editExperience(int id){
		
		
		Experience experience_dao=(Experience) sessionFactory.getCurrentSession().get(Experience.class,id);
		
		return experience_dao;
	}
	

	public Experience removeExperience(int id){
		
		System.out.println("removeExperience");
		Experience experience_dao=(Experience) sessionFactory.getCurrentSession().get(Experience.class,id);
		
		sessionFactory.getCurrentSession().delete(experience_dao);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Recommendation> listRecommendation(String userId)
	{
		System.out.println("Doa layer UserId:"+userId);
		/*List<Experience> experience_list = (List<Experience>) sessionFactory.getCurrentSession().getNamedQuery("asd").setString("userId",userId).list();*/
		return (List<Recommendation>) sessionFactory.getCurrentSession().createCriteria(Recommendation.class,"recom")
				.createCriteria("profileRecommendByJoin", "profile")
				.add(Restrictions.eq("recom.recommendFor", userId))
				.setProjection(Projections.projectionList()
						.add(Projections.property("profile.userId"),"userId")
						.add(Projections.property("profile.name"),"name")
						.add(Projections.property("profile.lastName"),"lastName")
						.add(Projections.property("recom.recommendBy"),"recommendBy")
						.add(Projections.property("recom.desc"),"description")
						.add(Projections.property("recom.recommendByTitle"),"recommendByTitle")
						.add(Projections.property("recom.status"),"status")
						.add(Projections.property("recom.hide"),"hide")
						.add(Projections.property("recom.fk_key_by_exp"),"fk_key_by_exp")
						.add(Projections.property("recom.fk_key_for_exp"),"fk_key_for_exp")
						.add(Projections.property("recom.recommendType"),"recommendType")
						).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
						.list();
		
	}
}
