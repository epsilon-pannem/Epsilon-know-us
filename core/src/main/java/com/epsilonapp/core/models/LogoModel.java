package com.epsilonapp.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epsilonapp.core.utils.LinksUtil;


/**
 * The Class LogoModel.
 */
@Model(adaptables = Resource.class)
public class LogoModel {

	/** The Constant logger. */
	final private static Logger logger = LoggerFactory
			.getLogger(LogoModel.class);

	/** The resource resolver. */
	@SlingObject
	private ResourceResolver resourceResolver;

	/** The url. */
	@ValueMapValue
	private String URL;

	/** The URL path. */
	private String URLPath;

	/** The resource. */
	private Resource resource;

	/**
	 * Init() calls checkInternalExternalURLByResource returns URLPath appended
	 * with .html
	 */
	@PostConstruct
	protected void init() {
		logger.debug("Starts checkInternalExternalURL method !");
		resource = resourceResolver.getResource(URL);
		URLPath = LinksUtil.checkInternalExternalURLByResource(URL, resource);
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * Gets the URL path.
	 *
	 * @return the URL path
	 */
	public String getURLPath() {
		return URLPath;
	}
}
