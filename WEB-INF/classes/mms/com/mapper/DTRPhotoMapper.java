package mms.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mms.com.beans.DTRPhoto;

public class DTRPhotoMapper {

	public DTRPhoto dtrPhotoMapper(ResultSet resultSet){
		DTRPhoto dtrPhoto = new DTRPhoto();
		try {
			dtrPhoto.setReportId(resultSet.getString(1));
			dtrPhoto.setPhoto(resultSet.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [dtrPhotoMApper]"+e);
		}
		
		return dtrPhoto;
	}
}
