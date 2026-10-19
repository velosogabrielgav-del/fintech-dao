package br.com.fintech.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String ARQUIVO = "db.properties";

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        Properties props = carregarArquivo();

        String url = valor("DB_URL", props.getProperty("db.url"));
        String usuario = valor("DB_USER", props.getProperty("db.user"));
        String senha = valor("DB_PASSWORD", props.getProperty("db.password"));

        if (vazio(url) || vazio(usuario) || vazio(senha)) {
            throw new SQLException("Preencha db.url, db.user e db.password em "
                    + "src/main/resources/" + ARQUIVO
                    + " (ou defina DB_URL, DB_USER e DB_PASSWORD no ambiente).");
        }
        return DriverManager.getConnection(url.trim(), usuario.trim(), senha);
    }

    private static Properties carregarArquivo() throws SQLException {
        Properties props = new Properties();
        try (InputStream in = ConnectionFactory.class.getClassLoader()
                .getResourceAsStream(ARQUIVO)) {
            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            throw new SQLException("Não foi possível ler o arquivo " + ARQUIVO
                    + ": " + e.getMessage(), e);
        }
        return props;
    }

    private static String valor(String variavelAmbiente, String doArquivo) {
        String doAmbiente = System.getenv(variavelAmbiente);
        return vazio(doAmbiente) ? doArquivo : doAmbiente;
    }

    private static boolean vazio(String s) {
        return s == null || s.isBlank();
    }
}
