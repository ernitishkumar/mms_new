package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.Location;
import mms.com.mapper.LocationMapper;
import mms.com.utility.DatabaseConnection;

public class LocationDAO {

	Connection connection = DatabaseConnection.getConnection();
	public ArrayList<Location> getAll(){
		ArrayList<Location> list = new ArrayList<Location>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from blukloaction");
			ResultSet resultSet = ps.executeQuery();
			LocationMapper locationMapper = new LocationMapper();
			while(resultSet.next()){
				list.add(locationMapper.locationMapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in getAll "+e);
		} finally{
			
		}
		
		return list;
		
	}
	
	public Location getbyLocationCode(String locationCode){
		Location location = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from blukloaction where locationcode=?");
			ps.setString(1, locationCode);
			ResultSet resultSet = ps.executeQuery();
			LocationMapper locationMapper = new LocationMapper();
			while(resultSet.next()){
				location = locationMapper.locationMapper(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in getbyLocationCode "+e);
		} finally{
			
		}
		
		return location;
	}
	
	public void updateUser(Location location){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into mms_user_login(sno,locationcode,region,circle,division,dc) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1,location.getSno());
			ps.setString(2,location.getLocationCode());
			ps.setString(3,location.getRegion());
			ps.setString(4,location.getCircle());
			ps.setString(5,location.getDivision());
			ps.setString(6,location.getDc());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in p[getbyLocationCode] "+e);
		}
	}
	
}
