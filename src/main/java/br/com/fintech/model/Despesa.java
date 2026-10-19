package br.com.fintech.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Despesa {

    private int id;
    private int idUsuario;
    private int idCategoria;
    private BigDecimal valor;
    private LocalDate dataDespesa;
    private String descricao;

    public Despesa() {
    }

    public Despesa(int idUsuario, int idCategoria, BigDecimal valor,
                   LocalDate dataDespesa, String descricao) {
        this.idUsuario = idUsuario;
        this.idCategoria = idCategoria;
        this.valor = valor;
        this.dataDespesa = dataDespesa;
        this.descricao = descricao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDate getDataDespesa() { return dataDespesa; }
    public void setDataDespesa(LocalDate dataDespesa) { this.dataDespesa = dataDespesa; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return "Despesa{id=" + id + ", idUsuario=" + idUsuario + ", idCategoria=" + idCategoria
                + ", valor=" + valor + ", dataDespesa=" + dataDespesa
                + ", descricao='" + descricao + "'}";
    }
}
