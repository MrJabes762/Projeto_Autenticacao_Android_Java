package com.example.projetoautenticaoandroidjava.Services.Direcionador;

import android.content.Context;
import android.content.Intent;

public abstract class DirecionadorDeLayout {
    public static void irParaLayout (Context contexto, Class<?> layout){
        contexto.startActivity(new Intent(contexto,layout));
    }
}
