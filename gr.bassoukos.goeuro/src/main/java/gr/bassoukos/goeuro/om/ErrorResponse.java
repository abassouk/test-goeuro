package gr.bassoukos.goeuro.om;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Error response from the server. Directly mapped to a java.lang.Exception
 * subclass, hence the XmlAccessType.NONE - need to explicitly map all fields.
 * 
 * @author abas
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Generated("org.jsonschema2pojo")
public class ErrorResponse extends Exception {
	private static final long serialVersionUID = 2372069603642182878L;

	private String message;
	private String errorClass;
	private String description;

	@XmlAttribute
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlAttribute
	public String getErrorClass() {
		return errorClass;
	}

	public void setErrorClass(String errorClass) {
		this.errorClass = errorClass;
	}

	@XmlAttribute
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}