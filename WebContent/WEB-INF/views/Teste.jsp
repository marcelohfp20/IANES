<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   <c:url value="/app/adm/usuario/novo" var="urlUsuarioNovo" />
   <c:url value="/app/ambiente" var="urlAmbiente" />
   <c:url value="/app/categoria" var="urlCategoria" />
   <c:url value="/app/adm/usuario" var="urlListaUsuario" />
   <c:url value="/app/patrimonio" var="urlPatrimonio" />
   <c:url value="/app/usuario/alterarSenha" var="urlAlterar" />
   <c:url value="/" var="raiz" />
   <c:url value="/assets" var="assets" />
   <c:url value="/app/itemDePatrimonio" var="urlItem" />
      <c:url value="/sair" var="urlSair" />
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	body{
 		background-image: url('${raiz}assets/images/bbb.png');
		 background-size: 100%;
		 background-repeat: no-repeat;
	 }
	 
	 .menu{
	margin-left: 30%;
	font-size: 40px;
	font-family: sans-serif;  
     }
     .tit{
     color:black;
     margin-left: 42%;
	margin-top: 3%;
	font-size: 50px;
	}
	
 </style>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:import url="temp/head.jsp"/>

<title>Menu</title>
</head>
<body>

<div class = "tit">
<h1>IANES</h1>
</div>
<br>

<div class = "menu">


	<a href ="${urlAmbiente}">Ambientes</a>
	<br>
	<c:if test="${usuarioAutenticado.tipo eq 'ADMINISTRADOR' }">
	<a href = "${urlListaUsuario}">Usuarios</a>
	<br>
	</c:if>
	<a href = "${urlPatrimonio}">Patrimonios</a>
	<br>
	<a href = "${urlCategoria}">Categorias</a>
	<br>
	<a href = "${urlItem}">Itens</a>
	<br>
	<a href = "${urlAlterar}">Alterar Senha</a>
	<br>
	<a href = "${urlSair}">Sair</a>
	
	
	
</div>
	
			

</body>
</html>