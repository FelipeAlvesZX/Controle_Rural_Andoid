package ClassModels;

import java.io.Serializable;

public class Frotas implements Serializable {
    private int idFrota;
    private int numeroFrota;  // Número da frota
    private String nome;  // Nome da frota
    private String modelo;
    private String marca;
    private String statusVeiculo;
    private String ano;  // Ano do veículo

    public Frotas(int numeroFrota, String nome, String modelo, String marca, String statusVeiculo, String ano) {
        this.numeroFrota = numeroFrota;
        this.nome = nome;
        this.modelo = modelo;
        this.marca = marca;
        this.statusVeiculo = statusVeiculo;
        this.ano = ano;
    }

    public Frotas() {

    }

    public int getIdFrota() {
        return idFrota;
    }

    public void setIdFrota(int idFrota) {
        this.idFrota = idFrota;
    }

    public int getNumeroFrota() {
        return numeroFrota;
    }

    public void setNumeroFrota(int numeroFrota) {
        this.numeroFrota = numeroFrota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getStatusVeiculo() {
        return statusVeiculo;
    }

    public void setStatusVeiculo(String statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
