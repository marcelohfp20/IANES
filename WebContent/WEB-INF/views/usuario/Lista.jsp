<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app/adm/usuario/editar" var="urlEditarUsuario" />
<c:url value="/app/adm/usuario/novo" var="urlNovoUsuario" />
<c:url value="/app/adm/usuario/deletar" var="urlUsuarioDeletar" />
  <c:url value="/app/" var="urlVoltar" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>


.img{
width: 200px;}

.cas{
font-size: 30px;
margin-left: 50%;}
</style>
<c:import url="../temp/head.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usuario</title>
</head>
<body>
	<div class = "cas">
		<a href = "${urlVoltar}">Menu</a>
	</div>
	<a href = "${urlNovoUsuario}">Novo Usuario</a>
		<br>
			<br>
	<div class = "teste">
	<h1>Usuários</h1>
	
		<c:forEach items = "${usuarios}" var="usuario">
			<a href="${urlEditarUsuario}?id=${usuario.id}">
						<div class="card-header">${usuario.nome} ${usuario.sobrenome}</div>
						</div>
						<div class = "img">
						<img class="img" alt="Foto do usuário" src="${assets}/images/foto_${usuario.id}">
						</div>
			<c:if test="${not empty usuario.id}">
				<a href="${urlUsuarioDeletar}?id=${usuario.id}" class="btn btn-red">DELETAR</a>
				</div>
				<br>
			</c:if>
					</a>
					<br>
					<hr>
		
		 </c:forEach>
		

</body>
</html>