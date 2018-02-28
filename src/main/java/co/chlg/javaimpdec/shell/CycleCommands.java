package co.chlg.javaimpdec.shell;

import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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

  @ShellMethod(group = "cycle", value = "Ejercicio de comparación")
  private List<String> doNameScores(@ShellOption String nameA, @ShellOption String nameB,
      @ShellOption List<Long> scoresA, @ShellOption List<Long> scoresB) {
    List<String> res = new LinkedList<>();
    Iterator<Long> itB = scoresB.iterator();
    scoresA.forEach(a -> res.add(a > itB.next() ? nameA : nameB));
    return res;
  }

  @ShellMethod(group = "cycle", value = "Ejercicio de ordenamiento")
  private List<String> doSortLastnames(@ShellOption List<String> people) {
    return null;
  }

}
