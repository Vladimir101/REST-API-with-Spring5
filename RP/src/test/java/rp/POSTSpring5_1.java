package rp;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import postbodies.SimplePostBody;

class POSTSpring5_1
{
	private WebTestClient client;
	private String URL;
	
	@Test
	void POST()
	{
		URL = "http://httpbin.org";
		var postBody = new SimplePostBody("bar");
		client = WebTestClient.bindToServer().baseUrl(URL).build();
		
		EntityExchangeResult<String> result = client.post().uri("/post")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(postBody))
			.exchange()
			.expectStatus().isOk()
			.expectBody(String.class)
			.returnResult();
		
		System.out.println("Response Content-Type: " + result.getResponseHeaders().getContentType());
		System.out.println(result.getResponseBody());
	}
	
	@Test
	void POSTwithJsonPath()
	{
		URL = "http://httpbin.org";
		var postBody = new SimplePostBody("bar");
		client = WebTestClient.bindToServer().baseUrl(URL).build();
		
		EntityExchangeResult<byte[]> result = client.post().uri("/post")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(postBody))
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.origin").isEqualTo("73.162.153.202")
			.returnResult();
		System.out.println(result.getResponseBody());
	}
}
