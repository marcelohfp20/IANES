<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <c:url value="/app/itemDePatrimonio/novo" var="urlNovoItem" />
    <c:url value="/app/adm/itemDePatrimonio/deletar" var="urlDeletarItem" />
     <c:url value="/app/movimentacao/nova" var="urlMovimentar" />
     <c:url value="/app/movimentacao" var="urlMovimentacoes" />
     <c:url value="/app/" var="urlVoltar" />
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Itens de Patrimonio</title>
</head>
<body>
<h1>Itens</h1>
	<a href = "${urlNovoItem}">Nova Item</a>
			<br>
	<a href = "${urlVoltar}">Menu</a>
			<br>
				<th>Itens</th>
				<br>
				<br>
			
		
		<c:forEach items ="${itemDePatrimonio}" var="itemDePatrimonio">
			
		<a "${urlEditarAmbiente}?id=${itemDePatrimonio.id}">${itemDePatrimonio.nome} </a>
		
				
				<c:if test="${not empty itemDePatrimonio.id}">
				<br>
				<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR' }">
				<a href="${urlDeletarItem}?id=${itemDePatrimonio.id}" class="btn btn-red">DELETAR</a>
				<br>
				</c:if>
				<a href="${urlMovimentar}?idItem=${itemDePatrimonio.id}">Movimentar</a>
				<br>
				<a href="${urlMovimentacoes}?idItem=${itemDePatrimonio.id}">Movimentações</a>
				</c:if>
					<br>
					<hr>
		</c:forEach>	

</body>
</html>