package LongestNap;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

  record Compromisso(LocalTime inicio, LocalTime fim) {
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    LocalTime inicioExpediente = LocalTime.of(10, 0);
    LocalTime fimExpediente = LocalTime.of(18, 0);

    int numeroDoDia = 1;

    while (sc.hasNextInt()) {
      int quantidadeCompromissos = sc.nextInt();
      sc.nextLine();

      List<Compromisso> compromissos = new ArrayList<>();

      for (int i = 0; i < quantidadeCompromissos; i++) {
        String linha = sc.nextLine();
        String[] partes = linha.split(" ");

        LocalTime inicio = LocalTime.parse(partes[0]);
        LocalTime fim = LocalTime.parse(partes[1]);

        compromissos.add(new Compromisso(inicio, fim));
      }

      compromissos.add(new Compromisso(inicioExpediente, inicioExpediente));
      compromissos.add(new Compromisso(fimExpediente, fimExpediente));

      List<Compromisso> compromissosOrdenados = compromissos.stream()
          .sorted(Comparator.comparing(Compromisso::inicio))
          .toList();

      LocalTime melhorInicioCochilo = inicioExpediente;
      int melhorDuracaoEmMinutos = -1;

      LocalTime fimUltimoCompromisso = inicioExpediente;

      for (Compromisso atual : compromissosOrdenados) {
        if (atual.inicio().isAfter(fimUltimoCompromisso)) {
          int minutosLivres = (int) Duration.between(fimUltimoCompromisso, atual.inicio()).toMinutes();

          if (minutosLivres > melhorDuracaoEmMinutos) {
            melhorDuracaoEmMinutos = minutosLivres;
            melhorInicioCochilo = fimUltimoCompromisso;
          }
        }

        if (atual.fim().isAfter(fimUltimoCompromisso)) {
          fimUltimoCompromisso = atual.fim();
        }
      }

      int horasCochilo = melhorDuracaoEmMinutos / 60;
      int minutosCochilo = melhorDuracaoEmMinutos % 60;

      StringBuilder saida = new StringBuilder();
      saida.append("Day #")
          .append(numeroDoDia)
          .append(": the longest nap starts at ")
          .append(formatarHora(melhorInicioCochilo))
          .append(" and will last for ");

      if (melhorDuracaoEmMinutos >= 60) {
        saida.append(horasCochilo).append(" hours and ");
      }
      saida.append(minutosCochilo).append(" minutes.");

      System.out.println(saida);
      numeroDoDia++;
    }

    sc.close();
  }

  private static String formatarHora(LocalTime horario) {
    return String.format("%02d:%02d", horario.getHour(), horario.getMinute());
  }
}
