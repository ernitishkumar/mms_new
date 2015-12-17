package mms.com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mms.com.utility.DatabaseConnection;
import mms.com.beans.User;
public class UserDAO {

	Connection connection = DatabaseConnection.getConnection("mms_new");
	public void addUser(User user){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into user(username, password, name) VALUES(?,?,?)");
			ps.setString(1,user.getUsername());
			ps.setString(2,user.getPassword());
			ps.setString(3,user.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}
	}
	
	public User getByUsername(String userName){
		User user = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from user where username=?");
			ps.setString(1, userName);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				user=new User();
				user.setUsername(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setName(resultSet.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in class : UserDAO : method : [getByUsername]"+e);
		} finally{	
		}
		return user;
	}
}
