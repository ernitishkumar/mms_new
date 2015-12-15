package mms.com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import mms.com.utility.DatabaseConnection;
import mms.com.beans.EHVSS;
import java.util.ArrayList;

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
			System.out.println("Exception in [updateUser]"+e);
		}
	}

	public ArrayList<String> get(){
		ArrayList<String> ehvssNames=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT name FROM ehvss");
			ResultSet rs=ps.executeQuery();
			ehvssNames=new ArrayList<String>();
			while(rs.next()){
				ehvssNames.add(rs.getString(1).trim());
			}
			System.out.println("Number of Ehvss Locations :"+ehvssNames.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [get]"+e);
		}
		return ehvssNames;
	}

	public ArrayList<EHVSS> getAll(){
		ArrayList<EHVSS> ehvssNames=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss");
			ResultSet rs=ps.executeQuery();
			ehvssNames=new ArrayList<EHVSS>();
			while(rs.next()){
				EHVSS ehvss=new EHVSS();
				ehvss.setId(String.valueOf(rs.getInt(1)));
				ehvss.setName(rs.getString(3).trim());
				ehvss.setCode(rs.getString(2).trim());
				ehvss.setLocation(rs.getString(4).trim());
				ehvss.setRegion(rs.getString(5).trim());
				ehvss.setCircle(rs.getString(6).trim());
				ehvss.setDivision(rs.getString(7).trim());
				ehvssNames.add(ehvss);
			}
			System.out.println("Number of Ehvss Locations :"+ehvssNames.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [get]"+e);
		}
		return ehvssNames;
	}
}
