package mms.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mms.com.beans.Location;

public class LocationMapper {

	public Location locationMapper(ResultSet resultSet) {

		Location location = new Location();
		try {
			location.setSno(resultSet.getString(1));
			location.setLocationCode(resultSet.getString(2));
			location.setRegion(resultSet.getString(3));
			location.setCircle(resultSet.getString(4));
			location.setDivision(resultSet.getString(5));
			location.setDc(resultSet.getString(6));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [LocationMapper] " + e);
		}
		return location;
	}
}
