package com.atacadao.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.atacadao.model.Admin;
import com.atacadao.model.Gerente;
import com.atacadao.model.Produto;
import com.atacadao.service.AdminService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminServlet")
public class AdminServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("adm")==null){//se não existir
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        Admin a = (Admin) session.getAttribute("adm");
        String acao = req.getParameter("acao");

        switch (acao) {
            /* Voltar para a home admin com a tela limpa */
            case "voltar":
                req.getRequestDispatcher("/WEB-INF/admin/homeAdmin.jsp").forward(req, resp);
                break;
            /*VISITAR O PERFIL DE ADMINISTRADOR */
            case "perfil":
                req.setAttribute("perfilAdm", a);
                req.setAttribute("current_page", "perfil");
                req.getRequestDispatcher("/WEB-INF/admin/homeAdmin.jsp").forward(req, resp);
                break;

            /* CRIAR UMA COLEÇAO DE DADOS DE TODOS OS GERENTES EM UMA LISTA */
            case "gerentes":
                try {
                    ArrayList <Gerente> gs = AdminService.obterGerentes();
                    req.setAttribute("gerentes", gs);
                    req.setAttribute("current_page", "gerentes");
                    req.getRequestDispatcher("/WEB-INF/admin/homeAdmin.jsp").forward(req, resp);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }            
                break;
            /* CRIAR UMA COLEÇÃO DE DADOS DE TODOS OS PRODUTOS EM UMA LISTA */
            case "produtos":
                try {
                    ArrayList <Produto> ps = AdminService.obterProdutos();
                    req.setAttribute("produtos", ps);
                    req.setAttribute("current_page", "produtos");
                    req.getRequestDispatcher("/WEB-INF/admin/homeAdmin.jsp").forward(req, resp);
                } catch (Exception e) {
                
                }
                break;
            /* INVALIDA A SEÇÃO ATUAL E VOLTA PARA HOME */
            case "sair":
                session.invalidate();
                resp.sendRedirect(req.getContextPath() + "/");
                break;

            case "edit_nome":
                req.setAttribute("edit_opcao", "editarNome");
                req.setAttribute("perfilAdm", a);
                req.setAttribute("current_page", "perfil");
                req.getRequestDispatcher("/WEB-INF/admin/homeAdmin.jsp").forward(req, resp);
                break;

            case "edit_email":
                req.setAttribute("edit_opcao", "editarEmail");
                req.setAttribute("perfilAdm", a);
                req.setAttribute("current_page", "perfil");
                req.getRequestDispatcher("/WEB-INF/admin/homeAdmin.jsp").forward(req, resp);
                break;

            case "edit_telefone":
                req.setAttribute("edit_opcao", "editarTelefone");
                req.setAttribute("perfilAdm", a);
                req.setAttribute("current_page", "perfil");
                req.getRequestDispatcher("/WEB-INF/admin/homeAdmin.jsp").forward(req, resp);
                break;

            case "edit_senha":
                req.setAttribute("edit_opcao", "editarSenha");
                req.setAttribute("perfilAdm", a);
                req.setAttribute("current_page", "perfil");
                req.getRequestDispatcher("/WEB-INF/admin/homeAdmin.jsp").forward(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/adminServlet?acao=voltar");
            break;
        }
    }
}
