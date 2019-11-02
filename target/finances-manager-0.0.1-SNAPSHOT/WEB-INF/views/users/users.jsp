<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:template>
	<jsp:body>
		<h1 class="title-page">Usuários</h1>

		<a href="novo-usuario">
		<button type="button" class="btn btn-primary button-add">
			<i class="large material-icons">add</i>
		</button>
		</a>
		<div id="user-roles">
			<a href="#">
				<h5 id="user-roles-text">Regras de Usuario cadastradas </h5>
				
			</a>
		</div>
		
		<table class="table table-striped">
			<thead class="bg-info"> <!-- class="thead-dark"-->
				<tr>
				  <th scope="col">COD.</th>
				  <th scope="col">NOME</th>
				  <th scope="col">USUÁRIO</th>
				  <th scope="col">PERFIL ACESSO</th>
				  <th scope="col">AÇÃO</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ users }" var="user">
					<tr>
						<td>${ user.id }</td>
						<td>${ user.name }</td>
						<td>${ user.username }</td>
						<td>${ user.userRole.role == "USR" ? "Usuário" : "Administrador" }</td>
						<td>
							<a href="editar-usuario?id=${user.id}">Editar</a> | <a href="deletar-usuario?id=${user.id}" class="delete">Deletar</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</jsp:body>
</t:template>