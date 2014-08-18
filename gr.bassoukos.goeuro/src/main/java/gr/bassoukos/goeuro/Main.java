package gr.bassoukos.goeuro;

import gr.bassoukos.goeuro.om.ErrorResponse;
import gr.bassoukos.goeuro.om.GeoPosition;
import gr.bassoukos.goeuro.om.Position;

import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

/**
 * Main class that performs argument handling and printing. VM exit code
 * indicates success/failure; on failure nothing is printed on {@link System.out).
 * <p>
 * One could argue that this class does not obey the single responsibility principle...
 * 
 * @author abas
 */
public class Main {
	private CSVFormat csvFormat = CSVFormat.EXCEL
			.withQuoteMode(QuoteMode.NON_NUMERIC)
			.withNullString(null)
			.withHeader("_type", "_id", "name", "type", "latitude", "longitude");

	private SuggestionRetriever retriever;

	public CSVFormat getCsvFormat() {
		return csvFormat;
	}

	public void setCsvFormat(CSVFormat csvFormat) {
		this.csvFormat = csvFormat;
	}

	public SuggestionRetriever getRetriever() {
		return retriever;
	}

	public void setRetriever(SuggestionRetriever retriever) {
		this.retriever = retriever;
	}

	/**
	 * Write CSV file for the give positions to the given appendable.
	 * 
	 * @param positions
	 *            the list of positions that should be written.
	 * @param out
	 *            where the CSV should be written.
	 * @throws IOException
	 */
	public void writeCSV(List<Position> positions, Appendable out)
			throws IOException {
		CSVPrinter csv = csvFormat.print(out);
		for (Position p : positions) {
			GeoPosition gp = p.getGeoPosition();
			csv.printRecord(p.getTypeIdentifier(), p.getId(), p.getName(),
					p.getType(), gp == null ? null : gp.getLatitude(),
					gp == null ? null : gp.getLongitude());
		}
		csv.close();
	}

	/**
	 * Show the user the details of the error that the server responded with.
	 * 
	 * @param resp
	 *            the error response from the server.
	 */
	public void showErrorResponse(ErrorResponse resp) {
		System.err.println("Got error response from server: ");
		System.err.println(" error class: " + resp.getErrorClass());
		System.err.println("     message: " + resp.getMessage());
		System.err.println(" description: " + resp.getDescription());
	}

	/**
	 * Show the user the usage of the current program.
	 * 
	 * @param args
	 */
	public void showUsage(String[] args) {
		System.err.println("Incorrect usage: application needs "
				+ "exactly one parameter, you supplied " + args.length);
	}

	/**
	 * Perform the whole request/output process. Check for exceptions, handle
	 * them.
	 * 
	 * @param what
	 *            the search term to use on the server.
	 * @return 0 on success, 1 on failure.
	 * @throws IOException
	 *             if it can't write to System.out.
	 */
	public int processTerm(String what) {
		if (retriever == null)
			retriever = new SuggestionRetriever();
		try {
			List<Position> result = retriever.search(what);
			writeCSV(result, System.out);
			return 0;
		} catch (ErrorResponse e) {
			showErrorResponse(e);
			return 1;
		} catch (IOException e) {
			System.err.println("Cannot write to standard output.");
			return 1;
		}
	}

	/**
	 * Check parameter validity.
	 * 
	 * @param args
	 *            the program arguments
	 * @return 0 on success, non-0 on failure.
	 */
	public int runMain(String[] args) {
		if (args.length != 1) {
			showUsage(args);
			return 1;
		}
		return processTerm(args[0]);
	}

	/**
	 * Main method. Hndled this way to ensure that a) class is testable b) exit
	 * code is set at all times.
	 * 
	 * @param args
	 *            the program arguments
	 */
	public static void main(String[] args) {
		System.exit(new Main().runMain(args));
	}
}
