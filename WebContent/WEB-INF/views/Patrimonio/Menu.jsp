<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   <c:url value="/app/adm/patrimonio/deletar" var="urlDeletarPatrimonio" />
     <c:url value="/app/patrimonio" var="urlPatrimonio" />
      <c:url value="/app/adm/patrimonio/novo" var="urlNovoPatrimonio" />
         <c:url value="/app/adm/patrimonio/alterar" var="urlEditarPatrimonio" />
         <c:url value="/app/" var="urlVoltar" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patrimonio</title>
</head>
<body>
	<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR' }">
		<a href = "${urlNovoPatrimonio}">Novo Patrimonio</a>
		<br>
	</c:if>
		
					<br>
    <a href = "${urlVoltar}">Menu</a>
			<br>

	<h1>Patrimonios</h1>
	
	<c:forEach items ="${patrimonios}" var="patrimonio">
		<c:choose>
			<c:when test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR' }">
				<p>Patrimonio: <a href="${urlEditarPatrimonio}?id=${patrimonio.id}"> ${patrimonio.nome}</a></p>
					<p>Data de Cadastro: ${patrimonio.dataCadastro}</p> 
					<p></p>
			</c:when>
		<c:otherwise>
				<a>${patrimonio.nome}</a>
			</c:otherwise>
		</c:choose>
				
				<c:if test="${not empty patrimonio.id}">
					<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR' }">
						<a href="${urlDeletarPatrimonio}?id=${patrimonio.id}" class="btn btn-red">DELETAR</a>
						<br>
					</c:if>
				</c:if>
					<br>
					<hr>
					
	
	</c:forEach>

</body>
</html>