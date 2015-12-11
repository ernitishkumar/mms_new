package mms.com.beans;

public class Photo11kv {

	private String photo;
	private String reportid;
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getReportid() {
		return reportid;
	}
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	public String toString() {
		return "Photo11kv [photo=" + photo + ", reportid=" + reportid + "]";
	}
	public Photo11kv(String photo, String reportid) {
		
		this.photo = photo;
		this.reportid = reportid;
	}
	public Photo11kv() {
		
	}
	public Photo11kv(String photo) {
		super();
		this.photo = photo;
	}
	
}
