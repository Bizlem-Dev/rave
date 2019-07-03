package org.rave.dao;

import java.util.List;
import org.rave.model.Experience;
import org.rave.model.Recommendation;

public interface ExperienceDao {
	
	public List<Experience> listExperience(String userId,String referringPageId);
	public Experience saveExperience(Experience experience);
	public Experience editExperience(int id);
	public Experience removeExperience(int id);
	public List<Recommendation> listRecommendation(String userId);

}
