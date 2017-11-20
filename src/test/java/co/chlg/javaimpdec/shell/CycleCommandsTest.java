package co.chlg.javaimpdec.shell;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.shell.Input;
import org.springframework.shell.Shell;
import org.springframework.test.context.junit4.SpringRunner;

import co.chlg.javaimpdec.TestApplicationRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import(TestApplicationRunner.class)
public class CycleCommandsTest {

	@Autowired
	private Shell shell;

	@Test
	public void disassembleWords() {
		// Given...
		String[] input = { "www.google.com.co", "application.device", "frank.d.cardona", "192.168.10.12" };
		// When...
		Object result = shell.evaluate(new Input() {
			@Override
			public String rawText() {
				return "do-flat-map " + String.join(" ", input);
			}
		});
		// Then...
		assertEquals(13, ((String[]) result).length);
		System.out.println(Arrays.asList((String[]) result));
	}

}
