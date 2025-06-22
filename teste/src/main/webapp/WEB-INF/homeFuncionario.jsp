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
    <title>Página do funcionario</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
</head>

<body>
    <!--Nav bar fixa no top-->
    <nav class="nav_menu">
        <ul>
            <li>
                <a class="current_page" href="#">Home</a>
            </li>
            <li class="perfil">
                <a href="${pageContext.request.contextPath}/usuarioServlet?acao=verPerfil">
                    <c:out value="${sessionScope.usuario.nome}"/>!
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/usuarioServlet?acao=sair">Sair</a>
            </li>
        </ul>
    </nav>

    
    <main class="content_layout">
        <p style="text-align: right; width: 100%; color:rgb(20,20,20); padding: 2px;">
            <span style="background-color: white;">contate seu gerente: </span>
            <span style="background-color: bisque;">
                ${sessionScope.ger_funcionario.usuario.nome} 
                ${sessionScope.ger_funcionario.usuario.email}
                ${sessionScope.ger_funcionario.usuario.celular}
            </span>
        </p>
        <h2 class="title">Página do Funcionário</h2>
        <table class="table_model">
            <caption>Dados Pessoais</caption>
            <thead>
                <th>Nome</th>
                <th>CPF</th>
                <th>Salário Atual</th>
                <th>Email</th>
                <th>Celular</th>
                <th>Cargo</th>
            </thead>
            <tbody>
                <td><c:out value="${sessionScope.usuario.nome}"/></td>
                <td><c:out value="${sessionScope.usuario.cpf}"/></td>
                <td><c:out value="${sessionScope.usuario.salario}"/></td>
                <td><c:out value="${sessionScope.usuario.email}"/></td>
                <td><c:out value="${sessionScope.usuario.celular}"/></td>
                <td><c:out value="${sessionScope.funcionario.cargo}"/></td>
            </tbody>
        </table>

        <h3>Produtos da sua seção</h3>

        <!--tabela de produtos que o funcionário pode ver de seu gerente-->
        <c:choose>
        <c:when test="${empty listaProdutos}">
            <h3>Nenhum produto cadastrado</h3>
        </c:when>
        <c:otherwise>
            <table class="table_model">
                <thead>
                    <th>nome:</th>
                    <th>quantidade:</th>
                    <th>valor:</th>
                    <th>id:</th>
                </thead>
                <tbody>
                <c:forEach var="p" items="${listaProdutos}">
                    <tr>
                        <td>${p.nome}</td>
                        <td>${p.quantidade}</td>
                        <td>${p.valor}</td>
                        <td>${p.id}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
        </c:choose>

    </main>
</body>
</html>