package com.epsilonapp.core.use;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.commons.Filter;
import com.day.cq.wcm.api.NameConstants;
import com.day.cq.wcm.api.Page;
import com.epsilonapp.core.constants.MenuConstants;
import com.epsilonapp.core.pojo.MenuOptionBean;

public class Menu extends WCMUsePojo{
	
	private static final Logger log = LoggerFactory.getLogger(Menu.class);
	private List<MenuOptionBean> optionsList=new ArrayList<MenuOptionBean>(0);
	
	@Override
	public void activate() throws Exception {
		String[] items = getProperties().get(MenuConstants.ITEMS, String[].class);
		if(items==null){
			return;
		}
		populateOptions(items);
	}
	private void populateOptions(String[] items) throws JSONException {
		for (String item : items) {
			JSONObject itemObj = new JSONObject(item);
			String menuType = itemObj.getString(MenuConstants.MENU_TYPE);
			MenuOptionBean bean ;
			
			if(MenuConstants.CHILDREN_DISPLAY.equalsIgnoreCase(menuType)){
				bean = buildchildrenDisplayMenuOption(itemObj);
				optionsList.add(bean);
			}else if(MenuConstants.REDIRECT_TYPE.equalsIgnoreCase(menuType)){
				bean = buildRedirectMenuOption(itemObj);
				optionsList.add(bean);
			}
		}
		
		log.info("Options details {}",optionsList.toString());
	}

	private MenuOptionBean buildRedirectMenuOption(JSONObject itemObj) throws JSONException {
		String redirectUrl = getRedirectUrl(itemObj.getString(MenuConstants.MENU_PATH));
		String title = itemObj.getString(MenuConstants.TITLE);
		return new MenuOptionBean(MenuConstants.REDIRECT_TYPE, redirectUrl, title, new ArrayList<MenuOptionBean>(0));
	}
	private MenuOptionBean buildchildrenDisplayMenuOption(JSONObject itemObj) throws JSONException {
		String menuPath = itemObj.getString(MenuConstants.MENU_PATH);
		Resource resource = getResourceResolver().getResource(menuPath);
		Page page = resource.adaptTo(Page.class);
		String title=StringUtils.isBlank(itemObj.getString(MenuConstants.TITLE))? page.getTitle():itemObj.getString(MenuConstants.TITLE);
		List<Page> childPages = fetchChildren(page);
		List<MenuOptionBean> childPagesMenuOptions= buildChildPageMenuOptions(childPages);
		return new MenuOptionBean(MenuConstants.CHILDREN_DISPLAY,getRedirectUrl(menuPath),title,childPagesMenuOptions);
	}

	private List<MenuOptionBean> buildChildPageMenuOptions(List<Page> childPages) {
		MenuOptionBean childPageBean;
		List<MenuOptionBean> childPageMenuOptions=new ArrayList<MenuOptionBean>();
		List<MenuOptionBean> temp=new ArrayList<MenuOptionBean>();
		for(Page childPage:childPages){
			childPageBean=new MenuOptionBean(MenuConstants.CHILD_MENU,getRedirectUrl(childPage.getPath()),childPage.getTitle(),temp);
			childPageMenuOptions.add(childPageBean);
		}
		return childPageMenuOptions;
	}

	private List<Page> fetchChildren(Page page) {
		Iterator<Page> childPageIt = page.listChildren(new Filter<Page>() {
			public boolean includes(Page childPage) {
				return !childPage.getProperties().get(NameConstants.PN_HIDE_IN_NAV,false);
			}
		});
		if(childPageIt.hasNext()){
			return IteratorUtils.toList(childPageIt);
		}
		return 	new ArrayList<Page>(0);
	}
	
	public List<MenuOptionBean> getOptionsList() {
		return optionsList;
	}
	
	private String getRedirectUrl(String menuPath){
		String url=menuPath;
		Page page=getPageManager().getPage(menuPath);
		if(page==null){
			return url;
		}
		return  menuPath ;
	}
	
	
}