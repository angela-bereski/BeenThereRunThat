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
	<title>Log a Run</title>
</head>
<body>
	<header>
		<h1>Log Training Run</h1>
		<nav>
			<a href="/dashboard">Home</a>
		</nav>
	</header>
	<main>
	<div>
		<h2>You Ran It, Now Log It!</h2>
		<br/>
		<form:form action="/training/add" method="post" modelAttribute="trainingLog" enctype="multipart/form-data">
			<input type="hidden" name="_method" value="GET">
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
				<form:textarea class="input" rows="5" path="runReflection" />
			</p>
			<p>
				<form:label path="runNutrition">Nutrition:</form:label>
				<form:errors path="runNutrition" />
				<form:textarea class="input" rows="3" path="runNutrition" />
			</p>
			<p>
				<form:label path="name"><svg xmlns="http://www.w3.org/2000/svg" width="23" height="23" fill="currentColor" class="bi bi-cloud-arrow-up" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M7.646 5.146a.5.5 0 0 1 .708 0l2 2a.5.5 0 0 1-.708.708L8.5 6.707V10.5a.5.5 0 0 1-1 0V6.707L6.354 7.854a.5.5 0 1 1-.708-.708l2-2z"/>
  <path d="M4.406 3.342A5.53 5.53 0 0 1 8 2c2.69 0 4.923 2 5.166 4.579C14.758 6.804 16 8.137 16 9.773 16 11.569 14.502 13 12.687 13H3.781C1.708 13 0 11.366 0 9.318c0-1.763 1.266-3.223 2.942-3.593.143-.863.698-1.723 1.464-2.383zm.653.757c-.757.653-1.153 1.44-1.153 2.056v.448l-.445.049C2.064 6.805 1 7.952 1 9.318 1 10.785 2.23 12 3.781 12h8.906C13.98 12 15 10.988 15 9.773c0-1.216-1.02-2.228-2.313-2.228h-.5v-.5C12.188 4.825 10.328 3 8 3a4.53 4.53 0 0 0-2.941 1.1z"/>
</svg> Upload a Photo:</form:label>
				<form:errors path="name" />
				<input type="file" name="file" />
			</p>
			<button>Submit</button>
		</form:form>
	</div>
	</main>
</body>
</html>