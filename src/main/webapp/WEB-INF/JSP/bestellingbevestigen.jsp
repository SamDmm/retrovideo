<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <vdab:head title="Bestelling bevestigen"/>
</head>
<body>
  <vdab:menu/>
  <h1>Bevestigen</h1>
  <p>${aantalFilmsInMandje} film(s) voor ${klant.voornaam} ${klant.familienaam}</p>
  <spring:url value="/mandje/bevestigen/{klantId}" var="url">
    <spring:param name="klantId" value="${klant.id}"/>
  </spring:url>
  <form:form action="${url}" method="post">
    <input type="submit" value="Bevestigen">
  </form:form>
</body>
</html>