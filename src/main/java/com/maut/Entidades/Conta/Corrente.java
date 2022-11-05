package com.maut.Entidades.Conta;

import com.maut.Entidades.Cliente;

public class Corrente extends Conta {
    private static double taxaTransferencia = (double) 0.05;
    private static int chequeEspecial = 50;
    private static int quantidadeTransferencias = 0;

    public Corrente(int agencia, int conta, Cliente cliente) {
        super(agencia, conta, cliente);
    }

    @Override
    protected boolean podeRetirar(double valor) {
        return super.getSaldo() - valor > -chequeEspecial;
    }

    @Override
    public void transfere(Conta contaDestino, double valor) throws IllegalArgumentException {
        if (quantidadeTransferencias <= 2) {
            super.transfere(contaDestino, valor);
        } else {
            if (!this.podeRetirar(valor)) {
                throw new IllegalArgumentException("Valor invalido para transferencia");
            }
            this.saldo -= valor;
            valor -= taxaTransferencia * valor;
            contaDestino.deposita(valor);
            this.enviaNotificacao("Transferência", valor);
        }
    }
}
