package org.rave.service;

import java.util.List;

import org.rave.dao.ExperienceDao;
import org.rave.model.Experience;
import org.rave.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("experienceService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ExperienceServiceImpl implements ExperienceService {
	
	@Autowired
	public ExperienceDao experience_dao;
	
	
	public List<Experience> listExperience(String userId,String referringPageId){
		
		return experience_dao.listExperience(userId,referringPageId);
	}
	
	public List<Recommendation> listRecommendation(String userId){
		
		return experience_dao.listRecommendation(userId);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Experience saveExperience(Experience experience){
		
		return experience_dao.saveExperience(experience);
	}
	
	public Experience editExperience(int id){
		
		return experience_dao.editExperience(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Experience removeExperience(int id){
		
		return experience_dao.removeExperience(id);
	}
}
