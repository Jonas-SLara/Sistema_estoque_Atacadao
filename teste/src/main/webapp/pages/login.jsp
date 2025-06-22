<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
</head>
<body>

    <nav class="nav_menu">
        <ul>
            <li><a href="${pageContext.request.contextPath}/usuarioServlet?acao=irIndex">Atacadão</a></li>
            <li><a class="current_page" href="#">Login</a></li>
            <li><a href="${pageContext.request.contextPath}/usuarioServlet?acao=irCadastro">Cadastro</a></li>
            <li><a href="${pageContext.request.contextPath}/usuarioServlet?acao=irLoginADM">ADM</a></li>
        </ul>
    </nav>

    <main class="content_layout">
        <h2 class="title">Login do Usuario</h2>
        <c:if test = "${not empty erro}">
            <p style="color : rgb(180, 0, 0);">${erro}</p>
        </c:if>

        <form method="POST" action="<%=request.getContextPath()%>/loginServlet" class="form">
            <label for="cpf">CPF:   </label>
            <input id="cpf" name="cpf" type="text" placeholder="*" required><br>
            <label for="senha">Senha: </label>
            <input id="senha" name="senha" type="password" placeholder="*" required><br>
            <p style="color: rgb(255, 255, 255); font-size: 0.8rem;">todos os campos * são obrigatórios</p>
            <button class="btn" id="ok" type="submit" name="op" value="login">Login</button>
        </form>
    </main>

</body>
</html>