package com.example.projetoautenticaoandroidjava.model;

import com.example.projetoautenticaoandroidjava.services.TelaFormCadastro.FormCadastroException;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private static String Id;

    public Usuario (String nome, String email, String senha) throws FormCadastroException {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
    }

    public Usuario(String email, String senha) throws FormCadastroException {
       setNome("");
       setEmail(email);
       setSenha(senha);
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws FormCadastroException {
        if (nome.isEmpty()) {
            throw new FormCadastroException("O Campo de Nome está vazio. Preencha novamente.");
        } else {
            this.nome = nome;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws FormCadastroException {
        if (email.isEmpty()){
            throw new FormCadastroException("O Campo de Email está Vazio");
        }else {
            this.email = email;
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws FormCadastroException {
        if (senha.isEmpty()){
            throw new FormCadastroException("O Campo de Senha está vazio");
        } else if (senha.length() < 6) {
            throw new FormCadastroException("Digite uma senha com no mínimo 6 caracteres");
        } else{
            this.senha = senha;
        }
    }

    public static String getId() {
        return Id;
    }

    public static void setId(String id) {
        Id = id;
    }
}
