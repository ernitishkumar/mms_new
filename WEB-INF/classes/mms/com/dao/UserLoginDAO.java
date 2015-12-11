package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.UserLogin;
import mms.com.mapper.UserLoginMapper;
import mms.com.utility.DatabaseConnection;

public class UserLoginDAO {
	Connection connection = DatabaseConnection.getConnection();
	public ArrayList<UserLogin> getAll(){
		ArrayList<UserLogin> users = new ArrayList<UserLogin>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_user_login");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				users.add(new UserLogin(resultSet.getString(1).trim(),resultSet.getString(2).trim(),resultSet.getString(3).trim(),resultSet.getString(4).trim(),resultSet.getString(5).trim(),resultSet.getString(6).trim(),resultSet.getString(7).trim()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
		}
		return users;
		
	}
	
	public UserLogin getByUserID(String userID){
		UserLogin userLogin = null;
		UserLoginMapper userLoginMapper = new UserLoginMapper();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_user_login where Uloginid=?");
			ps.setString(1, userID);
			ResultSet resultSet = ps.executeQuery();
			userLogin = userLoginMapper.userMapper(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
		}
		
		return userLogin;
	}
	
	public void updateUser(UserLogin userlogin){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into mms_user_login(UID,Uloginid,Upassword,Utype,Uname,Ulocation,status) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1,userlogin.getUid());
			ps.setString(2,userlogin.getUserLoginid());
			ps.setString(3,userlogin.getUserPassword());
			ps.setString(4,userlogin.getuType());
			ps.setString(5,userlogin.getuName());
			ps.setString(6,userlogin.getuLocation());
			ps.setString(7,userlogin.getStatus());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
