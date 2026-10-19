package br.com.fintech.dao;

import br.com.fintech.config.ConnectionFactory;
import br.com.fintech.model.Investimento;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class InvestimentoDAO {

    public List<Investimento> getAll() {
        List<Investimento> lista = new ArrayList<>();
        String sql = "SELECT id_investimento, id_usuario, nome, tipo, valor_investido, "
                + "data_investimento, rentabilidade FROM investimento "
                + "ORDER BY data_investimento DESC, id_investimento DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Investimento i = new Investimento();
                i.setId(rs.getInt("id_investimento"));
                i.setIdUsuario(rs.getInt("id_usuario"));
                i.setNome(rs.getString("nome"));
                i.setTipo(rs.getString("tipo"));
                i.setValorInvestido(rs.getBigDecimal("valor_investido"));
                Date data = rs.getDate("data_investimento");
                if (data != null) {
                    i.setDataInvestimento(data.toLocalDate());
                }
                i.setRentabilidade(rs.getBigDecimal("rentabilidade")); // null se vazio
                lista.add(i);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar investimentos: " + e.getMessage());
        }
        return lista;
    }

    public boolean insert(Investimento inv) {
        String sql = "INSERT INTO investimento (id_usuario, nome, tipo, valor_investido, "
                + "data_investimento, rentabilidade) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id_investimento"})) {

            ps.setInt(1, inv.getIdUsuario());
            ps.setString(2, inv.getNome());
            ps.setString(3, inv.getTipo());
            ps.setBigDecimal(4, inv.getValorInvestido());
            ps.setDate(5, Date.valueOf(inv.getDataInvestimento()));
            BigDecimal renta = inv.getRentabilidade();
            if (renta == null) {
                ps.setNull(6, Types.NUMERIC);
            } else {
                ps.setBigDecimal(6, renta);
            }

            if (ps.executeUpdate() == 0) {
                return false;
            }
            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) {
                    inv.setId(chaves.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir investimento: " + e.getMessage());
            return false;
        }
    }
}
