<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page isErrorPage="true" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- CSS only -->
	<link rel="stylesheet" href="css/styles.css">
	<title>Marathon Connection</title>
</head>
<body>
	<header>
		<h1>&nbsp Been There, Run That&nbsp</h1>
	</header>
	<main>
		
		<div class="col1">
			<h2>Register</h2>
			<br/>
			<form:form action="/register" method="POST" modelAttribute="user">
				<p class="mb-3">
					<form:label path="firstName" class="form-label">First Name:</form:label>
					<form:input path="firstName" class="form-control"/>
					<form:errors path="firstName" />
				</p>
				<p class="mb-3">
					<form:label path="lastName" class="form-label">Last Name:</form:label>
					<form:input path="lastName" class="form-control"/>
					<form:errors path="lastName" />

				</p>
				<p class="mb-3">
					<form:label path="email" class="form-label">Email:</form:label>
					<form:input path="email" class="form-control"/>
					<form:errors path="email" />
				</p>
				<p class="mb-3">
					<form:label path="password" class="form-label">Password:</form:label>
					<form:input path="password" class="form-control" />
					<form:errors path="password" />

				</p>
				<p class="mb-3">
					<form:label path="confirm" class="form-label">Confirm Password:</form:label>
					<form:input path="confirm" class="form-control"/>
					<form:errors path="confirm" />
				</p>
				<p>
					<button>Register</button>
				</p>
			</form:form>
		</div>
		<div class="col2">
			<h2>Login</h2>
			<br/>
				<form:form action="/login" method="POST" modelAttribute="logUser">
					<p class="mb-3">
						<form:label path="email" class="form-label">Email:</form:label>
						<form:input path="email" class="form-control"/>
						<form:errors path="email" />
					</p>
					<p class="mb-3">
						<form:label path="password" class="form-label">Password:</form:label>
						<form:input path="password" class="form-control" />
						<form:errors path="password" />

					</p>
					<p class="mb-3">
						<button>Login</button>
					</p>
				</form:form>
		</div>
	</main>
</body>
</html>