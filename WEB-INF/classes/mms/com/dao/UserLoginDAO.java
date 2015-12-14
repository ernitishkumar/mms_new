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
		ArrayList<UserLogin> list = new ArrayList<UserLogin>();
		UserLoginMapper map = new UserLoginMapper();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_user_login");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				list.add(map.userMapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in class : UserLoginDAO  method :[getAll]"+e);
		} finally{
			
		}
		return list;
	}
	
	public UserLogin getByUserID(String userID){
		UserLogin userLogin = null;
		UserLoginMapper userLoginMapper = new UserLoginMapper();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_user_login where Uloginid=?");
			ps.setString(1, userID);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				userLogin = userLoginMapper.userMapper(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [getByUserID]"+e);
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
			System.out.println("Exception in [updateUser]"+e);
		}
	}
}
