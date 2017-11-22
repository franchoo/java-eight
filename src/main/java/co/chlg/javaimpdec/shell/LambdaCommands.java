package co.chlg.javaimpdec.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;

@ShellComponent
public class LambdaCommands {

	@ShellMethod(group = "lambda", value = "Ejercicio de SAM y lambda")
	private int doMultPairs(@ShellOption String[] params) {
		return Arrays.stream(params)
				.mapToInt(Integer::valueOf) // Is the same as `item -> Integer.valueOf(item)`
				.filter(x -> x % 2 == 0)
				.reduce(1, (a,b) -> a*b);
	}

}
