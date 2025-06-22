package com.atacadao.model;

public class Produto {
    private int id;
    private String nome;
    private Double valor;
    private int quantidade;
    private int id_gerente;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(Double preco) {
        this.valor = preco;
    }

    public int getQuantidade(){
        return this.quantidade;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    public int getIdGerente(){
        return this.id_gerente;
    }
    public void setIdGerente(int id_gerente){
        this.id_gerente = id_gerente;
    }
}
