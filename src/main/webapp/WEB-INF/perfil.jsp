<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- VERIFICA SE O A SESSÃO DO USUARIO EXISTE (PRECISA FAZER LOGIN)-->
<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
    <title>Perfil</title>
</head>
<body>
    <nav class="nav_menu">
        <ul>
            <li>
                <!--Se for um gerente a página de perfil deve voltar para homeGerente.jsp em web-inf-->
                <c:if test="${not empty sessionScope.gerente}">
                    <a href="${pageContext.request.contextPath}/gerenteServlet?acao=voltar"> Home </a>
                </c:if>

                <!--Se for um funcionário a página de perfil deve voltar para homeFuncionario.jsp em web-inf-->
                <c:if test="${not empty sessionScope.funcionario}">
                    <a href="${pageContext.request.contextPath}/funcionarioServlet?acao=voltar"> Home </a>
                </c:if>
            </li>
            <li class="perfil">
                <a class="current_page" href="#">
                    <c:out value="${sessionScope.usuario.nome}"/>!
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/usuarioServlet?acao=sair">Sair</a>
            </li>
        </ul>
    </nav>

    <main class="content_layout">
        <table class="table_model">
            <caption>Dados Pessoais</caption>
            <thead>
                <th>Nome</th>
                <th>Email</th>
                <th>Celular</th>
            </thead>
            <tbody>
                <td><c:out value="${sessionScope.usuario.nome}"/></td>
                <td><c:out value="${sessionScope.usuario.email}"/></td>
                <td><c:out value="${sessionScope.usuario.celular}"/></td>
            </tbody>
        </table>
        
        <h3 class="title">Atualizar Perfil</h3>
        <c:if test="${not empty msg}">
            <p>${msg}</p>
        </c:if>
        <!-- alterar nome -->
        <form method="post" action="${pageContext.request.contextPath}/usuarioServlet">
            <label>Nome</label>
            <input type="text" name="nome" value="${sessionScope.usuario.nome}">

            <label>Email</label>
            <input type="text" name="email" value="${sessionScope.usuario.email}">

            <label>Celular</label>
            <input type="text" name="celular" value="${sessionScope.usuario.celular}">
            <input type="hidden" name="cpf" value="${sessionScope.usuario.cpf}">

            <button type="submit" name="op" value="alterarUsuario">Editar</button>
        </form>
    </main>
</body>
</html>