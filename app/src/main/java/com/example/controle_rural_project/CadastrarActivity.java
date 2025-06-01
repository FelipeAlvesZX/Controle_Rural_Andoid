package com.example.controle_rural_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ClassModels.Usuarios;
import ClassModels.dao.UsuarioDAO;

public class CadastrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        TextView jaPossuiCadastro = findViewById(R.id.JaPossuiCadastro);
        Button btnCadastrar = findViewById(R.id.btn_cadastrar);

        EditText inputNome = findViewById(R.id.inputNome);
        EditText inputEmail = findViewById(R.id.inputEmail);
        EditText inputSenha = findViewById(R.id.inputSenha);

        Intent intentJaPossuiCad = new Intent(this, MainActivity.class);
        Intent intentCadastrar = new Intent(this, MenuInicial_activity.class);

        jaPossuiCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentJaPossuiCad);
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = inputNome.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String senha = inputSenha.getText().toString().trim();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(CadastrarActivity.this, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show();
                } else {
                    cadastrar(nome, email, senha);
                    startActivity(intentCadastrar);
                }
            }
        });
    }

    public void cadastrar(String nome, String email, String senha) {
        try {
            Usuarios usuario = new Usuarios();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);

            // Inicialize os campos adicionais com valores padrão ou nulos
            usuario.setLogin(null);
            usuario.setCargo(null);
            usuario.setSituacao(null);
            usuario.setIdade(0); // ou qualquer valor padrão que você queira usar

            UsuarioDAO usuarioDAO = new UsuarioDAO(this);
            usuarioDAO.inserir(usuario);

            Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao cadastrar usuário: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
