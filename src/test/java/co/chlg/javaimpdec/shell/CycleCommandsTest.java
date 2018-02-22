package co.chlg.javaimpdec.shell;

import static co.chlg.javaimpdec.DeclarativeUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import co.chlg.javaimpdec.TestApplicationRunner;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
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
public class CycleCommandsTest {

  private static final Logger log = Logger.getLogger(CycleCommandsTest.class);

  @Autowired
  private Shell shell;

  @Test
  @SuppressWarnings("unchecked")
  public void disassembleWords() {
    // Given...
    Stream<String> input = Stream
        .of("www.google.com.co", "application.device", "frank.d.cardona", "192.168.10.12");
    // When...
    Object result = shell.evaluate(inputFrom("do-flat-map", input));
    // Then...
    assertThat(result, instanceOf(Set.class));
    assertThat((Set<String>) result, hasItems("d", "co", "com", "www", "10", "12", "device"));
    log.info(result);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void compareScores() {
    // Given...
    Stream<Long> scoresA = Stream.of(1234567890L, 4569871230L, 5632147890L);
    Stream<Long> scoresB = Stream.of(9856327410L, 2589637410L, 5632891470L);
    // When...
    Object result = shell.evaluate(inputFrom("do-name-scores Alice Bob", scoresA, scoresB));
    // Then...
    assertThat(result, instanceOf(List.class));
    assertEquals(Arrays.asList("Bob", "Alice", "Bob"), result);
    log.info(Arrays.asList((String[]) result));
  }

  @Test
  @SuppressWarnings("unchecked")
  public void orderedLastnames() {
    // Given...
    Stream<String> input = Stream
        .of("tom-hanks", "tom-cruise", "alec-baldwin", "anne-hathaway", "hugh-jackman");
    // When...
    Object result = shell.evaluate(inputFrom("do-sort-lastnames", input));
    // Then...
    assertThat(result, instanceOf(List.class));
    assertEquals(
        Arrays.asList("alec-baldwin", "tom-cruise", "tom-hanks", "anne-hathaway", "hugh-jackman"),
        result);
    log.info(result);
  }

}
