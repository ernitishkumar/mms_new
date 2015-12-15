package mms.com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import mms.com.utility.DatabaseConnection;
import mms.com.beans.Substation;

public class SubstationDAO {

	Connection connection = DatabaseConnection.getConnection("mms_new");
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

	public ArrayList<Substation> getAll() {
		ArrayList<Substation> substations=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM substation");
			ResultSet rs=ps.executeQuery();
			substations=new ArrayList<Substation>();
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
				substations.add(substation);
			}
			System.out.println("Number of Substations:"+substations.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : SubstationDAO : method : [getAll]"+e);
		}
		return substations;
	}
}
