package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.DTRPhoto;
import mms.com.mapper.DTRPhotoMapper;
import mms.com.utility.DatabaseConnection;

public class DTRPhotoDAO {

	Connection connection = DatabaseConnection.getConnection();
	public ArrayList<DTRPhoto> getAll(){
		ArrayList<DTRPhoto> list = new ArrayList<DTRPhoto>();
		try {
			DTRPhotoMapper mapper = new DTRPhotoMapper();
			PreparedStatement ps=connection.prepareStatement("select * from dtrphoto");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				list.add(mapper.dtrPhotoMapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
		}
		
		return list;
		
	}
	
	public DTRPhoto getbyReportID(String reportID){
		DTRPhoto dtrPhoto = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from dtrphoto where reportid=?");
			ps.setString(1, reportID);
			ResultSet resultSet = ps.executeQuery();
			DTRPhotoMapper dtrPhotoMapper = new DTRPhotoMapper();
			while(resultSet.next()){
				dtrPhoto = dtrPhotoMapper.dtrPhotoMapper(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
		}
		
		return dtrPhoto;
	}
	
	public void updateUser(DTRPhoto dtrPhoto){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into dtrphoto(reportid,photo) VALUES(?,?)");
			ps.setString(1,dtrPhoto.getReportId());
			ps.setString(2,dtrPhoto.getPhoto());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
