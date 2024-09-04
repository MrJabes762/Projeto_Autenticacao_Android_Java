package com.example.projetoautenticaoandroidjava.Data;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public abstract class FirebaseFirestoreRepository {

    public static void adicionarUsuario(Context context, String email, String senha) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(task -> {
                    try {
                        if (task.isSuccessful()) {
                            exibirMensagem(context, "Usuário cadastrado com sucesso no banco de dados!");
                        } else {
                            throw task.getException();
                        }
                    } catch (FirebaseAuthUserCollisionException e) {
                        exibirMensagem(context, "Esta conta já foi cadastrada");
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        exibirMensagem(context, "Email inválido");
                    }catch (Exception e){
                        exibirMensagem(context, "Erro ao realizar o cadastro ");
                    }
                });
    }

    private static void exibirMensagem(Context context, String mensagem) {
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }
}
