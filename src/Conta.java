public class Conta {
    private String numeroDeAgencia;
    private String numeroDeConta;
    private float saldo;
    private Cliente cliente;

    public Conta(String numeroDeAgencia, String numeroDeConta, float saldo, Cliente cliente) {
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

    public float getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void sacar(float valor) {
        this.saldo -= valor;
    }

    public void depositar(float valor) {
        this.saldo += valor;
    }

    public void transferir(Conta conta, float valor) {
        this.saldo -= valor;
        conta.saldo += valor;
    }
}
