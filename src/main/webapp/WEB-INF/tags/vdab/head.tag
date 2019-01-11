<%@ tag description="head van pagina" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true" type="java.lang.String"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<title>${title}</title>
<link href="<c:url value="/css/retrovideo.css"/>" rel="stylesheet">
<link href="<c:url value="/images/retrovideo.ico"/>" rel="icon" type="image/x-icon">
<meta name='viewport' content='width=device-width,initial-scale=1'>
