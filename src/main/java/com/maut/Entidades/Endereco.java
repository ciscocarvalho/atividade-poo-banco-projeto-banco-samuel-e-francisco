package com.maut.Entidades;

public class Endereco {
    String logradouro;
    int numero;
    String bairro;
    String cidade;
    String uf;

    public Endereco(String logradouro, int numero, String bairro, String cidade, String uf) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }
}
