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
		<h1><c:out value="${runSchedule.raceRunnerF.firstName}"></c:out>'s Upcoming Run: <c:out value="${runSchedule.numMiles}"></c:out> Miles on <fmt:formatDate value="${runSchedule.dateOfRun}" pattern="MM-dd-yyyy"/></h1>
		<nav>
			<a href="/dashboard">Home</a>
		</nav>
	</header>
	<main>
		<div class="row2">
			<h3>Mile Dedication Requests</h3>
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
		<h2>Request a Mile Dedication</h2>
		<form:form action="/milededication/${runSchedule.id}" method="post" modelAttribute="mileDedication">
			<input type="hidden" name="_method" value="GET">
			<input type="hidden" name="id" value="${runSchedule.id}">
			<p>
				<form:label path="whichMile">While Mile:</form:label>
				<form:errors path="whichMile" />
				<form:input path="whichMile" />
			</p>
			<p>
				<form:label path="dedicationRequest">Dedication Request:</form:label>
				<form:errors path="dedicationRequest" />
				<form:textarea class="input" rows="6" path="dedicationRequest" />
			</p>
			<button>Submit</button>
		</form:form>
	</main>
</body>
</html>