<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

	<c:url value="/survey" var="surveyUrl" />
<div id="bg-white">
<section id="survey-main">
	<h2>Vote For Your Favorite National Park!</h2>
		<form:form action="${surveyUrl}" method="POST" modelAttribute="survey">
			
			<label for="name">Favorite National Park: </label>
			<select name="parkCode">
				<c:forEach var="park" items="${parks}" >
					<option value="${park.parkCode}">${park.parkName} (${park.parkCode.toUpperCase()})</option>
				</c:forEach>
			</select>
			<br />
			<br />	
			<label for="email">Your email:</label>
			<form:input type="text" path="email" placeholder="youremail@email.com"/>	
			<form:errors path="email" cssClass="error"/>
			<br />
			<br />	
			<label for="state">State of residence:</label>
			<select name="state">
				<option value="AL">Alabama - AL</option>
				<option value="AK">Alaska - AK</option>
				<option value="AZ">Arizona - AZ</option>
				<option value="AR">Arkansas - AR</option>
				<option value="CA">California - CA</option>
				<option value="CO">Colorado - CO</option>
				<option value="CT">Connecticut - CT</option>
				<option value="DE">Delaware - DE</option>
				<option value="FL">Florida - FL</option>
				<option value="GA">Georgia - GA</option>
				<option value="HI">Hawaii - HI</option>
				<option value="ID">Idaho - ID</option>
				<option value="IL">Illinois - IL</option>
				<option value="IN">Indiana - IN</option>
				<option value="IA">Iowa - IA</option>
				<option value="KS">Kansas - KS</option>
				<option value="KY">Kentucky - KY</option>
				<option value="LA">Louisiana - LA</option>
				<option value="ME">Maine - ME</option>
				<option value="MD">Maryland - MD</option>
				<option value="MA">Massachusetts - MA</option>
				<option value="MI">Michigan - MI</option>
				<option value="MN">Minnesota - MN</option>
				<option value="MS">Mississippi - MS</option>
				<option value="MO">Missouri - MO</option>
				<option value="MT">Montana - MT</option>
				<option value="NE">Nebraska - NE</option>
				<option value="NV">Nevada - NV</option>
				<option value="NH">New Hampshire - NH</option>
				<option value="NJ">New Jersey - NJ</option>
				<option value="NM">New Mexico - NM</option>
				<option value="NY">New York - NY</option>
				<option value="NC">North Carolina - NC</option>
				<option value="ND">North Dakota - ND</option>
				<option value="OH">Ohio - OH</option>
				<option value="OK">Oklahoma - OK</option>
				<option value="OR">Oregon - OR</option>
				<option value="PA">Pennsylvania - PA</option>
				<option value="RI">Rhode Island - RI</option>
				<option value="SC">South Carolina - SC</option>
				<option value="SD">South Dakota - SD</option>
				<option value="TN">Tennessee - TN</option>
				<option value="TX">Texas - TX</option>
				<option value="UT">Utah - UT</option>
				<option value="VT">Vermont - VT</option>
				<option value="VA">Virginia - VA</option>
				<option value="WA">Washington - WA</option>
				<option value="WV">West Virginia - WV</option>
				<option value="WI">Wisconsin - WI</option>
				<option value="WY">Wyoming - WY</option>
				<option value="AS">American Samoa - AS</option>
				<option value="DC">District of Columbia - DC</option>
				<option value="FM">Federated States of Micronesia - FM</option>
				<option value="GU">Guam - GU</option>
				<option value="MH">Marshall Islands - MH</option>
				<option value="MP">Northern Mariana Islands - MP</option>
				<option value="PW">Palau - PW</option>
				<option value="PR">Puerto Rico - PR</option>
				<option value="VI">Virgin Islands - VI</option>
			</select>
			<br />
			<br />	
			<form:radiobutton path="activityLevel" value="Inactive"/>
			<label for="Inactive">Inactive</label>
			<form:radiobutton path="activityLevel" value="Sedentary"/>
			<label for="Sedentary">Sedentary</label>
			<form:radiobutton path="activityLevel" value="Active"/>
			<label for="Active">Active</label>
			<form:radiobutton path="activityLevel" value="Extremely Active"/>
			<label for="Extremely Active">Extremely Active</label><form:errors path="activityLevel" cssClass="error"/><br />
			<br />
			<br />	
			<button type="submit">Cast Your Vote!</button>
			<br />
			<br />	
		</form:form>
</section>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />