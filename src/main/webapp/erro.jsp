<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Erro no Sistema</title>
</head>
<body>
    <h1>Ocorreu um erro 😥</h1>

    <p><strong>Status:</strong> ${pageContext.errorData.statusCode}</p>
    <p><strong>Servlet:</strong> ${pageContext.errorData.servletName}</p>
    <p><strong>URL:</strong> ${pageContext.errorData.requestURI}</p>
    <p><strong>Exceção:</strong> <%= exception != null ? exception.getClass().getName() : "N/A" %></p>
    <p><strong>Mensagem:</strong> <%= exception != null ? exception.getMessage() : "Erro desconhecido." %></p>

    <a href="${pageContext.request.contextPath}/">Voltar à página inicial</a>
</body>
</html>
