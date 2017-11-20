package co.chlg.javaimpdec.shell;

import java.util.Arrays;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class CycleCommands {

	@ShellMethod(group = "cycle", value = ".")
	public String[] doFlatMap(@ShellOption(arity = 4) String[] params) {
		return null;
	}

}
