<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>

<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        return;
    }
%>
<!--Servlet gerenteServlet recebe o parametro 'acao' para saber qual ação fazer-->
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width initial-scale=1.0">
    <title>Página do Gerente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
  </head>

  <body>

    <!--nav bar fixa no topo-->
    <nav class="nav_menu">
        <ul>
          <li><a class="current_page" href="${pageContext.request.contextPath}/gerenteServlet?acao=voltar">Home</a></li>
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
            <a href="${pageContext.request.contextPath}/gerenteServlet?acao=listarProdutos">
              Seu Estoque
            </a>
          </li>
          <li><a href="${pageContext.request.contextPath}/usuarioServlet?acao=sair">Sair</a></li>
        </ul>
    </nav>

    <main class="content_layout">
        <h2 class="title">Página do Gerente</h2>
        
        <table class="table_model">
          <caption>Dados Pessoais</caption>
          <thead>
            <th>Nome</th>
            <th>CPF</th>
            <th>Salário Atual</th>
            <th>Email</th>
            <th>Celular</th>
            <th>Bonificação Atual</th>
          </thead>
          <tbody>
            <td><c:out value="${sessionScope.gerente.usuario.nome}"/></td>
            <td><c:out value="${sessionScope.usuario.cpf}"/></td>
            <td><c:out value="${sessionScope.usuario.salario}"/></td>
            <td><c:out value="${sessionScope.gerente.usuario.email}"/></td>
            <td><c:out value="${sessionScope.gerente.usuario.celular}"/></td>
            <td><c:out value="${sessionScope.gerente.bonificacao}"/></td>
          </tbody>
        </table>

        <div class="service">
          <h2>Cadastre um novo Funcionário Aqui</h2>
          <a href="${pageContext.request.contextPath}/gerenteServlet?acao=irCadastrarFuncionario"
          class="btn_link">
              Cadastrar Funcionário
          </a>
        </div>
        
        <div class="service">
          <h2>Cadastre um novo produto Aqui</h2>
          <a href="${pageContext.request.contextPath}/gerenteServlet?acao=irCadastrarProduto"
          class="btn_link">
            Cadastrar Produto
          </a>
        </div>
    </main>
  </body>
</html>
