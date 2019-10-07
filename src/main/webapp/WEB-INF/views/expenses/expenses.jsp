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
				  <th scope="col">Cod.</th>
				  <th scope="col">Descrição</th>
				  <th scope="col">Vencimento</th>
				  <th scope="col">Valor</th>
				  <th scope="col">Status</th>
				  <th scope="col">Ação</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">1</th>
					<td>Markdfngh woe gwég eŕg </td>
					<td>Ottojnwçergi hwuer guqe g</td>
					<td>@mdoeçjrgh ip erug ergweg qer </td>
					<td>SIM</td>
					<td>
						<a href="editar-despesa">Editar</a> | <a href="#">Deletar</a>
					</td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td>Jacobe rghuwepr g</td>
					<td>Thorntonejrhg q epg</td>
					<td>@fat egu ergug</td>
					<td>SIM</td>
					<td>
						<a href="#">Editar</a> | <a href="#">Deletar</a>
					</td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td>Larry r ughwue rghue g</td>
					<td>the Bird  r g9eyr gerg</td>
					<td>@twitter guhe wrgu</td>
					<td>SIM</td>
					<td>
						<a href="#">Editar</a> | <a href="#">Deletar</a>
					</td>
				</tr>
			</tbody>
		</table>
	
	</jsp:body>
</t:template>