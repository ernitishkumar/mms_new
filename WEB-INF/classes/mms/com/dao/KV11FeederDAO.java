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
	public KV11Feeder add11KVFeeder(KV11Feeder kv11Feeder){
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
			ResultSet resultSet=ps.executeQuery("select last_insert_id() as 'id'");
			resultSet.next();
			int id=resultSet.getInt("id");
			kv11Feeder.setId(String.valueOf(id));
			resultSet.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [add11KVFeeder(KV11Feeder)]"+e);
		}
		return kv11Feeder;
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

	public KV11Feeder update11KVFeeder(KV11Feeder kv11Feeder){
		try {
			PreparedStatement ps = connection.prepareStatement("update kv11feeder set code=?,name=?,region=?,circle=?,division=?,dc=?,feeder_type=?,substation_id=? where id=?");
			ps.setString(1,kv11Feeder.getCode());
			ps.setString(2,kv11Feeder.getName());
			ps.setString(3,kv11Feeder.getRegion());
			ps.setString(4,kv11Feeder.getCircle());
			ps.setString(5,kv11Feeder.getDivision());
			ps.setString(6,kv11Feeder.getDc());
			ps.setString(7,kv11Feeder.getFeederType());
			ps.setString(8,kv11Feeder.getSubstationID());
			ps.setInt(9,Integer.parseInt(kv11Feeder.getId()));
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [update11KVFeeder(KV11Feeder)]"+e);
		}
		return kv11Feeder;
	}
	public ArrayList<KV11Feeder> getAll(String startIndex,String pageSize){
		//System.out.println("Get All Feeders started");
		ArrayList<KV11Feeder> kv11Feeders=null;
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT kv11.id,kv11.code,kv11.name,kv11.region,kv11.circle,kv11.division,kv11.dc,kv11.substation_id,s.name,kv11.feeder_type FROM kv11feeder kv11 join substation s on kv11.substation_id=s.id limit "+startIndex+","+pageSize);
			ResultSet rs=ps.executeQuery();
			kv11Feeders=new ArrayList<KV11Feeder> ();
			resultSetParserForJtable(rs,kv11Feeders);
			//System.out.println("Number of 11 KV Feeder are :"+kv11Feeders.size());
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Exception in class : KV11FeederDAO : method :getAllKV11Feeders()"+e);
		}
		return kv11Feeders;
	}

	public ArrayList<KV11Feeder> getAll(){
		//System.out.println("Get All Feeders started");
		ArrayList<KV11Feeder> kv11Feeders=null;
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv11feeder");
			ResultSet rs=ps.executeQuery();
			kv11Feeders=new ArrayList<KV11Feeder> ();
			resultSetParser(rs,kv11Feeders);
			//System.out.println("Number of 11 KV Feeder are :"+kv11Feeders.size());
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Exception in class : KV11FeederDAO : method :getAll()"+e);
		}
		return kv11Feeders;
	}

	public int getKV11FeedersCount() {
		int count=0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT count(*) as count FROM kv11feeder");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("count");
			}
			//System.out.println("Count of 11KVFeeders :"+count);
		} catch (SQLException e) {
			System.out.println("Exception in class : KV11FeederDAO : method : [getKV11FeedersCount]"+e);
		}
		return count;
	}

	public int getKV11FeedersCountByRegion(String region) {
		int count=0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT count(*) as count FROM kv11feeder where region=?");
			ps.setString(1,region);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("count");
			}
			//System.out.println("Count of 11KVFeeders :"+count);
		} catch (SQLException e) {
			System.out.println("Exception in class : KV11FeederDAO : method : [getKV11FeedersCountByRegion(String)]"+e);
		}
		return count;
	}

	public int getKV11FeedersCountByCircle(String circle) {
		int count=0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT count(*) as count FROM kv11feeder where circle=?");
			ps.setString(1,circle);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("count");
			}
			//System.out.println("Count of 11KVFeeders :"+count);
		} catch (SQLException e) {
			System.out.println("Exception in class : KV11FeederDAO : method : [getKV11FeedersCountByCircle(String)]"+e);
		}
		return count;
	}

	public int getKV11FeedersCountByDivision(String division) {
		int count=0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT count(*) as count FROM kv11feeder where division=?");
			ps.setString(1,division);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("count");
			}
			//System.out.println("Count of 11KVFeeders :"+count);
		} catch (SQLException e) {
			System.out.println("Exception in class : KV11FeederDAO : method : [getKV11FeedersCountByDivision(String)]"+e);
		}
		return count;
	}

	public int getKV11FeedersCountBySubstationId(String substationId) {
		int count=0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT count(*) as count FROM kv11feeder where substation_id=?");
			ps.setString(1,substationId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("count");
			}
			//System.out.println("Count of 11KVFeeders :"+count);
		} catch (SQLException e) {
			System.out.println("Exception in class : KV11FeederDAO : method : [getKV11FeedersCountBySubstationId(String)]"+e);
		}
		return count;
	}


	public ArrayList<KV11Feeder> getByCode(String code){
		System.out.println("Get Feeder by code started for code : "+code);
		ArrayList<KV11Feeder> kv11Feeders=null;
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv11feeder where code=?");
			ps.setString(1,code);
			ResultSet rs=ps.executeQuery();
			kv11Feeders=new ArrayList<KV11Feeder> ();
			resultSetParser(rs,kv11Feeders);
			//System.out.println("Number of 11 KV Feeder for code : "+code+" are :"+kv11Feeders.size());
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Exception in class : KV11FeederDAO : method :getByCode(String)"+e);
		}
		return kv11Feeders;
	}

	public ArrayList<KV11Feeder> getBySubstationId(String substationId){
		ArrayList<KV11Feeder> kv11Feeders=null;
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv11feeder where substation_id=?");
			ps.setInt(1,Integer.parseInt(substationId));
			ResultSet rs=ps.executeQuery();
			kv11Feeders=new ArrayList<KV11Feeder> ();
			resultSetParser(rs,kv11Feeders);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Exception in class : KV11FeederDAO : method :getBySubstationId()"+e);
		}
		return kv11Feeders;
	}

	public ArrayList<KV11Feeder> getBySubstationId(String substationId,String startIndex,String pageSize){
		ArrayList<KV11Feeder> kv11Feeders=null;
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT kv11.id,kv11.code,kv11.name,kv11.region,kv11.circle,kv11.division,kv11.dc,kv11.substation_id,s.name,kv11.feeder_type FROM kv11feeder kv11 join substation s on kv11.substation_id=s.id where kv11.substation_id=? limit "+startIndex+","+pageSize);
			ps.setInt(1,Integer.parseInt(substationId));
			ResultSet rs=ps.executeQuery();
			kv11Feeders=new ArrayList<KV11Feeder> ();
			resultSetParserForJtable(rs,kv11Feeders);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Exception in class : KV11FeederDAO : method :getBySubstationId(String,String,String)"+e);
		}
		return kv11Feeders;
	}

	public ArrayList<KV11Feeder> getByCircle(String circle){
		ArrayList<KV11Feeder> kv11Feeders=null;
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv11feeder where circle=?");
			ps.setString(1,circle);
			ResultSet rs=ps.executeQuery();
			kv11Feeders=new ArrayList<KV11Feeder> ();
			resultSetParser(rs,kv11Feeders);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Exception in class : KV11FeederDAO : method :getByCircle(String)"+e);
		}
		return kv11Feeders;
	}

	public ArrayList<KV11Feeder> getByCircle(String circle,String startIndex,String pageSize){
		ArrayList<KV11Feeder> kv11Feeders=null;
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT kv11.id,kv11.code,kv11.name,kv11.region,kv11.circle,kv11.division,kv11.dc,kv11.substation_id,s.name,kv11.feeder_type FROM kv11feeder kv11 join substation s on kv11.substation_id=s.id where kv11.circle=? limit "+startIndex+","+pageSize);
			ps.setString(1,circle);
			ResultSet rs=ps.executeQuery();
			kv11Feeders=new ArrayList<KV11Feeder> ();
			resultSetParserForJtable(rs,kv11Feeders);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Exception in class : KV11FeederDAO : method :getBySubstationId(String,String,String)"+e);
		}
		return kv11Feeders;
	}

	public ArrayList<KV11Feeder> getByRegion(String region){
		ArrayList<KV11Feeder> kv11Feeders=null;
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv11feeder where region=?");
			ps.setString(1,region);
			ResultSet rs=ps.executeQuery();
			kv11Feeders=new ArrayList<KV11Feeder> ();
			resultSetParser(rs,kv11Feeders);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Exception in class : KV11FeederDAO : method :getByRegion(String)"+e);
		}
		return kv11Feeders;
	}

	public ArrayList<KV11Feeder> getByRegion(String region,String startIndex,String pageSize){
		ArrayList<KV11Feeder> kv11Feeders=null;
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT kv11.id,kv11.code,kv11.name,kv11.region,kv11.circle,kv11.division,kv11.dc,kv11.substation_id,s.name,kv11.feeder_type FROM kv11feeder kv11 join substation s on kv11.substation_id=s.id where kv11.region=? limit "+startIndex+","+pageSize);
			ps.setString(1,region);
			ResultSet rs=ps.executeQuery();
			kv11Feeders=new ArrayList<KV11Feeder> ();
			resultSetParserForJtable(rs,kv11Feeders);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Exception in class : KV11FeederDAO : method :getBySubstationId(String,String,String)"+e);
		}
		return kv11Feeders;
	}

	public ArrayList<KV11Feeder> getByDivision(String division){
		ArrayList<KV11Feeder> kv11Feeders=null;
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv11feeder where division=?");
			ps.setString(1,division);
			ResultSet rs=ps.executeQuery();
			kv11Feeders=new ArrayList<KV11Feeder> ();
			resultSetParser(rs,kv11Feeders);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Exception in class : KV11FeederDAO : method :getByDivision(String)"+e);
		}
		return kv11Feeders;
	}

	public ArrayList<KV11Feeder> getByDivision(String division,String startIndex,String pageSize){
		ArrayList<KV11Feeder> kv11Feeders=null;
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT kv11.id,kv11.code,kv11.name,kv11.region,kv11.circle,kv11.division,kv11.dc,kv11.substation_id,s.name,kv11.feeder_type FROM kv11feeder kv11 join substation s on kv11.substation_id=s.id where kv11.division=? limit "+startIndex+","+pageSize);
			ps.setString(1,division);
			ResultSet rs=ps.executeQuery();
			kv11Feeders=new ArrayList<KV11Feeder> ();
			resultSetParserForJtable(rs,kv11Feeders);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Exception in class : KV11FeederDAO : method :getBySubstationId(String,String,String)"+e);
		}
		return kv11Feeders;
	}

	public void deleteKV11FeederBySubstationId(String id){
		//System.out.println("Deletion of 11kvfeeders by Substation ID started");
		try {
			//System.out.println("Now Deleting 11kv feeders from KV11Feeder table");
			PreparedStatement ps = connection.prepareStatement("delete from kv11feeder where substation_id=?");
			ps.setInt(1,Integer.parseInt(id));
			ps.executeUpdate();
			ps.close();
			//System.out.println("Successfully deleted 11kvfeeder for substation id : "+id);
			//System.out.println("Going back to SubstationDAO for further deletion");
		} catch (SQLException e) {
			System.out.println("Exception in [deleteKV11FeederBySubstationId(id)]"+e);
		}
	}

	public void deleteById(String id){
		try {
			//System.out.println("Deletion of 11kvfeeders by 11KVFeeder id "+id+" started");
			PreparedStatement ps = connection.prepareStatement("delete from kv11feeder where id=?");
			ps.setInt(1,Integer.parseInt(id));
			ps.executeUpdate();
			ps.close();
			//System.out.println("Successfully deleted from 11KVFeeder for 11kvfeeder id : "+id);
		} catch (SQLException e) {
			System.out.println("Exception in [deleteById(id)]"+e);
		}
	}

	private void resultSetParserForJtable(ResultSet rs,ArrayList<KV11Feeder> kv11Feeders){
		try{
			while(rs.next()){
				KV11Feeder kv11Feeder = new KV11Feeder();
				kv11Feeder.setId(String.valueOf(rs.getInt(1)));
				kv11Feeder.setName(rs.getString(3).trim());
				kv11Feeder.setCode(rs.getString(2).trim());
				kv11Feeder.setRegion(rs.getString(4).trim());
				kv11Feeder.setCircle(rs.getString(5).trim());
				kv11Feeder.setDivision(rs.getString(6).trim());
				kv11Feeder.setDc(rs.getString(7).trim());
				kv11Feeder.setSubstationID(rs.getString(9)+"(ID:"+rs.getString(8).trim()+")");
				kv11Feeder.setFeederType(rs.getString(10).trim());
				kv11Feeders.add(kv11Feeder);
			}
		}catch(SQLException e){
			System.out.println("Exception in class : KV11FeederDAO : method : [resultSetParserForJtable(ResultSet,ArrayList<Substation>)]"+e);
		}
	}

	private void resultSetParser(ResultSet rs,ArrayList<KV11Feeder> kv11Feeders){
		try{
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
		}catch(SQLException e){
			System.out.println("Exception in class : KV11FeederDAO : method : [resultSetParser(ResultSet,ArrayList<Substation>)]"+e);
		}
	}
}
