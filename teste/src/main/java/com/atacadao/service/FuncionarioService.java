package com.atacadao.service;

import java.util.ArrayList;

import com.atacadao.dao.FuncionarioDAO;
import com.atacadao.model.Funcionario;

public class FuncionarioService {

    private static FuncionarioDAO fdao = new FuncionarioDAO();

    public static Funcionario getFuncionario(String cpf){
        cpf=UsuarioService.formatarCPF(cpf);
        return  fdao.buscar_funcionario(cpf);
    }
    
    public static ArrayList<Funcionario> list(){
        return fdao.listar_funcionarios();
    }

    public static boolean cadastrarFuncionario(Funcionario f){
        boolean sucesso = fdao.inserir_funcionario(f);
        return sucesso;
    }

    public static Funcionario buscarFuncionarioId(int id){
        return fdao.buscarFuncionarioId(id);
    }

    public static boolean excluirFuncionario(int id){
        return fdao.remover_funcionario(id);
    }

    public static boolean editarFuncionario(Funcionario f){
        return fdao.alterar_funcionario(f);
    }
}
