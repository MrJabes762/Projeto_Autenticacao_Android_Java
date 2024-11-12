package com.example.projetoautenticaoandroidjava.controllers;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.services.Operacoes;

public abstract class TelaPrincipal extends AppCompatActivity implements Operacoes {
    private TextView textExibirUsuario;
    private TextView textExibirEmail;
    private AppCompatButton btnDeslogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        inicializarComponentes();
        observadores();
    }

    @Override
    public void inicializarComponentes() {
        textExibirEmail = findViewById(R.id.textExibirEmail);
        textExibirUsuario = findViewById(R.id.textexibirUsuario);
        btnDeslogar = findViewById(R.id.btnDeslogar);
    }

    protected TextView getTextExibirUsuario() {
        return textExibirUsuario;
    }

    protected TextView getTextExibirEmail() {
        return textExibirEmail;
    }

    protected AppCompatButton getBtnDeslogar() {
        return btnDeslogar;
    }
}
