<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>

<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        return;
    }
%>


<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Produto</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
</head>
<body>

     <!--nav bar fixa no topo-->
    <nav class="nav_menu">
        <ul>
            <li>
                <a  href="${pageContext.request.contextPath}/gerenteServlet?acao=voltar">Voltar</a>
            </li>
            <li class="perfil"><c:out value="${sessionScope.usuario.nome}"/>!</li>
            <li>
                <a class="current_page" href="#">Editar Produto</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/gerenteServlet?acao=listarProdutos">
                Seu Estoque
                </a>
            </li>
            <li><a href="${pageContext.request.contextPath}/usuarioServlet?acao=sair">Sair</a></li>
        </ul>
    </nav>

     <main class="content_layout">
        <!-- mostra o produto a ser editado passao pela servlet no atributo p_edit -->
        <table class="table_model">
            <thead>
                <th>Nome</th>
                <th>Preço</th>
                <th>Quantidade</th>
            </thead>
            <tbody>
                <td>${p_edit.nome}</td>
                <td>${p_edit.valor}</td>
                <td>${p_edit.quantidade}</td>
            </tbody>
        </table>

        <form class="form" action="${pageContext.request.contextPath}/gerenteServlet" method="post">
            <label for="nome">Nome:</label>
            <input id="nome" type="text" name="nome" value="${p_edit.nome}">
            <label for="quantidade">Quantidade:</label>
            <input id="quantidade" type="number" name="quantidade" value="${p_edit.quantidade}">
            <label for="valor">Preço:</label>
            <input id="valor" type="number" step="0.01" name="valor" value="${p_edit.valor}">
            <input type="hidden" name="id" value="${p_edit.id}">
            <button type="submit" name="acao" value="alterarProduto">Confirmar</button>
        </form>
    </main>
</body>
</html>