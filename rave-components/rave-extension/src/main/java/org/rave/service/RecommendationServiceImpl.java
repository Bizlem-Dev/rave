package org.rave.service;

import java.util.List;

import org.rave.dao.ExperienceDao;
import org.rave.dao.ProfileDao;
import org.rave.dao.RecommendationDao;
import org.rave.model.Experience;
import org.rave.model.Profile;
import org.rave.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("recommendationService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RecommendationServiceImpl  implements RecommendationService{
	
	
	@Autowired
	public ExperienceDao experience_dao;
	
	@Autowired
	public ProfileDao profile_dao;
	
	@Autowired
	public RecommendationDao recommend_dao;
	
	public List<Experience> listExperience(String userId){
		
		return experience_dao.listExperience(userId,"0");
	} 
	
	public List<Profile> userName(){
		
		return profile_dao.getUserName();
	} 
	
	public List<Recommendation> showRequest(String userId){
		
		return recommend_dao.showRequest(userId);
	} 

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveRequestRecommend(Recommendation recommendation) {
		
		recommend_dao.saveRequestRecommend(recommendation);
	}
	
	public Recommendation editRecommendation(int id){
		
		return recommend_dao.editRecommendation(id);
	}
	
	public Experience showExperience(int id){
		
		return experience_dao.editExperience(id);
	}
	
	public List<Recommendation> showAllRecommend(String userId){
		
		return recommend_dao.showAllRecommend(userId); 
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void removeRecommend(int id){
		
		 recommend_dao.removeRecommend(id);
	}
	
	public List<Recommendation> manageRecommendation(int id,String userId){
		return recommend_dao.manageRecommendation(id,userId);
	}
}
