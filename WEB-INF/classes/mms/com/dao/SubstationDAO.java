package feeder.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mms.com.utility.DatabaseConnection;
import feeder.com.beans.Substation;

public class SubstationDAO {

	Connection connection = DatabaseConnection.getConnection();
	public void addSubstation(Substation substation){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into substation(code, name, location, region, circle, division, dc, kv33feeder_id) VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1,substation.getCode());
			ps.setString(2,substation.getName());
			ps.setString(3,substation.getLocation());
			ps.setString(4,substation.getRegion());
			ps.setString(5,substation.getCircle());
			ps.setString(6,substation.getDivision());
			ps.setString(7,substation.getDc());
			ps.setString(8,substation.getKv33FeederID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}
	}
}
