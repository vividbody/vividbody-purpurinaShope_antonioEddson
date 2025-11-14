package com.purpurina.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false, name = "metal")
    private String metal;

    @Column(name = "gema1")
    private String gema1;

    @Column(name = "gema2")
    private String gema2;

    @Column(name = "gema3")
    private String gema3;

    @Column(name = "gema4")
    private String gema4;

    @Column(name = "gema5")
    private String gema5;

    @Column(nullable = false)
    private BigDecimal peso;

    @Column
    private BigDecimal quilates;

    @Column(nullable = false)
    private String dimensoes;

    @Column(nullable = false, name = "preco_custo")
    private BigDecimal precoCusto;

    @Column(nullable = false, name = "preco_venda")
    private BigDecimal precoVenda;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    
    public Produto() {}

    public Produto(String marca, String metal, BigDecimal peso, BigDecimal quilates,
                   String dimensoes, BigDecimal precoCusto, BigDecimal precoVenda) {
        this.marca = marca;
        this.metal = metal;
        this.peso = peso;
        this.quilates = quilates;
        this.dimensoes = dimensoes;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getMetal() { return metal; }
    public void setMetal(String metal) { this.metal = metal; }

    public String getGema1() { return gema1; }
    public void setGema1(String gema1) { this.gema1 = gema1; }

    public String getGema2() { return gema2; }
    public void setGema2(String gema2) { this.gema2 = gema2; }

    public String getGema3() { return gema3; }
    public void setGema3(String gema3) { this.gema3 = gema3; }

    public String getGema4() { return gema4; }
    public void setGema4(String gema4) { this.gema4 = gema4; }

    public String getGema5() { return gema5; }
    public void setGema5(String gema5) { this.gema5 = gema5; }

    public BigDecimal getPeso() { return peso; }
    public void setPeso(BigDecimal peso) { this.peso = peso; }

    public BigDecimal getQuilates() { return quilates; }
    public void setQuilates(BigDecimal quilates) { this.quilates = quilates; }

    public String getDimensoes() { return dimensoes; }
    public void setDimensoes(String dimensoes) { this.dimensoes = dimensoes; }

    public BigDecimal getPrecoCusto() { return precoCusto; }
    public void setPrecoCusto(BigDecimal precoCusto) { this.precoCusto = precoCusto; }

    public BigDecimal getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(BigDecimal precoVenda) { this.precoVenda = precoVenda; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", metal='" + metal + '\'' +
                '}';
    }
}
