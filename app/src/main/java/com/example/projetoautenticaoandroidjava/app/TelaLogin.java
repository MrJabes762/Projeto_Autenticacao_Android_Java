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
    private final HashMap<Integer, Class<?>> layoutCache = new HashMap<>();
    private Class<?> cachedLayoutPrincipal;
    private Class<?> cachedLayoutFormCadastro;
    private Class<?> layoutClass;

    private EditText pegarEmail;
    private EditText pegarSenha;
    private AppCompatButton btnEntrar;
    private AppCompatButton btnCadastrar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        inicializarComponentes();
        observadores();
    }

    @Override
    public void inicializarComponentes() {
        setEditTexts();
        setBtns();
        progressBar = findViewById(R.id.Progressbar);
    }

    private void setBtns() {
        btnEntrar = findViewById(R.id.btnEntrar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
    }

    private void setEditTexts() {
        pegarEmail = findViewById(R.id.pegarEmail);
        pegarSenha = findViewById(R.id.pegarSenha);
    }

    protected EditText getPegarEmail() {
        return pegarEmail;
    }

    protected EditText getPegarSenha() {
        return pegarSenha;
    }

    protected AppCompatButton getBtnEntrar() {
        return btnEntrar;
    }

    protected AppCompatButton getBtnCadastrar() {
        return btnCadastrar;
    }

    protected ProgressBar getProgressBar() {
        return progressBar;
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
}
