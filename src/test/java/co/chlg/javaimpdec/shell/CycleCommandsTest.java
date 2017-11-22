package co.chlg.javaimpdec.shell;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.stream.IntStream;

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
		String[] input = { "www.google.com.co", "application.device", "frank.d.cardona", "192.168.10.12" };
		// When...
		Object result = shell.evaluate(
			() -> "do-flat-map " + String.join(",", input));
		// Then...
		assertEquals(13, ((String[]) result).length);
		log.info(Arrays.asList((String[]) result));
	}

	@Test
	public void filterPairsAndMultiply() {
		// Given...
		// String [] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		String[] input = IntStream.rangeClosed(1, 10)
			.mapToObj(String::valueOf) // Is the same as `x -> String.valueOf(x)`
			.toArray(String[]::new);   // Is the same as `size -> new String[size]`
		// When...
		Object result = shell.evaluate(
			() -> "do-mult-pairs " + String.join(",", input));
		// Then...
		assertEquals(3840, result);
	}

}
