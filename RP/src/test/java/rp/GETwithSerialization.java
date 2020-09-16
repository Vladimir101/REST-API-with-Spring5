package rp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import pages.*;

class GETwithSerialization
{
	private WebTestClient client;
	private String URL;
	private String FILE_NAME = "Timezone.json";
	String jsonStringExpected = "{\"abbreviation\":\"PDT\",\"client_ip\":" 
			+ "\"73.162.153.202\",\"datetime\":\"2020-09-15T17:49:48.105377-07:00\","
			+ "\"day_of_week\":2,\"day_of_year\":259,\"dst\":true,\"dst_from\":"
			+ "\"2020-03-08T10:00:00+00:00\",\"dst_offset\":3600,\"dst_until\":"
			+ "\"2020-11-01T09:00:00+00:00\",\"raw_offset\":-28800,\"timezone\":"
			+ "\"America/Los_Angeles\",\"unixtime\":1600217388,\"utc_datetime\":"
			+ "\"2020-09-16T00:49:48.105377+00:00\",\"utc_offset\":\"-07:00\",\"week_number\":38}";
	
	@Test
	void readJSON() throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		
		TimezonePage t = mapper.readValue(jsonStringExpected, TimezonePage.class);
		System.out.println(t.getAbbreviation());
		System.out.println(t.getTimezone());
	}
	
	@Test
	void GETwithPOJO() throws IOException
	{
		URL = "http://worldtimeapi.org/api/timezone";
		client = WebTestClient.bindToServer().baseUrl(URL).build();
		
		EntityExchangeResult<TimezonePage> result = client.get()
				.uri("/America/Los_Angeles")
		        .exchange()
		        .expectBody(TimezonePage.class)
		        .returnResult();
		
		TimezonePage actual = result.getResponseBody();
		
// read the expected response from the JSON file		
		ObjectMapper mapper = new ObjectMapper();
		InputStream jsonInput = 
				GETwithSerialization.class.getResourceAsStream(FILE_NAME);
		
		TimezonePage expected = mapper.readValue(jsonInput, TimezonePage.class);
		
		assertEquals(expected.getClient_ip(), actual.getClient_ip());
		assertEquals(expected.getDst_offset(), actual.getDst_offset());
	}
}

