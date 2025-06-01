package com.example.controle_rural_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuInicial_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        Button MenuInicial = findViewById(R.id.MenuInicial);
        Button btn_frotas = findViewById(R.id.btn_frotas);
        Button btn_funcionarios = findViewById(R.id.btn_funcionarios);



        Intent intentFrotas = new Intent(this, ClickFrotas.class);
        Intent intentFuncionarios = new Intent(this, ClickFuncionarios.class);
        Intent intentSairSistema = new Intent(this, MainActivity.class);
        btn_frotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intentFrotas);
            }


        });


        btn_funcionarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentFuncionarios); // Inicia a atividade de funcionários quando o botão é clicado
            }
        });

        MenuInicial.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(intentSairSistema);
            }
        });
    }
}