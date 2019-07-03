package org.rave.service;

import java.math.BigInteger;

import java.util.List;


import org.rave.dao.WidgetNewPageDao;
/*
 * import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.rave.model.RaveDefaultPage;
import org.rave.model.RavePage;
import org.rave.model.RavePageRegion;
import org.rave.model.RavePageRegionWidget;*/
import org.rave.model.RavePageUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("widgetNewPageService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class WidgetNewPageServiceImpl implements WidgetNewPageService {
	
	@Autowired
	private WidgetNewPageDao pageService;
	
/*	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public RavePage createNewPage(RavePage page,RavePageUser pageUser){
		
		return pageService.createNewPage(page,pageUser);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createRavePageRegion(RavePageRegion ravePageRegion) {
		pageService.createRavePageRegion(ravePageRegion);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createRavePageRegionWidget(
			RavePageRegionWidget ravePageRegionWidget) {
		pageService.createRavePageRegionWidget(ravePageRegionWidget);
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RaveDefaultPage> getDefaultPage(){
		
		List<RaveDefaultPage> pageList=pageService.getDefaultPage();
		Map<Object,Object> pageOrder=new HashMap<Object,Object>();
		Map<Object,Object> pageDetail=new HashMap<Object,Object>();
		Map<Object,Object> renderOrder=new HashMap<Object,Object>();
		Map<Object,Object> widgetOrder=new HashMap<Object,Object>();
		ArrayList widget=new ArrayList();
		for(int i=0;i<pageList.size();i++){		

			if(pageOrder.containsKey(pageList.get(i).getPage_no())){
				pageDetail=(Map<Object, Object>) pageOrder.get(pageList.get(i).getPage_no());
				renderOrder=(Map<Object, Object>) pageDetail.get("render");
				if(renderOrder.containsKey(pageList.get(i).getRender_sequence())){
					ArrayList widgetMap= (ArrayList) renderOrder.get(pageList.get(i).getRender_sequence());
					widgetOrder=new HashMap<Object,Object>();
					widgetOrder.put("widgetOrder",pageList.get(i).getWidget_render());
					widgetOrder.put("widgetId",pageList.get(i).getWidget_id());
					widgetMap.add(widgetOrder);
					renderOrder.put(pageList.get(i).getRender_sequence(),widgetMap);
				}else{
					widget=new ArrayList();
					widgetOrder=new HashMap<Object,Object>();
					widgetOrder.put("widgetOrder",pageList.get(i).getWidget_render());
					widgetOrder.put("widgetId",pageList.get(i).getWidget_id());
					widget.add(widgetOrder);
					renderOrder.put(pageList.get(i).getRender_sequence(), widget);
				}
			}else{
				widgetOrder=new HashMap<Object,Object>();
				widget=new ArrayList();
				renderOrder=new HashMap<Object,Object>();
				pageDetail=new HashMap<Object,Object>();
				widgetOrder.put("widgetOrder",pageList.get(i).getWidget_render());
				widgetOrder.put("widgetId",pageList.get(i).getWidget_id());
				widget.add(widgetOrder);
				renderOrder.put(pageList.get(i).getRender_sequence(),widget);
				pageDetail.put("pageName",pageList.get(i).getPage_name());
				pageDetail.put("pageLayout",pageList.get(i).getPage_layout_id());
				pageDetail.put("render",renderOrder);
				pageOrder.put(pageList.get(i).getPage_no(),pageDetail);

			}
						
			
		}
		
		return null;
	}*/

	public List<RavePageUser> getDefaultPages(BigInteger userId){
		
		return pageService.getDefaultPages(userId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void setDefaultPages(RavePageUser page_user){
		
		pageService.setDefaultPages(page_user);
	}
	public void setPages(String userId,String adminUser){
		
		
		BigInteger user = new BigInteger(userId);
		BigInteger adminUserId = new BigInteger(adminUser);
		List<RavePageUser> pageList= getDefaultPages(adminUserId);
		for(RavePageUser pageUser:pageList){
			RavePageUser pageObject=new RavePageUser();
			pageObject.setEntity_id(0);
			pageObject.setEditor(false);
			pageObject.setPage_status("ACCEPTED");
			pageObject.setPage(pageUser.getPage());
			pageObject.setRender_sequence(pageUser.getRender_sequence());
			pageObject.setUser_id(user);
			setDefaultPages(pageObject);
		}
		
	}
}
