import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        String nome = JOptionPane.showInputDialog("Nome: ");
        String cpf = JOptionPane.showInputDialog("CPF: ");

        String dia = JOptionPane.showInputDialog("dia: ");
        String mes = JOptionPane.showInputDialog("mes: ");
        String ano = JOptionPane.showInputDialog("ano: ");

        List<Integer> opcoes = new ArrayList<>();
        opcoes.add(6);
        opcoes.add(5);
        opcoes.add(4);
        opcoes.add(3);
        opcoes.add(2);
        opcoes.add(1);
        Cliente cliente;
        List<Conta> contas = new ArrayList<>();
        String numeroDeAgencia = JOptionPane.showInputDialog("numeroDeAgencia");

        int opcaoSelecionada = 1;
        while (opcoes.get(opcaoSelecionada) != 6) {
            opcaoSelecionada = exibeMenu(opcoes);
            if (opcoes.get(opcaoSelecionada) == 1) {
                // if (conta == null) {
                //     // proibir o usuario de ver o saldo
                // }float else {
                    verSaldo();
                // }
            } else if (opcoes.get(opcaoSelecionada) == 2) {
                 //String numeroDeAgencia;
                 //String numeroDeConta;
                 //float saldo;
                 Cliente cliente;
                String numeroDeAgencia = JOptionPane.showInputDialog("numeroDeAgencia");
                String numeroDeConta = JOptionPane.showInputDialog("numeroDeConta");
                double saldo = Double.parseDouble(JOptionPane.showInputDialog("saldo"));
                
                contas.add(abrirConta(numeroDeAgencia, numeroDeConta, saldo, cliente));
            
            
            
            } else if (opcoes.get(opcaoSelecionada) == 3) {
                depositar();
            } else if (opcoes.get(opcaoSelecionada) == 4) {
                transferir();
            } else if (opcoes.get(opcaoSelecionada) == 5) {
                sacar();
            }
        }
    }

    private static Cliente cadastrarCliente(String nome, String cpf, LocalDate dataDeNascimento, Object endereco) {
        return new Cliente(nome, cpf, dataDeNascimento, endereco);
    }

    private static void sacar() {
    }

    private static void transferir() {
    }

    private static void depositar() {
    }

    private static Conta abrirConta(String numeroDeAgencia, String numeroDeConta, double saldo, Cliente cliente) {
        return new Conta(numeroDeAgencia, numeroDeConta, saldo, cliente);
    }

    private static void verSaldo() {
    }

    private static int exibeMenu(List<Integer> opcoes) {
        Object[] optionsArray = opcoes.toArray();
        int opcaoSelecionada = JOptionPane.showOptionDialog(null,
                "1. Ver saldo\n2. Abrir uma conta\n3. depositar\n4. Transferir\n5. Sacar\n6. Encerrar",
                "Selecione",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                optionsArray, null);
        return opcaoSelecionada;
    }
}
