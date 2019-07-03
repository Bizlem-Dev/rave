package org.rave.service;

import java.math.BigInteger;
import java.util.List;

/*import org.rave.model.RaveDefaultPage;
import org.rave.model.RavePage;
import org.rave.model.RavePageRegion;
import org.rave.model.RavePageRegionWidget;*/
import org.rave.model.RavePageUser;

public interface WidgetNewPageService {
/*	public RavePage createNewPage(RavePage page,RavePageUser pageUser);
	public void createRavePageRegion(RavePageRegion ravePageRegion);
	public void createRavePageRegionWidget(RavePageRegionWidget ravePageRegionWidget);
	List<RaveDefaultPage> getDefaultPage();*/
	List<RavePageUser> getDefaultPages(BigInteger userId);
	void setDefaultPages(RavePageUser page_user);
	void setPages(String userId,String adminUser);
}
