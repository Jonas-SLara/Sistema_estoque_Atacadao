package com.atacadao.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/funcionarioServlet")
public class FuncionarioServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
    HttpSession session = req.getSession(false);
    if(session == null || session.getAttribute("funcionario")==null){
        resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
        return;
    }

    String acao = req.getParameter("acao");
      switch (acao) {
        case "voltar":
            req.getRequestDispatcher("/WEB-INF/homeFuncionario.jsp").forward(req, resp);
            break;
      
        default:
            System.out.println("erro, nenhuma açao deste tipo na funcionario servlet do get");
            break;
      }
    }
}
