package com.atacadao.util;

import java.util.ArrayList;
import java.util.Iterator;

import com.atacadao.dao.*;

import com.atacadao.model.*;

class Teste {
    public static UsuarioDAO udao = new UsuarioDAO();
    public static FuncionarioDAO fdao = new FuncionarioDAO();
    public static GerenteDAO gdao = new GerenteDAO();
    public static ProdutoDAO pdao = new ProdutoDAO();
    

    public static void main(String[] args) {
        mostarAdmin(1);
        mostarAdmin("12345678000199");
        
    }

    public static void mostarAdmin(int id){
       Admin a = AdminDAO.buscarPorId(id);
       System.out.println("cnpj: " + a.getCnpj() + " id: " + a.getId() + " nome: " + a.getNome());
       System.out.println("telefone:" + a.getTelefone() + " email: " + a.getEmail() + " senha: " + a.getSenha());
    }

    public static void mostarAdmin(String cnpj){
       Admin a = AdminDAO.buscarPorCNPJ(cnpj);
       System.out.println("cnpj: " + a.getCnpj() + " id: " + a.getId() + " nome: " + a.getNome());
       System.out.println("telefone:" + a.getTelefone() + " email: " + a.getEmail() + " senha: " + a.getSenha());
    }

    public static void mostrarUsers(ArrayList<Usuario> users){
        for (Usuario u : users) {
            System.out.println(u.getNome() + " " + u.getCelular() + " " + u.getEmail() +
               " " + u.getCpf() + " " + u.getSenha() + " " + u.getSalario());
        }
        System.out.println("_______________________________________________________________");
        System.out.println("\n");
    }
    
    public static void mostrarFuncionarios(ArrayList<Funcionario> funcionarios){
        for (Funcionario f : funcionarios) {
            System.out.println(f.getId() + " " + f.getUsuario().getNome() + " " + f.getUsuario().getEmail() + " "
             + f.getUsuario().getCelular() + " " + f.getCargo() + " " + f.getCpfUsuario() + " " + f.getIdGerente()
             + " " + f.getUsuario().getSalario());
        }
        System.out.println("_______________________________________________________________");
        System.out.println("\n");
    }

    public static void mostrarProdutos(ArrayList<Produto> produtos){
        Iterator<Produto> it = produtos.iterator();
        Produto p = null;
        while (it.hasNext()) {
            p = it.next();
            System.out.println(p.getNome() + " " + p.getId() + " " + p.getValor() +
               " " + p.getQuantidade() + " " + p.getIdGerente());
        }
        System.out.println("_______________________________________________________________");
        System.out.println("\n");
    }

    public static void mostrarFuncionario(Funcionario f){
        System.out.println(f.getId() + " " + f.getUsuario().getNome() + " " + f.getUsuario().getEmail() + " "
        + f.getUsuario().getCelular() + " " + f.getCargo() + " " + f.getCpfUsuario() + " " + f.getIdGerente()
        + " " + f.getUsuario().getSalario());
        System.out.println("_______________________________________________________________");
        System.out.println("\n");
    }

    public static void mostrarGerentes(ArrayList<Gerente> gerentes){
        for (Gerente g : gerentes) {
            System.out.println(g.getId() + " " + g.getUsuario().getNome() + " " + g.getUsuario().getEmail() + " " +
            g.getCpfUsuario() + " " + g.getBonificacao() + " " + g.getUsuario().getSalario());
        }    
        System.out.println("_______________________________________________________________");
        System.out.println("\n");      
    }

    public static void mostrarGerente(Gerente g){
        System.out.println(g.getId() + " " + g.getUsuario().getNome() + " " + g.getUsuario().getEmail() + " " +
            g.getCpfUsuario() + " " + g.getBonificacao() + " " + g.getUsuario().getSalario());
        System.out.println("_______________________________________________________________");
        System.out.println("\n"); 
    }
}