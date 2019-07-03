package org.rave.service;


import org.rave.model.Summary;

public interface SummaryService {
	
	
	public Summary listSummary(String userId,String referringPageId);
	public Summary saveSummary(Summary summary);
}
