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
	<!-- CSS only -->
	<link rel="stylesheet" href="../css/view.css">
	<title>Training Run Details</title>
</head>
<body>
	<input type="hidden" name="_method" value="GET">
	<header>
		<h1><c:out value="${trainingLog.raceRunner.firstName}"></c:out>'s <c:out value="${trainingLog.name}"></c:out></h1>
		<nav>
			<a href="/dashboard">Home</a>
		</nav>
	</header>
	<main>
		<div>
			<div class="makeCol">
				<h2>Cheering Section</h2>
				<table class="table table-bordered">
					<thead>
						<tr class="table-dark">
							<th scope="col">Cheerleader Name</th>
							<th scope="col">Words of Support</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="supports" items="${trainingLog.trainingSupports}">
							<tr class="table-light">
									<td><c:out value="${supports.supportCreator.firstName }"></c:out></td>
									<td><c:out value="${supports.supportWords}"></c:out></td>
									<td>
										<c:if test="${supports.supportCreator.id == user.id}">
											<form action="/support/${supports.id}" method="POST">
												<input type="hidden" name="_method" value="DELETE">
												<button>Delete</button>
											</form>
										</c:if>
									</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
				<form action="/support/${trainingLog.id}" method="Get">
					<button>Add Some Support</button>
				</form>
		</div>
		<div>
			<h2>Run Details</h2>
			<table class="table table-bordered">
				<thead>
					<tr class="table-dark">
						<th scope="col">Runner Name</th>
						<th scope="col">Date of Run</th>
						<th scope="col">Location</th>
						<th scope="col">Miles Completed</th>
						<th scope="col">Run Company</th>
						<th scope="col">Reflection</th>
						<th scope="col">Nutrition</th>
					</tr>
				</thead>
				<tbody>
						<tr class="table-light">
								<td><c:out value="${trainingLog.raceRunner.firstName}"></c:out> <c:out value="${trainingLog.raceRunner.lastName}"></c:out></td>
								<td><fmt:formatDate value="${trainingLog.dateOfRun}" pattern="MM-dd-yyyy" /></td>
								<td><c:out value="${trainingLog.location}"></c:out></td>
								<td><c:out value="${trainingLog.numMiles}"></c:out></td>
								<td><c:out value="${trainingLog.ranWith}"></c:out></td>
								<td><c:out value="${trainingLog.runReflection}"></c:out></td>
								<td><c:out value="${trainingLog.runNutrition}"></c:out></td>
						</tr>
				</tbody>
			</table>

			<img src = "/${trainingLog.imagePath }" />

			<c:choose>
				<c:when test="${trainingLog.raceRunner.id == user.id}">
					<form action="/training/edit/${trainingLog.id}" method="Get">
						<button>Edit Training Log</button>
					</form>
					<form action="/training/${trainingLog.id}" method="POST">
						<input type="hidden" name="_method" value="DELETE">
						<button>Delete</button>
					</form>
				</c:when>
				<c:otherwise>
					<br/>
					<a href="/dashboard">Home</a>
				</c:otherwise>
			</c:choose>
		</div>
	</main>
</body>
</html>