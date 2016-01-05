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
	private Connection connection = DatabaseConnection.getConnection("mms_new");
	public KV33Feeder addKV33Feeder(KV33Feeder kv33Feeder){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into KV33Feeder(code, name, location, region, circle, division, ehvss_id) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1,kv33Feeder.getCode());
			ps.setString(2,kv33Feeder.getName());
			ps.setString(3,"DUMMY");
			ps.setString(4,kv33Feeder.getRegion());
			ps.setString(5,kv33Feeder.getCircle());
			ps.setString(6,kv33Feeder.getDivision());
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

	public KV33Feeder updateKV33Feeder(KV33Feeder kv33Feeder){
		try {
			PreparedStatement ps = connection.prepareStatement("update kv33feeder set code=?,name=?,region=?,circle=?,division=?,ehvss_id=? where id=?");
			ps.setString(1,kv33Feeder.getCode());
			ps.setString(2,kv33Feeder.getName());
			ps.setString(3,kv33Feeder.getRegion());
			ps.setString(4,kv33Feeder.getCircle());
			ps.setString(5,kv33Feeder.getDivision());
			ps.setInt(6,Integer.parseInt(kv33Feeder.getEhvssID()));
			ps.setInt(7,Integer.parseInt(kv33Feeder.getId()));
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Exception in [updateKV33Feeder(KV33Feeder)]"+e);
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
				ps.setString(6,kv33Feeder.getDivision());
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
			resultSetParser(rs,kv33Feeders);
			//System.out.println("Number of 33KV Feeders :"+kv33Feeders.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getAll]"+e);
		}
		return kv33Feeders;
	}

	public ArrayList<String> getAllKV33FeedersNameWithId() {
		ArrayList<KV33Feeder> kv33Feeders=getAll();
		ArrayList<String> kv33FeedersNames=new ArrayList<String>();
		for(KV33Feeder kv33Feeder:kv33Feeders){
			kv33FeedersNames.add(kv33Feeder.getName()+"(ID:"+kv33Feeder.getId()+")");
		}
		return kv33FeedersNames;
	}

	public ArrayList<String> getKV33FeedersNameWithIdByCircle(String circle) {
		ArrayList<KV33Feeder> kv33Feeders=getByCircle(circle);
		ArrayList<String> kv33FeedersNames=new ArrayList<String>();
		for(KV33Feeder kv33Feeder:kv33Feeders){
			kv33FeedersNames.add(kv33Feeder.getName()+"(ID:"+kv33Feeder.getId()+")");
		}
		return kv33FeedersNames;
	}

	public ArrayList<String> getKV33FeedersNameWithIdByRegion(String region) {
		ArrayList<KV33Feeder> kv33Feeders=getByRegion(region);
		ArrayList<String> kv33FeedersNames=new ArrayList<String>();
		for(KV33Feeder kv33Feeder:kv33Feeders){
			kv33FeedersNames.add(kv33Feeder.getName()+"(ID:"+kv33Feeder.getId()+")");
		}
		return kv33FeedersNames;
	}

	public ArrayList<KV33Feeder> getAll(String startIndex,String pageSize) {
		ArrayList<KV33Feeder> kv33Feeders=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT kv33.id,kv33.code,kv33.name,kv33.location,kv33.region,kv33.circle,kv33.division,kv33.ehvss_id,e.name FROM kv33feeder kv33 join ehvss e on kv33.ehvss_id=e.id limit "+startIndex+","+pageSize);
			ResultSet rs=ps.executeQuery();
			kv33Feeders=new ArrayList<KV33Feeder>();
			resultSetParserForJtable(rs,kv33Feeders);
			//System.out.println("Number of 33KV Feeders :"+kv33Feeders.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getAll(String,String)]"+e);
		}
		return kv33Feeders;
	}

	public ArrayList<KV33Feeder> getByRegion(String region,String startIndex,String pageSize) {
		ArrayList<KV33Feeder> kv33Feeders=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT kv33.id,kv33.code,kv33.name,kv33.location,kv33.region,kv33.circle,kv33.division,kv33.ehvss_id,e.name FROM kv33feeder kv33 join ehvss e on kv33.ehvss_id=e.id where kv33.region=? limit "+startIndex+","+pageSize);
			ps.setString(1,region);
			ResultSet rs=ps.executeQuery();
			kv33Feeders=new ArrayList<KV33Feeder>();
			resultSetParserForJtable(rs,kv33Feeders);
			//System.out.println("Number of 33KV Feeders :"+kv33Feeders.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getByRegion(String,String,String)]"+e);
		}
		return kv33Feeders;
	}

	public ArrayList<KV33Feeder> getByCircle(String circle,String startIndex,String pageSize) {
		ArrayList<KV33Feeder> kv33Feeders=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT kv33.id,kv33.code,kv33.name,kv33.location,kv33.region,kv33.circle,kv33.division,kv33.ehvss_id,e.name FROM kv33feeder kv33 join ehvss e on kv33.ehvss_id=e.id where kv33.circle=? limit "+startIndex+","+pageSize);
			ps.setString(1,circle);
			ResultSet rs=ps.executeQuery();
			kv33Feeders=new ArrayList<KV33Feeder>();
			resultSetParserForJtable(rs,kv33Feeders);
			//System.out.println("Number of 33KV Feeders :"+kv33Feeders.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getByCircle(String,String,String)]"+e);
		}
		return kv33Feeders;
	}

	public ArrayList<KV33Feeder> getByDivision(String division,String startIndex,String pageSize) {
		ArrayList<KV33Feeder> kv33Feeders=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT kv33.id,kv33.code,kv33.name,kv33.location,kv33.region,kv33.circle,kv33.division,kv33.ehvss_id,e.name FROM kv33feeder kv33 join ehvss e on kv33.ehvss_id=e.id where kv33.division=? limit "+startIndex+","+pageSize);
			ps.setString(1,division);
			ResultSet rs=ps.executeQuery();
			kv33Feeders=new ArrayList<KV33Feeder>();
			resultSetParserForJtable(rs,kv33Feeders);
			//System.out.println("Number of 33KV Feeders :"+kv33Feeders.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getByCircle(String,String,String)]"+e);
		}
		return kv33Feeders;
	}

	public ArrayList<KV33Feeder> getByEhvssId(String ehvssId,String startIndex,String pageSize) {
		ArrayList<KV33Feeder> kv33Feeders=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT kv33.id,kv33.code,kv33.name,kv33.location,kv33.region,kv33.circle,kv33.division,kv33.ehvss_id,e.name FROM kv33feeder kv33 join ehvss e on kv33.ehvss_id=e.id where kv33.ehvss_id=? limit "+startIndex+","+pageSize);
			ps.setString(1,ehvssId);
			ResultSet rs=ps.executeQuery();
			kv33Feeders=new ArrayList<KV33Feeder>();
			resultSetParserForJtable(rs,kv33Feeders);
			//System.out.println("Number of 33KV Feeders :"+kv33Feeders.size());
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
			//System.out.println("Count of 33KV Feeders :"+count);
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getKV33FeederCount]"+e);
		}
		return count;
	}

	public int getKV33FeederCountByRegion(String region) {
		int count=0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT count(*) as count FROM kv33feeder where region=?");
			ps.setString(1,region);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("count");
			}
			//System.out.println("Count of 33KV Feeders :"+count);
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getKV33FeederCountByRegion(String)]"+e);
		}
		return count;
	}

	public int getKV33FeederCountByCircle(String circle) {
		int count=0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT count(*) as count FROM kv33feeder where circle=?");
			ps.setString(1,circle);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("count");
			}
			//System.out.println("Count of 33KV Feeders :"+count);
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getKV33FeederCountByCircle(String)]"+e);
		}
		return count;
	}

	public int getKV33FeederCountByDivision(String division) {
		int count=0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT count(*) as count FROM kv33feeder where division=?");
			ps.setString(1,division);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("count");
			}
			//System.out.println("Count of 33KV Feeders :"+count);
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getKV33FeederCountByCircle(String)]"+e);
		}
		return count;
	}

	public int getKV33FeederCountByEhvssId(String ehvssId) {
		int count=0;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT count(*) as count FROM kv33feeder where ehvss_id=?");
			ps.setString(1,ehvssId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("count");
			}
			//System.out.println("Count of 33KV Feeders :"+count);
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getKV33FeederCountByEhvssId(String)]"+e);
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
			resultSetParser(rs,kv33Feeders);
			//System.out.println("Number of 33KV Feeders  for code :"+code+" is : "+kv33Feeders.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getByCode]"+e);
		}
		return kv33Feeders;
	}

	public KV33Feeder getById(String id) {
		KV33Feeder kv33Feeder=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv33feeder where id=?");
			ps.setInt(1,Integer.parseInt(id));
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				kv33Feeder = new KV33Feeder();
				kv33Feeder.setId(String.valueOf(rs.getInt(1)));
				kv33Feeder.setName(rs.getString(3).trim());
				kv33Feeder.setCode(rs.getString(2).trim());
				kv33Feeder.setLocation(rs.getString(4).trim());
				kv33Feeder.setRegion(rs.getString(5).trim());
				kv33Feeder.setCircle(rs.getString(6).trim());
				kv33Feeder.setDivision(rs.getString(7).trim());
				kv33Feeder.setEhvssID(rs.getString(8).trim());
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getById]"+e);
		}
		return kv33Feeder;
	}

	public ArrayList<KV33Feeder> getByCircle(String circle) {
		ArrayList<KV33Feeder> kv33Feeders=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv33feeder where circle=?");
			ps.setString(1,circle);
			ResultSet rs=ps.executeQuery();
			kv33Feeders=new ArrayList<KV33Feeder>();
			resultSetParser(rs,kv33Feeders);
			//System.out.println("Number of 33KV Feeders  for circle :"+circle+" is : "+kv33Feeders.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getByCircle]"+e);
		}
		return kv33Feeders;
	}

	public ArrayList<KV33Feeder> getByRegion(String region) {
		ArrayList<KV33Feeder> kv33Feeders=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv33feeder where region=?");
			ps.setString(1,region);
			ResultSet rs=ps.executeQuery();
			kv33Feeders=new ArrayList<KV33Feeder>();
			resultSetParser(rs,kv33Feeders);
			//System.out.println("Number of 33KV Feeders  for circle :"+circle+" is : "+kv33Feeders.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getByRegion]"+e);
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
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getBykv33FeederCode]"+e);
		}
		return kv33Feeder;
	}

	public ArrayList<KV33Feeder> getKV33FeederByEhvssId(String ehvssId){
		ArrayList<KV33Feeder> kv33Feeders=new ArrayList<KV33Feeder>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM KV33Feeder where ehvss_id=?");
			ps.setInt(1, Integer.parseInt(ehvssId));
			ResultSet rs=ps.executeQuery();
			resultSetParser(rs,kv33Feeders);
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getKV33FeederByEhvssId]"+e);
		}
		return kv33Feeders;
	}

	public void deleteKV33FeederById(String id){
		SubstationDAO substationDAO=new SubstationDAO();
		try {
			//System.out.println("Deletion of 33KVFeeders started for 33KVFeeder ID : "+id);
			//System.out.println("First deleting 33KVFeeder id from substation");
			substationDAO.deleteSubstationByKV33FeederId(id);
			//System.out.println("Substation deletion successful for 33KVFeeder id : "+id);
			//System.out.println("Now deleting from 33KVFeeder table");
			PreparedStatement ps = connection.prepareStatement("delete from kv33feeder where id=?");
			ps.setInt(1,Integer.parseInt(id));
			ps.executeUpdate();
			ps.close();
			//System.out.println("33KVFeeder deletion successful for 33KVFeeder id : "+id);
		} catch (SQLException e) {
			System.out.println("Exception in [deleteKV33Feeder(id)]"+e);
		}
	}

	public void deleteKV33FeederByEhvssId(String id){
		SubstationDAO substationDAO=new SubstationDAO();
		try {
			//System.out.println("Deletion of 33KV Feeders started for ehvss ID : "+id);
			ArrayList<KV33Feeder> kv33Feeders = getKV33FeederByEhvssId(id);
			//System.out.println("Number of 33KV Feeders for ehvssID : "+id+" is : "+kv33Feeders.size());
			//System.out.println("Now deleting these 33KV Feeders from Substation Table : ");
			for(KV33Feeder kv33Feeder:kv33Feeders){
				substationDAO.deleteSubstationByKV33FeederId(kv33Feeder.getId());
			}
			//System.out.println("33KV Deleted from substation now deleting from 33KV Feeder table");
			PreparedStatement ps = connection.prepareStatement("delete from kv33feeder where ehvss_id=?");
			ps.setInt(1,Integer.parseInt(id));
			ps.executeUpdate();
			ps.close();
			//System.out.println("33KVFeeder deletion successful for ehvss id : "+id);
			//System.out.println("Going back to EHVSSDAO for further deletion");
		} catch (SQLException e) {
			System.out.println("Exception in [deleteKV33Feeder(id)]"+e);
		}
	}

	private void resultSetParserForJtable(ResultSet rs,ArrayList<KV33Feeder> kv33Feeders){
		try{
			while(rs.next()){
				KV33Feeder kv33Feeder = new KV33Feeder();
				kv33Feeder.setId(String.valueOf(rs.getInt(1)));
				kv33Feeder.setName(rs.getString(3).trim());
				kv33Feeder.setCode(rs.getString(2).trim());
				kv33Feeder.setLocation(rs.getString(4).trim());
				kv33Feeder.setRegion(rs.getString(5).trim());
				kv33Feeder.setCircle(rs.getString(6).trim());
				kv33Feeder.setDivision(rs.getString(7).trim());
				kv33Feeder.setEhvssID(rs.getString(9)+"(ID:"+rs.getString(8).trim()+")");
				kv33Feeders.add(kv33Feeder);
			}
		}catch(SQLException e){
			System.out.println("Exception in class : KV33FeederDAO : method : [resultSetParserForJtable(ResultSet,ArrayList<Substation>)]"+e);
		}
	}

	private void resultSetParser(ResultSet rs,ArrayList<KV33Feeder> kv33Feeders){
		try{
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
		}catch(SQLException e){
			System.out.println("Exception in class : KV33FeederDAO : method : [resultSetParser(ResultSet,ArrayList<Substation>)]"+e);
		}
	}
}
