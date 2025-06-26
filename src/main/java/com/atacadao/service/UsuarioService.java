package com.atacadao.service;

import com.atacadao.dao.FuncionarioDAO;
import com.atacadao.dao.GerenteDAO;
import com.atacadao.dao.UsuarioDAO;
import com.atacadao.model.Funcionario;
import com.atacadao.model.Gerente;
import com.atacadao.model.Usuario;

public class UsuarioService {

    private static UsuarioDAO udao = new UsuarioDAO();

    /*formata o cpf com REGEX para retorna apenas os números */
    public static String formatarCPF(String cpf){
        String cpf_formatado = cpf.replaceAll("//.", "");
        cpf_formatado = cpf_formatado.replaceAll("//-", "");
        cpf_formatado = cpf_formatado.replaceAll("[^0-9]+", "");
        return cpf_formatado;
    }
    /*formata o cpf com REGEX para retornar apenas os numeros do celular*/
    public static String formatarCelular(String telefone){
        String telefone_formatado = telefone.replaceAll("//.", "");
        telefone_formatado = telefone_formatado.replaceAll("//-", "");
        telefone_formatado = telefone_formatado.replaceAll("[^0-9]+", "");
        return telefone_formatado;
    }

    /*retorna null se o usuario não foi encontrado*/
    public static Usuario buscarUsuario(String cpf){
        cpf = formatarCPF(cpf);
        Usuario u = udao.buscar_por_cpf(cpf);
        return u;
    }

    /*autentica um usuario com suas cadastrais informadas*/
    public static boolean autenticar(String cpf, String senha){

        String cpf_formatado = formatarCPF(cpf);

        Usuario u = udao.buscar_por_cpf(cpf_formatado);
        if(u == null) return false;

        if(u.getCpf().equals(cpf_formatado) && senha.equals(u.getSenha())) return true;

        return false;
    }

    public static boolean cadastrarUsuario(Usuario u){
        u.setCpf(formatarCPF(u.getCpf()));
        u.setCelular(formatarCelular(u.getCelular()));
        return  udao.inserirUsuario(u);
    }

    //se for um funcionario ou um gerente retorna false se for um usuario sem especialização retorna true
    public static boolean eUsuarioNaoCadastrado(String cpf){
        cpf = formatarCPF(cpf);
        Funcionario f = new FuncionarioDAO().buscar_funcionario(cpf);
        Gerente g = new GerenteDAO().buscar_gerente(cpf);
        if(f!=null || g!=null) return false;
        return true;
    }

    public static boolean alterarUsuario(Usuario u){
        return  udao.alterarUsuario(u);
    }
}
