package com.maut.Entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.maut.Entidades.Conta.Conta;
import com.maut.Entidades.Conta.Corrente;
import com.maut.Entidades.Conta.Poupanca;

public class Cliente {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Endereco endereco;
    private List<Conta> contas = new ArrayList<>();
    private String email;

    public Cliente(String nome, String cpf, LocalDate dataNascimento, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public Cliente(String nome, String cpf, LocalDate dataNascimento, Endereco endereco, String email) {
        this(nome, cpf, dataNascimento, endereco);
        this.email = email;
    }

    public Conta criaConta(int agencia, int conta, String tipo) {
        Conta _conta;

        if (tipo.equalsIgnoreCase("poupanca")) {
            _conta = new Poupanca(agencia, conta, this);
        } else {
            _conta = new Corrente(agencia, conta, this);
        }

        contas.add(_conta);
        return _conta;
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

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }

    public Endereco getEndereco(){
        return this.endereco;
    }

    public List<Conta> getContas(){
        return this.contas;
    }

    public Conta getConta(int numeroConta, int numeroAgencia){
        Conta conta = null;

        for (Conta c : this.contas) {
            if (c.getConta() == numeroConta && c.getAgencia() == numeroAgencia) {
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
