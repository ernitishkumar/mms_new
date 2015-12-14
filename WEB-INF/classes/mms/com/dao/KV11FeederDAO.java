package feeder.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mms.com.utility.DatabaseConnection;
import feeder.com.beans.KV11Feeder;

public class KV11FeederDAO {

	Connection connection = DatabaseConnection.getConnection();
	public void add11KVFeeder(KV11Feeder kv11Feeder){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into KV11FEEDER(code, name, location, region, circle, division, dc, substation_id) VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1,kv11Feeder.getCode());
			ps.setString(2,kv11Feeder.getName());
			ps.setString(3,kv11Feeder.getLocation());
			ps.setString(4,kv11Feeder.getRegion());
			ps.setString(5,kv11Feeder.getCircle());
			ps.setString(6,kv11Feeder.getDivision());
			ps.setString(7,kv11Feeder.getDc());
			ps.setString(8,kv11Feeder.getSubstationID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}
	}
}
