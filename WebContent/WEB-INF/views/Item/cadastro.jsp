<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <c:url value="/app/itemDePatrimonio/salvar" var="urlSalvarItem" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Item</title>
</head>
<body>
<form:form modelAttribute="itemDePatrimonio" action="${urlSalvarItem}" method="post" class="grid-flex">
			<form:hidden path="id"/>
			
			<form:input path="nome" placeholder="itemDePatrimonio"/>	
			
			<form:select path="ambiente.id" items="${ambientes}"
					itemValue="id" itemLabel="nome"></form:select>
					
			<form:select path="patrimonio.id" items="${patrimonios}"
			         itemValue="id" itemLabel="nome"></form:select>
					
					<button type="submit" class="btn btn-blue col flex-1">SALVAR</button>
					
					
				
</form:form>

</body>
</html>