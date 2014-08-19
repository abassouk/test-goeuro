package gr.bassoukos.goeuro;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import gr.bassoukos.goeuro.om.Position;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import org.junit.Before;

public abstract class TestBase {
	protected URL targetURL;

	private static URLConnection connection;

	protected ListResponseRetriever<Position> retriever;

	protected Main main;

	public static class TestFactoryImpl extends URLStreamHandler {
		@Override
		protected URLConnection openConnection(URL u) throws IOException {
			return connection;
		}
	}

	@Before
	public void setup() throws MalformedURLException {
		connection = mock(URLConnection.class);
		targetURL = new URL(null, "test://", new TestFactoryImpl());
		retriever = new SuggestionRetriever().targeting(targetURL);
		main = new Main();
		main.setRetriever(retriever);
	}

	protected void remoteRespondsWith(String response) throws IOException {
		when(connection.getInputStream()).thenReturn(
				new ByteArrayInputStream(response.getBytes("UTF-8")));
	}
}
