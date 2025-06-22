package com.atacadao.model;

public class Gerente {

    private int id;
    private String cpf_usuario;
    private Double bonificacao;
    private Usuario usuario;
    public Gerente(){}
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getBonificacao() {
        return bonificacao;
    }

    public void setBonificacao(Double bonificacao) {
        this.bonificacao = bonificacao;
    }

    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public String getCpfUsuario(){
        return this.cpf_usuario;
    }

    public void setCpfUsuario(String cpf_usuario){
        this.cpf_usuario = cpf_usuario;
    }
}

