package com.atacadao.controller;

import java.io.IOException;

import com.atacadao.model.Admin;
import com.atacadao.model.Funcionario;
import com.atacadao.model.Gerente;
import com.atacadao.model.Usuario;
import com.atacadao.model.Produto;
import com.atacadao.service.AdminService;
import com.atacadao.service.FuncionarioService;
import com.atacadao.service.GerenteService;
import com.atacadao.service.UsuarioService;
import java.util.ArrayList;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest resq, HttpServletResponse resp)
            throws ServletException, IOException{

        String op = resq.getParameter("op");
        switch (op) {
            case "login":
                String cpf = resq.getParameter("cpf");
                String senha = resq.getParameter("senha");

                if(UsuarioService.autenticar(cpf, senha)){
                //login bem sucedido agora valida se é funcionario ou gerente 
                Usuario u = UsuarioService.buscarUsuario(cpf);

                //primeiro guarda o objeto usuario em uma session
                HttpSession session = resq.getSession();
                session.setAttribute("usuario", u);
            
                Funcionario f = FuncionarioService.getFuncionario(cpf);
                if(f!=null){
                    //guarda o objeto funcionario em uma seção para ser usado posteriormente
                    session.setAttribute("funcionario", f);
                    
                    /* aqui devemos quardar dados do nome e do contato do gerente do funcionario*/
                    Gerente gfUsuario = GerenteService.getGerenteById(f.getIdGerente());
                    session.setAttribute("ger_funcionario", gfUsuario);
                    
                    //monta a lista de produtos da sua seção
                    ArrayList<Produto> produtos = GerenteService.listProdutos(f.getIdGerente());
                    resq.setAttribute("listaProdutos", produtos);

                    RequestDispatcher dispatcher = resq.getRequestDispatcher("/WEB-INF/homeFuncionario.jsp");
                    dispatcher.forward(resq, resp);
                    return;
                }

                Gerente g = GerenteService.getGerente(cpf);
                if(g!=null){
                    //guarda o objeto gerente em uma seção para ser usado posteriormente
                    session.setAttribute("gerente", g);
                    
                    RequestDispatcher dispatcher = resq.getRequestDispatcher("/WEB-INF/homeGerente.jsp");
                    dispatcher.forward(resq, resp);
                    return;
                }

                //se nao encontrado então o usuario ainda nao foi cadastrado
                resq.setAttribute("erro", "Seu CPF nao foi cadastrado como integrante da empresa ainda, fale com o RH");
                resq.getRequestDispatcher("/pages/login.jsp").forward(resq, resp);
                return;

                }else{
                //dados invalidos usuario não encontrado
                resq.setAttribute("erro", "CPF ou senha incorretos");
                resq.getRequestDispatcher("/pages/login.jsp").forward(resq, resp);
                }
            break;

            case "loginADM":
                String adm_nome = resq.getParameter("nome");
                String adm_senha = resq.getParameter("senha");
                String cnpj = resq.getParameter("cnpj");
                if(AdminService.autenticar(cnpj, adm_nome, adm_senha)){
                    HttpSession session = resq.getSession();
                    Admin a= AdminService.obterAdmin(cnpj);
                    session.setAttribute("adm", a);
                    resq.getRequestDispatcher("/WEB-INF/admin/homeAdmin.jsp").forward(resq, resp);
                }else{
                    resq.setAttribute("erro", "dados incorretos");
                    resq.getRequestDispatcher("/pages/loginADM.jsp").forward(resq, resp);
                }
            break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest resq, HttpServletResponse resp)
            throws ServletException, IOException{
        //redireciona para login
        resp.sendRedirect("/pages/login.jsp");
    }
}