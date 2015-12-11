package mms.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mms.com.beans.Point11kv;

public class Point11kvMapper {

	public Point11kv point11kvMapper(ResultSet resultSet) {

		Point11kv point11kv = new Point11kv();
		try {
			point11kv.setId(resultSet.getString(1));
			point11kv.setPoint(resultSet.getString(2));
			point11kv.setCat1(resultSet.getString(3));
			point11kv.setCat2(resultSet.getString(4));
			point11kv.setStatus(resultSet.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [point11kvMapper] "+e);
		}
		return point11kv;
	}
}
