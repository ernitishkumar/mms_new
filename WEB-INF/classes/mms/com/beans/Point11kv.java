package mms.com.beans;

public class Point11kv {

	private String id;
	
	private String point;
	
	private String cat1;
	
	private String cat2;
	
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getCat1() {
		return cat1;
	}

	public void setCat1(String cat1) {
		this.cat1 = cat1;
	}

	public String getCat2() {
		return cat2;
	}

	public void setCat2(String cat2) {
		this.cat2 = cat2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return "Point11kv [ID=" + id + ", point=" + point + ", cat1=" + cat1
				+ ", cat2=" + cat2 + ", status=" + status + "]";
	}

	public Point11kv(String iD, String point, String cat1, String cat2,
			String status) {
		id = iD;
		this.point = point;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.status = status;
	}

	public Point11kv(String point, String cat1, String cat2, String status) {
		this.point = point;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.status = status;
	}

	public Point11kv() {
	}
	
	
}
