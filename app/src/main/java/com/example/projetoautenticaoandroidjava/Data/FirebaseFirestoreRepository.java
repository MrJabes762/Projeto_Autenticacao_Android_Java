package com.example.projetoautenticaoandroidjava.Data;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.Task;

public abstract class FirebaseFirestoreRepository {

    public static void adicionarUsuario(Context context, String email, String senha) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(context, "Usuário cadastrado com sucesso no banco de dados!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
