<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page isErrorPage="true" %> 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- CSS only -->
	<link rel="stylesheet" href="./css/dashboard.css">
	<title>Been There, Run That Dashboard</title>
</head>
<body class=" col-12">
	<input type="hidden" name="_method" value="GET">
	<header>
		<h1>Welcome, <c:out value="${user.firstName}"/>!</h1>
		<nav>
		<a href="/">Logout</a>
		<a href="/training/add">Log a Run</a>
		</nav>
	</header>
	<main>
		<div class="row2">
			<h2>Completed Runs</h2>
			<h4>Check out your fave runner's stats. Throw some kudos for a job well done!</h4>
			<table class="table table-bordered">
				<thead>
					<tr class="table-dark">
						<th scope="col">Runner Name</th>
						<th scope="col">Date of Run</th>
						<th scope="col">Location</th>
						<th scope="col">Number of Miles</th>
						<th scope="col">Run Company</th>
						<th scope="col">Reflection</th>
						<th scope="col">Give Kudos!</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="trainingLogs" items="${trainingLogs}">
						<tr class="table-light">
								<td><c:out value="${trainingLogs.raceRunner.firstName}"></c:out></td>
								<td><fmt:formatDate value="${trainingLogs.dateOfRun}" pattern="MM-dd-yyyy"/></td>
								<td><c:out value="${trainingLogs.location}"></c:out></td>
								<td><c:out value="${trainingLogs.numMiles}"></c:out></td>
								<td><c:out value="${trainingLogs.ranWith}"></c:out></td>
								<td><c:out value="${trainingLogs.runReflection}"></c:out></td>
								<td><button type="button" class="btn btn-info"><a href="/training/${trainingLogs.id}">Cheer! <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
</svg></a></button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
			<form action="/training/add" method="GET">
				<button>Log a Recent Run</button>
			</form>
		<div class="row2">
			<h2>Future Runs</h2>
			<h4>Motivation Station. Request a mile dedication and feel the run love!</h4>
			<table class="table table-bordered">
				<thead>
					<tr class="table-dark">
						<th scope="col">Runner Name</th>
						<th scope="col">Date of Run</th>
						<th scope="col">Number of Miles</th>
						<th scope="col">Request a Mile Dedication</th>					
					</tr>
				</thead>
				<tbody>
					<c:forEach var="runSchedules" items="${runSchedules}">
						<tr class="table-light">
								<td><c:out value="${runSchedules.raceRunnerF.firstName}"></c:out></td>
								<td><fmt:formatDate value="${runSchedules.dateOfRun}" pattern="MM-dd-yyyy"/></td>
								<td><c:out value="${runSchedules.numMiles}"></c:out></td>
								<td>
									<button type="button" class="btn btn-info"><a href="/runschedule/${runSchedules.id}">Request <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
  <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
</svg></a></button>
								</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
			<form action="/runschedule/add" method="GET">
				<button>Schedule a Run</button>
			</form>
	</main>
</body>
</html>