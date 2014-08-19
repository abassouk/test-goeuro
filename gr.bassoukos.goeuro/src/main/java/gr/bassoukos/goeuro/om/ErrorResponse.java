package gr.bassoukos.goeuro.om;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;

/**
 * Error response from the server. Directly mapped to a java.lang.Exception
 * subclass, hence the XmlAccessType.NONE - need to explicitly map all fields.
 * 
 * @author abas
 */
@Generated("org.jsonschema2pojo")
public class ErrorResponse extends Exception {
	private static final long serialVersionUID = 2372069603642182878L;

	@Expose
	private String message;
	@Expose
	private String errorClass;
	@Expose
	private String description;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorClass() {
		return errorClass;
	}

	public void setErrorClass(String errorClass) {
		this.errorClass = errorClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}