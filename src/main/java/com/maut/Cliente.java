package com.maut;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Object endereco; // Declaramos um variavel do tipo object pelo fato dela receber varios valores.
    private List<Conta> contas = new ArrayList<>();
    private String email;

    public Cliente(String nome, String cpf, LocalDate dataNascimento, Object endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public Cliente(String nome, String cpf, LocalDate dataNascimento, Object endereco, String email) {
        this(nome, cpf, dataNascimento, endereco);
        this.email = email;
    }

    public Conta criaConta(int agencia, int conta) {
        // nomear essa variavel "conta" aparentemente buga o intellisense
        Conta _conta = new Conta(agencia, conta, this);
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

    public void setEndereco(Object endereco){
        this.endereco = endereco;
    }

    public Object getEndereco(){
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
