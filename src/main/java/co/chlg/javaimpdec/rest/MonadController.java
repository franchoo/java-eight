package co.chlg.javaimpdec.rest;

import java.util.Map;
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
    return null;
  }

}
