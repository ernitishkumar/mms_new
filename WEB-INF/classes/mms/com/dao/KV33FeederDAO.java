package mms.com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mms.com.utility.DatabaseConnection;
import mms.com.beans.KV33Feeder;
import java.util.ArrayList;
import java.sql.ResultSet;
import mms.com.beans.ErrorBean;
public class KV33FeederDAO {

	Connection connection = DatabaseConnection.getConnection("mms_new");
	public KV33Feeder addKV33Feeder(KV33Feeder kv33Feeder){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into KV33Feeder(code, name, location, region, circle, division, ehvss_id) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1,kv33Feeder.getCode());
			ps.setString(2,kv33Feeder.getName());
			ps.setString(3,"DUMMY");
			ps.setString(4,kv33Feeder.getRegion());
			ps.setString(5,kv33Feeder.getCircle());
			ps.setString(6,"DUMMY");
			ps.setString(7,kv33Feeder.getEhvssID());
			ps.executeUpdate();
			ResultSet resultSet=ps.executeQuery("select last_insert_id() as 'id'");
			resultSet.next();
			int id=resultSet.getInt("id");
			kv33Feeder.setId(String.valueOf(id));
			resultSet.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [addKV33Feeder]"+e);
		}
		return kv33Feeder;
	}

	public boolean addKV33Feeder(KV33Feeder kv33Feeder,ErrorBean errorBean){
		ArrayList<KV33Feeder> kv33Feeders=new ArrayList<KV33Feeder>();
		boolean added;
		try {
			kv33Feeders=getByCode(kv33Feeder.getCode());
			if(kv33Feeders==null || kv33Feeders.size()==0){
				PreparedStatement ps = connection.prepareStatement("insert into KV33Feeder(code, name, location, region, circle, division, ehvss_id) VALUES(?,?,?,?,?,?,?)");
				ps.setString(1,kv33Feeder.getCode());
				ps.setString(2,kv33Feeder.getName());
				ps.setString(3,"DUMMY");
				ps.setString(4,kv33Feeder.getRegion());
				ps.setString(5,kv33Feeder.getCircle());
				ps.setString(6,"DUMMY");
				ps.setString(7,kv33Feeder.getEhvssID());
				ps.executeUpdate();
				added=true;	
			}else{
				errorBean.setErrorMessage("33KV Feeder Code Already Exist. Please provide Different 33KV Feeder Code");
				added=false;
			}
		} catch (SQLException e) {
			added=false;
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}
		return added;
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

	public ArrayList<KV33Feeder> getAll(String startIndex,String pageSize) {
		ArrayList<KV33Feeder> kv33Feeders=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv33feeder limit "+startIndex+","+pageSize);
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
			System.out.println("Exception in class : KV33FeederDAO : method : [getAll(int,int)]"+e);
		}
		return kv33Feeders;
	}

	public int getKV33FeederCount() {
		int count=0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT count(*) as count FROM kv33feeder");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("count");
			}
			System.out.println("Count of 33KV Feeders :"+count);
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getKV33FeederCount]"+e);
		}
		return count;
	}

	public ArrayList<KV33Feeder> getByCode(String code) {
		ArrayList<KV33Feeder> kv33Feeders=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv33feeder where code=?");
			ps.setString(1,code);
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
			System.out.println("Number of 33KV Feeders  for code :"+code+" is : "+kv33Feeders.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getByCode]"+e);
		}
		return kv33Feeders;
	}

	public ArrayList<KV33Feeder> getByCircle(String circle) {
		ArrayList<KV33Feeder> kv33Feeders=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv33feeder where circle=?");
			ps.setString(1,circle);
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
			System.out.println("Number of 33KV Feeders  for circle :"+circle+" is : "+kv33Feeders.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getByCircle]"+e);
		}
		return kv33Feeders;
	}
	
	public KV33Feeder getBykv33FeederCode(String code){
		KV33Feeder kv33Feeder = new KV33Feeder();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM KV33Feeder where code=?");
			ps.setString(1, code);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				kv33Feeder.setId(String.valueOf(rs.getInt(1)));
				kv33Feeder.setName(rs.getString(3).trim());
				kv33Feeder.setCode(rs.getString(2).trim());
				kv33Feeder.setLocation(rs.getString(4).trim());
				kv33Feeder.setRegion(rs.getString(5).trim());
				kv33Feeder.setCircle(rs.getString(6).trim());
				kv33Feeder.setDivision(rs.getString(7).trim());
				kv33Feeder.setEhvssID(rs.getString(8).trim());
				System.out.println("");
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getBykv33FeederCode]"+e);
		}
		return kv33Feeder;
	}
}
