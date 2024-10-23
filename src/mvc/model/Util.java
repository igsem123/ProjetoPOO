package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Util {
    private static Pessoa pessoaLogada = null;

    private static LocalDateTime diaAtual;
    private static LocalDateTime dia;
    private static LocalDate dia2;
    private static LocalDate diaInicioDoSistema;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static Pessoa getPessoaLogada() {
        return pessoaLogada;
    }

    public static void setPessoaLogada(Pessoa pessoaLogada) {
        Util.pessoaLogada = pessoaLogada;
    }

    public static LocalDateTime getDiaAtual() {
        return diaAtual;
    }

    public static LocalDateTime getDia() {
        return dia;
    }

    public static LocalDate getDia2() {
        return dia2;
    }

    public static LocalDate getDiaInicioDoSistema() {
        return diaInicioDoSistema;
    }

    public static int getDiaDoMes() {
        return diaAtual.getDayOfMonth();
    }

    public static void incrementaDias(int dias) {
        diaAtual.plusDays((long)dias);
    }

    public static void incrementaMes(int numeroMeses) {
        diaAtual.plusMonths((long)numeroMeses);
    }

    public static LocalDate formataData(String data) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(data, dtf);
    }

    static {
        diaAtual = LocalDateTime.of(2024, Month.OCTOBER, 10, 22, 5);
        dia = LocalDateTime.now();
        dia2 = LocalDate.now();
        diaInicioDoSistema = LocalDate.of(2024, Month.MAY, 1);
    }
}
