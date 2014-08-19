package gr.bassoukos.goeuro.om;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;

/**
 * Holds the latitude and longitude of a position.
 * 
 * @author abas
 */
@Generated("org.jsonschema2pojo")
public class GeoPosition {
	@Expose
	private double latitude;

	@Expose
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