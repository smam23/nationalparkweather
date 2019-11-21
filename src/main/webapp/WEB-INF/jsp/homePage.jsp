<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div id="bg-white">
<table id="table">
	<div id="home-table">
	<c:forEach var="park" items="${parks}">
		<c:url var="detailUrl" value="/detail">
				<c:param name="parkCode" value="${park.parkCode}" />
		</c:url>
		
		<tr id="park-list">
				<td id="park-image"> <a href="${detailUrl}"><img src="img/parks/${park.parkCode}.jpg" /></a></td>
		
				<td id="park-name"> <a href="${detailUrl}"><c:out value="${park.parkName}" /></a></td>
			
				<td id="park-description"><c:out value="${park.description}" /></td>
		</tr>
	</c:forEach>
	</div>
</table>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />