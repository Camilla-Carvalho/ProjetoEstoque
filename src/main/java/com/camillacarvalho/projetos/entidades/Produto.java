package com.camillacarvalho.projetos.entidades;

import java.io.Serializable;

/**
 *
 * @author Camilla Carvalho, Fatec Botucatu 2022
 */
public class Produto implements Serializable {

    private int id;
    private String nome;
    private String descricao;
    private String preco;

    public Produto(int _id, String _nome, String _desc, String _preco) {
        id = _id;
        nome = _nome;
        descricao = _desc;
        preco = _preco;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the preco
     */
    public String getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(String preco) {
        this.preco = preco;
    }
    
}
