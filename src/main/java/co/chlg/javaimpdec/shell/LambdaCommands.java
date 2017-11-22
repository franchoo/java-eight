package co.chlg.javaimpdec.shell;

import java.util.List;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class LambdaCommands {

  @ShellMethod(group = "lambda", value = "Ejercicio de SAM y lambda")
  private int doMultPairs(@ShellOption List<Integer> params) {
    return params.stream()
        .filter(x -> x % 2 == 0)
        .reduce(1, (a, b) -> a * b);
  }

}
