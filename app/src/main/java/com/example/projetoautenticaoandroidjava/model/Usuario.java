
package com.example.projetoautenticaoandroidjava.model;

import com.example.projetoautenticaoandroidjava.services.TelaFormCadastro.FormCadastroException;

public class Usuario {
    private String nome;
    private String email;
    private String senha;

    public Usuario(String nome, String email, String senha) throws FormCadastroException {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
    }

    public Usuario(String email, String senha) throws FormCadastroException {
        this("", email, senha);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws FormCadastroException {
        if (nome == null || nome.isEmpty()) {
            throw new FormCadastroException("O campo de Nome está vazio. Preencha novamente.");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws FormCadastroException {
        if (email == null || email.isEmpty()) {
            throw new FormCadastroException("O campo de Email está vazio.");
        }
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws FormCadastroException {
        if (senha == null || senha.isEmpty()) {
            throw new FormCadastroException("O campo de Senha está vazio.");
        } else if (senha.length() < 6) {
            throw new FormCadastroException("Digite uma senha com no mínimo 6 caracteres.");
        }
        this.senha = senha;
    }
}
