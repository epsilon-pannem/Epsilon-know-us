<%@page session="false"
	    import="com.day.cq.wcm.api.WCMMode" %><%
%><%@include file="/libs/foundation/global.jsp" %><%
%>
<c:set var="wcmMode"><%= WCMMode.fromRequest(request) == WCMMode.EDIT %></c:set>
<cq:includeClientLib css="apps.epsilon.epsilon-mobile-app.all"/>
<cq:includeClientLib css="apps.epsilon-mobile-app.internal"/>
<cq:includeClientLib js="apps.epsilon-mobile-app.modernizer"/>

<!-- Enable all requests, inline styles, and eval() -->
<!-- TODO: set a more restrictive CSP for production -->
<meta http-equiv="Content-Security-Policy" content="default-src 'self' gap://ready; style-src 'self' 'unsafe-inline'; script-src 'self' 'unsafe-inline' 'unsafe-eval'; connect-src *;">