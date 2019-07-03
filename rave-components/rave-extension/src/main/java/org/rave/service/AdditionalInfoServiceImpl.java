package org.rave.service;

import java.util.List;

import org.rave.dao.AdditionalInfoDao;
import org.rave.model.AdditionalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("additionalInfoService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AdditionalInfoServiceImpl implements AdditionalInfoService {
	
	@Autowired
	public AdditionalInfoDao info_dao;
	
	
	public List<AdditionalInfo> listInfo(String userId){
		
		return info_dao.listInfo(userId);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public AdditionalInfo saveInfo(AdditionalInfo info){
		
		return info_dao.saveInfo(info);
	}
	
	public AdditionalInfo editInfo(String id){
		
		return info_dao.editInfo(id);
	}
}
