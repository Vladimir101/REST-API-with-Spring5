package parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

class ParseJSON
{
	private WebTestClient client;
	private String URL = "https://jsonplaceholder.typicode.com";

	@BeforeEach
	void setUp() throws Exception
	{
		client = WebTestClient.bindToServer().baseUrl(URL).build();
	}

	@Test
	public void parseGet() throws IOException, JSONException
	{
		EntityExchangeResult<String> result = client.get().uri("/users")
		        .exchange()
		        .expectStatus().isOk()
		        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		        .expectBody(String.class)
		        .returnResult();
		String page = result.getResponseBody();
		System.out.println("Response body:");
		System.out.println(page);
		
// using org.json			
		JSONArray response = new JSONArray(page);
		JSONObject e1 = response.getJSONObject(0);
		int id = e1.getInt("id");
		System.out.println("id: " + id);
	
		String geoLng = e1.getJSONObject("address").getJSONObject("geo").getString("lng");
		System.out.println("geo:lng: " + geoLng);
	}
}
