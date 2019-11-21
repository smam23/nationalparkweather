<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />
<div id="bg-white">
<section id="detail-page">
<div id="detail-main">
	<h1>${park.parkName}</h1>
	
	<p>${park.description}</p>
	
	<img src="img/parks/${park.parkCode}.jpg" />

	<p id="quote">"${park.quote}" - ${park.quoteSource}</p>
</div>	

<div id="detail-facts">
	<h2>Quick Facts</h2>
	
	<table id="fact-table">
		<tr id="park-facts">
			<th align="left">Located in: </th>
			<td><c:out value="${park.state}" /></td>
		</tr>
		<tr id="park-facts">
			<th align="left">Year Founded: </th>
			<td><c:out value="${park.year}" /></td>
		</tr>
		<tr id="park-facts">
			<th align="left">Climate: </th>
			<td><c:out value="${park.climate}" /></td>
		</tr>
		<tr id="park-facts">
			<th align="left">Elevation: </th>
			<td><c:out value="${park.elevation}" /></td>
		</tr>
		<tr id="park-facts">
			<th align="left">Acreage: </th>
			<td><c:out value="${park.acreage}" /></td>
		</tr>
		<tr id="park-facts">
			<th align="left">Trail Miles: </th>
			<td><c:out value="${park.trailMiles}" /></td>
		</tr>
		<tr id="park-facts">
			<th align="left">Number of Animal Species: </th>
			<td><c:out value="${park.numberOfAnimalSpecies}" /></td>
		</tr>
		<tr id="park-facts">
			<th align="left">Campsites: </th>
			<td><c:out value="${park.numberOfCampsites}" /></td>
		</tr>
		<tr id="park-facts">
			<th align="left">Annual Visitors: </th>
			<td><c:out value="${park.annualVisitorCount}" /></td>
		</tr>
		<tr id="park-facts">
			<th align="left">Entry Fee: </th>
			<td>$<c:out value="${park.entryFee}" /></td>
		</tr>
	</table>
	
	<hr>
	
	<div id="forecast">
		<h3>5-Day Forecast</h3>
		<c:forEach var="forecast" items="${forecast}">
			<c:choose>
				<c:when test="${forecast.forecastDay == 1}">
					<h4>Today's Weather</h4>
					<div id="temp">
						<c:url var="temperatureLink" value="/detail">
							<c:param name="temperature" value="celsius" />
							<c:param name="parkCode" value="${park.parkCode}" />
						</c:url>
						<a href="${temperatureLink}">°C</a>
						<c:url var="temperatureLink" value="/detail">
							<c:param name="temperature" value="fahrenheit" />
							<c:param name="parkCode" value="${park.parkCode}" />
						</c:url>
						<a href="${temperatureLink}"> | °F</a>

					</div>
	
					<div id="forecast-main">
						<img src="img/weather/${forecast.forecast}.png" />
						<c:choose>
							<c:when test="${temperature == 'celsius'}">
								<p>High: ${forecast.celsiusHigh}</p>
								<p>Low: ${forecast.celsiusLow}</p>
							</c:when>
							<c:otherwise>
								<p>High: ${forecast.high}</p>
								<p>Low: ${forecast.low}</p>
							</c:otherwise>	
						</c:choose>	
					</div>
					
					<div id="alert">
					<ul>
						<c:if test="${forecast.forecast == 'snow'}">
						<li>Pack snowshoes!</li>
						</c:if>
						
						<c:if test="${forecast.forecast == 'rain'}">
						<li>Pack rain gear and waterproof shoes!</li>
						</c:if>
						
						<c:if test="${forecast.forecast == 'thunderstorms'}">
						<li>Seek shelter and avoid exposed ridges!</li>
						</c:if>
						
						<c:if test="${forecast.forecast == 'sunny'}">
						<li>Pack sunblock!</li>
						</c:if>
						
						<c:if test="${forecast.high > 75}">
						<li>Bring extra gallon of water!</li>
						</c:if>
						
						<c:if test="${(forecast.high - forecast.low) > 20}">
						<li>Wear breathable layers!</li>
						</c:if>
						
						<c:if test="${forecast.low < 20}">
						<li>BEWARE of exposure to frigid temperatures! BRRR!!!</li>
						</c:if>
						</ul>
					</div>	
				</c:when>
				<c:otherwise>
					<div id="week">
						<div id="day">
							<img src="img/weather/${forecast.forecast}.png" />
							<c:choose>
							<c:when test="${temperature == 'celsius'}">
								<p>High: ${forecast.celsiusHigh}</p>
								<p>Low: ${forecast.celsiusLow}</p>
							</c:when>
							<c:otherwise>
								<p>High: ${forecast.high}</p>
								<p>Low: ${forecast.low}</p>
							</c:otherwise>	
						</c:choose>	
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</div>
</section>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />