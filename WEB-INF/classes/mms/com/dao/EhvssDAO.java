package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mms.com.utility.DatabaseConnection;
import mms.com.beans.EHVSS;

public class EhvssDAO {

	Connection connection = DatabaseConnection.getConnection("mms_new");
	public void addEHVSS(EHVSS ehvss){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into EHVSS(code, name, location, region, circle, division) VALUES(?,?,?,?,?,?)");
			ps.setString(1,ehvss.getCode());
			ps.setString(2,ehvss.getName());
			ps.setString(3,ehvss.getLocation());
			ps.setString(4,ehvss.getRegion());
			ps.setString(5,ehvss.getCircle());
			ps.setString(6,ehvss.getDivision());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}
	}
}
