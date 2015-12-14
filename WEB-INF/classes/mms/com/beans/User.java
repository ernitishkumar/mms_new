package mms.com.beans;

public class User {

	private String id;
	private String userName;
	private String password;
	private String name;
	public User(String id, String userName, String password, String name) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.name = name;
	}
	public User(String userName, String password, String name) {
		this.userName = userName;
		this.password = password;
		this.name = name;
	}
	public User() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
