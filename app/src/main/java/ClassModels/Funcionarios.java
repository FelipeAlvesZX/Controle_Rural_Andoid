package ClassModels;

public class Funcionarios {
    private int idFuncionario;
    private String nome;  // Adicionar atributo nome
    private String cargo;
    private String status;

    public Funcionarios(String nome, String cargo, String status) {
        this.nome = nome;
        this.cargo = cargo;
        this.status = status;
    }

    public Funcionarios() {
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Funcionarios{" +
                "idFuncionario=" + idFuncionario +
                ", nome='" + nome + '\'' +
                ", cargo='" + cargo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
