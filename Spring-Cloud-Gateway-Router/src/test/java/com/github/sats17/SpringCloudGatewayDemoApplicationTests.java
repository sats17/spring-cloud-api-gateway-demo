package com.github.sats17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@ActiveProfiles(profiles = "default")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SpringCloudGatewayDemoApplicationTests {

	@LocalServerPort
	private String port;
	
	private MockWebServer mockWebServer;

	@Autowired
	private WebTestClient client;

	@BeforeEach
	public void clearLogList() throws IOException {
		client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port)
				.responseTimeout(Duration.ofMillis(80000)).build();
		mockWebServer = new MockWebServer();
		mockWebServer.start(8093);
	}
	
	@Test
	public void testRootPath() throws Exception {
		String downstreamresponse =  readFileAsString("src/test/resources/Test.json");
		mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(downstreamresponse)
        );
		 mockWebServer.enqueue(
	                new MockResponse()
	                        .setResponseCode(200)
	                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	                        .setBody(downstreamresponse)
	        );
		 
		ResponseSpec response = client.get()
									  .uri("/customer")
//									  .header("host", "http://localhost:8093")
									  .exchange();
		System.out.println("Is this printed");
		
		response.expectStatus().isOk();
	}
	
	public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
