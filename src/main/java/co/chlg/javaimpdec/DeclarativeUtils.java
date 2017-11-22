package co.chlg.javaimpdec;

import java.util.stream.Stream;

import org.springframework.shell.Input;

/**
 * Class for declarative utility functions.
 */
public final class DeclarativeUtils {

    private DeclarativeUtils() {
    }

    public static Input streamInput(String method, Stream<?> input) {
        String params = input.map(String::valueOf)
                .reduce((a, b) -> String.join(",", a, b))
                .orElse("");
        return () -> method + ' ' + params;
    }

}
