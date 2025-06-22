package com.atacadao.controller;

import com.atacadao.model.Usuario;
import com.atacadao.service.UsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/usuarioServlet")
public class UsuarioServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String op = req.getParameter("op");
        switch (op) {
            case "cadastrar":
                try {
                    String nome = req.getParameter("nome");
                    String senha = req.getParameter("senha");
                    String cpf = req.getParameter("cpf");
                    String celular = req.getParameter("celular");
                    String email = req.getParameter("email");

                    //instancia um novo usuario
                    Usuario u = new Usuario();
                    u.setNome(nome);
                    u.setSenha(senha);
                    u.setCpf(cpf);
                    u.setCelular(celular);
                    u.setEmail(email);

                    boolean sucesso = UsuarioService.cadastrarUsuario(u);
                    String msg = (sucesso)?
                "Cadastrado com sucesso! usuario(a): " + u.getEmail() + "  Comunique com o RH para finalizar"
                : "erro ao cadastrar usuario tente outro CPF";

                    req.setAttribute("msg", msg);
                    req.getRequestDispatcher("pages/cadastro.jsp").forward(req, resp);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                break;

            case "buscar":
                try{
                    String cpf = req.getParameter("cpf");
                    Usuario u = UsuarioService.buscarUsuario(cpf);
                    if(u!=null){
                    req.setAttribute("usuarioEncontrado", u);
                    //agora ele verifica se é um funcionario ou gerente
                    boolean livre = UsuarioService.eUsuarioNaoCadastrado(cpf);
                    if(!livre){
                        req.setAttribute("erro", "este usuário já tem um vínculo no sistema");
                    }
                    }else{
                        req.setAttribute("erro", "nenhum usuário com este cpf foi encontrado");
                    }
                    req.getRequestDispatcher("/WEB-INF/cadastrarFuncionario.jsp").forward(req, resp);
                } catch(Exception e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                break;

            case "alterarUsuario":
                //pegar a sessão atual do usuario se não existir volta para o login
                try{
                    HttpSession session = req.getSession(false);
                    if(session == null || session.getAttribute("usuario")==null){
                        resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
                        return;
                    }

                    String n_nome = req.getParameter("nome");
                    String n_email = req.getParameter("email");
                    String n_celular = req.getParameter("celular");

                    if (n_nome == null || n_email == null || n_celular == null) {
                        req.setAttribute("msg", "Dados inválidos: todos os campos são obrigatórios.");
                        req.getRequestDispatcher("/WEB-INF/perfil.jsp").forward(req, resp);
                        return;
                    }

                    Usuario u = (Usuario) session.getAttribute("usuario");
                    u.setCelular(n_celular);
                    u.setEmail(n_email);
                    u.setNome(n_nome);

                    boolean sucesso = UsuarioService.alterarUsuario(u);
                    String msg = (sucesso)? "alterações salvas" : "erro ao alterar perfil";
                    req.setAttribute("msg", msg);
                } catch(Exception e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/WEB-INF/perfil.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest resq, HttpServletResponse resp)
            throws ServletException, IOException{
        String op = resq.getParameter("acao");
        switch (op) {
            case "irLogin":
                resp.sendRedirect(resq.getContextPath() + "/pages/login.jsp");
                break;
            case "irCadastro":
                resp.sendRedirect(resq.getContextPath() + "/pages/cadastro.jsp");
                break;
            case "irIndex":
                resp.sendRedirect(resq.getContextPath() + "/");
                break;
            case "irLoginADM":
                resp.sendRedirect(resq.getContextPath() + "/pages/loginADM.jsp");
                break;
            case "sair":
                HttpSession session = resq.getSession(false);
                if(session != null){
                session.invalidate();
                }
                resp.sendRedirect(resq.getContextPath() + "/pages/login.jsp");
                break;
            //todos os usuarios veem o seu perfil
            case "verPerfil":
                resq.getRequestDispatcher("/WEB-INF/perfil.jsp").forward(resq, resp);
                break;
            default:
                break;
        }
    }
}