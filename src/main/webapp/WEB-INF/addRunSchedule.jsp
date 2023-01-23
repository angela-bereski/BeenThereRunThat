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
	<title>Schedule a Run</title>
</head>
<body>
	<header>
		<h1>Schedule a Run</h1>
		<nav>
			<a href="/dashboard">Home</a>
		</nav>
	</header>
	<main>
	<div>
	<h2>When Is Your Next Run?</h2>
	<br/>
			<form:form action="/runschedule/add" method="post" modelAttribute="runSchedule">
				<input type="hidden" name="_method" value="GET">
				<p>
					<form:label path="dateOfRun">Date of Run:</form:label>
					<form:errors path="dateOfRun" />
					<form:input type="date" path="dateOfRun" />
				</p>
				<p>
					<form:label path="numMiles"># of Miles:</form:label>
					<form:errors path="numMiles" />
					<form:input type="number" path="numMiles" />
				</p>
				<button>Submit</button>
			</form:form>
	</div>
	</main>
</body>
</html>