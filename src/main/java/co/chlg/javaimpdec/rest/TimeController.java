package co.chlg.javaimpdec.rest;

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
    return null;
  }

  @GetMapping("/add-hours-return-day/{hours}")
  private Integer getLocalDayAfterTime(@PathVariable("hours") int hours) {
    return null;
  }

  @GetMapping("/get-first/{dayOfWeek}/from/{year}/month/{month}")
  private Integer getFirstWeekDay(@PathVariable("year") int year, @PathVariable("month") int month,
      @PathVariable("dayOfWeek") String dayOfWeek) {
    return null;
  }

}
