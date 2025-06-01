package ClassModels.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ClassModels.Frotas;
import ClassModels.data.ControleRuralDBHelper;
import ClassModels.data.ControleRuralContract.FrotaEntry;

public class FrotasDAO {
    private static final String TAG = "FrotasDAO";
    private ControleRuralDBHelper dbHelper;

    public FrotasDAO(Context context) {
        dbHelper = new ControleRuralDBHelper(context);
    }

    public boolean inserir(Frotas frota) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FrotaEntry.COLUNA_NUMERO_FROTA, frota.getNumeroFrota());
        values.put(FrotaEntry.COLUNA_NOME, frota.getNome());
        values.put(FrotaEntry.COLUNA_MODELO, frota.getModelo());
        values.put(FrotaEntry.COLUNA_MARCA, frota.getMarca());
        values.put(FrotaEntry.COLUNA_STATUS_VEICULO, frota.getStatusVeiculo());
        values.put(FrotaEntry.COLUNA_ANO, frota.getAno());

        long result = db.insert(FrotaEntry.TABELA_NOME, null, values);
        db.close();

        if (result == -1) {
            Log.e(TAG, "Falha ao inserir frota no banco de dados");
            return false;
        } else {
            Log.i(TAG, "Frota inserida com sucesso: ID=" + result);
            return true;
        }
    }

    public List<Frotas> getAllFrotas() {
        List<Frotas> frotas = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                FrotaEntry._ID,
                FrotaEntry.COLUNA_NUMERO_FROTA,
                FrotaEntry.COLUNA_NOME,
                FrotaEntry.COLUNA_MODELO,
                FrotaEntry.COLUNA_MARCA,
                FrotaEntry.COLUNA_STATUS_VEICULO,
                FrotaEntry.COLUNA_ANO
        };

        Cursor cursor = db.query(
                FrotaEntry.TABELA_NOME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Frotas frota = new Frotas();
                frota.setIdFrota(cursor.getInt(cursor.getColumnIndexOrThrow(FrotaEntry._ID)));
                frota.setNumeroFrota(cursor.getInt(cursor.getColumnIndexOrThrow(FrotaEntry.COLUNA_NUMERO_FROTA)));
                frota.setNome(cursor.getString(cursor.getColumnIndexOrThrow(FrotaEntry.COLUNA_NOME)));
                frota.setModelo(cursor.getString(cursor.getColumnIndexOrThrow(FrotaEntry.COLUNA_MODELO)));
                frota.setMarca(cursor.getString(cursor.getColumnIndexOrThrow(FrotaEntry.COLUNA_MARCA)));
                frota.setStatusVeiculo(cursor.getString(cursor.getColumnIndexOrThrow(FrotaEntry.COLUNA_STATUS_VEICULO)));
                frota.setAno(cursor.getString(cursor.getColumnIndexOrThrow(FrotaEntry.COLUNA_ANO)));

                frotas.add(frota);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return frotas;
    }

    public boolean excluir(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = db.delete(FrotaEntry.TABELA_NOME, FrotaEntry._ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return deletedRows > 0;
    }
}
