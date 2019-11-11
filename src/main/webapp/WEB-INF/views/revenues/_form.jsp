<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form method="post" action="${action}">
	<input type="hidden" name="revId" value="${ revenue.id }">
	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="revId">Código</label> 
				<input type="text" value="${ revenue.id }" class="form-control" placeholder="Nova Receita" disabled>
			</div>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="description">Descrição</label> 
				<input type="text"
					   value="${ revenue.description }"
					   class="form-control ${empty revenue.errors.description?'':'is-invalid'}"   
					   placeholder="Descrição da conta a receber"
					   name="revDescription" required="required">
				<div class="invalid-feedback">
					${ revenue.errors.description }
				</div>
			</div>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="dateReceiveble">Data Recebimento</label> 
				<input type="date" 
						class="form-control ${empty revenue.errors.dateReceiveble?'':'is-invalid'}"
				 		value="<fmt:formatDate value="${ revenue.dateReceiveble }" pattern="yyyy-MM-dd"/>"
				 		name="revDateReceiveble" 
				 		required="required">
				 <div class="invalid-feedback">
					${ revenue.errors.dateReceiveble }
				</div>
			</div>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="valueReceiveble">Valor a Receber</label>
				<div class="input-group mb-2">
					<div class="input-group-prepend">
						<div class="input-group-text">R$</div>
					</div>
					<input	name="revValueReceiveble"
							type="text" 
							class="form-control ${empty revenue.errors.valueReceiveble?'':'is-invalid'}"
						   	placeholder="Valor a receber" 
						   	value="${ revenue.valueReceiveble }" 
						   	required="required">
					<div class="invalid-feedback">
					${ revenue.errors.valueReceiveble }
				</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="receivebled">Pago?</label>
				<div class="input-group mb-2">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<input type="checkbox" id="revReceivebledCheckbox" name="receivebled" class="input-group-text">
						</div>
					</div>
					<input type="text" class="form-control" id="revReceivebled" value="NÃO" disabled>
				</div>
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



