package org.rave.dao;


import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.rave.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("recommendationDao")
public class RecommendationDaoImpl implements RecommendationDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveRequestRecommend(Recommendation recommendation){
		
		sessionFactory.getCurrentSession().saveOrUpdate(recommendation);
	
	}
	
	@SuppressWarnings("unchecked")
	public List<Recommendation> showRequest(String userId){
		
		/*return (List<Recommendation>) sessionFactory.getCurrentSession().createCriteria(Recommendation.class)
				.add(Restrictions.eq("recommendBy", userId))
				.add(Restrictions.eq("status", "PENDING")).list();
		*/
		
		return (List<Recommendation>) sessionFactory.getCurrentSession().createCriteria(Recommendation.class,"recom")
				.createCriteria("profileRecommendJoin", "profile")
				.add(Restrictions.eq("recom.recommendBy", userId))
				.add(Restrictions.eq("recom.status", "PENDING"))
				.setProjection(Projections.projectionList()
						.add(Projections.property("profile.userId"),"userId")
						.add(Projections.property("profile.name"),"name")
						.add(Projections.property("profile.lastName"),"lastName")
						.add(Projections.property("recom.recommendId"),"recommendId")
						.add(Projections.property("recom.recommendForTitle"),"recommendForTitle")
						/*.add(Projections.property("recom.recommendByTitle"),"recommendByTitle")*/
						.add(Projections.property("recom.status"),"status")
						/*.add(Projections.property("recom.hide"),"hide")
						.add(Projections.property("recom.fk_key_by_exp"),"fk_key_by_exp")
						.add(Projections.property("recom.fk_key_for_exp"),"fk_key_for_exp")
						.add(Projections.property("recom.recommendType"),"recommendType")*/
						).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		
	}
	
	public Recommendation editRecommendation(int id){
		
		
		Recommendation recommendation_dao=(Recommendation) sessionFactory.getCurrentSession().get(Recommendation.class,id);
		
		return recommendation_dao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Recommendation> showAllRecommend(String userId){
		
		return (List<Recommendation>) sessionFactory.getCurrentSession().createCriteria(Recommendation.class,"recommend")
			
				.add(Restrictions.eq("recommend.recommendFor", userId))
				.setProjection(Projections.projectionList()
						.add(Projections.property("recommend.recommendBy"),"recommendBy")
						.add(Projections.property("recommend.desc"),"desc")
						.add(Projections.property("recommend.recommendByTitle"),"recommendByTitle")
						.add(Projections.property("recommend.status"),"status")
						.add(Projections.property("recommend.hide"),"hide")
						.add(Projections.property("recommend.fk_key_by_exp"),"fk_key_by_exp")
						.add(Projections.property("recommend.fk_key_for_exp"),"fk_key_for_exp")
						.add(Projections.property("recommend.recommendType"),"recommendType"))
				.setResultTransformer(Transformers.aliasToBean(Recommendation.class))
				.list();
		
	}
		
	public int getAllRecommend(String userId){
		
		return (Integer) sessionFactory.getCurrentSession().createCriteria(Recommendation.class)
				.add(Restrictions.like("status", "ACCEPT")).add(Restrictions.eq("recommendFor", userId)).list().size();
	}

	public int getHiddenCount(String userId){
		
		return (Integer) sessionFactory.getCurrentSession().createCriteria(Recommendation.class)
				.add(Restrictions.like("status", "ACCEPT")).add(Restrictions.like("hide", "true"))
				.add(Restrictions.eq("recommendFor", userId)).list().size();
	}
	
	public void removeRecommend(int id){
		
		
		Recommendation recommendation_dao=(Recommendation) sessionFactory.getCurrentSession().get(Recommendation.class,id);
		
		sessionFactory.getCurrentSession().delete(recommendation_dao);
	}
	
	@SuppressWarnings("unchecked")
	public List<Recommendation> manageRecommendation(int id,String userId){
		
		
		
		return (List<Recommendation>) sessionFactory.getCurrentSession().createCriteria(Recommendation.class,"recom")
				.createCriteria("profileRecommendByJoin", "rec")
				.add(Restrictions.eq("recom.recommendFor", userId))
				.add(Restrictions.eq("recom.fk_key_for_exp", id))
				.setProjection(Projections.projectionList()
						.add(Projections.property("rec.userId"),"userId")
						.add(Projections.property("rec.name"),"name")
						.add(Projections.property("rec.lastName"),"lastName")
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
