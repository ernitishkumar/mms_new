package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.Location;
import mms.com.mapper.LocationMapper;
import mms.com.utility.DatabaseConnection;

public class LocationHelper {

	Connection connection = DatabaseConnection.getConnection();
	
	public ArrayList<String> getCirclesByRegionId(String regionId){
		ArrayList<String> locations = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from CIRCLE where region_code=?");
			ps.setInt(1, Integer.parseInt(regionId));
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				locations.add(resultSet.getString(4).trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in Class : LocationHelper : Method : getCirclesByRegionId "+e);
		} finally{
			
		}
		System.out.println("Size of Circle for region Id : "+regionId+"  is : "+locations.size());
		return locations;
	}

	public ArrayList<String> getCirclesByRegionName(String regionName){
		ArrayList<String> locations = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from CIRCLE where region_name=?");
			ps.setString(1, regionName);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				locations.add(resultSet.getString(4).trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in Class : LocationHelper : Method : getCirclesByRegionId "+e);
		} finally{
			
		}
		System.out.println("Size of Circle for region Name : "+regionName+"  is : "+locations.size());
		return locations;
	}
	
}
