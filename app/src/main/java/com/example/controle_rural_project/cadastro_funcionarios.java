package com.example.controle_rural_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ClassModels.Funcionarios;
import ClassModels.dao.FuncionariosDAO;

public class cadastro_funcionarios extends AppCompatActivity {

    private static final String TAG = "CadastroFuncionarios";
    private FuncionariosDAO funcionariosDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_funcionarios);

        funcionariosDAO = new FuncionariosDAO(this);

        EditText inputNome = findViewById(R.id.inputNome);
        EditText inputCargo = findViewById(R.id.inputCargo);
        EditText inputStatus = findViewById(R.id.inputStatus);

        Button cadastrarBtn = findViewById(R.id.btn_cadastrar);
        Button voltarBtn = findViewById(R.id.btn_voltar);

        cadastrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = inputNome.getText().toString().trim();
                String cargo = inputCargo.getText().toString().trim();
                String status = inputStatus.getText().toString().trim();

                if (nome.isEmpty() || cargo.isEmpty() || status.isEmpty()) {
                    Toast.makeText(cadastro_funcionarios.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                Funcionarios funcionario = new Funcionarios();
                funcionario.setNome(nome);
                funcionario.setCargo(cargo);
                funcionario.setStatus(status);

                Log.d(TAG, "Tentando inserir funcionário: " + funcionario.toString());

                boolean isInserted = funcionariosDAO.inserir(funcionario);

                if (isInserted) {
                    Toast.makeText(cadastro_funcionarios.this, "Funcionário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    // Atualizar a lista de funcionários na atividade ClickFuncionarios
                    Intent intent = new Intent(cadastro_funcionarios.this, ClickFuncionarios.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish(); // Finalizar a atividade atual para evitar empilhamento
                } else {
                    Toast.makeText(cadastro_funcionarios.this, "Falha ao cadastrar funcionário", Toast.LENGTH_SHORT).show();
                }

                // Limpar os campos após a inserção
                inputNome.setText("");
                inputCargo.setText("");
                inputStatus.setText("");
            }
        });

        voltarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
