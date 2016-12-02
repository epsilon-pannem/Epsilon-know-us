package com.epsilonapp.core.models;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.epsilonapp.core.utils.CasestudyDetail;
import com.epsilonapp.core.utils.LinksUtil;

/**
 * The Class LogoModel.
 */
@Model(adaptables = Resource.class)
public class CasestudyModel {
	/** The Constant logger. */
	final private static Logger logger = LoggerFactory
			.getLogger(CasestudyModel.class);
	
	/** The resource resolver. */
	@SlingObject
	private ResourceResolver resourceResolver;

	/** The URL path. */
	private String URLPath;

	/** The resource. */
	private Resource resource;

	/**
	 * Init() calls checkInternalExternalURLByResource returns URLPath appended
	 * with .html
	 */
	@ValueMapValue
	private String[] items;
	
	/** The document obj. */
	private JSONObject documentObj;	
	private String video;
	private String imagePath;
	private String URL;
	private int countofPages=0;
	List<CasestudyDetail> casestudyList = new ArrayList<CasestudyDetail>();
	
	@PostConstruct
	protected void init() {
		logger.info("In Casestudy init method !");
		fetchMultifieldDialogValues();		
	}
	
		private void fetchMultifieldDialogValues() {
			if (items != null && items.length > 0) {
				for (String item : items) {
					try {
						countofPages++;
						documentObj = new JSONObject(item);
						URL= documentObj.getString("casestudyUrl");
						resource = resourceResolver.getResource(URL);
						PageManager pm = resourceResolver.adaptTo(PageManager.class);
						Page page = pm.getPage(URL);
						Node node = resource.adaptTo(Node.class);
						if(node.hasNode("jcr:content")){
							Node JcrNode = node.getNode("jcr:content");
							imagePath = JcrNode.getProperty("thumbnailImage").getString();
						}
						if(node.hasNode("jcr:content")){
							Node JcrNode = node.getNode("jcr:content");
							video = JcrNode.getProperty("videoUrl").getString();
						}
						CasestudyDetail csdetail = new CasestudyDetail();
						csdetail.setPageName(page.getName());
						csdetail.setPageUrl(page.getPath());
						if(!imagePath.equals(null)){
							csdetail.setImage(imagePath);
						}
						if(!video.equals(null)){
							csdetail.setVideo(video);
						}
						casestudyList.add(csdetail);					
						
					} catch (JSONException e) {
						e.printStackTrace();
					} catch (RepositoryException e) {
						e.printStackTrace();
					}
					
				}
			
		}
	}		
		public List<CasestudyDetail> getCasestudyDetails() {
			return casestudyList;
		}
		
		public int getCountofPages() {
			return countofPages;
		}
}
