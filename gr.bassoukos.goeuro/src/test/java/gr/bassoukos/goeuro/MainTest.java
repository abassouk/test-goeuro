package gr.bassoukos.goeuro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import gr.bassoukos.goeuro.om.ErrorResponse;
import gr.bassoukos.goeuro.om.GeoPosition;
import gr.bassoukos.goeuro.om.Position;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

public class MainTest {

	private Client client;
	private WebTarget webTarget;
	private Builder requestBuilder;
	private Response response;

	@Before
	public void setup() {
		this.client = mock(Client.class);
		this.webTarget = mock(WebTarget.class);
		this.requestBuilder = mock(Builder.class);
		this.response = mock(Response.class);

		when(client.target(SuggestionRetriever.TARGET_API)).thenReturn(
				webTarget);
		when(webTarget.path(anyString())).thenReturn(webTarget);
		when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(
				requestBuilder);
		when(requestBuilder.get()).thenReturn(response);
	}

	@Test
	public void testArgs() throws IOException {
		assertNotEquals(new Main().runMain(new String[] {}), 0);
		assertNotEquals(new Main().runMain(new String[] { "foo", "bar" }), 0);
	}

	@Test
	public void testSuggestionQuery() throws ErrorResponse {
		when(response.getStatus()).thenReturn(200);
		when(response.readEntity(any(ListOfPositions.class))).thenReturn(
				new ArrayList<Position>());
		assertEquals(new SuggestionRetriever(client).search("somePath").size(),
				0);
		verify(webTarget).path(eq("somePath"));
	}

	@Test(expected = ErrorResponse.class)
	public void testSuggestionException() throws ErrorResponse {
		when(response.getStatus()).thenReturn(400);
		when(response.readEntity(ErrorResponse.class)).thenReturn(
				new ErrorResponse());
		new SuggestionRetriever(client).search("");
	}

	// this test should probably be broken into smaller ones...
	@Test
	public void testCSVOutput() throws IOException {
		Position p = new Position();
		p.setId(12345);
		p.setName("SomeName");
		p.setTypeIdentifier("Position");
		p.setType("location");
		GeoPosition gp = new GeoPosition();
		gp.setLatitude(1.0);
		gp.setLongitude(2.0);
		p.setGeoPosition(gp);
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(p);

		Main main = new Main();
		main.setRetriever(new SuggestionRetriever(client));

		when(response.getStatus()).thenReturn(200);
		when(response.readEntity(any(ListOfPositions.class))).thenReturn(
				positions);
		PrintStream ps = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			System.setOut(new PrintStream(baos, true, "UTF-8"));
			main.processTerm("");
		} finally {
			System.setOut(ps);
		}
		String rv = new String(baos.toByteArray(), "UTF-8");
		String expected = "\"_type\",\"_id\",\"name\",\"type\",\"latitude\",\"longitude\""
				+ main.getCsvFormat().getRecordSeparator()
				+ "\"Position\",12345,\"SomeName\",\"location\",1.0,2.0"
				+ main.getCsvFormat().getRecordSeparator();
		assertEquals(expected, rv);
	}
}
