package com.atacadao.service;

import com.atacadao.dao.AdminDAO;
import com.atacadao.model.Admin;

public class AdminService {
    
  public static boolean autenticar(String cnpj, String nome, String senha){
    Admin a = AdminDAO.buscarPorCNPJ(cnpj);
    if(a==null) return false;
    if(a.getCnpj().equals(cnpj) && a.getNome().equals(nome) && a.getSenha().equals(senha)){
        return true;
    }
    return false;
  }

  public static Admin obterAdmin(String cnpj){
    Admin a = AdminDAO.buscarPorCNPJ(cnpj);
    return a;
  }
}
