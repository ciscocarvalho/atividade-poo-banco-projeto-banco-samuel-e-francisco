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
            LocalDate dataDeNascimento;
            Object endereco;

            switch (opcaoSelecionada) {
                case "Criar Cadastro":
                    nome = JOptionPane.showInputDialog("Nome");
                    cpf = JOptionPane.showInputDialog("CPF");
                    dataDeNascimento = LocalDate.parse(JOptionPane.showInputDialog("Data de nascimento"));
                    endereco = JOptionPane.showInputDialog("Endereço");

                    cliente = new Cliente(nome, cpf, dataDeNascimento, endereco);
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
        String numeroDeConta;
        String numeroDeAgencia;

        while (conta == null) {
            opcaoSelecionada = exibeMenu(opcoes, mensagemPadrao);

            switch (opcaoSelecionada) {
                case "Criar Conta":
                    // TODO: Melhorar isto
                    int contasSize = contas.size() + 1;
                    numeroDeConta = Integer.toString(contasSize);
                    numeroDeAgencia = Integer.toString(contasSize * 10);
                    conta = cliente.criarConta(numeroDeAgencia, numeroDeConta);
                    String mensagem = "Número de Conta: " + numeroDeConta +
                                        "\nNúmero de Agência: " + numeroDeAgencia;
                    JOptionPane.showMessageDialog(null, mensagem);

                    break;
                case "Acessar conta":
                    numeroDeConta = JOptionPane.showInputDialog("Numero de Conta");
                    numeroDeAgencia = JOptionPane.showInputDialog("Numero de Agência");
                    conta = cliente.getConta(numeroDeConta, numeroDeAgencia);

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
                    conta.depositar(valor);
                    break;
                case "Transferir":
                    numeroDeAgencia = JOptionPane.showInputDialog("Numero de Agência");
                    numeroDeConta = JOptionPane.showInputDialog("Numero de Conta");

                    Conta contaRecebedora = null;

                    for (Conta c : contas) {
                        if (c.getNumeroDeAgencia() == numeroDeAgencia && c.getNumeroDeConta() == numeroDeConta) {
                            contaRecebedora = c;
                            break;
                        }
                    }

                    if (contaRecebedora == null) {
                        JOptionPane.showMessageDialog(null, "Não existe conta com os dados informados, tente novamente");
                    } else {
                        valor = Double.parseDouble(JOptionPane.showInputDialog("Valor:"));
                        conta.transferir(conta, valor);
                    }

                    break;
                case "Sacar":
                    valor = Double.parseDouble(JOptionPane.showInputDialog("Valor:"));
                    conta.sacar(valor);
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