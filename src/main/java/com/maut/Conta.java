package com.maut;
import com.maut.Notificacao.Email;
import io.github.cdimascio.dotenv.Dotenv;

public class Conta {
    private int agencia;
    private int conta;
    private double saldo = 0;
    private Email notificacaoEmail = null;
    private Cliente cliente;

    public Conta(int agencia, int conta, Cliente cliente) {
        this.agencia = agencia;
        this.conta = conta;
        this.cliente = cliente;

        if (this.cliente.getEmail() != null) {
            Dotenv dotenv = Dotenv.load();
            String mautEmail = dotenv.get("MAUT_EMAIL");
            String mautSenha = dotenv.get("MAUT_SENHA");
            this.notificacaoEmail = new Email(cliente, mautEmail, mautSenha, "Banco Maut");
        }
    }

    public int getAgencia() {
        return agencia;
    }

    public int getConta() {
        return conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void saca(double valor) {
        this.saldo -= valor;
        this.enviaNotificacao("Saque", valor);
    }

    public void deposita(double valor) {
        this.saldo += valor;
        this.enviaNotificacao("Depósito", valor);
    }

    public void transfere(Conta contaDestino, double valor) {
        this.saldo -= valor;
        contaDestino.saldo += valor;
        this.enviaNotificacao("Transferência", valor);
    }

    private void enviaNotificacao(String operacao, double valor) {
        if (notificacaoEmail != null) {
            notificacaoEmail.enviaNotificacao(operacao, valor);
        }
    }
}
