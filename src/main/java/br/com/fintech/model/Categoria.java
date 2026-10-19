package br.com.fintech.model;

public class Categoria {

    private int id;
    private String nome;
    private String tipo; // RECEITA ou DESPESA

    public Categoria() {
    }

    public Categoria(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return "Categoria{id=" + id + ", nome='" + nome + "', tipo='" + tipo + "'}";
    }
}
