<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página do cadastro do usuário</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
</head>

<body>

   <nav class="nav_menu">
        <ul>
            <li><a href="${pageContext.request.contextPath}/usuarioServlet?acao=irIndex">Atacadão</a></li>
            <li><a href="${pageContext.request.contextPath}/usuarioServlet?acao=irLogin">Login</a></li>
            <li><a class="current_page" href="#">Cadastro</a></li>
            <li><a href="${pageContext.request.contextPath}/usuarioServlet?acao=irLoginADM">ADM</a></li>
        </ul>
    </nav>

    <main class="content_layout">
        <h2 class="title">Cadastro do Usuário</h2>

        <form method="post" action="<%=request.getContextPath()%>/usuarioServlet" class="form">
            <label for="nome">Nome:   </label>
            <input id="nome" name="nome" type="text" placeholder="*" required><br>
            <label for="email">Email:  </label>
            <input id="email" name="email" type="text" placeholder="*" required><br>
            <label for="celular">Celular:</label>
            <input id="celular" name="celular" type="text" placeholder="*" required><br>
            <label for="senha">Senha:  </label>
            <input id="senha" name="senha" type="password" placeholder="*" required><br>
            <label for="cpf">CPF:    </label>
            <input id="cpf" name="cpf" type="text" placeholder="*" required><br>
            <p style="color: rgb(255, 255, 255); font-size: 0.8rem;">todos os campos * são obrigatórios</p>

            <button class="btn" id="ok" type="submit" value="cadastrar" name="op">Cadastre-se</button>
            <button class="btn" id="cancel" type="reset">Reset</button>
        </form>

        <c:if test="${not empty msg}">
                <h3>${msg}</h3>
        </c:if>
    </main>
</body>
</html>