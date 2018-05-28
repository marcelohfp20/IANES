<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/usuario/autenticar" var="urlAutenticarUsuario" />
<html>
<head>
<style>
 body{
 background-image: url('${raiz}assets/images/aa.jpg');
 background-size: 100%;
 background-repeat: no-repeat;
 }
 
 
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:import url="temp/head.jsp"/>
<title>login</title>
</head>
<body>
<div class = "titulo">
<h1>IANES</h1>
</div>
<form action="${urlAutenticarUsuario}" method="post">
				<div class = "form">
				<label>
					E-mail
					<input name="email" type="email" required="required" maxlength="120" id="inputEmail"/>
				</label>
				<label>
				<br>
				<br>
					Senha
					<input name="senha" type="password" required="required" maxlength="20" />
				</label>
				<br>
				<br>
				
				<button class="btn" type="submit">ENTRAR</button>
			</form>
			</div>

</body>
</html>