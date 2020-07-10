package rp;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import postbodies.Address;
import reactor.core.publisher.Mono;

public class POSTSpring5_2
{
	private WebTestClient client;
	private String URL = "http://www.htmlgoon.com/api/POST_JSON_Service.php";
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeEach
	void setUp() throws Exception
	{
		client = WebTestClient.bindToServer().baseUrl(URL).build();
	}

	@Test
	void POSTwithBody() throws IOException 
	{
		Address address =  new Address("Vladimir", "B", "Foster City");
		
		EntityExchangeResult<String> result = client.post()
				.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("MyRequestHeader", "MyValue") // custom header
//                .body(Mono.just(address), Address.class) 
                .body(BodyInserters.fromValue(address))
		        .exchange()
		        .expectStatus().isOk()
		        .expectBody(String.class)
		        .returnResult();
		System.out.println("Response body:\n" + result.getResponseBody());
		
// Using Jackson library for mapping			
		JsonNode root = objectMapper.readTree(result.getResponseBody());
		System.out.println(root.get("status"));
		System.out.println(root.get("message"));
		System.out.println(root.get("data")); // get nested data
		System.out.println(root.get("data").get(0).get("firstname"));	 
	}
	
	@Test
	void POSTwithJSONResponse() 
	{
		Address address =  new Address("Vladimir", "B", "Foster City");
		
		client.post()
				.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
    			.body(BodyInserters.fromValue(address))
//                .body(Mono.just(address), Address.class) 
		        .exchange()
		        .expectStatus().isOk()
		        .expectBody()
		        .json("{\"status\":\"ok\"}") // requires JSONassert dependency
		        .jsonPath("data[0].firstname").isEqualTo("Vladimir");
	}
}
