<%@page session="false"
	    import="com.day.cq.wcm.api.WCMMode,
	            com.adobe.cq.mobile.angular.data.util.FrameworkContentExporterUtils" %><%
%><%@include file="/libs/foundation/global.jsp" %><%
    // Remove invalid characters for JS function names
    String controllerNameStripped = currentPage.getPath().replaceAll("[^A-Za-z0-9]", "");
    pageContext.setAttribute("controllerNameStripped", controllerNameStripped);

    String relativePathToRoot = FrameworkContentExporterUtils.getRelativePathToRootLevel(currentPage.adaptTo(Resource.class));
%><c:set var="wcmMode"><%= WCMMode.fromRequest(request) != WCMMode.DISABLED %></c:set><%
%>
<body ng-controller="AppController">
    <div id="appWrap" ng-controller="AppNavigationController" ng-cloak>
 	<c:choose>
    	<c:when test="${wcmMode}">
        	<div ng-controller="<c:out value="${controllerNameStripped}"/>">
        		<cq:include script="template.html"/>
            </div>
        </c:when>
        <c:otherwise>
            <div ng-view class="view-animate" ng-class="transition"></div>
            <script type="text/javascript" src="<%= xssAPI.getValidHref( relativePathToRoot ) %>cordova.js"></script>
        </c:otherwise>
    </c:choose>
    </div>
    <cq:includeClientLib js="epsilon.ionic-1.1.0"/>
    <script src="<c:out value='${currentPage.name}'/>.angular-app-module.js"></script>
    <script src="<c:out value='${currentPage.name}'/>.angular-app-controllers.js"></script>
    <cq:include script="js_clientlibs.jsp"/>
</body>