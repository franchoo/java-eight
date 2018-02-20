package co.chlg.javaimpdec.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;

@ShellComponent
public class CycleCommands {

	@ShellMethod(group = "cycle", value = "Ejercicio de ciclos implicitos")
	private String[] doFlatMap(@ShellOption String[] params) {
		return Arrays.stream(params)
				.map(item -> item.split("\\."))
				.flatMap(Arrays::stream) // Is the same as `x -> Arrays.stream(x)`
				.toArray(String[]::new); // Is the same as `size -> new String[size]`
	}

}
