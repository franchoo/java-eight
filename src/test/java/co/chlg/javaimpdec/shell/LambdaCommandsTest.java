package co.chlg.javaimpdec.shell;

import static co.chlg.javaimpdec.DeclarativeUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import co.chlg.javaimpdec.TestApplicationRunner;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.shell.Shell;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import(TestApplicationRunner.class)
@SpringBootTest
public class LambdaCommandsTest {

  @SuppressWarnings("unused")
  private static final Logger log = Logger.getLogger(LambdaCommandsTest.class);

  @Autowired
  private Shell shell;

  @Test
  public void filterPairsAndMultiply() {
    // Given...
    // String [] input = { 1, 2, 3, 4, 5, ..., 30};
    Stream<Integer> input = IntStream.rangeClosed(1, 30).boxed();
    // When...
    Object result = shell.evaluate(inputFrom("do-mult-pairs", input));
    // Then...
    assertThat(result, is(42849873690624000L));
  }

  @Test
  public void validateNames() {
    // Simulate a concurrence...
    List<Thread> threads = IntStream.rangeClosed(1, 9)
        .mapToObj(x -> new Thread(() -> {
          // Given...
          Stream<String> input = Stream.of("Bernard" + x, "Eliza" + x, "Hilda" + x, "Karmen" + x,
              "Norman" + x, "Pablo" + x, "Sergio" + x, "Vanessa" + x, "Yadira" + x);
          // When...
          Object result = shell.evaluate(inputFrom("do-process-names", input));
          // Then...
          assertThat(result, instanceOf(String.class));
          log.info(result);
        }))
        .peek(Thread::start)
        .collect(Collectors.toList());
    // Waiting for all threads to die...
    threads.forEach(t -> {
      try {
        t.join();
      } catch (InterruptedException e) {
        log.warn("Interrupted " + t.getName(), e);
      }
    });
  }

}
