<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<form method="post" action="${action}">
	<input type="hidden" name="expId" value="${ expense.id }">
	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="expId">Código</label> 
				<input type="text" value="${ expense.id }"	class="form-control" placeholder="Nova conta a pagar"
					 disabled>
			</div>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="expDescription">Descrição</label> 
				<input  type="text"
						class="form-control ${empty expense.errors.description?'':'is-invalid'}"   
						placeholder="Descrição da conta a pagar"
						name="expDescription" 
						value="${ expense.description }" 
						required="required">
				<div class="invalid-feedback">
					${ expense.errors.description }
				</div>
			</div>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="expDatePay">Data Vencimento</label>
				 <input type="date" 
				 		class="form-control ${empty expense.errors.datePay?'':'is-invalid'}" 
				 		value="<fmt:formatDate value="${ expense.datePay }" pattern="yyyy-MM-dd"/>" 
				 		name="expDatePay" required="required">
				 <div class="invalid-feedback">
					${ expense.errors.datePay }
				</div>
			</div>
			<div class="invalid-feedback">
					${ expense.errors.datePay }
				</div>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="expValuePay">Valor a Pagar</label>
				<div class="input-group mb-2">
					<div class="input-group-prepend">
						<div class="input-group-text">R$</div>
					</div>
					<input 	type="text" 
							class="form-control ${empty expense.errors.valuePay?'':'is-invalid'}"  
							placeholder="Valor a pagar"
						   value="${ expense.valuePay }"
						   name="expValuePay" required="required">
					<div class="invalid-feedback">
						${ expense.errors.valuePay }
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="fexpPaid">Pago?</label>
				<div class="input-group mb-2">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<input type="checkbox" name="expPaid" class="input-group-text">
						</div>
					</div>
					<input type="text" class="form-control" id="RevReceivebled" value="${ expense.paid == 'F' ? 'SIM' : 'NÃO' }" disabled>
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
