package com.example.projetoautenticaoandroidjava.services.TelaFormCadastro;

public class FormCadastroException extends Exception{
    private String mensagem;
    public FormCadastroException (String mensagem){
        super (mensagem);
    }
}
