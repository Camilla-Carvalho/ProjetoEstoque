package com.camillacarvalho.projetos.entidades;

import java.io.Serializable;

/**
 * 
 * @author Camilla Carvalho, Fatec Botucatu 2022
 */
public class Cliente implements Serializable {
    private int id;
     private String nome;
     private String usuario;
     private String senha;
     private String cpf;
     private Endereco endereco;
     private String telefone;
     private String email;
     private String nascimento;

    public Cliente(int _id, String _nome, String _usuario, String _senha, String _cpf, Endereco _endereco, String _telefone, String _email, String _nascimento) {
        this.id = _id;
        this.nome = _nome;
        this.usuario = _usuario;
        this.senha = _senha;
        this.cpf = _cpf;
        this.endereco = _endereco;
        this.telefone = _telefone;
        this.email = _email;
        this.nascimento = _nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usario) {
        this.usuario = usario;
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
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
     
}
