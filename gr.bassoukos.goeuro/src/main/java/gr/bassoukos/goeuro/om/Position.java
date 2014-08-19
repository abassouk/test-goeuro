package gr.bassoukos.goeuro.om;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Models a JSON object, as returned from the server; some ambiguity exists
 * w.r.t. the actual types of some properties.
 * 
 * @author abas
 */
@Generated("org.jsonschema2pojo")
public class Position {
	@Expose
	@SerializedName("_type")
	private String typeIdentifier;

	@Expose
	@SerializedName("_id")
	private int id;

	// TODO: determine actual type.
	@Expose
	private String key;

	@Expose
	private String name;

	@Expose
	private String fullName;

	@SerializedName("iata_airport_code")
	@Expose
	private String iataAirportCode;

	@Expose
	private String type;

	@Expose
	private String country;

	@Expose
	@SerializedName("geo_position")
	private GeoPosition geoPosition;

	@Expose
	private Integer locationId;

	@Expose
	private boolean inEurope;

	@Expose
	private String countryCode;

	@Expose
	private boolean coreCountry;

	// TODO: determine actual type.
	@Expose
	private String distance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIataAirportCode() {
		return iataAirportCode;
	}

	public void setIataAirportCode(String iataAirportCode) {
		this.iataAirportCode = iataAirportCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public GeoPosition getGeoPosition() {
		return geoPosition;
	}

	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public boolean isInEurope() {
		return inEurope;
	}

	public void setInEurope(boolean inEurope) {
		this.inEurope = inEurope;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public boolean isCoreCountry() {
		return coreCountry;
	}

	public void setCoreCountry(boolean coreCountry) {
		this.coreCountry = coreCountry;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getTypeIdentifier() {
		return typeIdentifier;
	}

	public void setTypeIdentifier(String typeIdentifier) {
		this.typeIdentifier = typeIdentifier;
	}
}