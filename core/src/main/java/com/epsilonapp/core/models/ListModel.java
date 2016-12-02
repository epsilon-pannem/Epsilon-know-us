package com.epsilonapp.core.models;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epsilonapp.core.constants.CommonConstants;
import com.epsilonapp.core.constants.ListModelConstants;
import com.epsilonapp.core.pojo.ListLinkProperties;

/**
 * The Class ListModel.
 */
@Model(adaptables = Resource.class)
public class ListModel {

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/** The links obj. */
	private JSONObject linksObj;

	/** The text obj. */
	private JSONObject textObj;

	/** The list linkItems. */
	@ValueMapValue
	@Named(ListModelConstants.LIST_LINKITEMS)
	@Default(values = { "" })
	private String[] listLinkItems;

	/** The text list items. */
	@ValueMapValue
	@Named(ListModelConstants.TEXT_LISTITEMS)
	@Default(values = { "" })
	private String[] textListItems;

	/** The type. */
	@ValueMapValue
	private String type;

	/** The resource resolver. */
	@SlingObject
	private ResourceResolver resourceResolver;

	/** The url link list. */
	List<ListLinkProperties> urlLinkList = new ArrayList<ListLinkProperties>();

	/** The text list. */
	List<String> textList = new ArrayList<String>();

	/**
	 * Init() getting called for creating JsonObject.
	 */
	@PostConstruct
	protected void init() {
		logger.debug("Initializing ListModel");
		try {
			createJsonObjectFromListModelLinks();
		} catch (JSONException j1) {
			logger.error("Exception in init method for json ");
		} catch (Exception e) {
			logger.error("Exception in init method of list");
		}
	}

	/**
	 * Creates the json object from list model links.
	 *
	 * @throws JSONException
	 *             the JSON exception
	 * @throws Exception
	 *             the exception
	 */
	private void createJsonObjectFromListModelLinks() throws JSONException,
			Exception {
		if (type != null
				&& type.trim().equals(ListModelConstants.TYPE_LINKVALUE)) {
			for (String links : listLinkItems) {
				linksObj = new JSONObject(links);
				ListOfListModelLinks();
			}
		} else if (type != null
				&& type.trim().equals(ListModelConstants.TYPE_TEXTVALUE)) {
			for (String text : textListItems) {
				textObj = new JSONObject(text);
				textList.add((String) textObj.get(ListModelConstants.LABEL));
			}
		} else {
			logger.debug("Type is not selected");
		}
	}

	/**
	 * List of list model links.s
	 *
	 * @throws JSONException
	 *             the JSON exception
	 * @throws Exception
	 *             the exception
	 */
	private void ListOfListModelLinks() throws JSONException, Exception {

		ListLinkProperties listLinkProperties = new ListLinkProperties();
		listLinkProperties.setLabel((String) linksObj
				.get(ListModelConstants.LABEL));
		String linkTarget = (String) linksObj.get(CommonConstants.NEW_WINDOW);

		if (linkTarget != null && linkTarget.equals(CommonConstants.TRUE)) {
			listLinkProperties.setNewWindow(CommonConstants.BLANK);
		} else {
			listLinkProperties.setNewWindow(CommonConstants.SELF);
		}

		String listLinkUrl = (String) linksObj.get(CommonConstants.URL);			
		listLinkProperties.setUrl(listLinkUrl);
		urlLinkList.add(listLinkProperties);
	}
	/**
	 * Gets the text list items.
	 *
	 * @return the text list items
	 */
	public String[] getTextListItems() {
		return textListItems;
	}

	/**
	 * Sets the text list items.
	 *
	 * @param textListItems
	 *            the new text list items
	 */
	public void setTextListItems(String[] textListItems) {
		this.textListItems = textListItems;
	}

	/**
	 * Gets the list link items.
	 *
	 * @return the list link items
	 */
	public String[] getListLinkItems() {
		return listLinkItems;
	}

	/**
	 * Sets the list link items.
	 *
	 * @param listLinkItems
	 *            the new list link items
	 */
	public void setListLinkItems(String[] listLinkItems) {
		this.listLinkItems = listLinkItems;
	}

	/**
	 * Gets the url link list.
	 *
	 * @return the url link list
	 */
	public List<ListLinkProperties> getUrlLinkList() {
		return urlLinkList;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the text list.
	 *
	 * @return the text list
	 */
	public List<String> getTextList() {
		return textList;
	}

	/**
	 * Sets the text list.
	 *
	 * @param textList
	 *            the new text list
	 */
	public void setTextList(List<String> textList) {
		this.textList = textList;
	}

}
