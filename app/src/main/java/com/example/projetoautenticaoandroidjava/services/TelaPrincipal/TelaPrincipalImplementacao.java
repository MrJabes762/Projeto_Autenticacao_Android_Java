package com.example.projetoautenticaoandroidjava.services.TelaPrincipal;

import android.widget.Toast;

import com.example.projetoautenticaoandroidjava.controllers.TelaPrincipal;
import com.example.projetoautenticaoandroidjava.data.FirebaseFirestoreRepository;
import com.example.projetoautenticaoandroidjava.R;
import com.example.projetoautenticaoandroidjava.services.Direcionador.DirecionadorDeLayout;
import com.example.projetoautenticaoandroidjava.services.TelaLogin.TelaLoginImplementacao;
import com.example.projetoautenticaoandroidjava.model.Usuario;

public class TelaPrincipalImplementacao extends TelaPrincipal {
    private String email;
    @Override
    public void inicializarComponentes() {
        setEditTexs();
        setBtnDeslogar(findViewById(R.id.btnDeslogar));
    }

    private void setEditTexs() {
        setTextExibirEmail(findViewById(R.id.textExibirEmail));
        setTextExibirUsuairo(findViewById(R.id.textexibirUsuario));
    }

    @Override
    public void Observadores() {
        getBtnDeslogar().setOnClickListener(v -> {
            deslogarUsuario();
            voltarParaLogin();
        });
    }

    private void deslogarUsuario() {
        FirebaseFirestoreRepository.delogarUsuario();
    }

    protected void onStart() {
        super.onStart();
        String usuarioId = FirebaseFirestoreRepository.pegarUsuarioAtual().getUid();
            Usuario.setId(usuarioId);
            setEmail(FirebaseFirestoreRepository.pegarUsuarioAtual().getEmail());
            FirebaseFirestoreRepository.pegarDados(Usuario.getId(), new Callback<String>() {
                @Override
                public void onSuccess(String nome) {// Implementação dos metodos
                    if (nome != null && !nome.isEmpty()) {
                        getTextExibirUsuairo().setText(nome);
                    } else {
                        Toast.makeText(TelaPrincipalImplementacao.this, "Usuario Não Encontrado", Toast.LENGTH_SHORT).show();
                    }
                    getTextExibirEmail().setText(getEmail());
                }

                @Override
                public void onError(String error) {// em caso de erro
                    Toast.makeText(getApplicationContext(), "Erro ao buscar dados: " + error, Toast.LENGTH_LONG).show();
                }
            });
    }


    private void voltarParaLogin() {
        DirecionadorDeLayout.irParaLayout(this, TelaLoginImplementacao.class);
        finish();
    }
    public interface Callback<T> {// Interface para pegar o nme ou os dados
        void onSuccess(T nome);
        void onError(String error);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
