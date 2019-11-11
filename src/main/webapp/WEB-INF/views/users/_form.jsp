<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form method="POST" action="${action}">
	<input 	type="hidden" name="usrId" value="${user.id == 0 ? '' : user.id}" >

	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="usrId">Codigo</label> 
				<input 	type="number" 
						class="form-control" 
						placeholder="Novo Usuário" 
						value="${user.id == 0 ? '' : user.id}"
						disabled="disabled">
			</div>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="usrName">Nome Completo</label> 
				<input 	type="text" 
						class="form-control ${empty user.errors.name?'':'is-invalid'}" 
						placeholder="Nome Completo" 
						name="usrName" 
						value="${user.name}"
						required="required">
					<div class="invalid-feedback">
						${ user.errors.name }
					</div>
			</div>
		</div>
	</div>
	<c:if test="${action ne 'editar-usuario'}">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="form-group">
					<label for="usrUsername">Usuário</label> 
					<input 	type="text"	
							class="form-control ${empty user.errors.username?'':'is-invalid'}" 
							placeholder="Usuário" 
							name="usrUsername" 
							value="${user.username}"
							required="required">
					<div class="invalid-feedback">
						${ user.errors.username }
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="usrPassword" class="form-inline label-toogle">
					${action eq 'editar-usuario' ? '<a href=#>Alterar Senha</a>' : 'Senha'} 
				</label> 
				<input 	type="password" 
						class="form-control ${action eq 'editar-usuario'?'hide':''} ${empty user.errors.password?'':'is-invalid'}" 
						placeholder="Senha" 	
						name="usrPassword">
				<div class="invalid-feedback">
					${ user.errors.password }
				</div>
			</div>
		</div>
	</div>
	
	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="usrUserRole">Perfil de Acesso</label> 
				<select class="custom-select" name="usrUserRole">
					<c:forEach var="roleAccess" items="${rolesAccess}">
					   <option value="${ roleAccess.key }" ${ roleAccess.key == user.userRole.role ? "selected" : ""}>${ roleAccess.value}</option>
				    </c:forEach>
			    </select>
			</div>
		</div>
	</div>
	
	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="button-save">
				<button class="btn btn-primary button-save">Salvar</button>
			</div>
		</div>
	</div>
</form>



