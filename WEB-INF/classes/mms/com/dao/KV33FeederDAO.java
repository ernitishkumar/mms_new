package mms.com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mms.com.utility.DatabaseConnection;
import mms.com.beans.KV33Feeder;
import java.util.ArrayList;
import java.sql.ResultSet;
public class KV33FeederDAO {

	Connection connection = DatabaseConnection.getConnection("mms_new");
	public void addKV33Feeder(KV33Feeder kv33Feeder){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into KV33Feeder(code, name, location, region, circle, division, ehvss_id) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1,kv33Feeder.getCode());
			ps.setString(2,kv33Feeder.getName());
			ps.setString(3,kv33Feeder.getLocation());
			ps.setString(4,kv33Feeder.getRegion());
			ps.setString(5,kv33Feeder.getCircle());
			ps.setString(6,kv33Feeder.getDivision());
			ps.setString(7,kv33Feeder.getEhvssID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}
	}
	public ArrayList<KV33Feeder> getAll() {
		ArrayList<KV33Feeder> kv33Feeders=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv33feeder");
			ResultSet rs=ps.executeQuery();
			kv33Feeders=new ArrayList<KV33Feeder>();
			while(rs.next()){
				KV33Feeder kv33Feeder = new KV33Feeder();
				kv33Feeder.setId(String.valueOf(rs.getInt(1)));
				kv33Feeder.setName(rs.getString(3).trim());
				kv33Feeder.setCode(rs.getString(2).trim());
				kv33Feeder.setLocation(rs.getString(4).trim());
				kv33Feeder.setRegion(rs.getString(5).trim());
				kv33Feeder.setCircle(rs.getString(6).trim());
				kv33Feeder.setDivision(rs.getString(7).trim());
				kv33Feeder.setEhvssID(rs.getString(8).trim());
				kv33Feeders.add(kv33Feeder);
			}
			System.out.println("Number of 33KV Feeders :"+kv33Feeders.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getAll]"+e);
		}
		return kv33Feeders;
	}
}
