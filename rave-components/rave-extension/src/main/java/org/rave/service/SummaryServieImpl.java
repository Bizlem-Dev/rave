package org.rave.service;


import org.rave.dao.SummaryDao;


import org.rave.model.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("summaryService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SummaryServieImpl implements SummaryService {
	
	@Autowired
	private SummaryDao summary_dao;
	
	
	public Summary listSummary(String userId,String referringPageId) {
		
		/*Summary employee1=new Summary();
		System.out.println("service_Summary_list");
		List a=summary.listSummary(userId);
		for (Iterator iterator = a.iterator(); iterator.hasNext();)
		{ Summary employee = (Summary) iterator.next();
		employee.setSkills(employee.getSkills());
		
		System.out.print("First Nam12e: " + employee.getSkills());
		
		}
		System.out.print("First Nam12e: " + employee1.getSkills());*/
		return summary_dao.listSummary(userId,referringPageId);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Summary saveSummary(Summary summary) {
		
		return summary_dao.saveSummary(summary);
	}
	

}
