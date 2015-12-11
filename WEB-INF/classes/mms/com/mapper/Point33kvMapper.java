package mms.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mms.com.beans.Point33kv;

public class Point33kvMapper {

	public Point33kv point33kvMapper(ResultSet resultSet){
		Point33kv point33kv = new Point33kv();
		try {
		point33kv.setId(resultSet.getString(1));
		point33kv.setPoint(resultSet.getString(2));
		point33kv.setCat1(resultSet.getString(3));
		point33kv.setCat2(resultSet.getString(4));
		
			point33kv.setStatus(resultSet.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [point33kvMapper] "+e);
		}
		return point33kv;
	}
}
