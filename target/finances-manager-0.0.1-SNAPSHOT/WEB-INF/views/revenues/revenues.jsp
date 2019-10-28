<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:template>
	<jsp:body>
		<h1 class="title-page">Receitas</h1>
		
		<a href="nova-receita">
		<button type="button" class="btn btn-primary button-add">
			<i class="large material-icons">add</i>
		</button>
		</a>
		
		<table class="table table-striped">
			<thead class="bg-info">
				<tr>
				  <th scope="col">COD.</th>
				  <th scope="col">DESCRIÇÃO</th>
				  <th scope="col">VENCIMENTO</th>
				  <th scope="col">VALOR</th>
				  <th scope="col">STATUS</th>
				  <th scope="col">AÇÃO</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${revenues}" var="revenue">
					<tr>
						<td>${ revenue.id }</td>
						<td>${ revenue.description }</td>
						<td><fmt:formatDate value="${ revenue.dateReceiveble }" pattern="dd/MM/yyyy"/></td>
						<td><fmt:formatNumber value="${ revenue.valueReceiveble }" type ="currency"/></td>
						<td>${ revenue.receivebled == 'F' ? "SIM" : "NÃO" }</td>
						<td>
							<a href="editar-receita?id=${revenue.id}">Editar</a> | 
							<a href="deletar-receita?id=${revenue.id}">Deletar</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:body>
</t:template>