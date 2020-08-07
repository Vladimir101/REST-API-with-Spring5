package approvals;

import org.approvaltests.Approvals;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

class GETwithApproval
{
	private WebTestClient client;
	private String baseURL = "http://api.zippopotam.us";
	private String endPoint = "/us/94404";
	
	@Test
	void GET() throws JSONException
	{
		client = WebTestClient.bindToServer().baseUrl(baseURL).build();
		
		EntityExchangeResult<String> result = client.get().uri(endPoint)
        	.accept(MediaType.APPLICATION_JSON)
        	.exchange()
        	.expectStatus().isEqualTo(200)
        	.expectHeader().contentType(MediaType.APPLICATION_JSON)
        	.expectBody(String.class)
        	.returnResult();
		
		String body = result.getResponseBody();
		JSONObject json = new JSONObject(body); 
		System.out.println("Pretty print:");
		System.out.println(json.toString(4)); // Print it with specified indentation
		Approvals.verify(json.toString(4));
	}
}
