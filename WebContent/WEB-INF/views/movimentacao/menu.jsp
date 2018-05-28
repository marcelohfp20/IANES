<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

     <c:url value="/app/" var="urlVoltar" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
</head>
<body>
			<br>
<a href = "${urlVoltar}">Menu</a>
			<br>
	<h2>Movimentações Cadastradas</h2>
					<table id="tabelaMovimentacoes" class="table container read-container ma-t-l">
						<thead>
							<tr>
								<th>Movimentações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${movimentacoes}" var="movimentacao">
								<tr>
									<td>
										<p class="item-id">
											COD ${movimentacao.id} 
										</p> 
										
										<p>
											<b class="color-pink">Item: </b>
											${movimentacao.itemPatrimonio} 
										</p>
																		
										<p>
											<b class="color-pink">Origem: </b>
											${movimentacao.ambienteOrigem.nome}  
											<b class="color-pink"> | Destino: </b>
											${movimentacao.ambienteDestino.nome} 
										</p>
										<hr>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

</body>
</html>