package gr.bassoukos.goeuro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import gr.bassoukos.goeuro.ListResponseRetriever.Handler;
import gr.bassoukos.goeuro.om.ErrorResponse;
import gr.bassoukos.goeuro.om.GeoPosition;
import gr.bassoukos.goeuro.om.Position;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MainTests extends TestBase {
	@Test
	public void testArgs() throws IOException {
		assertNotEquals(new Main().runMain(new String[] {}), 0);
		assertNotEquals(new Main().runMain(new String[] { "foo", "bar" }), 0);
	}

	// this test should probably be broken into smaller ones...
	@SuppressWarnings("unchecked")
	@Test
	public void testCSVOutput() throws IOException, ErrorResponse {
		final Position p = new Position();
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

		// short-circuit the retriever and
		main.setRetriever(retriever = mock(SuggestionRetriever.class));
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Handler<Position> h = (Handler<Position>) invocation.getArguments()[1];
				h.handle(p);
				return null;
			}
		}).when(retriever).search(anyString(), any(Handler.class));

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
