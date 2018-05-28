<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <c:url value="/app/movimentacao/salvar" var="urlMovimentar" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movimentação</title>
</head>
<body>
	${idItem}
	<form:form modelAttribute="movimentacao" action="${urlMovimentar}?idItem=${idItem}" method="post" class="grid-flex">
			<form:hidden path="id"/>	
		<form:hidden path="itemPatrimonio.id" value="${itemDePatrimonio.id}" />
			<form:select path="ambienteDestino.id" items="${ambientes}"
					itemValue="id" itemLabel="nome"></form:select>
					
					<button type="submit" class="btn btn-blue col flex-1">SALVAR</button>
					
					
				
</form:form>

</body>
</html>