package com.maut;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;
    private Object endereco; // Declaramos um variavel do tipo object pelo fato dela receber varios valores.
    private List<Conta> contas = new ArrayList<>();
    private String email;

    public Cliente(String nome, String cpf, LocalDate dataDeNascimento, Object endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
    }

    public Cliente(String nome, String cpf, LocalDate dataDeNascimento, Object endereco, String email) {
        this(nome, cpf, dataDeNascimento, endereco);
        this.email = email;
    }

    public Conta criarConta(String numeroDeAgencia, String numeroDeConta) {
        Conta conta = new Conta(numeroDeAgencia, numeroDeConta, this);
        contas.add(conta);
        return conta;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public String getCpf(){
        return this.cpf;
    } 

    public LocalDate getDataDeNacimento() {
        return this.dataDeNascimento;
    }

    public void setEndereco(Object endereco){
        this.endereco = endereco;
    }

    public Object getEndereco(){
        return this.endereco;
    }

    public List<Conta> getContas(){
        return this.contas;
    }

    public Conta getConta(String numeroDeConta, String numeroDeAgencia){
        Conta conta = null;

        for (Conta c : this.contas) {
            if (c.getNumeroDeConta() == numeroDeConta && c.getNumeroDeAgencia() == numeroDeAgencia) {
                conta = c;
                break;
            }
        }

        return conta;
    }

    public String getEmail() {
        return this.email;
    }
}
