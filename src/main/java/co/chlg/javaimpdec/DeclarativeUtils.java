package co.chlg.javaimpdec;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;
import org.springframework.shell.Input;

/**
 * Class for declarative utility functions.
 */
@SuppressWarnings("WeakerAccess")
public final class DeclarativeUtils {

  private DeclarativeUtils() {
  }

  public static BinaryOperator<String> joinBy(CharSequence delimiter) {
    return (a, b) -> String.join(delimiter, a, b);
  }

  public static String getParam(Stream<?> input) {
    return input.map(String::valueOf)
        .reduce(joinBy(","))
        .orElse("");
  }

  public static Input inputFrom(String method, Stream<?>... inputs) {
    String command = Arrays.stream(inputs)
        .map(DeclarativeUtils::getParam)
        .reduce(method, joinBy(" "));
    // Consume the Stream outside the lambda, to avoid IllegalStateException
    return () -> command;
  }

}
