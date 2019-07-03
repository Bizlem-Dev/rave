package org.rave.service;

import java.util.List;

import org.rave.model.Experience;
import org.rave.model.Recommendation;

public interface ExperienceService {

	public List<Experience> listExperience(String userId,String referringPageId);
	public Experience editExperience(int id);
	public Experience removeExperience(int id);
	public Experience saveExperience(Experience experience);
	public List<Recommendation> listRecommendation(String userId);
}
