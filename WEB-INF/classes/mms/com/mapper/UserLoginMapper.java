package mms.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mms.com.beans.UserLogin;

public class UserLoginMapper {

	public UserLogin userMapper(ResultSet resultSet) {
		UserLogin userlogin = null;
		int i = 1;
		try {
			while(resultSet.next()){
				userlogin=new UserLogin();
				userlogin.setUid(resultSet.getString(1).trim());
				userlogin.setUserLoginid(resultSet.getString(2).trim());
				userlogin.setUserPassword(resultSet.getString(3).trim());
				userlogin.setuType(resultSet.getString(4).trim());
				userlogin.setuName(resultSet.getString(5).trim());
				userlogin.setuLocation(resultSet.getString(6).trim());
				userlogin.setStatus(resultSet.getString(7).trim());
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return userlogin;
	}

}
