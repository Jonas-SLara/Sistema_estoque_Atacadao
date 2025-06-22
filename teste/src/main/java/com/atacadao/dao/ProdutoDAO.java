package com.atacadao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.atacadao.model.Produto;

public class ProdutoDAO {

    public ArrayList<Produto> listar_produtos(){
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        String sql = "SELECT * FROM produto";

        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while(rs.next()){
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setValor(rs.getDouble("valor"));
                p.setIdGerente(rs.getInt("id_gerente"));
                produtos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("erro ao listar usuarios: " + e.getMessage());
        } catch (IllegalStateException e){
            System.out.println("Banco de dados não configurado " + e.getMessage());
        }
        return produtos;
    }

    public Produto buscar_por_id(int id){
        String sql = "SELECT * FROM produto WHERE id = ?";
        Produto p = null;
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                p = new Produto();
                p.setId(rs.getInt("id"));
                p.setIdGerente(rs.getInt("id_gerente"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getDouble("valor"));
                p.setQuantidade(rs.getInt("quantidade"));
            }else{
                System.out.println("produto não encontrado");
            }

        } catch (SQLException e) {
            System.out.println("erro ao listar produto: " + e.getMessage());
        } catch (IllegalStateException e){
            System.out.println("Banco de dados não configurado: " + e.getMessage());
        }
        
        return p;
    }

    public boolean alterar_produto(Produto p){
        String sql = "UPDATE produto SET nome=?, quantidade=?, valor=? WHERE id = ?";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getQuantidade());
            stmt.setDouble(3, p.getValor());
            stmt.setInt(4, p.getId());

            stmt.execute();
        } catch (SQLException e) {
            System.out.println("erro ao alterar produto: " + e.getMessage());
            return false;
        } catch (IllegalStateException e){
            System.out.println("Banco de dados não configurado: " + e.getMessage());
            return false;
        }
        System.out.println("produto alterado com sucesso");
        return true;
    }

    public boolean remover_produto(int id){
        String sql = "DELETE FROM produto where id = ?";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            if(stmt.getUpdateCount() <= 0){
                System.out.println("nenhum produto excluido");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("erro ao deletar produto");
            return false;
        } catch (IllegalStateException e){
            System.out.println("Banco de dados não configurado: " + e.getMessage());
            return false;
        }
        System.out.println("produto removido com sucesso");
        return true;
    }

    public boolean inserir_produto(Produto p){
        String sql = "INSERT INTO produto (nome, valor, quantidade, id_gerente) VALUES (?,?,?,?)";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getValor());
            stmt.setInt(3, p.getQuantidade());
            stmt.setInt(4, p.getIdGerente());

            stmt.execute();
        } catch (SQLException e) {
            System.out.println("erro ao inserir produto: " + e.getMessage());
            return false;
        }catch(IllegalStateException e){
            System.out.println("Banco de dados não configurado: " + e.getMessage());
            return false;
        }
        System.out.println("produto cadastrado com sucesso");
        return true;
    }
}
