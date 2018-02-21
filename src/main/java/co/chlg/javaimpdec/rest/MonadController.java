package co.chlg.javaimpdec.rest;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monad")
public class MonadController {

  @GetMapping("/map-age/:name")
  private Map<String, String> getMappingNameAge(@PathVariable("name") String name) {
    return null;
  }

}
