package co.chlg.javaimpdec.shell;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import org.apache.log4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class LambdaCommands {

  private static final Logger log = Logger.getLogger(LambdaCommands.class);
  Function<String, BinaryOperator<String>> joining = (delim) -> (a, b) -> String.join(delim, a, b);

  @ShellMethod(group = "lambda", value = "Ejercicio de SAM y lambda")
  private long doMultPairs(@ShellOption List<Integer> params) {
    return params.stream()
        .filter(x -> x % 2 == 0)
        .map(Long::valueOf)
        .reduce(1L, (a, b) -> a * b);
  }

  @ShellMethod(group = "lambda", value = "Ejercicio de paralelismo")
  private String doProcessNames(@ShellOption List<String> names) {
    return names.parallelStream().peek(s -> {
      try {
        // Simulate a long processing...
        Thread.sleep(500);
      } catch (InterruptedException e) {
        log.warn("Interrupted " + s, e);
      }
      log.info(s);
    }).reduce("", joining.apply("-"));
  }

}
