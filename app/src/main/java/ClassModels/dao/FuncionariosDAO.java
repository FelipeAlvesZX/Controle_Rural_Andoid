package ClassModels.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ClassModels.Funcionarios;
import ClassModels.data.ControleRuralDBHelper;
import ClassModels.data.ControleRuralContract.FuncionarioEntry;

public class FuncionariosDAO {
    private static final String TAG = "FuncionariosDAO";
    private ControleRuralDBHelper dbHelper;

    public FuncionariosDAO(Context context) {
        dbHelper = new ControleRuralDBHelper(context);
    }

    public boolean inserir(Funcionarios funcionario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FuncionarioEntry.COLUNA_NOME_FUNCIONARIO, funcionario.getNome());
        values.put(FuncionarioEntry.COLUNA_CARGO, funcionario.getCargo());
        values.put(FuncionarioEntry.COLUNA_STATUS, funcionario.getStatus());

        long result = db.insert(FuncionarioEntry.TABELA_NOME, null, values);
        db.close();

        if (result == -1) {
            Log.e(TAG, "Falha ao inserir funcionario no banco de dados");
            return false;
        } else {
            Log.i(TAG, "Funcionario inserido com sucesso: ID=" + result);
            return true;
        }
    }

    public List<Funcionarios> getAllFuncionarios() {
        List<Funcionarios> funcionarios = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                FuncionarioEntry._ID,
                FuncionarioEntry.COLUNA_NOME_FUNCIONARIO,
                FuncionarioEntry.COLUNA_CARGO,
                FuncionarioEntry.COLUNA_STATUS
        };

        Cursor cursor = db.query(
                FuncionarioEntry.TABELA_NOME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Funcionarios funcionario = new Funcionarios();
                funcionario.setIdFuncionario(cursor.getInt(cursor.getColumnIndexOrThrow(FuncionarioEntry._ID)));
                funcionario.setNome(cursor.getString(cursor.getColumnIndexOrThrow(FuncionarioEntry.COLUNA_NOME_FUNCIONARIO)));
                funcionario.setCargo(cursor.getString(cursor.getColumnIndexOrThrow(FuncionarioEntry.COLUNA_CARGO)));
                funcionario.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(FuncionarioEntry.COLUNA_STATUS)));


                funcionarios.add(funcionario);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return funcionarios;
    }

    public boolean excluir(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = db.delete(FuncionarioEntry.TABELA_NOME, FuncionarioEntry._ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return deletedRows > 0;
    }
}
