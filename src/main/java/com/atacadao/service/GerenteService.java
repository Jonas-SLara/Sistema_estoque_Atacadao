package com.atacadao.service;

import java.util.ArrayList;

import com.atacadao.dao.GerenteDAO;
import com.atacadao.model.Gerente;
import com.atacadao.model.Produto;
import com.atacadao.model.Funcionario;

public class GerenteService {

    private static GerenteDAO gdao = new GerenteDAO();

    public static Gerente getGerente(String cpf){
        cpf = UsuarioService.formatarCPF(cpf);
        Gerente g = gdao.buscar_gerente(cpf);
        return g;
    }

    public static Gerente getGerenteById(int id){
        return gdao.buscar_gerente_id(id);
    }

    public static ArrayList<Gerente> list(){
        return gdao.listar_gerentes();
    }

    public static ArrayList<Funcionario> listFuncionarios(int id){
        return gdao.listar_seus_funcionarios(id);
    }

    public static ArrayList<Produto> listProdutos(int id){
        return gdao.listar_seus_produtos(id);
    }
}
