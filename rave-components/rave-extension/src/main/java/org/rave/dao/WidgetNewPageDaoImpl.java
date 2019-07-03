package org.rave.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
/*import org.rave.model.RaveDefaultPage;
import org.rave.model.RavePage;
import org.rave.model.RavePageRegion;
import org.rave.model.RavePageRegionWidget;*/
import org.rave.model.RavePageUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("widgetNewPage")
public class WidgetNewPageDaoImpl implements WidgetNewPageDao {

	@Autowired
	private SessionFactory sessionFactory;
/*	
	public RavePage createNewPage(RavePage page,RavePageUser pageUser){
		System.out.println(page.getEntity_id());

		sessionFactory.getCurrentSession().saveOrUpdate(page);
		
		pageUser.setPage(page);
		sessionFactory.getCurrentSession().saveOrUpdate(pageUser);
		return page;
	}

	@Override
	public void createRavePageRegion(RavePageRegion ravePageRegion) {
		sessionFactory.getCurrentSession().saveOrUpdate(ravePageRegion);
		
	}

	@Override
	public void createRavePageRegionWidget(
			RavePageRegionWidget ravePageRegionWidget) {
		sessionFactory.getCurrentSession().saveOrUpdate(ravePageRegionWidget);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<RaveDefaultPage> getDefaultPage(){		
		return (List<RaveDefaultPage>) sessionFactory.getCurrentSession().createCriteria(RaveDefaultPage.class).list();
	}*/
	
	@SuppressWarnings("unchecked")
	public List<RavePageUser> getDefaultPages(BigInteger userId){

		BigInteger renderSequence = new BigInteger("0");
		return (List<RavePageUser>) sessionFactory.getCurrentSession().createCriteria(RavePageUser.class)
				.add(Restrictions.eq("user_id", userId))
				.add(Restrictions.ne("render_sequence", renderSequence)).list();
	}
	public void setDefaultPages(RavePageUser page_user){
		sessionFactory.getCurrentSession().saveOrUpdate(page_user);
	}
}
