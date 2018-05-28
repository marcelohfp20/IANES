<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <c:url value="/app/usuario/alterarSenha" var="alterar" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alterar Senha</title>
</head>
<body>
	<h1>ALTERAR SENHA</h1>
		
		<form:form modelAttribute="usuario" action="${alterar}" method="post">
			<form:hidden path="id"/>
						<label for="inputSenhaAntiga">SENHA ANTIGA</label>
						<input type="password" id="inputSenhaAntiga" name="senhaAntiga"/>
						<form:errors path="senha" element="div" cssClass="error"/>

						<label for="inputSenha">NOVA SENHA</label>
						<form:password path="senha" id="inputSenha" name="novaSenha"/>
						<form:errors path="senha" element="div" cssClass="error"/>

				<!-- 
						<label for="inputConfirmaSenha">CONFIRMAR NOVA SENHA</label>
						<input type="password" id="inputConfirmaSenha" name="confirmacaoSenha"/>
						<form:errors path="senha" element="div" cssClass="error"/>
 -->
			<br>
			<button type="submit">SALVAR</button>
		</form:form>
	

</body>
</html>