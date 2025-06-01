package ClassModels.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ClassModels.data.ControleRuralContract.UsuarioEntry;
import ClassModels.data.ControleRuralContract.FrotaEntry;
import ClassModels.data.ControleRuralContract.FuncionarioEntry;

public class ControleRuralDBHelper extends SQLiteOpenHelper {
    private static final String BD_NOME = "controle_rural.db";
    private static final int BD_VERSAO = 1; // Incrementar a versão do banco de dados

    public ControleRuralDBHelper(Context context) {
        super(context, BD_NOME, null, BD_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        // SQL de criação da tabela usuarios
        String usuarioSql = "CREATE TABLE " + UsuarioEntry.TABELA_NOME + " ("
                + UsuarioEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UsuarioEntry.COLUNA_NOME + " TEXT, "
                + UsuarioEntry.COLUNA_EMAIL + " TEXT, "
                + UsuarioEntry.COLUNA_SENHA + " TEXT, "
                + UsuarioEntry.COLUNA_CARGO + " TEXT, "
                + UsuarioEntry.COLUNA_LOGIN + " TEXT, "
                + UsuarioEntry.COLUNA_SITUACAO + " TEXT, "
                + UsuarioEntry.COLUNA_IDADE + " INTEGER "
                + ")";
        bd.execSQL(usuarioSql);

        // SQL de criação da tabela frotas
        String frotaSql = "CREATE TABLE " + FrotaEntry.TABELA_NOME + " ("
                + FrotaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FrotaEntry.COLUNA_NUMERO_FROTA + " INTEGER, "
                + FrotaEntry.COLUNA_NOME + " TEXT, "
                + FrotaEntry.COLUNA_MODELO + " TEXT, "
                + FrotaEntry.COLUNA_MARCA + " TEXT, "
                + FrotaEntry.COLUNA_STATUS_VEICULO + " TEXT, "
                + FrotaEntry.COLUNA_ANO + " TEXT "
                + ")";

        bd.execSQL(frotaSql); // Adicione esta linha para criar a tabela de frotas

        // SQL de criação da tabela funcionarios
        String funcionariosSql = "CREATE TABLE " + FuncionarioEntry.TABELA_NOME + " ("
                + FuncionarioEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FuncionarioEntry.COLUNA_NOME_FUNCIONARIO + " TEXT, "
                + FuncionarioEntry.COLUNA_CARGO + " TEXT, "
                + FuncionarioEntry.COLUNA_STATUS + " TEXT "
                + ")";

        bd.execSQL(funcionariosSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        // SQL para deletar a tabela usuarios se existir
        String usuarioSql = "DROP TABLE IF EXISTS " + UsuarioEntry.TABELA_NOME;
        bd.execSQL(usuarioSql);

        // SQL para deletar a tabela frotas se existir
        String frotaSql = "DROP TABLE IF EXISTS " + FrotaEntry.TABELA_NOME;
        bd.execSQL(frotaSql);

        String funcionariosSql = "DROP TABLE IF EXISTS " + FuncionarioEntry.TABELA_NOME;
        bd.execSQL(funcionariosSql);

        // Chama onCreate para recriar as tabelas
        onCreate(bd);
    }
}
