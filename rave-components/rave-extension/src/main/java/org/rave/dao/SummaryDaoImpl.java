package org.rave.dao;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.rave.model.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("summaryDao")
public class SummaryDaoImpl implements SummaryDao {
	
	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private SessionFactory sessionFactory;
	 
	
	public Summary listSummary(String userId,String referringPageId)
	{
			
			System.out.println("dao_list");
			Summary summary = (Summary) sessionFactory.getCurrentSession().get(Summary.class, userId);
			
			
			return summary;
	}
	
	
	
	public Summary saveSummary(Summary summary)
	{
		logger.debug("Save Summary");		
		System.out.println("-----Dao Save summary started------"+summary.getSpecialities());
		try{
		 sessionFactory.getCurrentSession().saveOrUpdate(summary);
		}
		catch(HibernateException e)
		{
			e.getMessage();
		}
		 System.out.println("-----Dao Save summary completed------");
		 System.out.println("-----Dao Save Get summary started------");
		 Summary summary_object = null;
		 //(Summary)sessionFactory.getCurrentSession().get(Summary.class,summary.getUserName());
		 System.out.println("-----Dao Save Get summary completed------");
		
		 return summary_object;
	}
	

}
