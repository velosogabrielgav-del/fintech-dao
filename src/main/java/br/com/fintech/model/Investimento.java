package br.com.fintech.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Investimento {

    private int id;
    private int idUsuario;
    private String nome;
    private String tipo;
    private BigDecimal valorInvestido;
    private LocalDate dataInvestimento;
    private BigDecimal rentabilidade; // opcional (pode ser null)

    public Investimento() {
    }

    public Investimento(int idUsuario, String nome, String tipo,
                        BigDecimal valorInvestido, LocalDate dataInvestimento,
                        BigDecimal rentabilidade) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.tipo = tipo;
        this.valorInvestido = valorInvestido;
        this.dataInvestimento = dataInvestimento;
        this.rentabilidade = rentabilidade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getValorInvestido() { return valorInvestido; }
    public void setValorInvestido(BigDecimal valorInvestido) { this.valorInvestido = valorInvestido; }

    public LocalDate getDataInvestimento() { return dataInvestimento; }
    public void setDataInvestimento(LocalDate dataInvestimento) { this.dataInvestimento = dataInvestimento; }

    public BigDecimal getRentabilidade() { return rentabilidade; }
    public void setRentabilidade(BigDecimal rentabilidade) { this.rentabilidade = rentabilidade; }

    @Override
    public String toString() {
        return "Investimento{id=" + id + ", idUsuario=" + idUsuario + ", nome='" + nome
                + "', tipo='" + tipo + "', valorInvestido=" + valorInvestido
                + ", dataInvestimento=" + dataInvestimento
                + ", rentabilidade=" + rentabilidade + "}";
    }
}
