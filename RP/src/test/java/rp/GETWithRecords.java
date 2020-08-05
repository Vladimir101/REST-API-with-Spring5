// preview features must be enabled
package rp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import pages.TimezoneRecord;

class GETWithRecords
{
	private WebTestClient client;
	private String URL = "http://worldtimeapi.org/api/timezone";
	
	@Test
	void mapToString()
	{
		client = WebTestClient.bindToServer().baseUrl(URL).build();
		EntityExchangeResult<String> result = client.get()
				.uri("/America/Los_Angeles")
		        .exchange()
		        .expectStatus().isOk()
		        .expectBody(String.class)   
		        .returnResult();
		String response = result.getResponseBody();
		System.out.println(response);
	}
	
	@Test
	void mapToRecord()
	{
		client = WebTestClient.bindToServer().baseUrl(URL).build();
		EntityExchangeResult<TimezoneRecord> result = client.get()
				.uri("/America/Los_Angeles")
		        .exchange()
		        .expectStatus().isOk()
		        .expectBody(TimezoneRecord.class)   
		        .returnResult();
		TimezoneRecord response = result.getResponseBody();

		assertEquals("PDT", response.abbreviation());
		assertEquals("2020-11-01T09:00:00+00:00", response.dst_until()); // daylight saving time

	}
}
