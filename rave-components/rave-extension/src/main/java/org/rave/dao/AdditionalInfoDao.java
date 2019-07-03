package org.rave.dao;

import java.util.List;
import org.rave.model.AdditionalInfo;


public interface AdditionalInfoDao {
	
	public List<AdditionalInfo> listInfo(String userId);
	public AdditionalInfo editInfo(String id);
	public AdditionalInfo saveInfo(AdditionalInfo experience);

}
