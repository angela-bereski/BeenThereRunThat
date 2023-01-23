<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page isErrorPage="true" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/edits.css">
	<title>Edit Training Log</title>
</head>
<body>
	<header>
		<h1>Edit Training Run</h1>
		<nav>
			<a href="/dashboard">Home</a>
		</nav>
	</header>
	<main>
	<div>
		<h2>Edit Your Run</h2>
		<br/>
		<form:form action="/training/edit/${trainingLog.id }" method="post" modelAttribute="trainingLog">
			<input type="hidden" name="_method" value="GET">
			<input type="hidden" name="_method" value="PUT">
			<p>
				<form:label path="name">Name of Run:</form:label>
				<form:errors path="name" />
				<form:input path="name" />
			</p>
			<p>
				<form:label path="dateOfRun">Date of Run:</form:label>
				<form:errors path="dateOfRun" />
				<form:input type="date" path="dateOfRun" />
			</p>
			<p>
				<form:label path="location">Location:</form:label>
				<form:errors path="location" />
				<form:input path="location" />
			</p>
			<p>
				<form:label path="numMiles">Miles Completed:</form:label>
				<form:errors path="numMiles" />
				<form:input type="number" path="numMiles" />
			</p>
			<p>
				<form:label path="ranWith">Ran With:</form:label>
				<form:errors path="ranWith" />
				<form:input path="ranWith" />
			</p>
			<p>
				<form:label path="runReflection">Run Notes:</form:label>
				<form:errors path="runReflection" />
				<form:textarea class="input" rows="6" path="runReflection" />
			</p>
			<p>
				<form:label path="runNutrition">Nutrition:</form:label>
				<form:errors path="runNutrition" />
				<form:textarea class="input" rows="6" path="runNutrition" />
			</p>
			<button>Submit</button>
		</form:form>
	</div>
	</main>
</body>
</html>