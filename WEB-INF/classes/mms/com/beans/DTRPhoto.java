package mms.com.beans;

public class DTRPhoto {

	private String reportId;
	
	private String photo;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String toString() {
		return "DTRPhoto [reportId=" + reportId + ", photo=" + photo + "]";
	}

	public DTRPhoto(String reportId, String photo) {
		this.reportId = reportId;
		this.photo = photo;
	}

	public DTRPhoto() {

	}

	public DTRPhoto(String photo) {
		this.photo = photo;
	}
	
	
}
