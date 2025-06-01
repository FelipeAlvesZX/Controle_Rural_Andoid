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
import com.example.controle_rural_project.DetalhesFrota; // Importe a atividade DetalhesFrota

import java.util.List;

import ClassModels.Frotas;
import ClassModels.dao.FrotasDAO;

public class FrotasArrayAdapter extends ArrayAdapter<Frotas> {
    public FrotasArrayAdapter(Context context, List<Frotas> frotas) {
        super(context, 0, frotas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Frotas frota = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_frota, parent, false);
        }

        TextView txtNomeFrota = convertView.findViewById(R.id.txv_nome);
        txtNomeFrota.setText(frota.getNome());

        Button btnEditar = convertView.findViewById(R.id.btn_editar);
        Button btnExcluir = convertView.findViewById(R.id.btn_excluir);
        Button btnVerDetalhes = convertView.findViewById(R.id.btn_ver_detalhes); // Adicionando botão para ver detalhes

        // Inicialize o FrotasDAO
        FrotasDAO frotasDAO = new FrotasDAO(getContext());

        // Implementar a lógica para os botões editar, excluir e ver detalhes
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para editar a frota
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chama o método para excluir a frota
                if (frotasDAO.excluir(frota.getIdFrota())) {
                    // Remove a frota da lista
                    remove(frota);
                    notifyDataSetChanged(); // Notifica o adaptador sobre a alteração na lista
                    Toast.makeText(getContext(), "Frota excluída com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Falha ao excluir frota", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Adicionando a lógica para o botão de ver detalhes
        btnVerDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetalhesFrota.class);
                intent.putExtra("frota", frota);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
