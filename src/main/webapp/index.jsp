<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

<!-- ${pageContext.request.contextPath} = contexto atual da requisição, url absoluta -->

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/geral.css">
  </head>
  <body>

   <nav class="nav_menu">
        <ul>
            <li><a class="current_page" href="#">Atacadão</a></li>
            <li><a href="${pageContext.request.contextPath}/usuarioServlet?acao=irLogin">Login</a></li>
            <li><a href="${pageContext.request.contextPath}/usuarioServlet?acao=irCadastro">Cadastro</a></li>
            <li><a href="${pageContext.request.contextPath}/usuarioServlet?acao=irLoginADM">ADM</a></li>
        </ul>
    </nav>

  <!--ideia de latout para mostrar os serviços e funcionalidades do sistema em forma de cards-->
    <main class="content_layout">

      <h1 class="title">Sistema de estoque da Atacadão</h1>
      <div class="service">
        <img src="${pageContext.request.contextPath}/img/service2.jpg" class="img_card" alt="Serviço">
        <h2>Gerenciamento de equipe</h2>
        <p><span>Gerente</span>, cadastre e monitore seus funcionários com um clique</p>
      </div>

      <div class="service">
        <img src="${pageContext.request.contextPath}/img/service3.jpg" class="img_card" alt="Serviço">
        <h2>Gerenciamento de produtos</h2>
        <p><span>Gerente</span>, de a entrada de um produto no sistema de estoque e controle seu estoque</p>
      </div>
      
       <div class="service">
        <img src="${pageContext.request.contextPath}/img/service1.jpg" class="img_card" alt="Serviço">
        <h2>Monitoramento de estoque</h2>
        <p><span>Funcionario/Gerente</span>, controle a quantidade de produtos no seu estoque com novas
        entradas e saídas</p>
      </div>

       <div class="service">
        <img src="${pageContext.request.contextPath}/img/service4.jpg" class="img_card" alt="Serviço">
        <h2>RH e cadastro</h2>
        <p><span>Usuario</span>, é novo na empresa? faça o seu cadastro e converse com o RH para
        para você receber sua especialização e fazer parte de nossa equipe!</p>
      </div>
      
    </main>

  </body>
</html>
