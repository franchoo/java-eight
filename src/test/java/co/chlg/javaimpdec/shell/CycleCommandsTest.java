package co.chlg.javaimpdec.shell;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static co.chlg.javaimpdec.DeclarativeUtils.streamInput;

import java.util.Arrays;
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

import co.chlg.javaimpdec.TestApplicationRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import(TestApplicationRunner.class)
public class CycleCommandsTest {
	private static final Logger log = Logger.getLogger(CycleCommandsTest.class);

	@Autowired
	private Shell shell;

	@Test
	public void disassembleWords() {
		// Given...
		Stream<String> input = Stream.of("www.google.com.co", "application.device", "frank.d.cardona", "192.168.10.12");
		// When...
		Object result = shell.evaluate(streamInput("do-flat-map", input));
		// Then...
		assertEquals(13, ((String[]) result).length);
		log.info(Arrays.asList((String[]) result));
	}

	@Test
	public void filterPairsAndMultiply() {
		// Given...
		// String [] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		Stream<Integer> input = IntStream.rangeClosed(1, 10).boxed();
		// When...
		Object result = shell.evaluate(streamInput("do-mult-pairs", input));
		// Then...
		assertEquals(3840, result);
	}

}
