package org.rave.dao;


import java.util.List;

import org.rave.model.Recommendation;

public interface RecommendationDao {
	public void saveRequestRecommend(Recommendation recommendation);
	public List<Recommendation> showRequest(String userId);
	public Recommendation editRecommendation(int id);
	public List<Recommendation> showAllRecommend(String userId);
	public void removeRecommend(int id);
	public List<Recommendation> manageRecommendation(int id,String userId);

}
