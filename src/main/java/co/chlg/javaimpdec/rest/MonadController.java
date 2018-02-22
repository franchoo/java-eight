package co.chlg.javaimpdec.rest;

import static java.lang.Math.pow;
import static java.util.Arrays.stream;
import static java.util.Collections.singletonMap;
import static java.util.function.Predicate.isEqual;
import static java.util.stream.Stream.generate;
import static java.util.stream.Stream.iterate;
import static org.apache.commons.lang3.RandomUtils.nextInt;

import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monad")
public class MonadController {

  @GetMapping("/map-fibonacci/{num}")
  private Map<Integer, Integer> getMappingNumFib(@PathVariable("num") int num) {
    // Pair.of(0, 1) Pair.of(1, 1) Pair.of(1, 2) Pair.of(2, 3) Pair.of(3, 5) //fib(5)==5
    return singletonMap(num,
        iterate(Pair.of(0, 1), seq -> Pair.of(seq.getRight(), seq.getLeft() + seq.getRight()))
            .skip(num).findFirst().map(Pair::getLeft).orElse(null));
  }

  @GetMapping("/map-count/{luckyNum}/size/{exp}/from/{qty}")
  private Map<Integer, Integer> getMappingNumRandomsCount(@PathVariable("luckyNum") int luckyNum,
      @PathVariable("exp") int exp, @PathVariable("qty") int qty) {
    return singletonMap(luckyNum,
        (int) generate(() -> nextInt(0, (int) pow(10, exp))).limit(qty)
            .filter(isEqual(luckyNum))
            .count());
  }

  @GetMapping("/map-age/{fullName}")
  private Map<String, Integer> getMappingNameAge(@PathVariable("fullName") String fullName) {
    // TODO: sequential & reduce will be useful
    return singletonMap(fullName,
        stream(fullName.split(" ")).mapToInt(String::length).reduce(0, (a, b) -> a * 10 + b));
  }

}
