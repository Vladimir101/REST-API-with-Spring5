package rp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.springframework.http.HttpHeaders.*;
import pages.*;

class GETSpring5
{
	private WebTestClient client;
	private String URL;
	
	@Test
	void GETwithoutParams()
	{
		URL = "http://api.zippopotam.us";
		client = WebTestClient.bindToServer().baseUrl(URL).build();
		
		EntityExchangeResult<String> result = client.get().uri("/us/94404")
        	.accept(MediaType.APPLICATION_JSON)
        	.exchange()
        	.expectStatus().isOk()
        	.expectHeader().contentType(MediaType.APPLICATION_JSON)
        	.expectBody(String.class)
        	.returnResult();
		
		System.out.println(result.getResponseBody());
	}
	
	@Test
	void GETwithParams()
	{
		URL = "http://api.openweathermap.org";
		client = WebTestClient.bindToServer().baseUrl(URL).build();
		String city = "Prague";
		String appid = "a176e0999da6ade934326ce91abde8ea";
		
		EntityExchangeResult<String> result = client.get()
				.uri("/data/2.5/weather?q={city}&appid={appid}", city, appid)
		        .exchange()
		        .expectStatus().isOk()
		        .expectBody(String.class)
		        .consumeWith(response -> assertFalse((response.getResponseBody()).isEmpty()))
		        .returnResult();

		String response = result.getResponseBody();
		System.out.println(response);
	}
	
	@Test
	void GETwithResponseMappingToPOJO1()
	{
		URL = "http://worldtimeapi.org/api/timezone";
		client = WebTestClient.bindToServer().baseUrl(URL).build();
		EntityExchangeResult<TimezonePage> result = client.get()
				.uri("/America/Los_Angeles")
		        .exchange()
		        .expectStatus().isOk()
		        .expectHeader()
		        .contentType(MediaType.APPLICATION_JSON_UTF8)
		        .expectBody(TimezonePage.class)   
		        .returnResult();
		TimezonePage response = result.getResponseBody();

		assertEquals("PDT", response.getAbbreviation());
		assertEquals("2020-11-01T09:00:00+00:00", response.getDst_until()); // daylight saving time
	}
	
	@Test
	void GETwithResponseMappingToPOJO2()
	{
		URL = "https://jsonview.com/example.json";
		client = WebTestClient.bindToServer().baseUrl(URL).build();
		EntityExchangeResult<ExamplePage> result = client.get()
		        .exchange()
		        .expectStatus().isOk()
		        .expectBody(ExamplePage.class)
		        .returnResult();

// response headers
		MediaType responseContentType = result.getResponseHeaders().getContentType();
		System.out.println("Content-Type: " + responseContentType);
		
		long responseContentLegth = result.getResponseHeaders().getContentLength();
		System.out.println("Content-Length: " + responseContentLegth);

// status code 
		int statusCode = result.getStatus().value();
		System.out.println("Status code: " + statusCode);	
		
// get body elements		
		System.out.println(result.getResponseBody().getJapanese());
		System.out.println(result.getResponseBody().getNotLink());
		System.out.println(result.getResponseBody().getAnobject().getMore());
// Since anarray was mapped to the ArrayList of Objects, we must cast the its values 
// to the correct data type		
		System.out.println((Integer)result.getResponseBody().getAnobject().getAnarray().get(0));
		System.out.println((String)result.getResponseBody().getAnobject().getAnarray().get(2));	

	}
}
