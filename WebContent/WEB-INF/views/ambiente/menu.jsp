<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <c:url value="/app/adm/ambiente/salvar" var="urlSalvarAmbiente" />
    <c:url value = "/app/ambiente" var="urlAmbiente" />
    <c:url value="/app/adm/ambiente/deletar" var="urlDeletarAmbiente" />
    <c:url value="/app/adm/ambiente/editar" var="urlEditarAmbiente" />
     <c:url value="/app/adm/ambiente/novo" var="urlNovoAmbiente" />
       <c:url value="/app/" var="urlVoltar" />
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ambiente</title>
</head>
<body>
<h1>Ambiente</h1>
	<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR' }">
		<a href = "${urlNovoAmbiente}">Novo Ambiente</a>
		</c:if>
					<br>
<a href = "${urlVoltar}">Menu</a>
		

			<br>
			<br>
				<th>Ambientes</th>
				<br>
				<br>
			
		
		<c:forEach items ="${ambientes}" var="ambiente">
		
		<c:choose>
			<c:when test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR' }">
				<a href="${urlEditarAmbiente}?id=${ambiente.id}">${ambiente.nome} </a>
			</c:when>
			<c:otherwise>
				<a>${ambiente.nome}</a>
			
			</c:otherwise>
		</c:choose>
			
			
				<a href="${urlEditarAmbiente}?id=${ambiente.id}">
				
				<c:if test="${not empty ambiente.id}">
				<br>
				<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR' }">
				<a href="${urlDeletarAmbiente}?id=${ambiente.id}" class="btn btn-red">DELETAR</a>
				<br>
				</c:if>
				</c:if>
					<br>
		</c:forEach>	
		
		
</body>
</html>