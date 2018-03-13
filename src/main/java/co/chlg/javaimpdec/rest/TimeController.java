package co.chlg.javaimpdec.rest;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.ofDateAdjuster;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

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

  @GetMapping("/add-business-days/{quantity}/to/{year}/{month}/{day}")
  private String getBusinessDate(@PathVariable("quantity") int quantity,
      @PathVariable("year") int year, @PathVariable("month") int month,
      @PathVariable("day") int day) {
    return LocalDate.of(year, month, day).with(ofDateAdjuster(date -> {
      LocalDate baseDate = quantity > 0 ? date.with(previousOrSame(DayOfWeek.MONDAY))
          : quantity < 0 ? date.with(nextOrSame(DayOfWeek.FRIDAY)) : date;
      int businessDays = quantity + min(max(baseDate.until(date).getDays(), -4), 4);
      return baseDate.plusWeeks(businessDays / 5).plusDays(businessDays % 5);
    })).toString();
  }

}
