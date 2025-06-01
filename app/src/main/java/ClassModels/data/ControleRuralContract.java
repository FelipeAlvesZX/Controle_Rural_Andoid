package ClassModels.data;

import android.provider.BaseColumns;

public final class ControleRuralContract {
        private ControleRuralContract() {}

        public static class UsuarioEntry implements BaseColumns {
                public static final String TABELA_NOME = "usuarios";
                public static final String COLUNA_NOME = "nome";
                public static final String COLUNA_EMAIL = "email";
                public static final String COLUNA_SENHA = "senha";
                // Definindo outras colunas
                public static final String COLUNA_CARGO = "cargo";
                public static final String COLUNA_LOGIN = "login";
                public static final String COLUNA_SITUACAO = "situacao";
                public static final String COLUNA_IDADE = "idade";
        }

        public static final class FrotaEntry implements BaseColumns {
                public static final String TABELA_NOME = "frotas";
                public static final String COLUNA_NUMERO_FROTA = "numeroFrota";
                public static final String COLUNA_NOME = "nome";  // Adicionada a coluna nome
                public static final String COLUNA_MODELO = "modelo";
                public static final String COLUNA_MARCA = "marca";
                public static final String COLUNA_STATUS_VEICULO = "statusVeiculo";
                public static final String COLUNA_ANO = "ano";  // Adicionada a coluna ano
        }

        public static final class FuncionarioEntry implements BaseColumns {
                public static final String TABELA_NOME = "funcionarios";
                public static final String COLUNA_NOME_FUNCIONARIO = "nomeFuncionario";
                public static final String COLUNA_CARGO = "cargoFuncionario";  // Adicionada a coluna nome
                public static final String COLUNA_STATUS = "statusFuncionario";

        }
}