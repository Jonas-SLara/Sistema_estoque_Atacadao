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
    <title>Seus Funcionários</title>
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
                <a class="current_page" href="${pageContext.request.contextPath}/gerenteServlet?acao=listarFuncionarios">
                Seus Funcionários
                </a>
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
        <h2 class="title">Seus Funcionários</h2>
        <!--recolhe o funcionario que acabou de ser excluído-->
        <c:if test="${not empty funcionarioExcluido}">
            <table class="table_model">
                <caption>Funcionário Deletado!</caption>
                <thead>
                    <th>Nome</th>
                    <th>Cargo</th>
                    <th>Salário</th>
                    <th>CPF</th>
                </thead>
                <tbody>
                    <td>${funcionarioExcluido.usuario.nome}</td>
                    <td>${funcionarioExcluido.cargo}</td>
                    <td>${funcionarioExcluido.usuario.salario}</td>
                    <td>${funcionarioExcluido.usuario.cpf}</td>
                </tbody>
            </table>
        </c:if>

        <c:if test="${not empty msg_edit}">
            <h3 style="color: rgb(71, 141, 71);">${msg_edit}</h3>
        </c:if>
        <c:choose>
            <c:when test="${empty listaFuncionarios}">
                <p>Nenhum funcionário cadastrado</p>
            </c:when>
            <c:otherwise>
                <table class="table_model">
                    <thead>
                        <th>Id</th>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Celular</th>
                        <th>Salário</th>
                        <th>Cargo</th>
                        <th>Edit</th>
                        <th>Delet</th>
                    </thead>
                    <tbody>
                    <c:forEach var="f" items="${listaFuncionarios}">
                        <tr>
                            <td>${f.id}</td>
                            <td>${f.usuario.nome}</td>
                            <td>${f.usuario.email}</td>
                            <td>${f.usuario.celular}</td>
                            <td>${f.usuario.salario}</td>
                            <td>${f.cargo}</td>
                            <td>
    <a href="${pageContext.request.contextPath}/gerenteServlet?acao=editarFuncionario&&info=${f.id}"> Editar </a>
                            </td>
                            <td>
    <a href="${pageContext.request.contextPath}/gerenteServlet?acao=removerFuncionario&&info=${f.id}"> Remover </a>
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
