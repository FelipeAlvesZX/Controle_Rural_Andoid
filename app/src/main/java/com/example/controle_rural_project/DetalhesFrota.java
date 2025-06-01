package com.example.controle_rural_project;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ClassModels.Frotas;

public class DetalhesFrota extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhes_frota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Recupera os detalhes da frota do Intent
        Frotas frota = (Frotas) getIntent().getSerializableExtra("frota");

        // Atualiza a interface do usuário com os detalhes da frota
        if (frota != null) {
            TextView textViewNomeFrota = findViewById(R.id.txtNomeFrota);
            textViewNomeFrota.setText(frota.getNome());

            TextView textViewModeloFrota = findViewById(R.id.txtModeloFrota);
            textViewModeloFrota.setText(frota.getModelo());

            TextView textViewMarcaFrota = findViewById(R.id.txtMarcaFrota);
            textViewMarcaFrota.setText(frota.getMarca());

            TextView textViewAnoFrota = findViewById(R.id.txtAnoFrota);
            textViewAnoFrota.setText(frota.getAno());

            // Adicione outras TextViews e atualize-as com outros detalhes da frota, se necessário
        }
    }
}
