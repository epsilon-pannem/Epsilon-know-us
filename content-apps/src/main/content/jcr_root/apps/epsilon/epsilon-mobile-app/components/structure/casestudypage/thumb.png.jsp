<%--
  ADOBE CONFIDENTIAL

  Copyright 2014 Adobe Systems Incorporated
  All Rights Reserved.

  NOTICE:  All information contained herein is, and remains
  the property of Adobe Systems Incorporated and its suppliers,
  if any.  The intellectual and technical concepts contained
  herein are proprietary to Adobe Systems Incorporated and its
  suppliers and may be covered by U.S. and Foreign Patents,
  patents in process, and are protected by trade secret or copyright law.
  Dissemination of this information or reproduction of this material
  is strictly forbidden unless prior written permission is obtained
  from Adobe Systems Incorporated.
--%><%@ include file="/libs/foundation/global.jsp" %><%
%><%@ page session="false"
           import="com.day.cq.dam.api.Asset,
                   org.apache.sling.api.request.RequestDispatcherOptions,
                   org.apache.sling.api.resource.Resource" %><%
%><%
    Resource assetRes = resourceResolver.getResource(properties.get("fileReference", ""));

    if (assetRes == null) {
        assetRes = resourceResolver.getResource(properties.get("asset", ""));
    }

    Asset asset = null;
    if (assetRes != null) {
        asset = assetRes.adaptTo(Asset.class);
    }

    if (asset != null) {
        // if we have the 319.319 rendition then we will use that
        String rendition = (asset.getRendition("cq5dam.thumbnail.319.319.png") != null) ? "319.319" : "100.140";

        RequestDispatcherOptions options = new RequestDispatcherOptions();

        // point to the rendition that we want to render outfor example /content/dam/video.mp4.thumb.319.319.png
        options.setAddSelectors(rendition);

        // render the poster image
        slingRequest.getRequestDispatcher(asset.adaptTo(Resource.class), options).forward(request, response);
    }
%>
