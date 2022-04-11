package com.camillacarvalho.projetos.entidades;

import com.camillacarvalho.projetos.janelas.HomeController;
import java.io.Serializable;

/**
 * 
 * @author Camilla Carvalho <camillacarvalho.c4@gmail.com>
 */
public class Usuario implements Serializable {

    public Usuario(){}
    
    public Usuario(String _nome, String _login, String _senha, String _nivel){
        this.id = HomeController.getListaUsuarios().size()+1;
        this.nome = _nome;
        this.login = _login;
        this.senha = _senha;
        this.nivelAcesso = _nivel;
    }
    
    private int id;
    private String nome;
    private String login;
    private String senha;
    private String nivelAcesso;

    public Usuario(int _id, String _nome, String _login, String _senha, String _nivel) {
        this.id = _id;
        this.nome = _nome;
        this.login = _login;
        this.senha = _senha;
        this.nivelAcesso = _nivel;
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

    public String getNivel() {
        return nivelAcesso;
    }

    public void setNivel(String nivel) {
        this.nivelAcesso = nivel;
    }

    
}
