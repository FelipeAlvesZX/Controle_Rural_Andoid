package AdapterArrays;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controle_rural_project.R;
//import com.example.controle_rural_project.DetalhesFuncionario; // Importe a atividade DetalhesFuncionario

import java.util.List;

import ClassModels.Funcionarios;
import ClassModels.dao.FuncionariosDAO;

public class FuncionariosArrayAdapter extends ArrayAdapter<Funcionarios> {
    public FuncionariosArrayAdapter(Context context, List<Funcionarios> funcionarios) {
        super(context, 0, funcionarios);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Funcionarios funcionario = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_funcionario, parent, false);
        }

        TextView txtNomeFuncionario = convertView.findViewById(R.id.txv_nome);
        TextView txtCargo = convertView.findViewById(R.id.txv_cargo);
        TextView txtStatus = convertView.findViewById(R.id.txv_status);

        // Setando os valores nos TextViews
        txtNomeFuncionario.setText(funcionario.getNome()); // Supondo que getNome retorna o nome do funcionário
        txtCargo.setText(funcionario.getCargo());
        txtStatus.setText(funcionario.getStatus());

        Button btnEditar = convertView.findViewById(R.id.btn_editar);
        Button btnExcluir = convertView.findViewById(R.id.btn_excluir);
        Button btnVerDetalhes = convertView.findViewById(R.id.btn_ver_detalhes); // Adicionando botão para ver detalhes

        // Inicialize o FuncionariosDAO
        FuncionariosDAO funcionariosDAO = new FuncionariosDAO(getContext());

        // Implementar a lógica para os botões editar, excluir e ver detalhes
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para editar o funcionário
                // Aqui você pode abrir uma nova atividade ou um diálogo para editar o funcionário
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chama o método para excluir o funcionário
                if (funcionariosDAO.excluir(funcionario.getIdFuncionario())) {
                    // Remove o funcionário da lista
                    remove(funcionario);
                    notifyDataSetChanged(); // Notifica o adaptador sobre a alteração na lista
                    Toast.makeText(getContext(), "Funcionário excluído com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Falha ao excluir funcionário", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Adicionando a lógica para o botão de ver detalhes
//        btnVerDetalhes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), DetalhesFuncionario.class);
//                intent.putExtra("funcionario", funcionario);
//                getContext().startActivity(intent);
//            }
//        });

        return convertView;
    }
}
