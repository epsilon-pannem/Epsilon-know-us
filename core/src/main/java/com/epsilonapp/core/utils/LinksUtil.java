package com.epsilonapp.core.utils;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.epsilonapp.core.constants.CommonConstants;

/**
 * The Class LinksUtil.
 */
public class LinksUtil {

	/** The Constant LOG. */
	final private static Logger LOG = (Logger) LoggerFactory.getLogger(LinksUtil.class);

	/**
	 * Check for Internal URL by Resource.
	 *
	 * @param url            the url
	 * @param resource the resource
	 * @return Returns URL with path appended with '.html' if the URL is internal
	 */
	public static String checkInternalExternalURLByResource(String url,
			Resource resource) {
		LOG.debug("START LinksUtil class checkInternalExternalURL method !");
		try {
			if (null != resource) {
				LOG.debug("checking whether the resource is a cq:page or not");
				if (isCQPage(resource)) {
					if (url.startsWith(CommonConstants.SLASH_FORWARD)) {
						url = url + CommonConstants.DOT + CommonConstants.HTML;
					}
				}
			}
			LOG.debug("EXIT LinksUtil class checkInternalExternalURL method !");
		} catch (Exception e) {
			LOG.error("Exception in LinksUtil.checkInternalExternalURL method !" + e);
		}
		return url;
	}

	/**
	 * Check Internal URL by page.
	 *
	 * @param page
	 *            the page
	 * @return Returns URL with path appended with '.html' if the URL is internal
	 */
	public static String checkInternalURLByPage(Page page) {
		LOG.debug("START LinksUtil class checkInternalURLByPage method !");
		String url = null;
		if (null != page) {
			url = page.getPath();
			url = url + CommonConstants.DOT + CommonConstants.HTML;
		}
		LOG.debug("EXIT LinksUtil class checkInternalURLByPage method !");
		return url;
	}

	/**
	 * Check for Internal URL by Path.
	 *
	 * @param path the path
	 * @param resourceResolver the resource resolver
	 * @return Returns URL with path appended with '.html' if the URL is internal
	 */
	public static String checkInternalURLByPath(String path, ResourceResolver resourceResolver) {
		LOG.debug("START LinksUtil class checkInternalURLByPath method !");
		String url = null;
		try {
			if (null != path) {
				Resource resource = resourceResolver.getResource(path);
				url = checkInternalExternalURLByResource(path, resource);
			}
		LOG.debug("EXIT LinksUtil class checkInternalURLByPath method !");
		} catch (Exception e) {
			LOG.error("Exception in LinksUtil.checkInternalURLByPath method" + e);
		}
		return url;
	}

	/**
	 * Returns 'true' if a Resource is a cq:Page.
	 *
	 * @param resource the resource
	 * @return Returns 'true' if a Resource is a cq:Page
	 */
	public static boolean isCQPage(Resource resource) {
		LOG.debug("Starts linksutil class IsCQPage method !");
		ValueMap properties = ResourceUtil.getValueMap(resource);
		String primaryType = properties.get("jcr:primaryType", String.class);
		if (primaryType.equals("cq:Page")) {
			return true;
		}
		LOG.debug("EXIT LinksUtil class isCQPage method !");
		return false;
	}
}
