package com.pencil.kata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class KataApplicationTests {

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	String baseUrl = "http://localhost:8080";
	String endpoint = "/redpencil?id=";

	@Test
	public void contextLoads() {
	}

	@Test
	public void testItemThatIsValidForPromoItem() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange((baseUrl + endpoint + "prod6"), HttpMethod.GET, entity, String.class);
		String actual = response.getBody();
		assertThat(actual).contains("true");
	}

	@Test
	public void testItemNotValidForPromoItem() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange((baseUrl + endpoint + "prod5"), HttpMethod.GET, entity, String.class);
		String actual = response.getBody();
		assertThat(actual).contains("false");
	}

}
