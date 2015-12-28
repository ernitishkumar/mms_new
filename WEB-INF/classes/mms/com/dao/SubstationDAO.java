package mms.com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import mms.com.utility.DatabaseConnection;
import mms.com.beans.Substation;
import mms.com.beans.ErrorBean;
import mms.com.dao.KV11FeederDAO;
import mms.com.dao.KV33FeederDAO;
import mms.com.beans.KV11Feeder;
import mms.com.beans.KV33Feeder;
public class SubstationDAO {
	private Connection connection = DatabaseConnection.getConnection("mms_new");
	public Substation addSubstation(Substation substation){
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
			ResultSet resultSet=ps.executeQuery("select last_insert_id() as 'id'");
			resultSet.next();
			int id=resultSet.getInt("id");
			substation.setId(String.valueOf(id));
			resultSet.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [addSubstation]"+e);
		}
		return substation;
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

	public Substation updateSubstation(Substation substation){
		try {
			PreparedStatement ps = connection.prepareStatement("update substation set code=?,name=?,region=?,circle=?,division=?,kv33feeder_id=? where id=?");
			ps.setString(1,substation.getCode());
			ps.setString(2,substation.getName());
			ps.setString(3,substation.getRegion());
			ps.setString(4,substation.getCircle());
			ps.setString(5,substation.getDivision());
			ps.setInt(6,Integer.parseInt(substation.getKv33FeederID()));	
			ps.setInt(7,Integer.parseInt(substation.getId()));
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in [updateSubstation(Substation)]"+e);
		}
		return substation;
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
			rs.close();
			ps.close();
			//System.out.println("Number of Substations:"+substations.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : SubstationDAO : method : [getAll]"+e);
		}
		return substations;
	}

	public ArrayList<Substation> getAll(String startIndex,String pageSize){
		ArrayList<Substation> substations=null;
		try {
			System.out.println("getAll for substation called ");
			PreparedStatement ps = connection.prepareStatement("SELECT s.id,s.code,s.name,s.location,s.region,s.circle,s.division,s.dc,s.kv33feeder_id,kv33.name FROM substation s join kv33feeder kv33 on s.kv33feeder_id=kv33.id limit "+startIndex+","+pageSize);
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
				substation.setKv33FeederID(rs.getString(10).trim()+"(ID:"+rs.getString(9)+")");
				substations.add(substation);
			}
			rs.close();
			ps.close();

			System.out.println("Number of Substations:"+substations.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : SubstationDAO : method : [getAll(int,int)]"+e);
		}
		return substations;
	}

	public int getSubstationCount() {
		int count=0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT count(*) as count FROM substation");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("count");
			}
			System.out.println("Count of Substations :"+count);
		} catch (SQLException e) {
			System.out.println("Exception in class : SubstationDAO : method : [getSubstationCount]"+e);
		}
		return count;
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

	public ArrayList<Substation> getByKV33FeederId(String kv33FeederId){
		ArrayList<Substation> substations = new ArrayList<Substation>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM substation where kv33feeder_id=?");
			ps.setString(1, kv33FeederId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Substation substation=new Substation();
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
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getBykv33FeederCode]"+e);
		}
		return substations;
	}

	public ArrayList<String> getAllSubstationsNameWithId() {
		ArrayList<Substation> substations=getAll();
		ArrayList<String> substationNames=new ArrayList<String>();
		for(Substation substation:substations){
			substationNames.add(substation.getName()+"(ID:"+substation.getId()+")");
		}
		return substationNames;
	}

	public ArrayList<String> getSubstationsNameWithIdByCircle(String circle) {
		ArrayList<Substation> substations=getByCircle(circle);
		ArrayList<String> substationNames=new ArrayList<String>();
		for(Substation substation:substations){
			substationNames.add(substation.getName()+"(ID:"+substation.getId()+")");
		}
		return substationNames;
	}

	public ArrayList<String> getSubstationsNameWithIdByRegion(String region) {
		ArrayList<Substation> substations=getByRegion(region);
		ArrayList<String> substationNames=new ArrayList<String>();
		for(Substation substation:substations){
			substationNames.add(substation.getName()+"(ID:"+substation.getId()+")");
		}
		return substationNames;
	}

	public ArrayList<String> getSubstationsNameWithIdByDivision(String division) {
		ArrayList<Substation> substations=getByDivision(division);
		ArrayList<String> substationNames=new ArrayList<String>();
		for(Substation substation:substations){
			substationNames.add(substation.getName()+"(ID:"+substation.getId()+")");
		}
		return substationNames;
	}

	public void deleteSubstationById(String id){
		KV11FeederDAO kv11FeederDAO=new KV11FeederDAO();
		try {
			System.out.println("Deletion of substation started for substation id : "+id);
			System.out.println("First deleting from 11KV Feeder table");
			kv11FeederDAO.deleteKV11FeederBySubstationId(id);
			System.out.println("Successfully deleted from 11KV Feeder table for substation id : "+id);
			System.out.println("Now deletign from substation table only for substation id : "+id);
			PreparedStatement ps = connection.prepareStatement("delete from substation where id=?");
			ps.setInt(1,Integer.parseInt(id));
			ps.executeUpdate();
			ps.close();
			System.out.println("Successfully deleted Substation for substation id : "+id);
		} catch (SQLException e) {
			System.out.println("Exception in [deleteSubstation(id)]"+e);
		}
	}

	public void deleteSubstationByKV33FeederId(String id){
		KV11FeederDAO kv11FeederDAO=new KV11FeederDAO();
		System.out.println("Deletion of substation by 33KV Feeder ID started");
		try {
			ArrayList<Substation> substations = getByKV33FeederId(id);
			System.out.println("Number of substations for 33KVFeeder ID :"+id+" is : "+substations.size());
			System.out.println("Deletion of substation for 11KVFeeder table started");
			for(Substation substation:substations){
				kv11FeederDAO.deleteKV11FeederBySubstationId(substation.getId());
			}
			System.out.println("Now Deleting substations from substation table");
			PreparedStatement ps = connection.prepareStatement("delete from substation where kv33feeder_id=?");
			ps.setInt(1,Integer.parseInt(id));
			ps.executeUpdate();
			ps.close();
			System.out.println("Successfully deleted Substation for kv33feeder id : "+id);
			System.out.println("Going back to 33KVFeederDAO for further deletion");
		} catch (SQLException e) {
			System.out.println("Exception in [deleteSubstationByKV33FeederId(id)]"+e);
		}
	}
}
