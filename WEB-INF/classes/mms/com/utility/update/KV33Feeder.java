
public class KV33Feeder {

	private String id;
	private String name;
	private String code;
	private String location;
	private String region;
	private String circle;
	private String division;
	private String ehvssID;
	public KV33Feeder(String id, String name, String code, String location,
			String region, String circle, String division, String ehvssID) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.location = location;
		this.region = region;
		this.circle = circle;
		this.division = division;
		this.ehvssID = ehvssID;
	}
	public KV33Feeder(String name, String code, String location, String region,
			String circle, String division, String ehvssID) {
		this.name = name;
		this.code = code;
		this.location = location;
		this.region = region;
		this.circle = circle;
		this.division = division;
		this.ehvssID = ehvssID;
	}
	public KV33Feeder(String name, String code,String region,
			String circle,String ehvssID) {
		this.name = name;
		this.code = code;
		this.region = region;
		this.circle = circle;
		this.ehvssID = ehvssID;
	}
	public KV33Feeder() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public String getEhvssID() {
		return ehvssID;
	}
	public void setEhvssID(String ehvssID) {
		this.ehvssID = ehvssID;
	}

	
	
}
