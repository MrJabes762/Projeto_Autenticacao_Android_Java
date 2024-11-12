
package com.example.projetoautenticaoandroidjava.data;

import android.content.Context;

import com.example.projetoautenticaoandroidjava.services.TelaPrincipal.TelaPrincipalImplementacao;
import com.example.projetoautenticaoandroidjava.model.Usuario;
import com.example.projetoautenticaoandroidjava.services.exibirMensagem.Exibir;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseFirestoreRepository {
    private static FirebaseFirestore firestoreInstance = FirebaseFirestore.getInstance();
    private static FirebaseAuth authInstance = FirebaseAuth.getInstance();

    public static void adicionarUsuario(Context context, String email, String senha, String nome) {
        authInstance.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        salvarDadosDoUsuario(context, nome);
                        Exibir.exibirMensagem(context, "Usuário cadastrado com sucesso no banco de dados!");
                    } else {
                        tratarExcecoesDeCadastro(context, task.getException());
                    }
                });
    }

    private static void salvarDadosDoUsuario(Context context, String nome) {
        Map<String, Object> dadosUsuario = new HashMap<>();
        dadosUsuario.put("Nome", nome);

        String usuarioID = authInstance.getCurrentUser().getUid();
        DocumentReference docRef = firestoreInstance.collection("Usuarios").document(usuarioID);

        docRef.set(dadosUsuario).addOnSuccessListener(sucess ->
                        Exibir.exibirMensagem(context, "Usuario Salvo Com Sucesso"))
                .addOnFailureListener(failure ->
                        Exibir.exibirMensagem(context, "Erro ao cadastrar o Usuario: " + failure.getMessage()));
    }

    private static void tratarExcecoesDeCadastro(Context context, Exception e) {
        if (e instanceof FirebaseAuthUserCollisionException) {
            Exibir.exibirMensagem(context, "Esta conta já foi cadastrada");
        } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
            Exibir.exibirMensagem(context, "Email inválido");
        } else {
            Exibir.exibirMensagem(context, "Erro ao realizar o cadastro");
        }
    }

    public static Task<AuthResult> autenticarUsuario(Usuario user) {
        return authInstance.signInWithEmailAndPassword(user.getEmail(), user.getSenha());
    }

    public static FirebaseUser pegarUsuarioAtual() {
        return authInstance.getCurrentUser();
    }

    public static void delogarUsuario() {
        authInstance.signOut();
    }

    public static void pegarDados(String id, TelaPrincipalImplementacao.Callback<String> callback) {
        DocumentReference docRef = firestoreInstance.collection("Usuarios").document(id);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null && task.getResult().exists()) {
                String dados = task.getResult().getString("Nome");
                callback.onSuccess(dados);
            } else {
                callback.onError("Usuário não encontrado no Firestore");
            }
        });
    }
}
