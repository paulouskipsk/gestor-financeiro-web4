<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Gestor Financeiro</title>	
	<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="assets/css/login.css">
	
	<script src="assets/js/jquery-4.3.1.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/main.js"></script>
</head>
<body>
	<!-- Flash Message -->
	<c:forEach var="message" items="${messages}">
		<div class="alert alert-${message.key} alert-dismissible fade show" id="flash-message" role="alert">
			<h3><strong>Menssagem</strong></h3>	<hr/>
			${message.value}
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:forEach>
	<!-- /Flash Message -->

	<form class="form-signin" method="post" action="login">
		<div class="text-center mb-4">
			<img class="mb-4" src="assets/img/user.png" id="image-login">
		</div>

		<div class="form-label-group">
			<input type="text" name="username" class="form-control"
				placeholder="Usuario" required autofocus> <label
				for="inputUsername">Usuario</label>
		</div>

		<div class="form-label-group">
			<input type="password" name="password" class="form-control"
				placeholder="Password" required> <label for="inputPassword">Senha</label>
		</div>

		<button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>

		<p class="mt-5 mb-3 text-muted text-center">&copy; 
			Gestor Financeiro
		</p>
	</form>

</body>
</html>