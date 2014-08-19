package gr.bassoukos.goeuro;

import gr.bassoukos.goeuro.ListResponseRetriever.Handler;
import gr.bassoukos.goeuro.om.ErrorResponse;
import gr.bassoukos.goeuro.om.GeoPosition;
import gr.bassoukos.goeuro.om.Position;

import java.io.Closeable;
import java.io.IOException;

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
	/**
	 * 
	 * @author abas
	 */
	public static class PositionHandler implements Handler<Position>, Closeable {
		private CSVPrinter csv;
		private CSVFormat format;
		private Appendable out;

		public PositionHandler(CSVFormat csvFormat, Appendable out) {
			this.format = csvFormat;
			this.out = out;
		}

		// handle a retrieved position: print it into CSV.
		public void handle(Position p) throws IOException {
			ensureStarted();
			// Q: Can it ever be null?
			GeoPosition gp = p.getGeoPosition();
			csv.printRecord(p.getTypeIdentifier(), p.getId(), p.getName(),
					p.getType(), gp == null ? null : gp.getLatitude(),
					gp == null ? null : gp.getLongitude());
		}

		/**
		 * Ensure that the CSV printer has started; if the format specifies
		 * headers, they will be printed when it starts.
		 * 
		 * @throws IOException
		 */
		public void ensureStarted() throws IOException {
			// lazily initialize the printer
			if (csv == null)
				csv = format.print(out);
		}

		/**
		 * Close the printer if it's been started.
		 */
		@Override
		public void close() throws IOException {
			if (csv != null) {
				csv.close();
				csv = null;
			}
		}
	}

	// The CSV format used to print.
	private CSVFormat csvFormat = CSVFormat.EXCEL
			.withQuoteMode(QuoteMode.NON_NUMERIC)
			.withNullString(null)
			.withHeader("_type", "_id", "name", "type", "latitude", "longitude");

	private ListResponseRetriever<Position> retriever;

	public CSVFormat getCsvFormat() {
		return csvFormat;
	}

	public void setCsvFormat(CSVFormat csvFormat) {
		this.csvFormat = csvFormat;
	}

	public ListResponseRetriever<Position> getRetriever() {
		return retriever;
	}

	public void setRetriever(ListResponseRetriever<Position> retriever) {
		this.retriever = retriever;
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
	 * Connects to the server and for each response retrieved, print a new line
	 * in the CSV
	 * 
	 * @param what
	 *            the search term to use on the server.
	 * @return 0 on success, 1 on failure.
	 */
	public int processTerm(String what) {
		// lazily initialize the actual retriever.
		if (retriever == null)
			retriever = new SuggestionRetriever();

		try (PositionHandler handler = new PositionHandler(csvFormat,
				System.out)) {
			retriever.search(what, handler);
			// ensure that if configured, a header gets printed even if the
			// empty array has been received.
			handler.ensureStarted();
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
	 * Main method. Handled this way to ensure that a) class is testable b) exit
	 * code is set at all times.
	 * 
	 * @param args
	 *            the program arguments
	 */
	public static void main(String[] args) {
		System.exit(new Main().runMain(args));
	}
}
