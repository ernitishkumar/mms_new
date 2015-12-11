package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.Photo11kv;
import mms.com.mapper.Photo11kvMapper;
import mms.com.utility.DatabaseConnection;

public class Photo11kvDAO {

	Connection connection = DatabaseConnection.getConnection();
	public ArrayList<Photo11kv> getAll(){
		ArrayList<Photo11kv> list = new ArrayList<Photo11kv>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from photo11kv");
			ResultSet resultSet = ps.executeQuery();
			Photo11kvMapper photo11kvMapper = new Photo11kvMapper();
			while(resultSet.next()){
				list.add(photo11kvMapper.photo11kvMapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [getAll] "+e);
		} finally{
			
		}
		
		return list;
		
	}
	
	public Photo11kv getbyReportID(String reportID){
		Photo11kv photo11kv = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from photo11kv where reportid=?");
			ps.setString(1, reportID);
			ResultSet resultSet = ps.executeQuery();
			Photo11kvMapper photo11kvMapper = new Photo11kvMapper();
			while(resultSet.next()){
				photo11kv = photo11kvMapper.photo11kvMapper(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [getbyReportID] "+e);
		} finally{
			
		}
		
		return photo11kv;
	}
	
	public void updatePhoto11kv(Photo11kv photo11kv){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into photo11kv(reportid,photo) VALUES(?,?)");
			ps.setString(2,photo11kv.getReportid());
			ps.setString(1,photo11kv.getPhoto());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updatePhoto11kv] "+e);
		}
	}

}
