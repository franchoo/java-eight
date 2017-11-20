package co.chlg.javaimpdec;

import java.util.logging.Logger;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class TestApplicationRunner implements ApplicationRunner {

	private static Logger log = Logger.getLogger(TestApplicationRunner.class.getName());

	public TestApplicationRunner() {
		log.info("Test Application Runner started!");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("About to do nothing!");
		// Do nothing...
	}

}
