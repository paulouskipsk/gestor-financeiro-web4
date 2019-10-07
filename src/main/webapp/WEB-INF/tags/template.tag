<%@tag description="Template principal" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="pt-br">
	<!-- Head -->
	<%@ include file="header.tag"%>
	<!-- /Head -->
<body>
	<!-- Navbar -->
	<%@ include file="horizontal-navbar-admin.tag"%>
	<!-- /Navbar -->
	
	<!-- Flash Message ->
	<div class="alert alert-danger" role="alert">
	  	Mensagem de erro
	</div>
	<!-- /Flash Message -->
	
	<!-- Content -->
	<main>
		<div class="container-fluid"> <jsp:doBody /> </div>
	</main>
	<!-- /Content -->
	
	<!-- Footer -->
	<%@ include file="footer.tag"%>
	<!--/Footer -->
</body>
</html>