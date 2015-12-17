package mms.com.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import mms.com.beans.UserLogin;
public class UserLoginMapper {

	public UserLogin userMapper(ResultSet resultSet) {
		UserLogin userlogin = new UserLogin();
		try {
			
			userlogin.setUid(resultSet.getString(1));
			userlogin.setUserLoginid(resultSet.getString(2));
			userlogin.setUserPassword(resultSet.getString(3));
			userlogin.setuType(resultSet.getString(4));
			userlogin.setuName(resultSet.getString(5));
			userlogin.setuLocation(resultSet.getString(6));
			userlogin.setStatus(resultSet.getString(7));
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [userMapper]"+e);
		}
		return userlogin;
	}

}
