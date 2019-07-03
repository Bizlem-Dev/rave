package org.rave.dao;

import org.rave.model.Summary;

public interface SummaryDao {
	
	public Summary listSummary(String userId,String referringPageId);
	public Summary saveSummary(Summary summary);
}
