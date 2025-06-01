// cadastro_frotas.java
package com.example.controle_rural_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ClassModels.Frotas;
import ClassModels.dao.FrotasDAO;

public class cadastro_frotas extends AppCompatActivity {

    private static final String TAG = "cadastro_frotas";
    private FrotasDAO frotasDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_frotas);

        frotasDAO = new FrotasDAO(this);

        EditText inputNome = findViewById(R.id.inputNome);
        EditText inputNumeroFrota = findViewById(R.id.inputNumeroFrota);
        EditText inputMarcaFrota = findViewById(R.id.inputMarcaFrota);
        EditText inputModeloFrota = findViewById(R.id.inputModeloFrota);
        EditText inputAnoFrota = findViewById(R.id.inputAnoFrota);

        Button cadastrarBtn = findViewById(R.id.btn_cadastrar);
        Button voltarBtn = findViewById(R.id.btn_voltar);

        cadastrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = inputNome.getText().toString().trim();
                String numeroFrotaStr = inputNumeroFrota.getText().toString().trim();
                String marca = inputMarcaFrota.getText().toString().trim();
                String modelo = inputModeloFrota.getText().toString().trim();
                String ano = inputAnoFrota.getText().toString().trim();

                if (nome.isEmpty() || numeroFrotaStr.isEmpty() || marca.isEmpty() || modelo.isEmpty() || ano.isEmpty()) {
                    Toast.makeText(cadastro_frotas.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                int numeroFrota = Integer.parseInt(numeroFrotaStr);

                Frotas frota = new Frotas();
                frota.setNome(nome);
                frota.setNumeroFrota(numeroFrota);
                frota.setMarca(marca);
                frota.setModelo(modelo);
                frota.setAno(ano);
                frota.setStatusVeiculo("Ativo");

                Log.d(TAG, "Tentando inserir frota: " + frota.toString());

                boolean isInserted = frotasDAO.inserir(frota);

                if (isInserted) {
                    Toast.makeText(cadastro_frotas.this, "Frota cadastrada com sucesso", Toast.LENGTH_SHORT).show();
                    // Atualizar a lista de frotas na atividade ClickFrotas
                    Intent intent = new Intent(cadastro_frotas.this, ClickFrotas.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish(); // Finalizar a atividade atual para evitar empilhamento
                } else {
                    Toast.makeText(cadastro_frotas.this, "Falha ao cadastrar frota", Toast.LENGTH_SHORT).show();
                }

                // Limpar os campos após a inserção
                inputNome.setText("");
                inputNumeroFrota.setText("");
                inputMarcaFrota.setText("");
                inputModeloFrota.setText("");
                inputAnoFrota.setText("");
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
