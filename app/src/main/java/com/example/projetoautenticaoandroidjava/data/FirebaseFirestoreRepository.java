package com.example.projetoautenticaoandroidjava.data;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public abstract class FirebaseFirestoreRepository {
    private static FirebaseFirestore bancoDeDados;
    private static FirebaseAuth bancoDeDadosAtenticacoa;
    private static FirebaseUser usuarioAtual;
    private static Map<String,Object> usuarios;
    private static String usuarioID;
    private static DocumentReference documentReference;

    public static void adicionarUsuario(Context context, String email, String senha, String nome) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(task -> {
                    try {
                        if (task.isSuccessful()) {
                            salvarDadosDoUsuario(context,nome);
                            Exibir.exibirMensagem(context, "Usuário cadastrado com sucesso no banco de dados!");
                        } else {
                            throw task.getException();
                        }
                    } catch (FirebaseAuthUserCollisionException e) {
                        Exibir.exibirMensagem(context,"Esta conta já foi cadastrada");
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        Exibir.exibirMensagem(context, "Email inválido");
                    }catch (Exception e){
                        Exibir.exibirMensagem(context, "Erro ao realizar o cadastro ");
                    }
                });
    }

    private static void salvarDadosDoUsuario(Context context, String nome) {// Pegandp o Nome Pra salvar no Firestore
        setBancoDeDados(FirebaseFirestore.getInstance());// Set do Banco de Dados
        setUsuarios(new HashMap<>());// Set do HashMap
        getUsuarios().put("Nome", nome);// Set da coleção de nomes que serão capturados
        setUsuarioID(FirebaseAuth.getInstance()
                .getCurrentUser()
                .getUid());// Recuerando o Id do Usuario no Banco de dados
        setDocumentReference(getBancoDeDados()
                .collection("Usuarios")
                .document(getUsuarioID())); // Criando a coleção de Usuarios e dentro dele terá documetos no modelo ID
        getDocumentReference().set(getUsuarios()).addOnSuccessListener( sucess -> { // se for sucesso ao salvar o nome
            Exibir.exibirMensagem(context, "Usuario Salvo Com Sucesso");
        }).addOnFailureListener( faliure -> {// se houver falha ao salvar o nome
            Exibir.exibirMensagem(context, "Erro ao cadastrar o Usuario" + faliure);
        });
    }

    public static Task<AuthResult> autenticarUsuario(Usuario user) {
        setBancoDeDadosAtenticacoa(FirebaseAuth.getInstance());
        return getBancoDeDadosAtenticacoa()
                .signInWithEmailAndPassword(user.getEmail(), user.getSenha());
    }
    public static FirebaseUser pegarUsuarioAtual (){
        setUsuarioAtual(FirebaseAuth.getInstance().getCurrentUser());
        return getUsuarioAtual();
    }
    public static void delogarUsuario() {
        setBancoDeDadosAtenticacoa(FirebaseAuth.getInstance());
        getBancoDeDadosAtenticacoa().signOut();
    }

    public static void pegarDados(String id, TelaPrincipalImplementacao.Callback<String> callback) {// pega os dados
        setBancoDeDados(FirebaseFirestore.getInstance());// setando a instancia do banco
        setDocumentReference(getBancoDeDados().collection("Usuarios").document(id));// pega o documento referente ao id informado dentro do banco
        getDocumentReference().get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null && task.getResult().exists()) {
                String dados = task.getResult().getString("Nome");
                callback.onSuccess(dados);// retorna pro ciclo de vida o dado
            } else { // em caso de falha
                callback.onError("Usuário não encontrado no Firestore"); /// ou error
            }
        });
    }

    private static FirebaseAuth getBancoDeDadosAtenticacoa() {
        return bancoDeDadosAtenticacoa;
    }

    private static void setBancoDeDadosAtenticacoa(FirebaseAuth bancoDeDadosAtenticacoa) {
        FirebaseFirestoreRepository.bancoDeDadosAtenticacoa = bancoDeDadosAtenticacoa;
    }

    private static FirebaseFirestore getBancoDeDados() {
        return bancoDeDados;
    }

    private static void setBancoDeDados(FirebaseFirestore bancoDeDados) {
        FirebaseFirestoreRepository.bancoDeDados = bancoDeDados;
    }
    private static Map<String, Object> getUsuarios() {
        return usuarios;
    }

    private static void setUsuarios(Map<String, Object> usuarios) {
        FirebaseFirestoreRepository.usuarios = usuarios;
    }

    private static String getUsuarioID() {
        return usuarioID;
    }

    private static void setUsuarioID(String usuarioID) {
        FirebaseFirestoreRepository.usuarioID = usuarioID;
    }

    private static DocumentReference getDocumentReference() {
        return documentReference;
    }

    private static void setDocumentReference(DocumentReference documentReference) {
        FirebaseFirestoreRepository.documentReference = documentReference;
    }

    private static FirebaseUser getUsuarioAtual() {
        return usuarioAtual;
    }

    private static void setUsuarioAtual(FirebaseUser usuarioAtual) {
        FirebaseFirestoreRepository.usuarioAtual = usuarioAtual;
    }
}
