
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>

<%
    if (session.getAttribute("adm") == null) {
        response.sendRedirect(request.getContextPath() + "/");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
</head>
<body>
    
     <!--nav bar fixa no topo-->
    <nav class="nav_menu">
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/adminServlet?acao=voltar"
                    class="${empty current_page ? 'current_page' : ' '}">
                    DashBoard
                </a>
            </li>
            <li class="perfil">
                <a href="${pageContext.request.contextPath}/adminServlet?acao=perfil">
                <c:out value="${sessionScope.adm.nome}"/>!
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/adminServlet?acao=gerentes"
                    class="${current_page eq 'gerentes' ? 'current_page' : ' '}">
                    Gerentes
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/adminServlet?acao=produtos"
                    class="${current_page eq 'produtos' ? 'current_page' : ' '}">
                    Estoque Completo
                </a>
            </li>
            <li><a href="${pageContext.request.contextPath}/adminServlet?acao=sair">Sair</a></li>
        </ul>
    </nav>

        <!-- INFO DO ADMIN-->
    <main class="content_layout">     
        <h1 class="title">DashBoard</h1>

        <!-- Tabela de Gerentes -->
        <c:if test="${current_page eq 'gerentes'}">
            <h2 class="title">Lista de Gerentes</h2>
            <form method="post" action="${pageContext.request.contextPath}/adminServlet">
                <button type="submit" name="acao" value="cadastrarGerente">
                    Novo +
                </button>
            </form>
            <section class="section">
                    <c:forEach var="g" items="${gerentes}">
                        <div class="info_card">
                            <img src="${pageContext.request.contextPath}/img/perfil.png" class="info_card">
                            <p class="info_card">Nome: <c:out value="${g.usuario.nome}"/></p>
                            <p class="info_card">Email: <c:out value="${g.usuario.email}"/></p>
                            <p class="info_card">Salário: <c:out value="${g.usuario.salario}"/></p>
                            <p class="info_card">Bonificação: <c:out value="${g.bonificacao}"/></p>
                            <form method="post" action="${pageContext.request.contextPath}/adminServlet">
                                <button type="submit" name="acao" value="editar">
                                    Editar
                                </button>
                                <button type="submit" name="acao" value="excluir">
                                    Excluir
                                </button>
                            </form>
                        </div>
                    </c:forEach>
            </section>
        </c:if>

        <!-- Tabela de Produtos -->
        <c:if test="${current_page eq 'produtos'}">
            <h2 class="title">Lista de Produtos</h2>
            <section class="section">
                 <c:forEach var="p" items="${produtos}">
                        <div>
                            <img src="${pageContext.request.contextPath}/img/produto.png" class="info_card_img">
                            <p class="info_card">Nome: <c:out value="${p.nome}"/></p>
                            <p class="info_card">Valor: <c:out value="${p.valor}"/></p>
                            <p class="info_card">Quantidade: <c:out value="${p.quantidade}"/></p>
                            <p class="info_card">Gerente ID: <c:out value="${p.id_gerente}"/></p>
                            <form method="post" action="${pageContext.request.contextPath}/adminServlet">
                                <button type="submit" name="acao" value="editar">
                                    Editar
                                </button>
                                <button type="submit" name="acao" value="excluir">
                                    Excluir
                                </button>
                            </form>
                        </div>    
                    </c:forEach>
            </section>
        </c:if>

        <!--Perfil da empresa-->
        <c:if test="${current_page eq 'perfil'}">
            <h2 class="title">Perfil da Empresa</h2>
            <section class="section2">
                <div class="label">
                    <div><p>Nome:</p></div>
                    <p>${perfilAdm.nome}</p>
                    <a href="${pageContext.request.contextPath}/adminServlet?acao=edit_nome"
                        class="edit">Editar</a>
                </div>
                <div class="label">
                   <div><p>Email:</p></div>
                    <p>${perfilAdm.email}</p>
                    <a href="${pageContext.request.contextPath}/adminServlet?acao=edit_email"
                       class="edit">Editar</a>
                </div>
                <div class="label">
                    <div><p>CNPJ:</p></div>
                    <p>${perfilAdm.cnpj}</p>
                </div>
                <div class="label">
                    <div><p>Telefone:</p></div>
                    <p>${perfilAdm.telefone}</p>
                    <a href="${pageContext.request.contextPath}/adminServlet?acao=edit_telefone"
                       class="edit">Editar</a>
                </div>
                <div class="label">
                    <div><p>Senha:</p></div>
                    <p>${perfilAdm.senha}</p>
                    <a href="${pageContext.request.contextPath}/adminServlet?acao=edit_senha"
                       class="edit">Editar</a>
                </div>
            </section>
            <c:if test="${edit_opcao eq 'editarNome'}">
                <section class="section2">
                    <form method="post" action="">
                        <label>Novo Nome:</label>
                        <input type="text" name="nome" value="${perfilAdm.nome}">
                        <button type="submit">Editar</button>
                    </form>
                </section>
            </c:if>
            <c:if test="${edit_opcao eq 'editarEmail'}">
                <section class="section2">
                    <form method="post" action="">
                        <label>Novo Email:</label>
                        <input type="text" name="email" value="${perfilAdm.email}">
                        <button type="submit">Editar</button>
                    </form>
                </section>
            </c:if>
            <c:if test="${edit_opcao eq 'editarTelefone'}">
                <section class="section2">
                    <form method="post" action="">
                        <label>Novo Telefone:</label>
                        <input type="text" name="telefone" value="${perfilAdm.telefone}">
                        <button type="submit">Editar</button>
                    </form>
                </section>
            </c:if>
            <c:if test="${edit_opcao eq 'editarSenha'}">
                <section class="section2">
                    <form method="post" action="">
                        <label>Nova Senha:</label>
                        <input type="password" name="senha" value="${perfilAdm.senha}">
                        <button type="submit">Editar</button>
                    </form>
                </section>
            </c:if>
        </c:if>
    </main>
</body>
</html>