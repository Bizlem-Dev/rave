package org.rave.service;

import java.util.List;


import org.rave.model.Experience;
import org.rave.model.Profile;
import org.rave.model.Recommendation;

public interface RecommendationService {
	
	
	public List<Experience> listExperience(String userId);
	public List<Profile> userName();
	public void saveRequestRecommend(Recommendation recommendation);
	public List<Recommendation> showRequest(String userId);
	public Recommendation editRecommendation(int id);
	public Experience showExperience(int id);
	public List<Recommendation> showAllRecommend(String userId);
	public void removeRecommend(int id);
	public List<Recommendation> manageRecommendation(int id,String userId);
}
