package com.example.controle_rural_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;
import AdapterArrays.FrotasArrayAdapter;
import ClassModels.Frotas;
import ClassModels.dao.FrotasDAO;

public class ClickFrotas extends AppCompatActivity {

    private FrotasDAO frotasDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_frotas);

        Button cadFrotasBtn = findViewById(R.id.cadastrarFrotas);
        Button voltarMenuBtn = findViewById(R.id.btn_voltar);

        frotasDAO = new FrotasDAO(this); // Instanciando o FrotasDAO

        cadFrotasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCadFrotas = new Intent(ClickFrotas.this, cadastro_frotas.class);
                startActivity(intentCadFrotas);
            }
        });

        voltarMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenu = new Intent(ClickFrotas.this, MenuInicial_activity.class);
                startActivity(intentMenu);
            }
        });

        atualizarListaFrotas();

        // Listener de clique nos itens da lista para ver os detalhes da frota
        ListView listFrotas = findViewById(R.id.ltv_frotas);
        listFrotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Recupera a frota selecionada com base na posição clicada
                Frotas frotaSelecionada = (Frotas) parent.getItemAtPosition(position);

                // Cria um Intent para abrir a atividade de detalhes da frota
                Intent intentDetalhesFrota = new Intent(ClickFrotas.this, DetalhesFrota.class);

                // Passa os detalhes da frota para a nova atividade usando o Intent
                intentDetalhesFrota.putExtra("frota", frotaSelecionada);

                // Inicia a nova atividade
                startActivity(intentDetalhesFrota);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarListaFrotas();
    }

    private void atualizarListaFrotas() {
        // Recuperar a lista de frotas do banco de dados
        List<Frotas> frotas = frotasDAO.getAllFrotas();

        // Configurar o ListView
        ListView listFrotas = findViewById(R.id.ltv_frotas);
        View emptyView = findViewById(R.id.frotas_empty);

        if (frotas.isEmpty()) {
            // Se não houver frotas cadastradas, exibir a emptyView e ocultar a listFrotas
            emptyView.setVisibility(View.VISIBLE);
            listFrotas.setVisibility(View.GONE);
        } else {
            // Se houver frotas cadastradas, exibir a listFrotas e ocultar a emptyView
            emptyView.setVisibility(View.GONE);
            listFrotas.setVisibility(View.VISIBLE);

            // Configurar o adaptador com a lista de frotas recuperadas
            FrotasArrayAdapter adapter = new FrotasArrayAdapter(this, frotas);
            listFrotas.setAdapter(adapter);
        }
    }
}
