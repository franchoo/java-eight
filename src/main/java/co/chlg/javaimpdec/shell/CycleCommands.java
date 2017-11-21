package co.chlg.javaimpdec.shell;

import java.util.Arrays;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class CycleCommands {

	@ShellMethod(group = "cycle", value = ".")
	public String[] doFlatMap(@ShellOption(arity = 4) String[] params) {
		// Try to use https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#flatMap-java.util.function.Function-
		// TODO: Implement multiple string splitting into array...
		return null;
	}

}
