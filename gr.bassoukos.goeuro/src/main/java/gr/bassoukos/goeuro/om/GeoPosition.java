package gr.bassoukos.goeuro.om;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Holds the latitude and longitude of a position.
 * 
 * @author abas
 */
@Generated("org.jsonschema2pojo")
public class GeoPosition {
	@XmlAttribute
	private double latitude;

	@XmlAttribute
	private double longitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}