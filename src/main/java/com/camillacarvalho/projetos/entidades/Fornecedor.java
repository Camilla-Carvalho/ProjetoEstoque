package com.camillacarvalho.projetos.entidades;

import java.io.Serializable;

/**
 * 
 * @author Camilla Carvalho, Fatec Botucatu 2022
 */
public class Fornecedor implements Serializable {
    
    private int id;
    private String nome;
    private String login;
    private String senha;
    private String cnpj;
    private String setorAtuacao;

    public Fornecedor(int _id, String _nome, String _login, String _senha, String _cnpj, String _setor) {
        id = _id;
        nome = _nome;
        login = _login;
        senha = _senha;
        cnpj = _cnpj;
        setorAtuacao = _setor;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String Login) {
        this.login = Login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSetorAtuacao() {
        return setorAtuacao;
    }

    public void setSetorAtuacao(String setorAtuacao) {
        this.setorAtuacao = setorAtuacao;
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
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
   
    
    


}
