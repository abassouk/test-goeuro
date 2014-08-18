package gr.bassoukos.goeuro.om;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Models a JSON object, as returned from the server; some ambiguity exists
 * w.r.t. the actual types of some properties.
 * 
 * @author abas
 */
@XmlRootElement
@Generated("org.jsonschema2pojo")
public class Position {
	@XmlAttribute(name = "_type")
	private String typeIdentifier;

	@XmlAttribute(name = "_id")
	private int id;

	// TODO: determine actual type.
	@XmlAttribute(name = "key")
	private String key;

	@XmlAttribute
	private String name;

	@XmlAttribute
	private String fullName;

	@XmlAttribute(name = "iata_airport_code")
	private String iataAirportCode;

	@XmlAttribute
	private String type;

	@XmlAttribute
	private String country;

	@XmlElement(name = "geo_position")
	private GeoPosition geoPosition;

	@XmlAttribute
	private Integer locationId;

	@XmlAttribute
	private boolean inEurope;

	@XmlAttribute
	private String countryCode;

	@XmlAttribute
	private boolean coreCountry;

	// TODO: determine actual type.
	@XmlAttribute
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