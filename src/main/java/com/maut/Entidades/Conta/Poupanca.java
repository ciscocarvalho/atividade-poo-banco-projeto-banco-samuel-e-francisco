package com.maut.Entidades.Conta;

import com.maut.Entidades.Cliente;

public class Poupanca extends Conta {
    private static double taxaTransferencia = (double) 0.05;
    private double rendimentoMaximo = 0.2;
    private double rendimentoAcrescimo = 0.001;
    private double rendimento = 0.1;

    public Poupanca(int agencia, int conta, Cliente cliente) {
        super(agencia, conta, cliente);
    }

    @Override
    public void deposita(double valor) {
        valor += this.rendimento * valor;
        super.deposita(valor);
        if (this.rendimento + this.rendimentoAcrescimo <= rendimentoMaximo) {
            this.rendimento += this.rendimentoAcrescimo;
        }
    }

    @Override
    public void transfere(Conta contaDestino, double valor) throws IllegalArgumentException {
        double valorComAcrescimo = valor + valor * taxaTransferencia;
        if (!this.podeRetirar(valorComAcrescimo)) {
            throw new IllegalArgumentException("Valor invalido para saque");
        }
        this.saldo -= valorComAcrescimo;
        contaDestino.deposita(valor);
        this.enviaNotificacao("Transferência", valor);
    }
}
