package com.maut;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class App {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Conta> contas = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        List<String> opcoes = new ArrayList<>(
            Arrays.asList(
                "Criar Cadastro",
                "Acessar Cadastro",
                "Encerrar"
            )
        );

        String mensagemPadrao = "O que deseja fazer?";
        String opcaoSelecionada;
        Cliente cliente = null;
        boolean continuar = true;

        while (continuar) {
            opcaoSelecionada = exibeMenu(opcoes, mensagemPadrao);

            String nome;
            String cpf;
            LocalDate dataNascimento;
            Object endereco;
            String email;

            switch (opcaoSelecionada) {
                case "Criar Cadastro":
                    nome = JOptionPane.showInputDialog("Nome");
                    cpf = JOptionPane.showInputDialog("CPF");
                    dataNascimento = LocalDate.parse(JOptionPane.showInputDialog("Data de nascimento"));
                    endereco = JOptionPane.showInputDialog("Endereço");
                    email = JOptionPane.showInputDialog("Email (deixe em branco se deseja não informar)");

                    if (email.isBlank()) {
                        cliente = new Cliente(nome, cpf, dataNascimento, endereco);
                    } else {
                        cliente = new Cliente(nome, cpf, dataNascimento, endereco, email);
                    }

                    clientes.add(cliente);
                    continuar = false;

                    break;
                case "Acessar Cadastro":
                    nome = JOptionPane.showInputDialog("Nome");
                    cpf = JOptionPane.showInputDialog("CPF");

                    for (Cliente clienteCadastrado : clientes) {
                        if (clienteCadastrado.getCpf() == cpf) {
                            cliente = clienteCadastrado;
                            continuar = false;
                            break;
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Não existe cadastro com os dados informados, tente novamente");

                    break;
                case "Encerrar":
                    return;
            }
        }

        mensagemPadrao = cliente.getNome() + ", o que deseja fazer?";

        opcoes = new ArrayList<>(
            Arrays.asList(
                "Criar Conta",
                "Acessar conta",
                "Encerrar"
            )
        );

        Conta conta = null;
        int numeroConta;
        int numeroAgencia;

        while (conta == null) {
            opcaoSelecionada = exibeMenu(opcoes, mensagemPadrao);

            switch (opcaoSelecionada) {
                case "Criar Conta":
                    // TODO: Melhorar isto
                    int contasSize = contas.size() + 1;
                    numeroConta = contasSize;
                    numeroAgencia = contasSize * 10;
                    conta = cliente.criaConta(numeroAgencia, numeroConta);
                    String mensagem = "Número de Conta: " + numeroConta +
                                        "\nNúmero de Agência: " + numeroAgencia;
                    JOptionPane.showMessageDialog(null, mensagem);

                    break;
                case "Acessar conta":
                    numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Numero de Conta"));
                    numeroAgencia = Integer.parseInt(JOptionPane.showInputDialog("Numero de Agência"));
                    conta = cliente.getConta(numeroConta, numeroAgencia);

                    if (conta == null) {
                        JOptionPane.showMessageDialog(null, "Você não possui conta com os dados informados, tente novamente");
                    }

                    break;
                case "Encerrar":
                    return;
            }
        }

        opcoes = new ArrayList<>(
            Arrays.asList(
                "Ver saldo",
                "Depositar",
                "Transferir",
                "Sacar",
                "Encerrar"
            )
        );

        double valor;

        while (true) {
            opcaoSelecionada = exibeMenu(opcoes, mensagemPadrao);

            switch (opcaoSelecionada) {
                case "Ver saldo":
                    double saldo = conta.getSaldo();
                    JOptionPane.showMessageDialog(null, saldo);
                    break;
                case "Depositar":
                    valor = Double.parseDouble(JOptionPane.showInputDialog("Valor:"));
                    conta.deposita(valor);
                    break;
                case "Transferir":
                    numeroAgencia = Integer.parseInt(JOptionPane.showInputDialog("Numero de Agência"));
                    numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Numero de Conta"));

                    Conta contaDestino = null;

                    for (Conta c : contas) {
                        if (c.getAgencia() == numeroAgencia && c.getConta() == numeroConta) {
                            contaDestino = c;
                            break;
                        }
                    }

                    if (contaDestino == null) {
                        JOptionPane.showMessageDialog(null, "Não existe conta com os dados informados, tente novamente");
                    } else {
                        valor = Double.parseDouble(JOptionPane.showInputDialog("Valor:"));
                        conta.transfere(conta, valor);
                    }

                    break;
                case "Sacar":
                    valor = Double.parseDouble(JOptionPane.showInputDialog("Valor:"));
                    conta.saca(valor);
                    break;
                case "Encerrar":
                    return;
            }
        }
    }

    private static String exibeMenu(List<String> opcoes, String mensagem) {
        Object[] optionsArray = opcoes.toArray();
        int opcaoSelecionada = JOptionPane.showOptionDialog(null,
                mensagem,
                "Selecione",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                optionsArray, null);
        return opcoes.get(opcaoSelecionada);
    }

    private static String exibeMenu(List<String> opcoes) {
        return exibeMenu(opcoes, "");
    }
}
