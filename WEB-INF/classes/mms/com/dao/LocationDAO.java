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
	
	public ArrayList<String> getCirclesByRegionName(String regionName){
		ArrayList<String> locations = new ArrayList<String>();
		try {
			PreparedStatement ps=connection.prepareStatement("SELECT distinct(circle_name) FROM mms_feeder_list m where resion=?");
			ps.setString(1, regionName);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				locations.add(resultSet.getString(1).trim());
			}
		} catch (SQLException e) {
			System.out.println("Exception occured in Class : LocationDAO : Method : getCirclesByRegionName"+e);
		} finally{
			
		}
		//System.out.println("Size of Circle for region Name : "+regionName+"  is : "+locations.size());
		return locations;
	}

	public ArrayList<String> getCircles(){
		ArrayList<String> locations = new ArrayList<String>();
		try {
			PreparedStatement ps=connection.prepareStatement("SELECT distinct(circle_name) FROM mms_feeder_list b");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				locations.add(resultSet.getString(1).trim());
			}
		} catch (SQLException e) {
			System.out.println("Exception occured in Class : LocationDAO : Method : getCircles"+e);
		} finally{
			
		}
		//System.out.println("Size of All circles is : "+locations.size());
		return locations;
	}

	public ArrayList<String> getDivisionByCircleName(String circleName){
		ArrayList<String> locations = new ArrayList<String>();
		try {
			PreparedStatement ps=connection.prepareStatement("SELECT distinct(division) FROM mms_feeder_list where circle_name=?");
			ps.setString(1, circleName);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				locations.add(resultSet.getString(1).trim());
			}
		} catch (SQLException e) {
			System.out.println("Exception occured in Class : LocationDAO : Method : getDivisionByCircleName"+e);
		} finally{
			
		}
		//System.out.println("Size of divisions for circle Name : "+circleName+"  is : "+locations.size());
		return locations;
	}

	public ArrayList<String> getDivisionByRegionName(String regionName){
		ArrayList<String> locations = new ArrayList<String>();
		try {
			PreparedStatement ps=connection.prepareStatement("SELECT distinct(division) FROM mms_feeder_list where resion=?");
			ps.setString(1, regionName);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				locations.add(resultSet.getString(1).trim());
			}
		} catch (SQLException e) {
			System.out.println("Exception occured in Class : LocationDAO : Method : getDivisionByRegionName"+e);
		} finally{
			
		}
		//System.out.println("Size of divisions for circle Name : "+circleName+"  is : "+locations.size());
		return locations;
	}

	public ArrayList<String> getAllDivisions(){
		ArrayList<String> locations = new ArrayList<String>();
		try {
			PreparedStatement ps=connection.prepareStatement("SELECT distinct(division) FROM mms_feeder_list");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				locations.add(resultSet.getString(1).trim());
			}
		} catch (SQLException e) {
			System.out.println("Exception occured in Class : LocationDAO : Method : getAllDivisions()"+e);
		} finally{
			
		}
		//System.out.println("Size of divisions for circle Name : "+circleName+"  is : "+locations.size());
		return locations;
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
