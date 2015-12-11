package mms.com.beans;

public class ReportPoint33kvDesc {

	private String id; 
	private String point_id;
	private String point; 
	private String mt_desc; 
	private String report_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPoint_id() {
		return point_id;
	}
	public void setPoint_id(String point_id) {
		this.point_id = point_id;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getMt_desc() {
		return mt_desc;
	}
	public void setMt_desc(String mt_desc) {
		this.mt_desc = mt_desc;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public String toString() {
		return "ReportPoint33kvDesc [id=" + id + ", point_id=" + point_id
				+ ", point=" + point + ", mt_desc=" + mt_desc + ", report_id="
				+ report_id + "]";
	}
	public ReportPoint33kvDesc(String id, String point_id, String point,
			String mt_desc, String report_id) {
		
		this.id = id;
		this.point_id = point_id;
		this.point = point;
		this.mt_desc = mt_desc;
		this.report_id = report_id;
	}
	public ReportPoint33kvDesc(String point_id, String point, String mt_desc,
			String report_id) {
		
		this.point_id = point_id;
		this.point = point;
		this.mt_desc = mt_desc;
		this.report_id = report_id;
	}
	public ReportPoint33kvDesc() {
		
	}
	
}
