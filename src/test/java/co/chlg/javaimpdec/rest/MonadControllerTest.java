package co.chlg.javaimpdec.rest;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import co.chlg.javaimpdec.TestApplicationRunner;
import java.net.URI;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import(TestApplicationRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MonadControllerTest {

  private static final Logger log = Logger.getLogger(MonadControllerTest.class);

  private URI url;
  @LocalServerPort
  private int port;
  @Autowired
  private TestRestTemplate testRest;

  @Before
  public void setUp() {
    url = URI.create("http://localhost:" + port + "/monad/");
  }

  @Test
  @SuppressWarnings("unchecked")
  public void getCountsFromMillionsRandom() {
    // Given...
    final String LUCKY_NUM = "13";
    URI uri = url.resolve("map-count/" + LUCKY_NUM + "/size/" + 2 + "/from/" + (10000));
    // When...
    ResponseEntity<Map> response = testRest.getForEntity(uri, Map.class);
    // Then...
    assertThat(response.getStatusCode(), is(OK));
    assertThat(response.getHeaders().getContentType().toString(),
        startsWith(APPLICATION_JSON_VALUE));
    assertThat(response.getBody(), notNullValue());
    assertThat((Set<String>) response.getBody().keySet(), hasItem(LUCKY_NUM));
    assertTrue((int) response.getBody().get(LUCKY_NUM) > 75);
    log.info(response.getBody());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void getFibonacci20() {
    // Given...
    final String RESOURCE = "map-fibonacci/";
    // When...
    ResponseEntity<Map> resp1 = testRest.getForEntity(url.resolve(RESOURCE + 5), Map.class);
    ResponseEntity<Map> resp2 = testRest.getForEntity(url.resolve(RESOURCE + 22), Map.class);
    // Then...
    assertThat(resp1.getStatusCode(), is(OK));
    assertThat(resp1.getHeaders().getContentType().toString(), startsWith(APPLICATION_JSON_VALUE));
    assertThat(resp1.getBody(), notNullValue());
    assertThat(resp1.getBody().get("5"), is(5)); // Fibonacci number in 5th position!
    assertThat(resp2.getStatusCode(), is(OK));
    assertThat(resp2.getHeaders().getContentType().toString(), startsWith(APPLICATION_JSON_VALUE));
    assertThat(resp2.getBody(), notNullValue());
    assertThat(resp2.getBody().get("22"), is(17711)); // Fibonacci number in 22th position!
  }

  @Test
  @SuppressWarnings("unchecked")
  public void getAgeFromNamesLength() {
    // Given...
    final String NAME = "Maria Gutierrez";
    URI uri = url.resolve("map-age/".concat(NAME.replace(" ", "%20")));
    // When...
    ResponseEntity<Map> response = testRest.getForEntity(uri, Map.class);
    // Then...
    assertThat(response.getStatusCode(), is(OK));
    assertThat(response.getHeaders().getContentType().toString(),
        startsWith(APPLICATION_JSON_VALUE));
    assertThat(response.getBody(), notNullValue());
    assertThat((Set<String>) response.getBody().keySet(), hasItem(NAME));
    assertThat(response.getBody().get(NAME), is(59)); // letter count of each name!
  }

}
