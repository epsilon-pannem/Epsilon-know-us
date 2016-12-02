package com.epsilonapp.core.utils;

import org.apache.sling.api.resource.Resource;

public class CasestudyDetail {
	private String Image;
	private String pageName;
	private String pageUrl;
	private String video;
	public String getPageUrl() {
		return pageUrl;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public void setPageUrl(String url) {
		this.pageUrl = url;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String imagePath) {
		this.Image = imagePath;
	}
	
	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
}
