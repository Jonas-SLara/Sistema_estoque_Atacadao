package com.atacadao.dao;

import com.atacadao.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO{

    //listar os usuarios do banco
    public ArrayList<Usuario> listarUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        String sql = "SELECT * FROM USUARIO";

        //abre e fecha a conexao com o banco com try resources
        try(Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                Usuario u = new Usuario();
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setCelular(rs.getString("celular"));
                u.setCpf(rs.getString("cpf"));
                u.setSalario(rs.getDouble("salario"));
                usuarios.add(u);
            }
        } catch(SQLException e) {
            System.err.println("Erro ao listar usuarios: " + e.getMessage());
        } catch(IllegalStateException e){
            System.err.println("erro ao configurar o banco de dados: " + e.getMessage());
        }

        return usuarios;
    }

    //busca um usuario pelo cpf
    public Usuario buscar_por_cpf(String cpf){
        String sql = "SELECT * FROM USUARIO WHERE cpf = ?";
        Usuario usuario = null;
        try(Connection con = Conexao.obterConexao();
                PreparedStatement stmt = con.prepareStatement(sql);){
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                usuario = new Usuario();// fica != de null
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setSalario(rs.getDouble("salario"));
                usuario.setCelular(rs.getString("celular"));
                usuario.setCpf(rs.getString("cpf"));
            }else{
                System.out.println("usuario não encontrado");
            }

        }
        catch (SQLException e){
            System.err.println("Erro ao buscar produto " + e.getMessage());
        }
        catch(IllegalStateException e){
            System.err.println("Erro ao configurar o banco de dados: " + e.getMessage());
        }
        return usuario;
    }

    //remover um usuario
    public boolean removerUsuario(String cpf){
        String sql = "DELETE FROM USUARIO WHERE cpf = ?";

        try( Connection con = Conexao.obterConexao();
                PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, cpf);
            stmt.execute();
            if(stmt.getUpdateCount()<=0){
                System.out.println("Nenhum usuario excluido");
                return false;
            }

        }
        catch(SQLException e){
            System.err.println("Erro ao remover usuario" + e.getMessage());
            return false;
        }
        catch(IllegalStateException e){
            System.err.println("erro ao configurar banco de dados: " + e.getMessage());
            return false;
        }

        System.out.println("usuario excluido com sucesso");
        return true;
    }

    //alterar um usuario da tabela de USUARIO
    public boolean alterarUsuario(Usuario u){
        String sql = "UPDATE USUARIO SET email = ?, senha = ?, nome = ?, salario = ?, celular = ? WHERE cpf = ?";
        try(Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getSenha());
            stmt.setString(3, u.getNome());
            stmt.setDouble(4, u.getSalario());
            stmt.setString(5, u.getCelular());
            stmt.setString(6, u.getCpf());
            stmt.execute();
        }
        catch (SQLException e){
            System.err.println("erro ao alterar usuario " + e.getMessage());
            return false;
        }
        catch (IllegalStateException e){
            System.err.println("erro ao configurar banco de dados: " + e.getMessage());
            return false;
        }
        System.out.println("usuario alterado com sucesso");
        return true;
    }

    //inserir um usuario novo
    public boolean inserirUsuario(Usuario u){
        String sql = "INSERT INTO USUARIO (email, senha, nome, salario, celular, cpf) VALUES(?, ?, ?, ?, ?, ?)";
        try(Connection con = Conexao.obterConexao();
            PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getSenha());
            stmt.setString(3, u.getNome());
            stmt.setDouble(4, u.getSalario());
            stmt.setString(5, u.getCelular());
            stmt.setString(6, u.getCpf());
            stmt.execute();
            System.out.println("novo usuario inserido com sucesso");
        }
        catch (SQLException e) {
            System.err.println("erro ao inserir usuario " + e.getMessage());
            return false;
        }
        catch (IllegalStateException e){
            System.err.println("erro ao configurar banco de dados: " + e.getMessage());
            return false;
        }
        return true;
    }
}
