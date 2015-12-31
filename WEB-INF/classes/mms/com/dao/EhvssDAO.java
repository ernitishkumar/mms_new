package mms.com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import mms.com.utility.DatabaseConnection;
import mms.com.beans.EHVSS;
import java.util.ArrayList;
import mms.com.beans.ErrorBean;
import mms.com.dao.KV33FeederDAO;
import mms.com.beans.KV33Feeder;
public class EhvssDAO {
	private KV33FeederDAO kv33FeederDAO=new KV33FeederDAO();
	public EHVSS addEHVSS(EHVSS ehvss){
		try {
			Connection connection = DatabaseConnection.getConnection("mms_new");
			PreparedStatement ps = connection.prepareStatement("insert into EHVSS(code, name, location, region, circle, division) VALUES(?,?,?,?,?,?)");
			ps.setString(1,ehvss.getCode());
			ps.setString(2,ehvss.getName());
			ps.setString(3,"DUMMY");
			ps.setString(4,ehvss.getRegion());
			ps.setString(5,"DUMMY");
			ps.setString(6,"DUMMY");
			ps.executeUpdate();
			ResultSet resultSet=ps.executeQuery("select last_insert_id() as 'id'");
			resultSet.next();
			int id=resultSet.getInt("id");
			ehvss.setId(String.valueOf(id));
			resultSet.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Exception in [addEHVSS(EHVSS)]"+e);
		}
		return ehvss;
	}

	public EHVSS updateEHVSS(EHVSS ehvss){
		try {
			Connection connection = DatabaseConnection.getConnection("mms_new");
			PreparedStatement ps = connection.prepareStatement("update EHVSS set code=?,name=?,location=?,region=?,circle=?,division=? where id=?");
			ps.setString(1,ehvss.getCode());
			ps.setString(2,ehvss.getName());
			ps.setString(3,"DUMMY");
			ps.setString(4,ehvss.getRegion());
			ps.setString(5,"DUMMY");
			ps.setString(6,"DUMMY");
			ps.setInt(7,Integer.parseInt(ehvss.getId()));
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Exception in [updateEHVSS(EHVSS)]"+e);
		}
		return ehvss;
	}

	public boolean addEHVSS(EHVSS ehvss,ErrorBean errorBean){
		ArrayList<EHVSS> ehvssList=new ArrayList<EHVSS>();
		boolean added;
		try {
			Connection connection = DatabaseConnection.getConnection("mms_new");
			ehvssList=getByCode(ehvss.getCode());
			if(ehvssList==null || ehvssList.size()==0){
				PreparedStatement ps = connection.prepareStatement("insert into EHVSS(code, name, location, region, circle, division) VALUES(?,?,?,?,?,?)");
				ps.setString(1,ehvss.getCode());
				ps.setString(2,ehvss.getName());
				ps.setString(3,"DUMMY");
				ps.setString(4,ehvss.getRegion());
				ps.setString(5,"DUMMY");
				ps.setString(6,"DUMMY");
				ps.executeUpdate();
				added=true;
				ps.close();

			}else{
				errorBean.setErrorMessage("EHVSS Code Already Exist. Please provide Different EHVSS Code");
				added=false;
			}
		} catch (SQLException e) {
			added=false;
			System.out.println("Exception in [updateUser]"+e);
		}
		return added;
	}

	public void deleteEHVSSById(String id){
		try {
			Connection connection = DatabaseConnection.getConnection("mms_new");
			System.out.println("Deletion of EHVSS started");
			System.out.println("First deleting 33kv feeders against EHVSSID : "+id);
			kv33FeederDAO.deleteKV33FeederByEhvssId(id);
			System.out.println("Successfully deleted from 33KVFeeder Table now deleting from ehvss for id : "+id);
			PreparedStatement ps = connection.prepareStatement("delete from ehvss where id=?");
			ps.setInt(1,Integer.parseInt(id));
			ps.executeUpdate();
			ps.close();

			System.out.println("Successfully deleted from ehvss for id : "+id);
		} catch (SQLException e) {
			System.out.println("Exception in [deleteEHVSSById(id)]"+e);
		}
	}
	
	public ArrayList<EHVSS> getAll(){
		ArrayList<EHVSS> ehvssNames=null;
		try {
			Connection connection = DatabaseConnection.getConnection("mms_new");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss");
			ResultSet rs=ps.executeQuery();
			ehvssNames=new ArrayList<EHVSS>();
			resultSetParser(rs,ehvssNames);
			rs.close();
			ps.close();
			//System.out.println("Number of Ehvss Locations :"+ehvssNames.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getAll]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getAll]"+exp);
		}
		return ehvssNames;
	}

