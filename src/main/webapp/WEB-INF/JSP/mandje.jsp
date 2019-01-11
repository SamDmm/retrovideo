<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <vdab:head title="Mandje"/>
</head>
<body>
  <vdab:menu/>
  <h1>Mandje</h1>
  <c:url value="/mandje/verwijderen" var="url"/>
  <form action='${url}' method='post'>
  <table>
    <tr>
      <th>Film</th>
      <th>Prijs</th>
      <th><input type="submit" value='Verwijderen'></th>
    </tr>
    <c:forEach var="film" items="${films}">
    <tr>
      <td>${film.titel}</td>
      <td>&euro; <spring:eval expression="film.prijs"/></td>
      <td class='verwijderenKolom'><input type='checkbox' name='verwijderFilmId' value='${film.id}'></td>
    </tr>
    </c:forEach>
    <tr>
      <td>Totaal: </td>
      <td>&euro; <spring:eval expression="prijsTotaal.waarde"/></td>
      <td></td>
    </tr>
  </table>
  </form>
</body>
</html>

