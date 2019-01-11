<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <vdab:head title="klant"/>
</head>
<body>
  <vdab:menu/>
  <h1>Klanten</h1>
  <c:url value="/klant"/>
  <form:form action="${url}" modelAttribute="klantForm" method="get">
    <form:label path="familieNaamDeel">Familienaam bevat: <form:errors path="familieNaamDeel"/></form:label>
    <form:input path="familieNaamDeel" required="required" autofocus="autofocus"/>
    <input type="submit" value="Zoeken">
    <form:errors/>
  </form:form>
  <c:if test="${not empty klanten}">
    <table>
      <tr>
        <th>Naam</th>
        <th>Straat - Huisnummer</th>
        <th>Postcode</th>
        <th>Gemeente</th>
      </tr>
      <c:forEach var="klant" items="${klanten}">
      <spring:url var="klantUrl" value="/mandje/{klantId}">
        <spring:param name="klantId" value="${klant.id}"/>
      </spring:url>
      <tr>
        <td><a href="${klantUrl}">${klant.voornaam} ${klant.familienaam}</a></td>
        <td>${klant.straatnummer}</td>
        <td>${klant.postcode}</td>
        <td>${klant.gemeente }</td>
      </tr>
      </c:forEach>
    </table>
  </c:if>
</body>
</html>