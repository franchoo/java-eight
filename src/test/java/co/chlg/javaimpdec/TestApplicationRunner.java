package co.chlg.javaimpdec;

import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.TestConfiguration;

/**
 * Helper configuration class, used in spring shell tests.
 */
@TestConfiguration
public class TestApplicationRunner implements ApplicationRunner {

  private static final Logger log = Logger.getLogger(TestApplicationRunner.class);

  public TestApplicationRunner() {
    log.info("Test Application Runner started!");
  }

  @Override
  public void run(ApplicationArguments args) {
    log.info("About to do nothing!");
  }

}
