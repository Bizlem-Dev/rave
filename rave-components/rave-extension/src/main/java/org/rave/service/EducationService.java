package org.rave.service;

import java.util.List;
import org.rave.model.Education;


public interface EducationService {
	
	public List<Education> listEducation(String userId);

	public Education saveEducation(Education education);
	public Education editEducation(int id);
	public Education removeEducation(int id);

}
