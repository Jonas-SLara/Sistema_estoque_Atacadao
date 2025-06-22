package com.atacadao.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.atacadao.model.*;
import com.atacadao.service.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/gerenteServlet")
public class GerenteServlet extends HttpServlet{
    /*toda requisição que esta servlet receber deve verificar se o gerente ainda
     * esta logado através de uma session chamada gerente
     * o parametro usado para determinar qual ação a servlet faz sera o 'acao'
    */

    //recebe requisiçoes post para cadastrar, excluir e editar
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("gerente")==null){//se não existir
            resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
            return;
        }
        //obter o gerente apartir da sessão existente
        Gerente gerente = (Gerente) session.getAttribute("gerente"); 
        String acao = req.getParameter("acao");

        switch (acao) {
            case "cadastrarProduto":
                //pode gerar erros de conversão ou do banco
                try {
                    String nome = req.getParameter("nome");
                    String valor = req.getParameter("valor");
                    String quantidade = req.getParameter("quantidade");
                    double valorConvertido = Double.parseDouble(valor);
                    int quantidadeConvertida = Integer.parseInt(quantidade);

                    Produto p = new Produto();

                    p.setIdGerente(gerente.getId());
                    p.setNome(nome);
                    p.setValor(valorConvertido);
                    p.setQuantidade(quantidadeConvertida);

                    boolean sucesso = ProdutoService.cadastrarProduto(p);
                    String msg = (sucesso)?
                    "novo produto " + p.getNome() + " cadastrado "
                    : "erro ao cadastrar produto, notifique o ADM";
                    req.setAttribute("msg", msg);

                } catch (Exception e) {
                    System.out.println("erro: " + e.getMessage());
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/WEB-INF/cadastrarProduto.jsp").forward(req, resp);
                break;

            case "cadastrarFuncionario":
                try{
                    String cpf = req.getParameter("cpf_usuario");
                    String cargo = req.getParameter("cargo");
                    String salario = req.getParameter("salario");
                    Funcionario f = new Funcionario();
                    f.setCargo(cargo);
                    cpf = UsuarioService.formatarCPF(cpf);
                    f.setCpfUsuario(cpf);
                    f.setIdGerente(gerente.getId());

                    //buscar o usuario relacionado a este funcionario para inserir novos dados preferenciais
                    Usuario u = UsuarioService.buscarUsuario(cpf);
                    u.setSalario(Double.parseDouble(salario));
                    f.setUsuario(u);

                    boolean sucesso = FuncionarioService.cadastrarFuncionario(f);
                    String msg = (sucesso) ? "novo funcionario cadastrado " + u.getNome() + " cadastrado"
                    : "não foi possivel cadastrar este funcionario, notifique o ADM";
                    req.setAttribute("msg", msg);
                } catch (Exception e) {
                    System.out.println("erro: " + e.getMessage());
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/WEB-INF/cadastrarFuncionario.jsp").forward(req, resp);

                break;

            case "alterarProduto":
                try {
                    Produto p = new Produto();
                    p.setNome(req.getParameter("nome"));
                    p.setQuantidade(Integer.parseInt(req.getParameter("quantidade")));
                    p.setValor(Double.parseDouble(req.getParameter("valor")));
                    p.setId(Integer.parseInt(req.getParameter("id")));

                    boolean sucesso = ProdutoService.alterarProduto(p);
                    String msg_edit = (sucesso)? "alterações salvas" : "não foi possível editar";
                    req.setAttribute("msg_edit", msg_edit);

                    //OBTER A LISTA DE PRODUTOS ATUALIZADA DO GERENTE
                    ArrayList<Produto> listaProdutos = GerenteService.listProdutos(gerente.getId());
                    req.setAttribute("listaProdutos", listaProdutos);

                } catch (Exception e) {
                    System.out.println("erro: " + e.getMessage());
                    e.printStackTrace();
                }

                req.getRequestDispatcher("/WEB-INF/verProdutos.jsp").forward(req, resp);
                break;

            case "alterarFuncionario":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    Funcionario f = FuncionarioService.buscarFuncionarioId(id);

                    String novo_cargo = req.getParameter("cargo");
                    Double novo_salario = Double.parseDouble(req.getParameter("salario"));

                    f.setCargo(novo_cargo);
                    f.getUsuario().setSalario(novo_salario);

                    boolean sucesso = FuncionarioService.editarFuncionario(f);
                    String msg_edit = (sucesso)? "alterações salvas" : "não foi possível editar";
                    req.setAttribute("msg_edit", msg_edit);

                    //OBTER A LISTA DE FUNCIONARIOS NOVAMENTE
                    ArrayList <Funcionario> listFuncionarios = GerenteService.listFuncionarios(gerente.getId());
                    req.setAttribute("listaFuncionarios", listFuncionarios);

                } catch (Exception e) {
                    // TODO: handle exception
                }
                req.getRequestDispatcher("/WEB-INF/verFuncionarios.jsp").forward(req, resp);
                break;
        }
    }

    //recebe requisições get como ir para as páginas de cadastrar e de listar e também para voltar e excluir
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("gerente")==null){//se não existir
            resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
            return;
        }

        Gerente gerente = (Gerente) session.getAttribute("gerente"); 
        String acao = req.getParameter("acao");
        ArrayList<Funcionario> funcionarios = null;
        ArrayList<Produto> produtos = null;
        String info;

        switch (acao) {
            case "listarFuncionarios":
                funcionarios = GerenteService.listFuncionarios(gerente.getId());
                req.setAttribute("listaFuncionarios", funcionarios);
                req.getRequestDispatcher("/WEB-INF/verFuncionarios.jsp").forward(req, resp);
                break;

            case "listarProdutos":
                produtos = GerenteService.listProdutos(gerente.getId());
                req.setAttribute("listaProdutos", produtos);
                req.getRequestDispatcher("/WEB-INF/verProdutos.jsp").forward(req, resp);
                break;

            case "irCadastrarProduto":
                req.getRequestDispatcher("/WEB-INF/cadastrarProduto.jsp").forward(req, resp);
                break;
            
            case "irCadastrarFuncionario" :
                req.getRequestDispatcher("/WEB-INF/cadastrarFuncionario.jsp").forward(req, resp);
            break;

            case "excluirProduto":
                info = req.getParameter("info");
                try {
                    int id = Integer.parseInt(info);
                    //obter o produto para consulta e excluir
                    Produto p = ProdutoService.buscarProduto(id);
                    ProdutoService.excluirProduto(id);

                    //precisa obter a nova lista de produtos atualizada
                    produtos = GerenteService.listProdutos(gerente.getId());

                    //envia os dados para a página de volta
                    req.setAttribute("listaProdutos", produtos);
                    req.setAttribute("produtoExcluido", p);

                } catch (Exception e) {
                    System.out.println("erro: " + e.getMessage());
                    e.printStackTrace();
                }
               
                req.getRequestDispatcher("/WEB-INF/verProdutos.jsp").forward(req, resp);
            break;

            case "removerFuncionario":
                info = req.getParameter("info");
                try{
                    int id = Integer.parseInt(info);
                    Funcionario f = FuncionarioService.buscarFuncionarioId(id);
                    FuncionarioService.excluirFuncionario(id);
                    
                    funcionarios = GerenteService.listFuncionarios(gerente.getId());
                    req.setAttribute("listaFuncionarios", funcionarios);
                    req.setAttribute("funcionarioExcluido", f);
                } catch (Exception e) {
                    System.out.println("erro: " + e.getMessage());
                    e.printStackTrace();
                }
                
                req.getRequestDispatcher("/WEB-INF/verFuncionarios.jsp").forward(req, resp);
            break;

            case "voltar":
                req.getRequestDispatcher("/WEB-INF/homeGerente.jsp").forward(req, resp);
                break;
            
            case "editarProduto":
                info = req.getParameter("info");
                //ache o produto pelo id passado no info e envie na requisição
                try{
                    Produto p = ProdutoService.buscarProduto(Integer.parseInt(info));
                    req.setAttribute("p_edit", p);
                } catch(Exception e){
                    System.out.println("erro " + e.getMessage());
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/WEB-INF/editarProduto.jsp").forward(req, resp);
                break;

            case "editarFuncionario":
                info = req.getParameter("info");
                //ache o funcionaro pelo id passado no info e envie na requisição
                try{
                    Funcionario f = FuncionarioService.buscarFuncionarioId(Integer.parseInt(info));
                    req.setAttribute("f_edit", f);
                } catch(Exception e){
                    System.out.println("erro " + e.getMessage());
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/WEB-INF/editarFuncionario.jsp").forward(req, resp);
                break;

            default:
                System.err.println("opção de servlet para gerente invalida");
                break;
        }
    }
}
