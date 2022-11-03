package com.maut;
import com.maut.Notificacao.Email;
import io.github.cdimascio.dotenv.Dotenv;

public class Conta {
    private String numeroDeAgencia;
    private String numeroDeConta;
    private double saldo = 0;
    private Email notificacaoEmail = null;
    private Cliente cliente;

    public Conta(String numeroDeAgencia, String numeroDeConta, Cliente cliente) {
        this.numeroDeAgencia = numeroDeAgencia;
        this.numeroDeConta = numeroDeConta;
        this.cliente = cliente;

        if (this.cliente.getEmail() != null) {
            Dotenv dotenv = Dotenv.load();
            String mautEmail = dotenv.get("MAUT_EMAIL");
            String mautSenha = dotenv.get("MAUT_SENHA");
            this.notificacaoEmail = new Email(cliente, mautEmail, mautSenha, "Banco Maut");
        }
    }

    public String getNumeroDeAgencia() {
        return numeroDeAgencia;
    }

    public String getNumeroDeConta() {
        return numeroDeConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void sacar(double valor) {
        this.saldo -= valor;
        this.enviarNotificacao("Saque", valor);
    }

    public void depositar(double valor) {
        this.saldo += valor;
        this.enviarNotificacao("Depósito", valor);
    }

    public void transferir(Conta conta, double valor) {
        this.saldo -= valor;
        conta.saldo += valor;
        this.enviarNotificacao("Transferência", valor);
    }

    private void enviarNotificacao(String operacao, double valor) {
        if (notificacaoEmail != null) {
            notificacaoEmail.enviar(operacao, valor);
        }
    }
}
