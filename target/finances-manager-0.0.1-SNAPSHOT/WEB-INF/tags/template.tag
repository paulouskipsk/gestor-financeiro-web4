<%@tag description="Template principal" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html lang="pt-br">
	<header>
		<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="assets/css/bootstrap.min.css">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="assets/css/main.css">		
		<title>Gestor Financeiro</title>
	</header>
<body>
	<!-- Navbar -->
	<%@ include file="horizontal-navbar.tag"%>
	<!-- /Navbar -->

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

	<!-- Content -->
	<main>
	<div class="container-fluid">
		<jsp:doBody />
	</div>
	</main>
	<!-- /Content -->

	<!-- Footer -->
	<%@ include file="footer.tag"%>
	<!--/Footer -->
</body>
</html>