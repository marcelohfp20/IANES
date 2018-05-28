<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <c:url value="/app/adm/categoria/salvar" var="urlSalvarCategoria" />
    <c:url value = "/app/adm/categoria" var="urlCategoria" />
    <c:url value="/app/adm/categoria/deletar" var="urlDeletarCategoria" />
    <c:url value="/app/adm/categoria/editar" var="urlEditarCategoria" />
     <c:url value="/app/adm/categoria/novo" var="urlNovaCategoria" />
     <c:url value="/app/" var="urlVoltar" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Categoria Patrimonio</title>
</head>
<body>
<h1>Categorias</h1>

	<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR' }">
		<a href = "${urlNovaCategoria}">Nova Categoria</a>
		<br>
	</c:if>
			
<a href = "${urlVoltar}">Menu</a>
			<br>
				<th>Categorias</th>
				<br>
				<br>
			
		
		<c:forEach items ="${categoriaPatrimonio}" var="categoriaPatrimonio">
			<c:choose>
				<c:when test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR' }">
				<a href="${urlEditarCategoria}?id=${categoriaPatrimonio.id}">${categoriaPatrimonio.nome} </a>
				<a href="${urlEditarCategoria}?id=${categoriaPatrimonio.id}">
				</c:when>
				
				<c:otherwise>
				<a>${categoriaPatrimonio.nome}</a>
				</c:otherwise>
			</c:choose>
			
				<c:if test="${not empty categoriaPatrimonio.id}">
				<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR' }">
				<br>
				<a href="${urlDeletarCategoria}?id=${categoriaPatrimonio.id}" class="btn btn-red">DELETAR</a>
				<br>
				</c:if>
				</c:if>
					<br>
					<hr>
		</c:forEach>	
	

</body>
</html>