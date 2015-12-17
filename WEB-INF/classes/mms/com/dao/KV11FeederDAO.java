package mms.com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import mms.com.utility.DatabaseConnection;
import mms.com.beans.KV11Feeder;
import mms.com.beans.ErrorBean;
import java.util.ArrayList;
public class KV11FeederDAO {

	private Connection connection = DatabaseConnection.getConnection("mms_new");
	public void add11KVFeeder(KV11Feeder kv11Feeder){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into KV11FEEDER(code, name, location, region, circle, division, dc, substation_id,feeder_type) VALUES(?,?,?,?,?,?,?,?,?)");
			ps.setString(1,kv11Feeder.getCode());
			ps.setString(2,kv11Feeder.getName());
			ps.setString(3,"DUMMY");
			ps.setString(4,kv11Feeder.getRegion());
			ps.setString(5,kv11Feeder.getCircle());
			ps.setString(6,kv11Feeder.getDivision());
			ps.setString(7,kv11Feeder.getDc());
			ps.setString(8,kv11Feeder.getSubstationID());
			ps.setString(9,kv11Feeder.getFeederType());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}
	}

	public boolean add11KVFeeder(KV11Feeder kv11Feeder,ErrorBean errorBean){
		ArrayList<KV11Feeder> kv11Feeders=new ArrayList<KV11Feeder> ();
		boolean added;
		try {
			kv11Feeders=getByCode(kv11Feeder.getCode());
			if(kv11Feeders==null || kv11Feeders.size()==0){
				PreparedStatement ps = connection.prepareStatement("insert into KV11FEEDER(code, name, location, region, circle, division, dc, substation_id,feeder_type) VALUES(?,?,?,?,?,?,?,?,?)");
				ps.setString(1,kv11Feeder.getCode());
				ps.setString(2,kv11Feeder.getName());
				ps.setString(3,"DUMMY");
				ps.setString(4,kv11Feeder.getRegion());
				ps.setString(5,kv11Feeder.getCircle());
				ps.setString(6,kv11Feeder.getDivision());
				ps.setString(7,kv11Feeder.getDc());
				ps.setString(8,kv11Feeder.getSubstationID());
				ps.setString(9,kv11Feeder.getFeederType());
				ps.executeUpdate();	
				added=true;
			}else{
				errorBean.setErrorMessage("11KV Feeder Code Already Exist. Please provide Different 11KV Feeder Code");
				added=false;
			}
		} catch (SQLException e) {
			added=false;
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}
		return added;
	}

	public ArrayList<KV11Feeder> getByCode(String code){
		System.out.println("Get Feeder by code started for code : "+code);

		ArrayList<KV11Feeder> kv11Feeders=null;
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv11feeder where code=?");
			ps.setString(1,code);
			ResultSet rs=ps.executeQuery();
			kv11Feeders=new ArrayList<KV11Feeder> ();
			while(rs.next()){
				KV11Feeder kv11Feeder = new KV11Feeder();
				kv11Feeder.setId(String.valueOf(rs.getInt(1)));
				kv11Feeder.setName(rs.getString(3).trim());
				kv11Feeder.setCode(rs.getString(2).trim());
				kv11Feeder.setLocation(rs.getString(4).trim());
				kv11Feeder.setRegion(rs.getString(5).trim());
				kv11Feeder.setCircle(rs.getString(6).trim());
				kv11Feeder.setDivision(rs.getString(7).trim());
				kv11Feeder.setDc(rs.getString(8).trim());
				kv11Feeder.setSubstationID(rs.getString(9).trim());
				kv11Feeder.setFeederType(rs.getString(10).trim());
				kv11Feeders.add(kv11Feeder);
			}
			System.out.println("Number of 11 KV Feeder for code : "+code+" are :"+kv11Feeders.size());
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Exception in class : KV11FeederDAO : method :getByCode()"+e);
		}
		return kv11Feeders;
	}
}
