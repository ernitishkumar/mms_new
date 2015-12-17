package mms.com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import mms.com.utility.DatabaseConnection;
import mms.com.beans.EHVSS;
import java.util.ArrayList;
import mms.com.beans.ErrorBean;
public class EhvssDAO {
	private Connection connection = DatabaseConnection.getConnection("mms_new");
	
	public void addEHVSS(EHVSS ehvss){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into EHVSS(code, name, location, region, circle, division) VALUES(?,?,?,?,?,?)");
			ps.setString(1,ehvss.getCode());
			ps.setString(2,ehvss.getName());
			ps.setString(3,"DUMMY");
			ps.setString(4,ehvss.getRegion());
			ps.setString(5,"DUMMY");
			ps.setString(6,"DUMMY");
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Exception in [updateUser]"+e);
		}
	}

	public boolean addEHVSS(EHVSS ehvss,ErrorBean errorBean){
		ArrayList<EHVSS> ehvssList=new ArrayList<EHVSS>();
		boolean added;
		try {
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
			System.out.println("Exception in class : EhvssDAO : method : [getAll]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getAll]"+exp);
		}
		return ehvssNames;
	}

	public ArrayList<EHVSS> getByCode(String code){
		System.out.println("GetByCode called with code : "+code);
		ArrayList<EHVSS> ehvssNames=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss where code=?");
			ps.setString(1,code);
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
			System.out.println("Exception in class : EhvssDAO : method : [getByCode]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getByCode]"+exp);
		}
		return ehvssNames;
	}

	public ArrayList<EHVSS> getByRegion(String region){
		System.out.println("GetByCode called with region : "+region);
		ArrayList<EHVSS> ehvssNames=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss where region=?");
			ps.setString(1,region);
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
			System.out.println("Number of Ehvss Locations for region :"+region+"  is :"+ehvssNames.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getByRegion]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getByRegion]"+exp);
		}
		return ehvssNames;
	}
	
	public EHVSS getByEhvssCode(String code){
		EHVSS ehvss = new EHVSS();
		
		try {
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
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getByEhvssCode]"+e);
		}
		
		return ehvss;
	}
}
