package ClassModels.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ClassModels.Usuarios;
import ClassModels.data.ControleRuralDBHelper;
import ClassModels.data.ControleRuralContract.UsuarioEntry;

public class UsuarioDAO {
    private ControleRuralDBHelper dbHelper;

    public UsuarioDAO(Context context) {
        dbHelper = new ControleRuralDBHelper(context);
    }

    public void inserir(Usuarios usuario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsuarioEntry.COLUNA_NOME, usuario.getNome());
        values.put(UsuarioEntry.COLUNA_EMAIL, usuario.getEmail());
        values.put(UsuarioEntry.COLUNA_SENHA, usuario.getSenha());

        db.insert(UsuarioEntry.TABELA_NOME, null, values);
    }

    public Usuarios getUserByEmail(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                UsuarioEntry._ID,
                UsuarioEntry.COLUNA_NOME,
                UsuarioEntry.COLUNA_EMAIL,
                UsuarioEntry.COLUNA_SENHA
        };

        String selection = UsuarioEntry.COLUNA_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(
                UsuarioEntry.TABELA_NOME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Usuarios user = null;
        if (cursor != null && cursor.moveToFirst()) {
            user = new Usuarios();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioEntry._ID)));
            user.setNome(cursor.getString(cursor.getColumnIndexOrThrow(UsuarioEntry.COLUNA_NOME)));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(UsuarioEntry.COLUNA_EMAIL)));
            user.setSenha(cursor.getString(cursor.getColumnIndexOrThrow(UsuarioEntry.COLUNA_SENHA)));
            cursor.close();
        }

        return user;
    }
}
