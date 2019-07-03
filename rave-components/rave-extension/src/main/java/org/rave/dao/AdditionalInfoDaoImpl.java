package org.rave.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.rave.model.AdditionalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("additionalInfoDao")
public class AdditionalInfoDaoImpl implements AdditionalInfoDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<AdditionalInfo> listInfo(String userId)
	{
		System.out.println("Doa layer UserId:"+userId);

		return null;
	}
	
	public AdditionalInfo saveInfo(AdditionalInfo info)
	{
		
		sessionFactory.getCurrentSession().saveOrUpdate(info);
		
		return null;
	}
	

	public AdditionalInfo editInfo(String userId){
		
		
		AdditionalInfo info_dao=new AdditionalInfo();
		try{
		info_dao=(AdditionalInfo) sessionFactory.getCurrentSession().get(AdditionalInfo.class,userId);
		}
		catch (Exception e) {
			// TODO: handle exception\
			System.out.println("oooooooo");
		 info_dao=new AdditionalInfo();
		 
		}
		return info_dao;
	}

}
