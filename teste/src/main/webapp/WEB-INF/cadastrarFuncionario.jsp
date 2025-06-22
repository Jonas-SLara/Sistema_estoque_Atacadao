<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    if (session.getAttribute("gerente") == null) {
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Cadastro de Funcionários</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
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
            <a class="current_page" href="#">
              Cadastro de Funcionário
            </a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/gerenteServlet?acao=listarFuncionarios">
              Seus Funcionários
            </a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/usuarioServlet?acao=sair">Sair</a>
          </li>
        </ul>
    </nav>

    <main class="content_layout">
        <h2 class="title">Cadastrar Funcionario</h2>
        <!--Primeiro vai ter a opção de buscar o usuario pelo cpf para o gerente ver se é
        este mesmo que deve adicionar, obs não é permitido cadastrar algúem que já foi
        cadastrado-->    

        <h3>1º Passo busque seu usuario pelo cpf e confira os dados dele</h3>
        <!--Servlet gerenteServlet recebe o parametro 'acao' para saber qual ação fazer-->
        <form method="post" action="<%=request.getContextPath()%>/usuarioServlet" class="form">
            <label for="cpf">CPF: </label>
            <input type="text" name="cpf" id="cpf" required>
            <button type="submit" name="op" value="buscar" class="btn" id="ok">Buscar</button>
        </form>

        <c:if test="${not empty erro}">
            <h3>${erro}</h3>
        </c:if>

        <c:if test="${not empty usuarioEncontrado}">
            <h3>usuario encontrado: ${usuarioEncontrado.nome}  ${usuarioEncontrado.email}</h3>
        </c:if>

        <!--caso não receba nenhum erro ao buscar o usuario prossiga-->
        <c:if test="${empty erro and not empty usuarioEncontrado}">
        <h3>2º Passo cadastre o usuario como seu funcionário</h3>

        <form action="${pageContext.request.contextPath}/gerenteServlet" method="post" class="form">
            <input id="cpf_usuario" name="cpf_usuario" value="${usuarioEncontrado.cpf}" type="hidden">
            <br>
            <label for="cargo">Cargo:</label>
            <input id="cargo" name="cargo" type="text" required>
            <br>
            <label for="salario">Salario:</label>
            <input id="salario" name="salario" type="number" step="0.01" value="0.00" required>
            <br>
            <button type="submit" name="acao" value="cadastrarFuncionario" class="btn" id="ok">Cadastrar</button>
        </form>
        </c:if>

        <c:if test="${not empty msg}">
            <h3>${msg}</h3>
        </c:if>
    </main>                                                  
</body>
</html>
