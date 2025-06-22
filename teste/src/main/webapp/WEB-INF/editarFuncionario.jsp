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
    <title>Editar Funcionário</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
</head>
<body>

     <!--nav bar fixa no topo-->
    <nav class="nav_menu">
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/gerenteServlet?acao=voltar">Voltar</a>
            </li>
            <li class="perfil"><c:out value="${sessionScope.usuario.nome}"/>!</li>
            <li>
                <a class="current_page" href="#">Editar Funcionário</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/gerenteServlet?acao=listarFuncionarios">
                Seus Funcionários
                </a>
            </li>
            <li><a href="${pageContext.request.contextPath}/usuarioServlet?acao=sair">Sair</a></li>
        </ul>
    </nav>

    <main class="content_layout">
        <!-- mostra o funcionario a ser editado passao pela servlet no atributo f_edit -->
        <table class="table_model">
            <thead>
                <th>Nome</th>
                <th>CPF</th>
                <th>Cargo</th>
                <th>Salário</th>
            </thead>
            <tbody>
                <td>${f_edit.usuario.nome}</td>
                <td>${f_edit.usuario.cpf}</td>
                <td>${f_edit.cargo}</td>
                <td>${f_edit.usuario.salario}</td>
            </tbody>
        </table>

         <form class="form" action="${pageContext.request.contextPath}/gerenteServlet" method="post">
            <label>Cargo: </label>
            <input name="cargo" type="text" value="${f_edit.cargo}">
            <label>Salario: </label>
            <input name="salario" type="number" value="${f_edit.usuario.salario}" step="0.01">
            <input type="hidden" name="id" value="${f_edit.id}">
            <button type="submit" name="acao" value="alterarFuncionario">Confirmar</button>
        </form>
        
    </main>

</body>
</html>