<%@tag description="Template principal" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- %@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"% -->

<!doctype html>
<html lang="pt-br">
	<header>
		<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="assets/css/bootstrap.min.css">
		<link rel="stylesheet"	href="assets/fontawesome/css/fontawesome.min.css">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="assets/css/main.css">
		<title>Gestor Financeiro</title>
	</header>
<body>
	<!-- Navbar -->
	<%@ include file="horizontal-navbar.tag"%>
	<!-- /Navbar -->

	<!-- Flash Message ->
	<div class="alert alert-danger" role="alert">
	  	Mensagem de erro
	</div>
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