package co.chlg.javaimpdec.shell;

import java.util.Arrays;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class CycleCommands {

	@ShellMethod(group = "cycle", value = "Ejercicio de ciclos implicitos")
	private String[] doFlatMap(@ShellOption String[] params) {
		return Arrays.stream(params)
				.map(item -> item.split("\\."))
				.flatMap(Arrays::stream) // Is the same as `x -> Arrays.stream(x)`
				.toArray(String[]::new); // Is the same as `size -> new String[size]`
	}

	@ShellMethod(group = "cycle", value = "Ejercicio de SAM y lambda")
	private int doMultPairs(@ShellOption String[] params) {
		return Arrays.stream(params)
				.mapToInt(Integer::valueOf) // Is the same as `item -> Integer.valueOf(item)`
				.filter(x -> x % 2 == 0)
				.reduce(1, (a,b) -> a*b);
	}

}
