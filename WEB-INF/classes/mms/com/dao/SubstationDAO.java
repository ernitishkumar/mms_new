package mms.com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import mms.com.utility.DatabaseConnection;
import mms.com.beans.Substation;
import mms.com.beans.ErrorBean;
public class SubstationDAO {

	private Connection connection = DatabaseConnection.getConnection("mms_new");
	public void addSubstation(Substation substation){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into substation(code, name, location, region, circle, division, dc, kv33feeder_id) VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1,substation.getCode());
			ps.setString(2,substation.getName());
			ps.setString(3,"DUMMY");
			ps.setString(4,substation.getRegion());
			ps.setString(5,substation.getCircle());
			ps.setString(6,substation.getDivision());
			ps.setString(7,"DUMMY");
			ps.setString(8,substation.getKv33FeederID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}
	}

	public boolean addSubstation(Substation substation,ErrorBean errorBean){
		ArrayList<Substation> substations=new ArrayList<Substation>();
		boolean added;
		try {
			substations=getByCode(substation.getCode());
			if(substations==null || substations.size()==0){
			PreparedStatement ps = connection.prepareStatement("insert into substation(code, name, location, region, circle, division, dc, kv33feeder_id) VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1,substation.getCode());
			ps.setString(2,substation.getName());
			ps.setString(3,"DUMMY");
			ps.setString(4,substation.getRegion());
			ps.setString(5,substation.getCircle());
			ps.setString(6,substation.getDivision());
			ps.setString(7,"DUMMY");
			ps.setString(8,substation.getKv33FeederID());
			ps.executeUpdate();
			added=true;	
			}else{
				errorBean.setErrorMessage("Substation Code Already Exist. Please provide Different Substation Code");
				added=false;
			}
		} catch (SQLException e) {
			added=false;
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}
		return added;
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

	public ArrayList<Substation> getByCode(String code) {
		ArrayList<Substation> substations=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM substation where code=?");
			ps.setString(1,code);
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
			System.out.println("Number of Substations for code : "+code+" are :"+substations.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : SubstationDAO : method : [getByCode]"+e);
		}
		return substations;
	}

	public ArrayList<Substation> getByDivision(String division) {
		ArrayList<Substation> substations=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM substation where division=?");
			ps.setString(1,division);
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
			System.out.println("Number of Substations for division : "+division+" are :"+substations.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : SubstationDAO : method : [getByCode]"+e);
		}
		return substations;
	}

	public ArrayList<Substation> getByCircle(String circle) {
		ArrayList<Substation> substations=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM substation where circle=?");
			ps.setString(1,circle);
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
			System.out.println("Number of Substations for circle : "+circle+" are :"+substations.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : SubstationDAO : method : [getByCircle]"+e);
		}
		return substations;
	}

	public ArrayList<Substation> getByRegion(String region) {
		ArrayList<Substation> substations=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM substation where region=?");
			ps.setString(1,region);
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
			System.out.println("Number of Substations for region : "+region+" are :"+substations.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : SubstationDAO : method : [getByRegion]"+e);
		}
		return substations;
	}
	
	public Substation getBySubstationCode(String code){
		Substation substation = new Substation();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM substation where code=?");
			ps.setString(1, code);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				substation.setId(String.valueOf(rs.getInt(1)));
				substation.setName(rs.getString(3).trim());
				substation.setCode(rs.getString(2).trim());
				substation.setLocation(rs.getString(4).trim());
				substation.setRegion(rs.getString(5).trim());
				substation.setCircle(rs.getString(6).trim());
				substation.setDivision(rs.getString(7).trim());
				substation.setDc(rs.getString(8).trim());
				substation.setKv33FeederID(rs.getString(9).trim());
				System.out.println("");
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getBykv33FeederCode]"+e);
		}
		return substation;
	}
}
