package co.chlg.javaimpdec.shell;

import java.util.Arrays;
import java.util.List;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class LambdaCommands {

  @ShellMethod(group = "lambda", value = "Ejercicio de SAM y lambda")
  private long doMultPairs(@ShellOption List<Integer> params) {
    return params.stream()
        .filter(x -> x % 2 == 0)
        .map(Long::valueOf)
        .reduce(1L, (a, b) -> a * b);
  }

  @ShellMethod(group = "lambda", value = "Ejercicio de concurrencia")
  private List<String> doProcessNames(@ShellOption List<String> names) {
    return Arrays.asList();
  }

}
