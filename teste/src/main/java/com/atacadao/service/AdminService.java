package com.atacadao.service;

import java.util.ArrayList;

import com.atacadao.dao.AdminDAO;
import com.atacadao.dao.FuncionarioDAO;
import com.atacadao.dao.GerenteDAO;
import com.atacadao.dao.ProdutoDAO;
import com.atacadao.dao.UsuarioDAO;
import com.atacadao.model.Admin;
import com.atacadao.model.Funcionario;
import com.atacadao.model.Gerente;
import com.atacadao.model.Produto;
import com.atacadao.model.Usuario;

public class AdminService {
    
  private static GerenteDAO gdao = new GerenteDAO();
  private static ProdutoDAO pdao = new ProdutoDAO();
  private static FuncionarioDAO fdao = new FuncionarioDAO();
  private static UsuarioDAO udao = new UsuarioDAO();

  /* AUTENTICAR O ADMINISTRADOR PELO CNPJ NOME E SENHA */
  public static boolean autenticar(String cnpj, String nome, String senha){
    Admin a = AdminDAO.buscarPorCNPJ(cnpj);
    if(a==null) return false;
    if(a.getCnpj().equals(cnpj) && a.getNome().equals(nome) && a.getSenha().equals(senha)){
        return true;
    }
    return false;
  }

  /* OBTER O ADMINISTRADOR PELO CNPJ */
  public static Admin obterAdmin(String cnpj){
    Admin a = AdminDAO.buscarPorCNPJ(cnpj);
    return a;
  }

  /* OBTER LISTA DE TODOS OS GERENTES */
  public static ArrayList<Gerente> obterGerentes(){
    return gdao.listar_gerentes(); 
  }

  /* OBTER LISTA DE TODOS OS PRODUTOS */
  public static ArrayList<Produto> obterProdutos(){
    return pdao.listar_produtos();
  }

  /* OBTER A LISTA DE TODOS OS FUNCIONARIOS */
  public static ArrayList<Funcionario> obterFuncionarios(){
    return fdao.listar_funcionarios();
  }

  /* OBTER A LISTA DE TODOS OS USUARIOS */
  public static ArrayList<Usuario> obterUsuarios(){
    return udao.listarUsuarios();
  }
}
