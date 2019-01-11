<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <vdab:head title="Rapport"></vdab:head>
</head>
<body>
  <vdab:menu/>
  <h1>Rapport</h1>
  <c:choose>
    <c:when test="${empty filmsMislukteReservaties}">
      <p>De reservatie is ok.</p>
    </c:when>
    <c:otherwise>
      <p>Volgende reservatie zijn mislukt:</p>
      <ul>
      <c:forEach var="film" items="${filmsMislukteReservaties}">
        <li>${film.titel}</li>
      </c:forEach>
      </ul>
    </c:otherwise>
  </c:choose>
</body>
</html>