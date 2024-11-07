package com.example.projetoautenticaoandroidjava.controllers;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.services.Operacoes;

import java.util.HashMap;

public abstract class TelaLogin extends AppCompatActivity implements Operacoes {
    // Utilizando Coletcion de Acesso Rapido
    private HashMap<Integer, Class<?>> layoutCache;
    private Class<?> cachedLayoutPrincipal;
    private Class<?> cachedLayoutFormCadastro;
    private Class<?> layoutClass;

    // Objetos da tela que serão manipulados

    private EditText pegarEmail;
    private EditText pegarSenha;
    private AppCompatButton btnEntrar;
    private AppCompatButton btnCadastrar;
    private ProgressBar progressBar;

    // Método Principal de criação da tela
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        inicializarComponentes();// vão apontar para os metodos da classe filha
        Observadores();
    }

    // getters seters

    protected HashMap<Integer, Class<?>> getLayoutCache() {
        return layoutCache;
    }

    protected void setLayoutCache(HashMap<Integer, Class<?>> layoutCache) {
        this.layoutCache = layoutCache;
    }

    protected Class<?> getCachedLayoutPrincipal() {
        return cachedLayoutPrincipal;
    }

    protected void setCachedLayoutPrincipal(Class<?> cachedLayoutPrincipal) {
        this.cachedLayoutPrincipal = cachedLayoutPrincipal;
    }

    protected Class<?> getCachedLayoutFormCadastro() {
        return cachedLayoutFormCadastro;
    }

    protected void setCachedLayoutFormCadastro(Class<?> cachedLayoutFormCadastro) {
        this.cachedLayoutFormCadastro = cachedLayoutFormCadastro;
    }

    protected EditText getPegarEmail() {
        return pegarEmail;
    }

    protected void setPegarEmail(EditText pegarEmail) {
        this.pegarEmail = pegarEmail;
    }

    protected EditText getPegarSenha() {
        return pegarSenha;
    }

    protected void setPegarSenha(EditText pegarSenha) {
        this.pegarSenha = pegarSenha;
    }

    protected AppCompatButton getBtnEntrar() {
        return btnEntrar;
    }

    protected void setBtnEntrar(AppCompatButton btnEntrar) {
        this.btnEntrar = btnEntrar;
    }

    protected AppCompatButton getBtnCadastrar() {
        return btnCadastrar;
    }

    protected void setBtnCadastrar(AppCompatButton btnCadastrar) {
        this.btnCadastrar = btnCadastrar;
    }

    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    protected void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    protected Class<?> getLayoutClass() {
        return layoutClass;
    }

    protected void setLayoutClass(Class<?> layoutClass) {
        this.layoutClass = layoutClass;
    }
}