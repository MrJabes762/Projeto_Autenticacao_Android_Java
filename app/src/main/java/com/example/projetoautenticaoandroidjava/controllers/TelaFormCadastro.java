package com.example.projetoautenticaoandroidjava.controllers;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.services.Operacoes;

public abstract class TelaFormCadastro extends AppCompatActivity implements Operacoes {
    //Pegando Os Objetos Da Tela
    //Edits
    private EditText pegarNomeCadastro;
    private EditText pegarEmailCadastro;
    private EditText pegarSenhaCadastro;;
    //Btn
    private AppCompatButton btnCadastrarForm;


    //Metodo Principal de Criação de Tela
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_form_cadastro);
        inicializarComponentes();
        Observadores();
    }

    protected EditText getPegarNomeCadastro() {
        return pegarNomeCadastro;
    }

    protected void setPegarNomeCadastro(EditText pegarNomeCadastro) {
        this.pegarNomeCadastro = pegarNomeCadastro;
    }

    protected EditText getPegarEmailCadastro() {
        return pegarEmailCadastro;
    }

    protected void setPegarEmailCadastro(EditText pegarEmailCadastro) {
        this.pegarEmailCadastro = pegarEmailCadastro;
    }

    protected EditText getPegarSenhaCadastro() {
        return pegarSenhaCadastro;
    }

    protected void setPegarSenhaCadastro(EditText pegarSenhaCadastro) {
        this.pegarSenhaCadastro = pegarSenhaCadastro;
    }

    protected AppCompatButton getBtnCadastrarForm() {
        return btnCadastrarForm;
    }

    protected void setBtnCadastrarForm(AppCompatButton btnCadastrarForm) {
        this.btnCadastrarForm = btnCadastrarForm;
    }
}