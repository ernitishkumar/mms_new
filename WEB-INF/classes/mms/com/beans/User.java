package mms.com.beans;

public class User {

	private String id;
	private String username;
	private String password;
	private String name;
	public User(String id, String username, String password, String name) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
	}
	public User(String username, String password, String name) {
		this.username = username;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
