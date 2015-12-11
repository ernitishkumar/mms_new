package mms.com.beans;

public class UserLogin {

	private String uid; 
	private String uderLoginid; 
	private String userPassword; 
	private String uType; 
	private String uName; 
	private String uLocation; 
	private String status;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUderLoginid() {
		return uderLoginid;
	}
	public void setUderLoginid(String uderLoginid) {
		this.uderLoginid = uderLoginid;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getuType() {
		return uType;
	}
	public void setuType(String uType) {
		this.uType = uType;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuLocation() {
		return uLocation;
	}
	public void setuLocation(String uLocation) {
		this.uLocation = uLocation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String toString() {
		return "UserLogin [uid=" + uid + ", uderLoginid=" + uderLoginid
				+ ", userPassword=" + userPassword + ", uType=" + uType
				+ ", uName=" + uName + ", uLocation=" + uLocation + ", status="
				+ status + "]";
	}
	public UserLogin(String uid, String uderLoginid, String userPassword,
			String uType, String uName, String uLocation, String status) {
		
		this.uid = uid;
		this.uderLoginid = uderLoginid;
		this.userPassword = userPassword;
		this.uType = uType;
		this.uName = uName;
		this.uLocation = uLocation;
		this.status = status;
	}
	public UserLogin(String uderLoginid, String userPassword, String uType,
			String uName, String uLocation, String status) {
		
		this.uderLoginid = uderLoginid;
		this.userPassword = userPassword;
		this.uType = uType;
		this.uName = uName;
		this.uLocation = uLocation;
		this.status = status;
	}
	public UserLogin() {
		
	}
	
	
}
