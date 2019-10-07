<%@ tag language="java" pageEncoding="UTF-8"%>

<%@attribute name="action"%>

<form method="post" action="${action}">
	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="expDescription">Descrição</label> <input type="text"
					class="form-control" placeholder="DescriÃ§Ã£o da conta a pagar"
					name="expDescription" required="required">
			</div>
		</div>
	</div>

	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="form-group">
				<label for="expDatePay">Data Vencimento</label> <input type="date"
					class="form-control" name="expDatePay" required="required">
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
					<input type="number" class="form-control"
						placeholder="Valor a pagar" name="expValuePay" required="required">
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
					<input type="text" class="form-control" value="NÃO" disabled>
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
