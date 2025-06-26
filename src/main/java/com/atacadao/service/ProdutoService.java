package com.atacadao.service;

import java.util.ArrayList;

import com.atacadao.dao.ProdutoDAO;
import com.atacadao.model.Produto;

public class ProdutoService {
    public static ProdutoDAO pdao = new ProdutoDAO();

    public static ArrayList<Produto> list(){
        return pdao.listar_produtos();
    }

    public static boolean cadastrarProduto(Produto p){
        return pdao.inserir_produto(p);
    }

    public static boolean excluirProduto(int id){
        return pdao.remover_produto(id);
    }

    public static Produto buscarProduto(int id){
        return pdao.buscar_por_id(id);
    }

    public static boolean alterarProduto(Produto p){
        return pdao.alterar_produto(p);
    }
}
