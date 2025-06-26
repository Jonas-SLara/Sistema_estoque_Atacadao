<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
    <title>Seu Estoque</title>
</head>
<body>

    <!--nav bar fixa no topo-->
    <nav class="nav_menu">
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/gerenteServlet?acao=voltar">Voltar</a>
            </li>
            <li class="perfil">
                <a href="${pageContext.request.contextPath}/usuarioServlet?acao=verPerfil">
                    <c:out value="${sessionScope.usuario.nome}"/>!
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/gerenteServlet?acao=listarFuncionarios">
                Seus Funcionários
                </a>
            </li>
            <li>
                <a class="current_page" href="${pageContext.request.contextPath}/gerenteServlet?acao=listarProdutos">
                Seu Estoque
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/usuarioServlet?acao=sair">Sair</a>
            </li>
        </ul>
    </nav>


    <!-- taglib if testa se uma determinada variavel esta vazia ou não passada pela servlet
     aqui é mostrado o produto que foi excluido -->

    <!-- taglib choose da core tem a função de servir como um switch case enquanto
    a taglib forEach tem a mesma função de uma estrutura de repetição-->
     <main class="content_layout">
        <h2 class="title">Seu Estoque</h2>

        <c:if test="${not empty produtoExcluido}">
        <table class="table_model">
            <legend>Produto Deletado!</legend>
            <thead>
                <th>Nome</th>
                <th>Quantidade</th>
                <th>Valor</th>
                <th>id</th>
                <th>Gerente</th>
            </thead>
            <tbody>
                <td>${produtoExcluido.nome}</td>
                <td>${produtoExcluido.quantidade}</td>
                <td>${produtoExcluido.valor}</td>
                <td>${produtoExcluido.id}</td>
                <td>${sessionScope.usuario.nome}</td>
            </tbody>
        </table>
        </c:if>

        <c:if test="${not empty msg_edit}">
            <h3 style="color: rgb(71, 141, 71);">${msg_edit}</h3>
        </c:if>

        <!--tabela de produtos cadastrados-->
        <c:choose>
        <c:when test="${empty listaProdutos}">
            <h2>Nenhum produto cadastrado</h2>
        </c:when>
        <c:otherwise>
            <table class="table_model">
                <thead>
                    <th>nome:</th>
                    <th>quantidade:</th>
                    <th>valor:</th>
                    <th>id:</th>
                    <th>Edit</th>
                    <th>Delet</th>
                </thead>
                <tbody>
                <c:forEach var="p" items="${listaProdutos}">
                    <tr>
                        <td>${p.nome}</td>
                        <td>${p.quantidade}</td>
                        <td>${p.valor}</td>
                        <td>${p.id}</td>
                        <td>
    <a href="${pageContext.request.contextPath}/gerenteServlet?acao=editarProduto&&info=${p.id}">Editar</a>
                        </td>
                        <td>
    <a href="${pageContext.request.contextPath}/gerenteServlet?acao=excluirProduto&&info=${p.id}">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
    </main>
    
</body>
</html>