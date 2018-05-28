<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app/adm/usuario/salvar" var="urlSalvarUsuario" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuario</title>
</head>
<body>

<h1>Cadastrar Usuario</h1>
	<form:form modelAttribute="usuario" action="${urlSalvarUsuario}" method="post" enctype="multipart/form-data">

		<form:hidden path="id"/>

			<label for = "inputNome">Nome</label>
			<form:input path = "nome" id = "inputNome"/>

			<label for = "inputSobrenome">Sobrenome</label>
				<form:input path="sobrenome"  id="inputSobrenome"/>

			<c:if test = "${empty usuario.id}">
				<label for = "inputEmail">Email</label>
					<form:input path = "email" type = "email" id = "inputEmail"/>
					<label for="inputSenha">SENHA</label>
					<form:password path="senha" id="inputSenha"/>
					
					<label for="inputFoto">Foto de Perfil</label>
						<input type="file" name="foto" accept=".png, .jpg, .jpeg">
					
					<label>
							Administrador
							<input type="checkbox" name="isAdministrador" 
							${usuario.tipo eq 'ADMINISTRADOR' ? 'checked'  : ''}
							id="inputAdministrador" class="d-inline">
						</label>
				
			</c:if>
		<button type="submit">SALVAR</button>



		</form:form>


</body>
</html>