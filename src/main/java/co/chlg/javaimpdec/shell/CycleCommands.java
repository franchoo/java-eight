package co.chlg.javaimpdec.shell;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Set;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class CycleCommands {

  @ShellMethod(group = "cycle", value = "Ejercicio de ciclos implicitos")
  private Set<String> doFlatMap(@ShellOption String... params) {
    return Arrays.stream(params)
        .map(item -> item.split("\\."))
        .flatMap(Arrays::stream) // Is the same as `x -> Arrays.stream(x)`
        //.toArray(String[]::new); // Is the same as `size -> new String[size]`
        .collect(toSet());
  }

}
