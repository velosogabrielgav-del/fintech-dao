package br.com.fintech.dao;

import br.com.fintech.config.ConnectionFactory;
import br.com.fintech.model.Despesa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO {

    public List<Despesa> getAll() {
        List<Despesa> lista = new ArrayList<>();
        String sql = "SELECT id_despesa, id_usuario, id_categoria, valor, "
                + "data_despesa, descricao FROM despesa "
                + "ORDER BY data_despesa DESC, id_despesa DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Despesa d = new Despesa();
                d.setId(rs.getInt("id_despesa"));
                d.setIdUsuario(rs.getInt("id_usuario"));
                d.setIdCategoria(rs.getInt("id_categoria"));
                d.setValor(rs.getBigDecimal("valor"));
                Date data = rs.getDate("data_despesa");
                if (data != null) {
                    d.setDataDespesa(data.toLocalDate());
                }
                d.setDescricao(rs.getString("descricao"));
                lista.add(d);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar despesas: " + e.getMessage());
        }
        return lista;
    }

    public boolean insert(Despesa d) {
        String sql = "INSERT INTO despesa (id_usuario, id_categoria, valor, "
                + "data_despesa, descricao) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id_despesa"})) {

            ps.setInt(1, d.getIdUsuario());
            ps.setInt(2, d.getIdCategoria());
            ps.setBigDecimal(3, d.getValor());
            ps.setDate(4, Date.valueOf(d.getDataDespesa()));
            ps.setString(5, d.getDescricao());

            if (ps.executeUpdate() == 0) {
                return false;
            }
            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) {
                    d.setId(chaves.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir despesa: " + e.getMessage());
            return false;
        }
    }
}
