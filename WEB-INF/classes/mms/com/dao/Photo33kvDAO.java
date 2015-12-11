package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.Photo33kv;
import mms.com.mapper.Photo33kvMapper;
import mms.com.utility.DatabaseConnection;

public class Photo33kvDAO {


	Connection connection = DatabaseConnection.getConnection();
	public ArrayList<Photo33kv> getAll(){
		ArrayList<Photo33kv> list = new ArrayList<Photo33kv>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from photo33kv");
			ResultSet resultSet = ps.executeQuery();
			Photo33kvMapper photo33kvMapper = new Photo33kvMapper();
			while(resultSet.next()){
				list.add(photo33kvMapper.photo33kvMapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ëxception occured in [getAll] "+e);
		} finally{
			
		}
		
		return list;
		
	}
	
	public Photo33kv getbyReportID(String reportID){
		Photo33kv photo33kv = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from photo33kv where reportid=?");
			ps.setString(1, reportID);
			ResultSet resultSet = ps.executeQuery();
			Photo33kvMapper photo33kvMapper = new Photo33kvMapper();
			while(resultSet.next()){
				photo33kv = photo33kvMapper.photo33kvMapper(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ëxception occured in [getbyReportID] "+e);
		} finally{
			
		}
		
		return photo33kv;
	}
	
	public void updatePhoto33kv(Photo33kv photo33kv){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into photo33kv(reportid,photo) VALUES(?,?)");
			ps.setString(1,photo33kv.getReportid());
			ps.setString(2,photo33kv.getPhoto());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ëxception occured in [updatePhoto33kv] "+e);
		}
	}
}
