package com.example.controle_rural_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ClassModels.Usuarios;
import ClassModels.dao.UsuarioDAO;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText editUser;
    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button MainLogin = findViewById(R.id.MainLogin);
        TextView Cadastrar = findViewById(R.id.Cadastrar);

        editUser = findViewById(R.id.editUser);
        editPassword = findViewById(R.id.editPassword);

        Intent intentLogin = new Intent(this, MenuInicial_activity.class);
        Intent intentCadastrar = new Intent(this, CadastrarActivity.class);

        MainLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "MainLogin button clicked");
                String user = editUser.getText().toString();
                String password = editPassword.getText().toString();

                if (verifyLogin(user, password)) {
                    startActivity(intentLogin);
                } else {
                    Toast.makeText(MainActivity.this, "Email ou Usuário inválidos!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Cadastrar text clicked");
                startActivity(intentCadastrar);
            }
        });
    }

    private boolean verifyLogin(String email, String password) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        Usuarios user = usuarioDAO.getUserByEmail(email);

        return user != null && user.getSenha().equals(password);
    }
}