	public ArrayList<EHVSS> getAll(String startIndex,String pageSize){
		ArrayList<EHVSS> ehvssNames=null;
		try {
			Connection connection = DatabaseConnection.getConnection("mms_new");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss limit "+startIndex+","+pageSize);
			ResultSet rs=ps.executeQuery();
			ehvssNames=new ArrayList<EHVSS>();
			resultSetParserForJtable(rs,ehvssNames);
			rs.close();
			ps.close();
			//System.out.println("Number of Ehvss Locations for PageSize : "+pageSize+" is : "+ehvssNames.size()); 
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getAll]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getAll]"+exp);
		}
		return ehvssNames;
	}

	public int getEhvssCount(){
		int count=0;
		try {
			Connection connection = DatabaseConnection.getConnection("mms_new");
			PreparedStatement ps = connection.prepareStatement("SELECT count(*) as count FROM ehvss");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("count");
			}
			rs.close();
			ps.close();
			//System.out.println("Count of EHVSS : "+count);
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getEhvssCount]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getEhvssCount]"+exp);
		}
		return count;
	}

	public int getEhvssCountByRegion(String region){
		int count=0;
		try {
			Connection connection = DatabaseConnection.getConnection("mms_new");
			PreparedStatement ps = connection.prepareStatement("SELECT count(*) as count FROM ehvss where region=?");
			ps.setString(1,region);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("count");
			}
			rs.close();
			ps.close();
			//System.out.println("Count of EHVSS : "+count);
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getEhvssCount]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getEhvssCount]"+exp);
		}
		return count;
	}

	public ArrayList<EHVSS> getByCode(String code){
		System.out.println("GetByCode called with code : "+code);
		ArrayList<EHVSS> ehvssNames=null;
		try {
			Connection connection = DatabaseConnection.getConnection("mms_new");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss where code=?");
			ps.setString(1,code);
			ResultSet rs=ps.executeQuery();
			ehvssNames=new ArrayList<EHVSS>();
			resultSetParser(rs,ehvssNames);
			rs.close();
			ps.close();
			//System.out.println("Number of Ehvss Locations :"+ehvssNames.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getByCode]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getByCode]"+exp);
		}
		return ehvssNames;
	}

	public ArrayList<EHVSS> getByRegion(String region){
		//System.out.println("GetByRegion called with region : "+region);
		ArrayList<EHVSS> ehvssNames=null;
		try {
			Connection connection = DatabaseConnection.getConnection("mms_new");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss where region=?");
			ps.setString(1,region);
			ResultSet rs=ps.executeQuery();
			ehvssNames=new ArrayList<EHVSS>();
			resultSetParser(rs,ehvssNames);
			rs.close();
			ps.close();
			//System.out.println("Number of Ehvss Locations for region :"+region+"  is :"+ehvssNames.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getByRegion]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getByRegion]"+exp);
		}
		return ehvssNames;
	}

	public ArrayList<EHVSS> getByRegion(String region,String startIndex,String pageSize){
		//System.out.println("GetByRegion called with region : "+region);
		ArrayList<EHVSS> ehvssNames=null;
		try {
			Connection connection = DatabaseConnection.getConnection("mms_new");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss where region=? limit "+startIndex+","+pageSize);
			ps.setString(1,region);
			ResultSet rs=ps.executeQuery();
			ehvssNames=new ArrayList<EHVSS>();
			resultSetParserForJtable(rs,ehvssNames);
			rs.close();
			ps.close();
			//System.out.println("Number of Ehvss Locations for region :"+region+"  is :"+ehvssNames.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getByRegion]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getByRegion]"+exp);
		}
		return ehvssNames;
	}

	public ArrayList<EHVSS> getByCircle(String circle){
		//System.out.println("GetByCircle called with circle : "+circle);
		ArrayList<EHVSS> ehvssNames=null;
		try {
			Connection connection = DatabaseConnection.getConnection("mms_new");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss where circle=?");
			ps.setString(1,circle);
			ResultSet rs=ps.executeQuery();
			ehvssNames=new ArrayList<EHVSS>();
			resultSetParser(rs,ehvssNames);
			rs.close();
			ps.close();
			//System.out.println("Number of Ehvss Locations for region :"+region+"  is :"+ehvssNames.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getByCircle(String)]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getByCircle(String)]"+exp);
		}
		return ehvssNames;
	}

	public ArrayList<EHVSS> getByCircle(String circle,String startIndex,String pageSize){
		//System.out.println("GetByCircle called with circle : "+circle);
		ArrayList<EHVSS> ehvssNames=null;
		try {
			Connection connection = DatabaseConnection.getConnection("mms_new");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss where circle=? limit "+startIndex+","+pageSize);
			ps.setString(1,circle);
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
			rs.close();
			ps.close();
			//System.out.println("Number of Ehvss Locations for region :"+region+"  is :"+ehvssNames.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getByCircle(String,String,String)]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getByCircle(String,String,String)]"+exp);
		}
		return ehvssNames;
	}
	
	public EHVSS getByEhvssCode(String code){
		EHVSS ehvss = new EHVSS();
		
		try {
			Connection connection = DatabaseConnection.getConnection("mms_new");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss where code=?");
			ps.setString(1, code);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ehvss.setId(String.valueOf(rs.getInt(1)));
				ehvss.setName(rs.getString(3).trim());
				ehvss.setCode(rs.getString(2).trim());
				ehvss.setLocation(rs.getString(4).trim());
				ehvss.setRegion(rs.getString(5).trim());
				ehvss.setCircle(rs.getString(6).trim());
				ehvss.setDivision(rs.getString(7).trim());
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getByEhvssCode]"+e);
		}
		return ehvss;
	}

	public ArrayList<String> getEhvssNamesByRegion(String region){
		ArrayList<EHVSS> ehvssRecords=getByRegion(region);
		ArrayList<String> ehvssNames=new ArrayList<String>();
		for(EHVSS ehvss:ehvssRecords){
			ehvssNames.add(ehvss.getName());
		}
		return ehvssNames;
	}

	public ArrayList<String> getEhvssNamesWithIdByRegion(String region){
		ArrayList<EHVSS> ehvssRecords=getByRegion(region);
		ArrayList<String> ehvssNames=new ArrayList<String>();
		for(EHVSS ehvss:ehvssRecords){
			ehvssNames.add(ehvss.getName()+"(ID:"+ehvss.getId()+")");
		}
		return ehvssNames;
	}

	public ArrayList<String> getEhvssNamesByCircle(String circle){
		ArrayList<EHVSS> ehvssRecords=getByCircle(circle);
		ArrayList<String> ehvssNames=new ArrayList<String>();
		for(EHVSS ehvss:ehvssRecords){
			ehvssNames.add(ehvss.getName());
		}
		return ehvssNames;
	}

	public ArrayList<String> getEhvssNamesWithIdByCircle(String circle){
		ArrayList<EHVSS> ehvssRecords=getByCircle(circle);
		ArrayList<String> ehvssNames=new ArrayList<String>();
		for(EHVSS ehvss:ehvssRecords){
			ehvssNames.add(ehvss.getName()+"(ID:"+ehvss.getId()+")");
		}
		return ehvssNames;
	}

	public ArrayList<String> getAllEhvssNames(){
		ArrayList<EHVSS> ehvssRecords=getAll();
		ArrayList<String> ehvssNames=new ArrayList<String>();
		for(EHVSS ehvss:ehvssRecords){
			ehvssNames.add(ehvss.getName());
		}
		return ehvssNames;
	}

	public ArrayList<String> getAllEhvssNamesWithId(){
		ArrayList<EHVSS> ehvssRecords=getAll();
		ArrayList<String> ehvssNames=new ArrayList<String>();
		for(EHVSS ehvss:ehvssRecords){
			ehvssNames.add(ehvss.getName()+"(ID:"+ehvss.getId()+")");
		}
		return ehvssNames;
	}

	private void resultSetParserForJtable(ResultSet rs,ArrayList<EHVSS> ehvssNames){
		try{
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
		}catch(SQLException e){
			System.out.println("Exception in class : EhvssDAO : method : [resultSetParserForJtable(ResultSet,ArrayList<Substation>)]"+e);
		}
	}

	private void resultSetParser(ResultSet rs,ArrayList<EHVSS> ehvssNames){
		try{
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
		}catch(SQLException e){
			System.out.println("Exception in class : EhvssDAO : method : [resultSetParser(ResultSet,ArrayList<Substation>)]"+e);
		}
	}
}
