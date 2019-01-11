<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <vdab:head title="Reserveren"></vdab:head>
</head>
<body>
  <vdab:menu/>
  <h1>${film.titel}</h1>
  <img src='<c:url value="/images/${film.id}.jpg"/>' alt='Afbeelding ${film.titel}'> 
  <dl>
    <dt>Prijs</dt><dd>&euro; ${film.prijs}</dd>
    <dt>Voorraad</dt><dd>${film.voorraad}</dd>
    <dt>Gereserveerd</dt><dd>${film.gereserveerd}</dd>
    <dt>Beschikbaar</dt><dd>${film.voorraad - film.gereserveerd}</dd>
  </dl>
  
  <spring:url value="/mandje/{id}" var="url">
    <spring:param name="id" value="${film.id}"/>
  </spring:url>
  <form:form action="${url}" method="post" id="mandjeForm">
    <input type="submit" value="In mandje" id="inMandjeKnop">
  </form:form>
  <script>
    document.getElementById("mandjeForm").onsubmit = function() {
    	document.getElementById("inMandjeKnop").disabled = true;
    }
  </script>
</body>
</html>