package br.com.fintech.dao;

import br.com.fintech.config.ConnectionFactory;
import br.com.fintech.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public List<Categoria> getAll() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT id_categoria, nome, tipo FROM categoria ORDER BY id_categoria";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id_categoria"));
                c.setNome(rs.getString("nome"));
                c.setTipo(rs.getString("tipo"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar categorias: " + e.getMessage());
        }
        return lista;
    }

    public boolean insert(Categoria c) {
        String sql = "INSERT INTO categoria (nome, tipo) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id_categoria"})) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getTipo());

            if (ps.executeUpdate() == 0) {
                return false;
            }
            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) {
                    c.setId(chaves.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir categoria: " + e.getMessage());
            return false;
        }
    }
}
