package org.rave.service;

import java.util.List;

import org.rave.model.AdditionalInfo;


public interface AdditionalInfoService {

	public List<AdditionalInfo> listInfo(String userId);
	public AdditionalInfo editInfo(String id);
	public AdditionalInfo saveInfo(AdditionalInfo experience);
}
