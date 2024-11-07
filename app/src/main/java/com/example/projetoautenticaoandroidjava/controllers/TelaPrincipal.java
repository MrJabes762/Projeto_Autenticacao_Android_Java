package com.example.projetoautenticaoandroidjava.controllers;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.services.Operacoes;

public abstract class TelaPrincipal extends AppCompatActivity implements Operacoes {
    // Objetos
    // TextsViews
    private TextView textExibirUsuairo;
    private TextView textExibirEmail;
    // Botoao
    private AppCompatButton btnDeslogar;

    //Metoodo Princpla de Criação da Tela
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        inicializarComponentes();
        Observadores();
    }

    protected TextView getTextExibirUsuairo() {
        return textExibirUsuairo;
    }

    protected void setTextExibirUsuairo(TextView textExibirUsuairo) {
        this.textExibirUsuairo = textExibirUsuairo;
    }

    protected TextView getTextExibirEmail() {
        return textExibirEmail;
    }

    protected void setTextExibirEmail(TextView textExibirEmail) {
        this.textExibirEmail = textExibirEmail;
    }

    protected AppCompatButton getBtnDeslogar() {
        return btnDeslogar;
    }

    protected void setBtnDeslogar(AppCompatButton btnDeslogar) {
        this.btnDeslogar = btnDeslogar;
    }
}