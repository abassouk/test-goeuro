package gr.bassoukos.goeuro;

import java.net.MalformedURLException;
import java.net.URL;

import gr.bassoukos.goeuro.om.Position;

/**
 * Position suggestion retriever. Specialized version of the
 * {@link ListResponseRetriever}.
 * 
 * @author abas
 */
public class SuggestionRetriever extends ListResponseRetriever<Position> {
	/**
	 * The default target URL.
	 */
	public static final String TARGET_API = "http://api.goeuro.com/api/v2/position/suggest/en/";

	// static initializer block is required to catch the probably-not-happening
	// MalformedURLException.
	private static final URL target;
	static {
		try {
			target = new URL(TARGET_API);
		} catch (MalformedURLException e) {
			// This should not happen... I know, famous last words.
			throw new RuntimeException(e);
		}
	}

	/**
	 * Creates a new suggestion retriever with default target search URL.
	 */
	public SuggestionRetriever() {
		super(Position.class, target);
	}
}
