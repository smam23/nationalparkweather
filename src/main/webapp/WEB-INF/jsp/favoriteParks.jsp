<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div id="bg-white">
<table class="fav-table">
	<div=id="home-table">
	<c:forEach var="result" items="${results}">
		<c:url var="favoriteUrl" value="/detail">
				<c:param name="parkCode" value="${result.parkCode}" />
		</c:url>
		
		<tr id="favorite-parks">
				<td id="favorite-image"> <a href="${favoriteUrl}"><img src="img/parks/${result.parkCode}.jpg" /></a></td>
		
				<td id="favorite-park"> <a href="${favoriteUrl}"><c:out value="${result.parkName}" /></a></td>
			
				<td id="favorite-park-votes"><c:out value="${result.voteCount}" /> number of votes!</td>
		</tr>
	</c:forEach>
	</div>
</table>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />