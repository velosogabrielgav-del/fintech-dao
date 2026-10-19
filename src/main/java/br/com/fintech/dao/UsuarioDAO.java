package br.com.fintech.dao;

import br.com.fintech.config.ConnectionFactory;
import br.com.fintech.model.Usuario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public List<Usuario> getAll() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id_usuario, nome, email, data_cadastro "
                + "FROM usuario ORDER BY id_usuario";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                Date data = rs.getDate("data_cadastro");
                if (data != null) {
                    u.setDataCadastro(data.toLocalDate());
                }
                lista.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar usuários: " + e.getMessage());
        }
        return lista;
    }

    public boolean insert(Usuario u) {
        String sql = "INSERT INTO usuario (nome, email, senha_hash) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id_usuario"})) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getSenhaHash());

            if (ps.executeUpdate() == 0) {
                return false;
            }
            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) {
                    u.setId(chaves.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
            return false;
        }
    }
}
