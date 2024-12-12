package br.com.tempjunior.model;

import java.math.BigDecimal;

public class Cliente {
    private Long id;
    private String nome;
    private BigDecimal idade;
    private String codigo;

    public Cliente(String nome, BigDecimal idade, String codigo) {
        this.nome = nome;
        this.idade = idade;
        this.codigo = codigo;
    }

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getIdade() {
        return idade;
    }

    public void setIdade(BigDecimal idade) {
        this.idade = idade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
