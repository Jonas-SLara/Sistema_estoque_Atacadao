package com.atacadao.model;

public class Funcionario {
    private int id;
    private int idGerente;
    private String cpfUsuario;
    private String cargo;
    private Usuario usuario; //relacionado a um usuario

    public Funcionario(){}
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCpfUsuario() {
        return this.cpfUsuario;
    }

    public void setCpfUsuario(String cpf_usuario) {
        this.cpfUsuario = cpf_usuario;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGerente() {
        return this.idGerente;
    }

    public void setIdGerente(int id_gerente) {
        this.idGerente = id_gerente;
    }

}