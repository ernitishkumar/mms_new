package mms.com.beans;

public class Location {

	private String sno;
	
	private String locationCode;
	
	private String region;
	
	private String circle;
	
	private String division;
	
	private String dc;

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String toString() {
		return "Location [sno=" + sno + ", locationCode=" + locationCode
				+ ", region=" + region + ", circle=" + circle + ", division="
				+ division + ", dc=" + dc + "]";
	}

	public Location(String sno, String locationCode, String region,
			String circle, String division, String dc) {
		this.sno = sno;
		this.locationCode = locationCode;
		this.region = region;
		this.circle = circle;
		this.division = division;
		this.dc = dc;
	}

	public Location(String locationCode, String region, String circle,
			String division, String dc) {
		this.locationCode = locationCode;
		this.region = region;
		this.circle = circle;
		this.division = division;
		this.dc = dc;
	}

	public Location() {

	}
		
}
