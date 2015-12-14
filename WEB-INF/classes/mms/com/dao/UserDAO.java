package feeder.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mms.com.utility.DatabaseConnection;
import feeder.com.beans.User;

public class UserDAO {

	Connection connection = DatabaseConnection.getConnection();
	public void addUser(User user){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into user(username, password, name) VALUES(?,?,?)");
			ps.setString(1,user.getUserName());
			ps.setString(2,user.getPassword());
			ps.setString(3,user.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}
	}
	
	public User getByUserName(String userName){
		User user = new User();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from user where Username=?");
			ps.setString(1, userName);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				user.setUserName(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setName(resultSet.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [getByUserID]"+e);
		} finally{	
		}
		return user;
	}
}
