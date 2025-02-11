import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, String funcao, BigDecimal salario) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void aumentaSalario(BigDecimal percentual) {
        this.salario = this.salario.multiply(BigDecimal.ONE.add(percentual.divide(BigDecimal.valueOf(100))));
    }

    @Override
    public String toString() {
        return nome + ", " + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", " + salario + ", "
                + funcao;
    }
}
