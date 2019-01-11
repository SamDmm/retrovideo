<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <vdab:head title="reserveren"/>
</head>
<body>
  <vdab:menu/>
  <h1>Reserveren</h1>
  <c:if test="${not empty genres}">
  <nav>
    <ul>
      <c:forEach var="genre" items="${genres}">
        <spring:url value='/{id}' var='url'>
          <spring:param name='id' value='${genre.id}'/>
        </spring:url>
        <li><a href="${url}">${genre.naam}</a></li>
      </c:forEach>
    </ul>
  </nav>
  </c:if>
  <c:if test="${not empty filmsVanGenre}">
    <ul>
    <c:forEach var="film" items="${filmsVanGenre}">
      <spring:url value="/film/{filmId}" var="filmUrl">
        <spring:param name="filmId" value="${film.id}"/>
      </spring:url>
      <a href="${filmUrl}">
        <img src="<c:url value="/images/${film.id}.jpg"/>" alt="Afbeelding ${film.titel}" title='${film.titel}: ${film.gereserveerd < film.voorraad ? "reservatie mogelijk" : "reservatie niet mogelijk"}'>
      </a>
    </c:forEach>
    </ul>
  </c:if>
</body>
</html>

