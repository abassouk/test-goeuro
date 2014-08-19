package gr.bassoukos.goeuro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gr.bassoukos.goeuro.om.ErrorResponse;
import gr.bassoukos.goeuro.om.Position;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class RetrieverTests extends TestBase {
	// one sample object.
	private static final String SAMPLE_OBJECT = "{\"_type\":\"Position\",\"_id\":376460,\"key\":\"someKey\",\"name\":\"Frankfurt am Main\","
			+ "\"fullName\":\"Frankfurt am Main, Germany\",\"iata_airport_code\":\"someAirport\","
			+ "\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":"
			+ "{\"latitude\":50.11552,\"longitude\":8.68417},\"locationId\":8629,"
			+ "\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,"
			+ "\"distance\":\"someDistance\"}";

	@Test
	public void emptyResult() throws ErrorResponse, IOException {
		remoteRespondsWith("[]");
		assertEquals(retriever.search("somePath").size(), 0);
	}

	@Test(expected = ErrorResponse.class)
	public void testSuggestionError() throws ErrorResponse, IOException {
		remoteRespondsWith("{\"message\":\"400: Bad Request\",\"errorClass\":\"TypeMismatchException\","
				+ "\"description\":\"Could not mold value to type Long\"}");
		retriever.search("");
	}

	@Test
	public void singleResult() throws ErrorResponse, IOException {
		remoteRespondsWith("[" + SAMPLE_OBJECT + "]");
		List<Position> results = retriever.search("somePath");
		assertEquals(results.size(), 1);
		Position p = results.get(0);
		assertNotNull(p);
		assertEquals(p.getTypeIdentifier(), "Position");
		assertEquals(p.getId(), 376460);
		assertEquals(p.getKey(), "someKey");
		assertEquals(p.getName(), "Frankfurt am Main");
		assertEquals(p.getFullName(), "Frankfurt am Main, Germany");
		assertEquals(p.getIataAirportCode(), "someAirport");
		assertEquals(p.getType(), "location");
		assertEquals(p.getCountry(), "Germany");
		assertNotNull(p.getGeoPosition());
		assertEquals(p.getGeoPosition().getLatitude(), 50.11552, 0.000005);
		assertEquals(p.getGeoPosition().getLongitude(), 8.68417, 0.000005);
		assertEquals(p.getLocationId(), Integer.valueOf(8629));
		assertEquals(p.isInEurope(), true);
		assertEquals(p.isCoreCountry(), true);
		assertEquals(p.getCountryCode(), "DE");
		assertEquals(p.getDistance(), "someDistance");
	}

	@Test
	public void multiResult() throws ErrorResponse, IOException {
		remoteRespondsWith("[" + SAMPLE_OBJECT + "," + SAMPLE_OBJECT + "]");
		assertEquals(retriever.search("somePath").size(), 2);
	}
}
