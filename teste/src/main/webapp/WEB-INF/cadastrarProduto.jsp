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
    <title>Cadastro de Produtos</title>
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
              Cadastro de Produtos
            </a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/gerenteServlet?acao=listarProdutos">
              Seu Estoque
            </a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/usuarioServlet?acao=sair">Sair</a>
          </li>
        </ul>
    </nav>

  <main class="content_layout">
    <h2 class="title">Cadastro de Produtos</h2>

    <!--Servlet gerenteServlet recebe o parametro 'acao' para saber qual ação fazer-->
    <form method="post" action="${pageContext.request.contextPath}/gerenteServlet" class="form">
      <label for="nome">Nome:</label>
      <input id="nome" type="text" name="nome" required>
      <br>
      <label for="quantidade">Quantidade:</label>
      <input id="quantidade" type="number" name="quantidade" required>
      <br>
      <label for="valor">Valor:</label>
      <input id="valor" name="valor" type="number" step="0.01" required>
      <br>
      <button type="submit" name="acao" value="cadastrarProduto" class="btn" id="ok">Cadastrar</button>
    </form>

    <c:if test="${not empty msg}">
      <h3>${msg}</h3>
    </c:if>
  </main>

</body>
</html>
