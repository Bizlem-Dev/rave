package org.rave.service;

import java.util.List;

import org.rave.dao.EducationDao;
import org.rave.model.Education;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("educationService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EducationServiceImpl implements EducationService {

	@Autowired
	private EducationDao eduacation_dao;

	public List<Education> listEducation(String userId) {

		return eduacation_dao.listEducation(userId);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Education saveEducation(Education education) {
		return eduacation_dao.saveEducation(education);
	}

	public Education editEducation(int id) {
		return eduacation_dao.editEducation(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Education removeEducation(int id){
		
		return eduacation_dao.removeEducation(id);
	}
}
