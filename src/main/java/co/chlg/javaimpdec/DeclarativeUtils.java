package co.chlg.javaimpdec;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;
import org.springframework.shell.Input;

/**
 * Class for declarative utility functions.
 */
public final class DeclarativeUtils {

  private DeclarativeUtils() {
  }

  public static BinaryOperator<String> joinBy(CharSequence delimiter) {
    return (a, b) -> String.join(delimiter, a, b);
  }

  public static String getParams(Stream<?> input) {
    return input.map(String::valueOf)
        .reduce(joinBy(","))
        .orElse("");
  }

  public static Input streamInput(String method, Stream<?> input) {
    String params = getParams(input);
    return () -> method + ' ' + params;
  }

  public static Input streamInputs(String method, Stream<?>... inputs) {
    String command = Arrays.stream(inputs).map(DeclarativeUtils::getParams)
        .reduce(method, joinBy(" "));
    return () -> command;
  }

}
