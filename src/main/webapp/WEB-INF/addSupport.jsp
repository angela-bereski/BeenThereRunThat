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

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/edits.css">
	<title>Cheering Section</title>
</head>
<body>
	<input type="hidden" name="_method" value="GET">
	<header>
		<h1><c:out value="${trainingLog.name}"></c:out></h1>
		<nav>
			<a href="/dashboard">Home</a>
		</nav>
	</header>
	<main>
	<div>
		<h2>Run Deets</h2>
		<br/>
				<table class="table table-bordered">
					<thead>
						<tr class="table-dark">
							<th scope="col">Run</th>
							<th scope="col">Runner</th>
						</tr>
					</thead>
					<tbody>
							<tr class="table-light">
									<td><c:out value="${trainingLog.name}"></c:out></td>
									<td><c:out value="${trainingLog.raceRunner.firstName}"></c:out></td>
							</tr>
					</tbody>
				</table>
		<br/>
		<h2>Give Some Kudos!</h2>
		<br/>
		<form:form action="/support/${trainingLog.id}" method="post" modelAttribute="support">
			<input type="hidden" name="_method" value="GET">
			<input type="hidden" name="id" value="${trainingLog.id}">
			<p>
				<form:label path="supportWords">Add your cheer here:</form:label>
				<form:errors path="supportWords" />
				<form:textarea class="input" rows="3" path="supportWords" />
			</p>
			<button>Submit</button>
		</form:form>
		<br/>
		<h2>Cheering Section</h2>
		<br/>

						<c:forEach var="supports" items="${trainingLog.trainingSupports}">
							<tr class="table-light">
									<td><c:out value="${supports.supportCreator.firstName }"></c:out>: </td>
									<td><c:out value="${supports.supportWords}"></c:out></td>

							</tr>
						</c:forEach>

	</div>
	</main>
</body>
</html>