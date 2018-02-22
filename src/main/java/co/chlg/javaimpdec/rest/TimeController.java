package co.chlg.javaimpdec.rest;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class TimeController {

  private static final Logger log = Logger.getLogger(TimeController.class);

  @GetMapping("/duration-rand/{quantity}")
  private String getRandomQuantityDuration(@PathVariable("quantity") int quantity) {
    Instant begin = Instant.now();
    log.info(IntStream.generate(RandomUtils::nextInt).limit(quantity).average());
    return Duration.between(begin, Instant.now()).toString();
  }

  @GetMapping("/add-hours-return-day/{hours}")
  private Integer getLocalDayAfterTime(@PathVariable("hours") int hours) {
    return LocalDateTime.now().plusHours(hours).getDayOfMonth();
  }

  @GetMapping("/get-first/{dayOfWeek}/from/{year}/month/{month}")
  private Integer getFirstWeekDay(@PathVariable("year") int year, @PathVariable("month") int month,
      @PathVariable("dayOfWeek") String dayOfWeek) {
    return LocalDate.of(year, month, 1)
        .with(firstInMonth(DayOfWeek.valueOf(dayOfWeek)))
        .getDayOfMonth();
  }

}
