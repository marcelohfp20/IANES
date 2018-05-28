<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <c:url value="/app/adm/categoria/salvar" var="urlSalvarCategoria" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro Categoria</title>
</head>
<body>

<form:form modelAttribute="categoriaPatrimonio" action="${urlSalvarCategoria}" method="post">
						<form:hidden path="id"/>
						<div class="flex-grid">
							<div class="row">
								<div class="col flex-1">
									<label>
										Nome
										<form:input path="nome"/>
										<form:errors path="nome" />
									</label>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<button type="submit" class="btn btn-blue">SALVAR</button>
								</div>
								</div>
								</div>
								</form:form>


</body>
</html>