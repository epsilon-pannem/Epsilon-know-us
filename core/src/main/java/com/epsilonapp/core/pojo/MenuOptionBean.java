package com.epsilonapp.core.pojo;

import java.util.List;

public class MenuOptionBean {
	
	private String menuType;
	private String redirectUrl;
	private String title;
	private List<MenuOptionBean> childMenuOptions;
		
	
	public MenuOptionBean(String menuType, String redirectUrl, String title, List<MenuOptionBean> childMenuOptions) {
		super();
		this.menuType = menuType;
		this.redirectUrl = redirectUrl;
		this.title = title;
		this.childMenuOptions = childMenuOptions;
	}

	public String getMenuType() {
		return menuType;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public String getTitle() {
		return title;
	}

	public List<MenuOptionBean> getChildMenuOptions() {
		return childMenuOptions;
	}

	@Override
	public String toString() {
		return "MenuOptionBean [menuType=" + menuType + ", redirectUrl=" + redirectUrl + ", title=" + title
				+ ", childMenuOptions=" + childMenuOptions + "]";
	}
	
	public boolean isChildrenDisplay(){
		return "childrenDisplay".equalsIgnoreCase(this.menuType);
	}
	
	public boolean isRedirectType(){
		return "redirectType".equalsIgnoreCase(this.menuType);
	}
	
	public boolean isExternalRedirect(){
		return "externalRedirect".equalsIgnoreCase(this.menuType);
	}
	
	
	
	
}
