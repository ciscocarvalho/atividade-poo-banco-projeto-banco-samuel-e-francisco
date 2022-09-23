public class Conta {
    private String numeroDeAgencia;
    private String numeroDeConta;
    private double saldo;
    private Cliente cliente;

    public Conta(String numeroDeAgencia, String numeroDeConta, double saldo, Cliente cliente) {
        this.numeroDeAgencia = numeroDeAgencia;
        this.numeroDeConta = numeroDeConta;
        this.saldo = saldo;
        this.cliente = cliente;
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
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public void transferir(Conta conta, double valor) {
        this.saldo -= valor;
        conta.saldo += valor;
    }
}
