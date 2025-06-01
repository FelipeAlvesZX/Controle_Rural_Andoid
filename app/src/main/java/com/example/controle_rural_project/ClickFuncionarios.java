package com.example.controle_rural_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;
import AdapterArrays.FuncionariosArrayAdapter;
import ClassModels.Funcionarios;
import ClassModels.dao.FuncionariosDAO;

public class ClickFuncionarios extends AppCompatActivity {

    private FuncionariosDAO funcionariosDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_funcionarios);

        Button cadFuncionariosBtn = findViewById(R.id.cadastrarFuncionarios);
        Button voltarMenuBtn = findViewById(R.id.btn_voltar_menu);

        funcionariosDAO = new FuncionariosDAO(this); // Instanciando o FuncionariosDAO

        cadFuncionariosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCadFuncionarios = new Intent(ClickFuncionarios.this, cadastro_funcionarios.class);
                startActivity(intentCadFuncionarios);
            }
        });

        voltarMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMenu = new Intent(ClickFuncionarios.this, MenuInicial_activity.class);
                startActivity(intentMenu);
            }
        });

        atualizarListaFuncionarios();

        // Listener de clique nos itens da lista para ver os detalhes do funcionário
        ListView listFuncionarios = findViewById(R.id.ltv_funcionarios);
        listFuncionarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Recupera o funcionário selecionado com base na posição clicada
                Funcionarios funcionarioSelecionado = (Funcionarios) parent.getItemAtPosition(position);

                // Cria um Intent para abrir a atividade de detalhes do funcionário
               // Intent intentDetalhesFuncionario = new Intent(ClickFuncionarios.this, DetalhesFuncionario.class);

                // Passa os detalhes do funcionário para a nova atividade usando o Intent
              //  intentDetalhesFuncionario.putExtra("funcionario", funcionarioSelecionado);

                // Inicia a nova atividade
            //    startActivity(intentDetalhesFuncionario);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarListaFuncionarios();
    }

    private void atualizarListaFuncionarios() {
        // Recuperar a lista de funcionários do banco de dados
        List<Funcionarios> funcionarios = funcionariosDAO.getAllFuncionarios();

        // Configurar o ListView
        ListView listFuncionarios = findViewById(R.id.ltv_funcionarios);
        View emptyView = findViewById(R.id.funcionarios_empty);

        if (funcionarios.isEmpty()) {
            // Se não houver funcionários cadastrados, exibir a emptyView e ocultar a listFuncionarios
            emptyView.setVisibility(View.VISIBLE);
            listFuncionarios.setVisibility(View.GONE);
        } else {
            // Se houver funcionários cadastrados, exibir a listFuncionarios e ocultar a emptyView
            emptyView.setVisibility(View.GONE);
            listFuncionarios.setVisibility(View.VISIBLE);

            // Configurar o adaptador com a lista de funcionários recuperados
            FuncionariosArrayAdapter adapter = new FuncionariosArrayAdapter(this, funcionarios);
            listFuncionarios.setAdapter(adapter);
        }
    }
}
