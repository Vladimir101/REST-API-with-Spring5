package parser;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

class ParseJSONHomework
{
	private WebTestClient client;
	private String URL = "https://airportgap.dev-tester.com/api";

	@BeforeEach
	void setUp() throws Exception
	{
		client = WebTestClient.bindToServer().baseUrl(URL).build();
	}

	@Test
	public void parseGet() throws IOException, JSONException
	{
		EntityExchangeResult<String> result = client.get().uri("/airports/SFO")
		        .exchange()
		        .expectStatus().isOk()
		        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		        .expectBody(String.class)
		        .returnResult();
		String page = result.getResponseBody();

// using org.json		
		JSONObject response = new JSONObject(page);
// provides JSON pretty-print with specified indentation, 4 in our case 		
		System.out.println(response.toString(4));
		
		JSONObject data = response.getJSONObject("data");
		String city = data.getJSONObject("attributes").getString("city");
		System.out.println(city);
		
		String id = data.getString("id");
		System.out.println(id);
	}
}
