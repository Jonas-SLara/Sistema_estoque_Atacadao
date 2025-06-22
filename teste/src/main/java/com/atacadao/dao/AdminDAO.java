package com.atacadao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.atacadao.model.Admin;

public class AdminDAO {

    // Buscar um admin pelo ID
    public static Admin buscarPorId(int id) {
        String sql = "SELECT * FROM admin WHERE id = ?";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Admin admin = new Admin();

                admin.setId(rs.getInt("id"));
                System.out.println(admin.getId());
                
                admin.setCnpj(rs.getString("cnpj"));
                admin.setNome(rs.getString("nome"));
                admin.setEmail(rs.getString("email"));
                admin.setTelefone(rs.getString("telefone"));
                admin.setSenha(rs.getString("senha"));

                System.out.println("admin buscado");
                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("admin nao encontrado");
        return null;
    }

    public static Admin buscarPorCNPJ(String cnpj){
         String sql = "SELECT * FROM admin WHERE cnpj = ?";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cnpj);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setCnpj(rs.getString("cnpj"));
                admin.setNome(rs.getString("nome"));
                admin.setEmail(rs.getString("email"));
                admin.setTelefone(rs.getString("telefone"));
                admin.setSenha(rs.getString("senha"));

                System.out.println("admin buscado");
                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("admin nao encontrado");
        return null;
    }

    // Atualizar os dados do admin
    public static boolean alterarAdmin(Admin admin) {
        String sql = "UPDATE admin SET cnpj = ?, nome = ?, email = ?, telefone = ?, senha = ? WHERE id = ?";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, admin.getCnpj());
            stmt.setString(2, admin.getNome());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getTelefone());
            stmt.setString(5, admin.getSenha()); // Idealmente, usar hash
            stmt.setInt(6, admin.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas > 0){
                System.out.println("daodos alterados");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("nenhum dado alterado");
        return false;
    }
}
