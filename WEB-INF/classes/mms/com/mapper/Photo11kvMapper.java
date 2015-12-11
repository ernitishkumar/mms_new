package mms.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mms.com.beans.Photo11kv;

public class Photo11kvMapper {

	public Photo11kv photo11kvMapper(ResultSet resultSet) {
		Photo11kv photo11kv = new Photo11kv();
		try {
			photo11kv.setReportid(resultSet.getString(2));
			photo11kv.setPhoto(resultSet.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [photo11kvMapper] "+e);
		}
		return photo11kv;
	}
}
