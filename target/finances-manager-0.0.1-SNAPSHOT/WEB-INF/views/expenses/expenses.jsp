<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:template>
	<jsp:body>
		<h1 class="title-page">Despesas</h1>
		
		<a href="nova-despesa">
		<button type="button" class="btn btn-primary button-add">
			<i class="large material-icons">add</i>
		</button>
		</a>
		
		<table class="table table-striped">
			<thead class="bg-info"> <!-- class="thead-dark"-->
				<tr>
				  <th scope="col">COD..</th>
				  <th scope="col">DESCRIÇÃO</th>
				  <th scope="col">VENCIMENTO</th>
				  <th scope="col">VALOR</th>
				  <th scope="col">STATUS</th>
				  <th scope="col">AÇÃO</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${expenses}" var="expense">
					<tr>
						<td>${ expense.id }</td>
						<td>${ expense.description }</td>
						<td><fmt:formatDate value="${ expense.datePay }" pattern="dd/MM/yyyy"/></td>
						<td><fmt:formatNumber value="${ expense.valuePay }" type ="currency"/></td>
						<td>${ expense.paid == 'F' ? "NÃO" : "SIM" }</td>
						<td>
							<a href="editar-despesa?id=${expense.id}">Editar</a> | 
							<a href="deletar-despesa?id=${expense.id}" class="delete">Deletar</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:body>
</t:template>