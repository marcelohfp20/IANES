<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/" var="raiz" />
<c:url value="/assets" var="assets" />
<c:url value="/app/patrimonio/salvar" var="urlSalvarPatrimonio" />
 <c:url value="/app/adm/itemDePatrimonio" var="urlItem" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Patrimonio</h1>
	<form:form modelAttribute="patrimonio" action="${urlSalvarPatrimonio}" method="post" class="grid-flex">
			<form:hidden path="id"/>
			
			<form:input path="nome" placeholder="Patrimonio"/>	
			
			<form:select path="categoria.id" items="${categorias}"
					itemValue="id" itemLabel="nome"></form:select>
					
					<button type="submit" class="btn btn-blue col flex-1">SALVAR</button>
					
					
				
</form:form>

</body>
</html>