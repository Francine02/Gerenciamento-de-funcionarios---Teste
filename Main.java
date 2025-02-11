import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList( // Adicionando os funcionarios
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), "Operador", new BigDecimal("2009.44")),
                new Funcionario("João", LocalDate.of(1990, 5, 12), "Operador", new BigDecimal("2284.38")),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), "Coordenador", new BigDecimal("9836.14")),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), "Diretor", new BigDecimal("19119.88")),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), "Recepcionista", new BigDecimal("2234.68")),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), "Operador", new BigDecimal("1582.72")),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), "Contador", new BigDecimal("4071.84")),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), "Gerente", new BigDecimal("3017.45")),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), "Eletricista", new BigDecimal("1606.85")),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), "Gerente", new BigDecimal("2799.93"))));

        // Removendo o funcionario -> João
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        // Impriminto todos os funcionarios
        System.out.println("----------Funcionários----------\n");
        funcionarios.forEach(System.out::println);

        // Aumento de salario
        funcionarios.forEach(f -> f.aumentaSalario(BigDecimal.TEN));

        // Funcionarios por função
        Map<String, List<Funcionario>> funcionariosFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // Imprimindo funcionarios por função
        System.out.println("\n-------Funcionários por função-------");
        funcionariosFuncao.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(System.out::println);
        });
        System.out.println("-------------------------------------------");

        // Imprimindo funcionarios por aniversário no mês 10 e 12
        System.out.println("\nFuncionários que nasceram no mês 10 e 12\n");
        funcionarios.stream().filter(
                f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);
        ;
        System.out.println("\n-------------------------------------------");

        // Imprimindo funcionarios com maior idade, mostrando os atributos: nome e idade
        Funcionario funcionarioMaiorIdade = Collections.min(funcionarios,
                Comparator.comparing(f -> f.getDataNascimento()));
        System.out.println("\nFuncionário com maior idade: " + funcionarioMaiorIdade.getNome() + ", idade: "
                + Period.between(funcionarioMaiorIdade.getDataNascimento(), LocalDate.now()).getYears());
        System.out.println("\n-------------------------------------------");

        // Imprimindo a lista de funcionarios por ordem alfabética
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("\nFuncionários ordenados: \n");
        funcionarios.forEach(System.out::println);
        System.out.println("\n-------------------------------------------");

        // Imprimindo total do salario dos funcionarios
        BigDecimal totalSalario = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO,
                BigDecimal::add);
        System.out.println("\nTotal dos sálarios: " + String.format("%,.2f", totalSalario));
        System.out.println("\n-------------------------------------------");

        // Imprimindo quantos salários mínimos ganha cada funcionário
        System.out.println("\nQuantos salarios minimos ganha cada funcionario: \n");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(f -> {
            BigDecimal quantidadeSalarioMinimo = f.getSalario().divide(salarioMinimo, 0, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " ganha: " + quantidadeSalarioMinimo + " salarios mínimos");
        });
    }
}
