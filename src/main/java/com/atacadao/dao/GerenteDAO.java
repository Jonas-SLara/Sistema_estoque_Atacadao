package com.atacadao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.atacadao.model.Gerente;
import com.atacadao.model.Produto;
import com.atacadao.model.Funcionario;
import com.atacadao.model.Usuario;

public class GerenteDAO {
    public ArrayList<Gerente> listar_gerentes(){
        ArrayList<Gerente> gerentes = new ArrayList<Gerente>();
        String sql = "SELECT * FROM gerente g INNER JOIN usuario u "+
        "ON g.cpf_usuario = u.cpf";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                //seta gerente
                Gerente g = new Gerente();
                g.setCpfUsuario(rs.getString("cpf_usuario"));
                g.setBonificacao(rs.getDouble("bonificacao"));
                g.setId(rs.getInt("id"));
                //seta usuario
                Usuario u = new Usuario();
                u.setCelular(rs.getString("celular"));
                u.setEmail(rs.getString("email"));
                u.setNome(rs.getString("nome"));
                u.setSalario(rs.getDouble("salario"));
                g.setUsuario(u);
                gerentes.add(g);
            }
        } catch (SQLException e) {
            System.out.println("erro ao listar gerentes: " + e.getMessage());
        } catch (IllegalStateException e){
            System.out.println("banco de dados não configurado: " + e.getMessage());
        }
        return gerentes;
    }

    public Gerente buscar_gerente(String cpf_usuario){
        Gerente g = null;
        String sql = "SELECT * FROM gerente g INNER JOIN usuario u "+
        "ON g.cpf_usuario = u.cpf AND g.cpf_usuario = ?";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cpf_usuario);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                g = new Gerente();
                g.setId(rs.getInt("id"));
                g.setBonificacao(rs.getDouble("bonificacao"));
                g.setCpfUsuario(rs.getString("cpf_usuario"));
                Usuario u = new Usuario();
                u.setCelular(rs.getString("celular"));
                u.setEmail(rs.getString("email"));
                u.setNome(rs.getString("nome"));
                u.setSalario(rs.getDouble("salario"));
                g.setUsuario(u);
            }else{
                System.out.println("gerente não encontrado");
                return g;
            }
        } catch (SQLException e) {
            System.out.println("erro ao buscar gerente: " + e.getMessage());
        } catch (IllegalStateException e){
            System.out.println("banco de dados não configurado: " + e.getMessage());
        }
        System.out.println("gerente buscado com sucesso");
        return g;
    }

    public Gerente buscar_gerente_id(int id){
        String sql = "SELECT * FROM gerente g INNER JOIN usuario u " +
        "ON g.cpf_usuario = u.cpf AND g.id = ?";

        Gerente g = null;
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                //pega apenas os dados nescessários do gerente
                g=new Gerente();
                Usuario u = new Usuario();
                u.setNome(rs.getString("nome"));
                u.setCelular(rs.getString("celular"));
                u.setEmail(rs.getString("email"));
                g.setUsuario(u);
            }else{
                System.out.println("gerente não encontrado");
                return g;
            }
        } catch (SQLException e) {
            System.out.println("erro ao buscar gerente: " + e.getMessage());
        } catch (IllegalStateException e){
            System.out.println("banco de dados não configurado: " + e.getMessage());
        }
        System.out.println("gerente buscado");
        return g;
    }
    
    public ArrayList<Funcionario> listar_seus_funcionarios(int id){
        
        String sql = "SELECT * FROM funcionario f INNER JOIN usuario u "+
        "ON f.cpf_usuario = u.cpf AND f.id_gerente = ?";
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

        try (Connection con = Conexao.obterConexao();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //seta funcionario
                Funcionario f = new Funcionario();
                f.setCargo(rs.getString("cargo"));
                f.setCpfUsuario(rs.getString("cpf_usuario"));
                f.setId(rs.getInt("id"));
                f.setIdGerente(rs.getInt("id_gerente"));
                //seta usuario
                Usuario u = new Usuario();
                u.setSalario(rs.getDouble("salario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setCelular(rs.getString("celular"));
                u.setCpf(rs.getString("cpf"));
                f.setUsuario(u);
                funcionarios.add(f);
            }
        } catch (SQLException e) {
            System.out.println("erro ao buscar funcionarios do gerente " +
             id + " " + e.getMessage());
            e.printStackTrace();
        }
        return funcionarios;
    } 

    public ArrayList<Produto> listar_seus_produtos(int id){
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        String sql = "SELECT p.id, p.nome, p.valor, p.quantidade, p.id_gerente " +
        "FROM gerente g, produto p " +
        "WHERE g.id = p.id_gerente AND " +
        "p.id_gerente = ?";

        try (Connection con = Conexao.obterConexao();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setIdGerente(rs.getInt("id_gerente"));
                p.setNome(rs.getString("nome"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setValor(rs.getDouble("valor"));
                produtos.add(p);
            }
            
        } catch (SQLException e) {
            System.out.println("erro ao listar produtos do gerente " +
            e.getMessage());
            e.printStackTrace();
        }
        return produtos;
    }

    public boolean inserir_gerente(Gerente g){
        String sql = "INSERT INTO gerente (cpf_usuario, bonificacao) VALUES(?, ?)";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, g.getCpfUsuario());
            stmt.setDouble(2, g.getBonificacao());
            stmt.execute();
            System.out.println("gerente cadastrado com sucesso");
        } catch (SQLException e) {
            System.out.println("erro ao cadastrar gerente");
            return false;
        } catch (IllegalStateException e){
            System.out.println("banco de dados não configurado: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean remover_gerente(int id){
        String sql = "DELETE FROM gerente WHERE id = ?";
        try(Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.execute();
            if(stmt.getUpdateCount() <= 0){
                System.out.println("nenhum gerente removido");
                return false;
            }
            System.out.println("gerente removido com sucesso");
        } catch(SQLException e){
            System.out.println("erro ao remover gerente: " + e.getMessage());
            return false;
        } catch (IllegalStateException e){
            System.out.println("banco de dados não configurado: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean alterar_gerente(Gerente g){
        String sql = "UPDATE gerente SET bonificacao = ? WHERE id = ?";
        try (Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDouble(1, g.getBonificacao());
            stmt.setInt(2, g.getId());
            stmt.execute();
            System.out.println("dados do gerente alterado");
        } catch (SQLException e) {
            System.out.println("erro ao alterar dados do gerente "+ e.getMessage());
            return false;
        } catch (IllegalStateException e){
            System.out.println("banco de dados não configurado: " + e.getMessage());
            return false;
        }
        return true;
    }
}
