<%@page import="com.guru.order.service.MetaDataLoader"%>
<%
String groups = (String) application.getAttribute(MetaDataLoader.GROUPS);
if (groups == null) {
	MetaDataLoader.loadData(application);
}
%>