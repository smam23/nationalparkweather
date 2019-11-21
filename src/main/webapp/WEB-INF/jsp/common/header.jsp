<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<!-- <link href="https://fonts.googleapis.com/css?family=Quicksand:300&display=swap" rel="stylesheet"> -->
<link href="https://fonts.googleapis.com/css?family=Advent+Pro|Quicksand&display=swap" rel="stylesheet">

<meta charset="UTF-8" content="width=device-width, initial-scale=1">
<title>National Park Geek</title>
<c:url value="/css/site.css" var="cssHref" />
<link rel="stylesheet" href="${cssHref}">


</head>

<body>
	<header>
		<c:url value="/" var="homePageHref" />
		<c:url value="/img/logo.png" var="logo" />
		<a href="${homePageHref}"> <img src="${logo}"
			alt="National Park Geek logo" />
		</a>
	</header>
	
<nav id="navbar">
		<ul>
			<li>
			<a href="<c:url value="/"/>" >Home Page</a>
			</li>
			<li>
			<a href="<c:url value="/survey"/>" >Vote For Your Favorite National Park</a>
			</li>
			<li>
			<a href="<c:url value="/favoriteParks"/>">See Popular National Parks!</a>
			</li>
		</ul>
	</nav>
