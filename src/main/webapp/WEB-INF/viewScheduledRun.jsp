<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page isErrorPage="true" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/view2.css">
	<title>Mile Dedication Request</title>
</head>
<body>
	<input type="hidden" name="_method" value="GET">
	<header>
		<h1><c:out value="${runSchedule.raceRunnerF.firstName}"></c:out>'s Run: <c:out value="${runSchedule.numMiles}"></c:out> Miles on <fmt:formatDate value="${runSchedule.dateOfRun}" pattern="MM-dd-yyyy"/></h1>
		<nav>
			<a href="/dashboard">Home</a>
		</nav>
	</header>
	<main>
		<div class="row2">
			<h2>Mile Dedication Requests</h2>
			<br/>
			<h4>Motivate your fave runner with some inspiration or have them send some good thoughts your way.</h4>
			<h4>Running thoughts are full of power!</h4>
			<br/>			
			<table class="table table-bordered">
				<thead>
					<tr class="table-dark">
						<th scope="col">Mile #</th>
						<th scope="col">Dedication Requester</th>
						<th scope="col">Request</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="mileDedications" items="${runSchedule.dedications}">
						<tr class="table-light">
								<td><c:out value="${mileDedications.whichMile}"></c:out></td>
								<td><c:out value="${mileDedications.mileRequester.firstName}"></c:out></td>
								<td><c:out value="${mileDedications.dedicationRequest}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<hr>
		<c:choose>
			<c:when test="${runSchedule.raceRunnerF.id == user.id}">
				<form action="/runschedule/edit/${runSchedule.id}" method="Get">
					<button>Edit Scheduled Run Log</button>
				</form>
				<form action="/runschedule/${runSchedule.id}" method="POST">
					<input type="hidden" name="_method" value="DELETE">
					<button>Delete</button>
				</form>
			</c:when>
			<c:otherwise>
				<p></p>
			</c:otherwise>
		</c:choose>
		<div>
		<h2>Request a Mile Dedication!</h2>
				<form action="/milededication/${runSchedule.id}" method="Get">
					<button>Request a Dedication</button>
				</form>
		</div>
		<hr>
	</main>
</body>
</html>