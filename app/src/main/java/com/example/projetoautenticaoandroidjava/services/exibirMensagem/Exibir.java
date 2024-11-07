package com.example.projetoautenticaoandroidjava.services.exibirMensagem;

import android.content.Context;
import android.widget.Toast;

public abstract class Exibir {
    public static void exibirMensagem(Context context, String mensagem) {
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }
}

