package co.chlg.javaimpdec.shell;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class LambdaCommands {

  private static final Logger log = Logger.getLogger(LambdaCommands.class);

  @ShellMethod(group = "lambda", value = "Ejercicio de SAM y lambda")
  private long doMultPairs(@ShellOption List<Integer> params) {
    return params.stream()
        .filter(x -> x % 2 == 0)
        .map(Long::valueOf)
        .reduce(1L, (a, b) -> a * b);
  }

  @ShellMethod(group = "lambda", value = "Ejercicio de concurrencia")
  private List<String> doProcessNames(@ShellOption List<String> names) {
    return names.parallelStream().peek(s -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      log.info(s);
    }).collect(Collectors.toList());
  }

}
