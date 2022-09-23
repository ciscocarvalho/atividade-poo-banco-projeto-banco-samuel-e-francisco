import java.time.LocalDate;

public class Cliente {
    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;
    private Object endereco; // Declaramos um variavel do tipo object pelo fato dela receber varios valores.

    public Cliente(String nome, String cpf, LocalDate dataDeNascimento, Object endereco){ // Criando o metudo contrutor 
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public String getNome(){
        return this.nome;
    }
    public String getCpf(){
        return this.cpf;
    } 
    public LocalDate  getDataDeNacimento(){
        return this.dataDeNascimento;

    }
    public void setEndereco(Object endereco){
        this.endereco = endereco;
        }
    public Object getEndereco(){
        return this.endereco;
    }


}
