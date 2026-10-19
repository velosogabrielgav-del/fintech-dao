package br.com.fintech.app;

import br.com.fintech.dao.CategoriaDAO;
import br.com.fintech.dao.DespesaDAO;
import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.model.Categoria;
import br.com.fintech.model.Despesa;
import br.com.fintech.model.Investimento;
import br.com.fintech.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Teste {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        DespesaDAO despesaDAO = new DespesaDAO();
        InvestimentoDAO investimentoDAO = new InvestimentoDAO();

        long sufixo = System.currentTimeMillis();

        System.out.println("=== Cadastrando usuários ===");
        Usuario[] usuarios = {
                new Usuario("Ana Souza", "ana" + sufixo + "@email.com", "hash_ficticio_1"),
                new Usuario("Bruno Lima", "bruno" + sufixo + "@email.com", "hash_ficticio_2"),
                new Usuario("Carla Mendes", "carla" + sufixo + "@email.com", "hash_ficticio_3"),
                new Usuario("Diego Rocha", "diego" + sufixo + "@email.com", "hash_ficticio_4"),
                new Usuario("Elisa Prado", "elisa" + sufixo + "@email.com", "hash_ficticio_5")
        };
        for (Usuario u : usuarios) {
            System.out.println(usuarioDAO.insert(u) ? "OK: " + u : "FALHA ao inserir " + u.getNome());
        }

        // CATEGORIA (necessária para Despesa)
        System.out.println("\n=== Cadastrando categorias ===");
        Categoria[] categorias = {
                new Categoria("Alimentação", "DESPESA"),
                new Categoria("Transporte", "DESPESA"),
                new Categoria("Moradia", "DESPESA"),
                new Categoria("Lazer", "DESPESA"),
                new Categoria("Salário", "RECEITA")
        };
        for (Categoria c : categorias) {
            System.out.println(categoriaDAO.insert(c) ? "OK: " + c : "FALHA ao inserir " + c.getNome());
        }

        if (usuarios[0].getId() == 0 || categorias[0].getId() == 0) {
            System.err.println("\nNão foi possível cadastrar usuários/categorias. "
                    + "Verifique a conexão e as variáveis DB_USER / DB_PASSWORD.");
            return;
        }

        //  DESPESA
        System.out.println("\n=== Cadastrando despesas ===");
        Despesa[] despesas = {
                new Despesa(usuarios[0].getId(), categorias[0].getId(), new BigDecimal("89.90"),
                        LocalDate.of(2026, 10, 1), "Supermercado"),
                new Despesa(usuarios[1].getId(), categorias[1].getId(), new BigDecimal("45.00"),
                        LocalDate.of(2026, 10, 3), "Corrida de aplicativo"),
                new Despesa(usuarios[2].getId(), categorias[2].getId(), new BigDecimal("1200.00"),
                        LocalDate.of(2026, 10, 5), "Aluguel"),
                new Despesa(usuarios[3].getId(), categorias[3].getId(), new BigDecimal("60.00"),
                        LocalDate.of(2026, 10, 7), "Cinema"),
                new Despesa(usuarios[4].getId(), categorias[0].getId(), new BigDecimal("32.50"),
                        LocalDate.of(2026, 10, 9), "Almoço")
        };
        for (Despesa d : despesas) {
            System.out.println(despesaDAO.insert(d) ? "OK: " + d : "FALHA ao inserir despesa " + d.getDescricao());
        }

        // INVESTIMENTO
        System.out.println("\n=== Cadastrando investimentos ===");
        Investimento[] investimentos = {
                new Investimento(usuarios[0].getId(), "Tesouro Selic 2029", "TESOURO_DIRETO",
                        new BigDecimal("1000.00"), LocalDate.of(2026, 9, 10), new BigDecimal("10.75")),
                new Investimento(usuarios[1].getId(), "CDB Banco X", "CDB",
                        new BigDecimal("2500.00"), LocalDate.of(2026, 9, 15), new BigDecimal("11.20")),
                new Investimento(usuarios[2].getId(), "Fundo Imobiliário ABCD11", "FII",
                        new BigDecimal("800.00"), LocalDate.of(2026, 9, 20), new BigDecimal("8.50")),
                new Investimento(usuarios[3].getId(), "Ação XYZ3", "ACAO",
                        new BigDecimal("1500.00"), LocalDate.of(2026, 9, 25), null),
                new Investimento(usuarios[4].getId(), "LCI Banco Y", "LCI",
                        new BigDecimal("3000.00"), LocalDate.of(2026, 10, 2), new BigDecimal("9.90"))
        };
        for (Investimento i : investimentos) {
            System.out.println(investimentoDAO.insert(i) ? "OK: " + i : "FALHA ao inserir " + i.getNome());
        }

        // CONSULTAS
        System.out.println("\n=================== getAll() ===================");

        System.out.println("\n--- Usuários ---");
        List<Usuario> listaUsuarios = usuarioDAO.getAll();
        listaUsuarios.forEach(System.out::println);

        System.out.println("\n--- Categorias ---");
        List<Categoria> listaCategorias = categoriaDAO.getAll();
        listaCategorias.forEach(System.out::println);

        System.out.println("\n--- Despesas ---");
        List<Despesa> listaDespesas = despesaDAO.getAll();
        listaDespesas.forEach(System.out::println);

        System.out.println("\n--- Investimentos ---");
        List<Investimento> listaInvestimentos = investimentoDAO.getAll();
        listaInvestimentos.forEach(System.out::println);
    }
}
