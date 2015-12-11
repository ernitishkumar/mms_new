package mms.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mms.com.beans.Photo33kv;

public class Photo33kvMapper {

	public Photo33kv photo33kvMapper(ResultSet resultSet) {

		Photo33kv photo33kv = new Photo33kv();
		try {
			photo33kv.setReportid(resultSet.getString(1));
			photo33kv.setPhoto(resultSet.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [photo33kvMapper] " + e);
		}
		return photo33kv;
	}
}
