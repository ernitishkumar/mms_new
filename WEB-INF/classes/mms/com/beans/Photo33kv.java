package mms.com.beans;

public class Photo33kv {

	private String reportid; 
	private String photo;
	public String getReportid() {
		return reportid;
	}
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String toString() {
		return "Photo33kv [reportid=" + reportid + ", photo=" + photo + "]";
	}
	public Photo33kv(String reportid, String photo) {
		
		this.reportid = reportid;
		this.photo = photo;
	}
	public Photo33kv(String photo) {
		
		this.photo = photo;
	}
	public Photo33kv() {
		
	}
	
}
