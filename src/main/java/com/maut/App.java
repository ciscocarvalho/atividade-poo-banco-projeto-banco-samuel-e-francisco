package com.maut;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import com.maut.Entidades.Cliente;
import com.maut.Entidades.Conta;
import com.maut.Entidades.Endereco;

public class App {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Conta> contas = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Cliente cliente = menuCliente();

        if (cliente == null) {
            return;
        }

        Conta conta = menuConta(cliente);

        if (conta == null) {
            return;
        }

        menuOpcoesConta(conta);
    }

    private static Cliente menuCliente() {
        String mensagem = "O que deseja fazer?";
        List<String> opcoes = new ArrayList<>(
            Arrays.asList(
                "Criar Cadastro",
                "Acessar Cadastro",
                "Encerrar"
            )
        );
        String opcaoSelecionada;
        Cliente cliente = null;
        String nome;
        String cpf;
        LocalDate dataNascimento;
        Endereco endereco;
        String email;

        while (true) {
            opcaoSelecionada = exibeMenu(opcoes, mensagem);

            switch (opcaoSelecionada) {
                case "Criar Cadastro":
                    nome = JOptionPane.showInputDialog("Nome");
                    cpf = JOptionPane.showInputDialog("CPF");
                    dataNascimento = LocalDate.parse(
                        JOptionPane.showInputDialog("Data de nascimento (formato: DD/MM/AAAA)"),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    );
                    String logradouro = JOptionPane.showInputDialog("Endereco (logradouro)");
                    int numero = Integer.parseInt(JOptionPane.showInputDialog("Endereco (Numero)"));
                    String bairro = JOptionPane.showInputDialog("Endereco (Bairro)");
                    String cidade = JOptionPane.showInputDialog("Endereco (Cidade)");
                    String uf = JOptionPane.showInputDialog("Endereco (UF)");
                    endereco = new Endereco(logradouro, numero, bairro, cidade, uf);
                    email = JOptionPane.showInputDialog("Email (deixe em branco se deseja não informar)");

                    if (email.isBlank()) {
                        cliente = new Cliente(nome, cpf, dataNascimento, endereco);
                    } else {
                        cliente = new Cliente(nome, cpf, dataNascimento, endereco, email);
                    }

                    clientes.add(cliente);
                    return cliente;
                case "Acessar Cadastro":
                    nome = JOptionPane.showInputDialog("Nome");
                    cpf = JOptionPane.showInputDialog("CPF");

                    for (Cliente clienteCadastrado : clientes) {
                        if (clienteCadastrado.getCpf().equals(cpf)) {
                            cliente = clienteCadastrado;
                            return cliente;
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Não existe cadastro com os dados informados, tente novamente");

                    break;
                case "Encerrar":
                    return null;
            }
        }
    }

    private static Conta menuConta(Cliente cliente) {
        String mensagem = cliente.getNome() + ", o que deseja fazer?";
        Conta conta = null;
        String opcaoSelecionada;
        List<String> opcoes = new ArrayList<>(
            Arrays.asList(
                "Criar Conta",
                "Acessar conta",
                "Voltar",
                "Encerrar"
            )
        );

        int numeroConta;
        int numeroAgencia;

        while (true) {
            opcaoSelecionada = exibeMenu(opcoes, mensagem);

            switch (opcaoSelecionada) {
                case "Criar Conta":
                    // TODO: Melhorar isto
                    int contasSize = contas.size() + 1;
                    numeroConta = contasSize;
                    numeroAgencia = contasSize * 10;
                    conta = cliente.criaConta(numeroAgencia, numeroConta);
                    contas.add(conta);
                    JOptionPane.showMessageDialog(
                        null, "Número de Conta: " + numeroConta + "\nNúmero de Agência: " + numeroAgencia
                    );
                    return conta;
                case "Acessar conta":
                    numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Numero de Conta"));
                    numeroAgencia = Integer.parseInt(JOptionPane.showInputDialog("Numero de Agência"));
                    conta = cliente.getConta(numeroConta, numeroAgencia);

                    if (conta == null) {
                        JOptionPane.showMessageDialog(null, "Você não possui conta com os dados informados, tente novamente");
                    } else {
                        return conta;
                    }

                    break;
                case "Voltar":
                    cliente = menuCliente();

                    if (cliente == null) {
                        return null;
                    }

                    break;
                case "Encerrar":
                    return null;
            }
        }
    }

    private static void menuOpcoesConta(Conta conta) {
        String mensagem = conta.getCliente().getNome() + ", o que deseja fazer?";
        List<String> opcoes = new ArrayList<String>(
            Arrays.asList("Ver saldo", "Depositar", "Transferir", "Sacar", "Voltar", "Encerrar")
        );

        String opcaoSelecionada;
        double valor;
        int numeroConta;
        int numeroAgencia;

        while (true) {
            opcaoSelecionada = exibeMenu(opcoes, mensagem);

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
                    numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Numero de Conta"));
                    numeroAgencia = Integer.parseInt(JOptionPane.showInputDialog("Numero de Agência"));

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
                        while (true) {
                            try {
                                valor = Double.parseDouble(JOptionPane.showInputDialog("Valor:"));
                                conta.transfere(conta, valor);
                                break;
                            } catch (IllegalArgumentException e) {
                                JOptionPane.showMessageDialog(null, "Valor invalido para transferencia, voce possui saldo suficiente?");
                            }
                        }
                    }

                    break;
                case "Sacar":
                    while (true) {
                        valor = Double.parseDouble(JOptionPane.showInputDialog("Valor:"));

                        try {
                            conta.saca(valor);
                            break;
                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(null, "Valor invalido para saque, voce possui saldo suficiente?");
                        }
                    }
                    break;
                case "Voltar":
                    conta = menuConta(conta.getCliente());

                    if (conta == null) {
                        return;
                    }

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
