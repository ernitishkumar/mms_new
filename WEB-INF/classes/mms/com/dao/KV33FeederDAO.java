package mms.com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mms.com.utility.DatabaseConnection;
import mms.com.beans.KV33Feeder;
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
	public ArrayList<Substation> getAll() {
		ArrayList<Substation> substationNames=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM substation");
			ResultSet rs=ps.executeQuery();
			substationNames=new ArrayList<Substation>();
			while(rs.next()){
				Substation substation = new Substation();
				substation.setId(String.valueOf(rs.getInt(1)));
				substation.setName(rs.getString(3).trim());
				substation.setCode(rs.getString(2).trim());
				substation.setLocation(rs.getString(4).trim());
				substation.setRegion(rs.getString(5).trim());
				substation.setCircle(rs.getString(6).trim());
				substation.setDivision(rs.getString(7).trim());
				substation.setDc(rs.getString(8).trim());
				substation.setKv33FeederID(rs.getString(9).trim());
				substationNames.add(substation);
			}
			System.out.println("Number of substation Locations :"+substationNames.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [get]"+e);
		}
		return substationNames;
	}
}
