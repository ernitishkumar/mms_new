package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.Point11kv;
import mms.com.mapper.Point11kvMapper;
import mms.com.utility.DatabaseConnection;

public class Point11kvDAO {

	Connection connection = DatabaseConnection.getConnection();
	public ArrayList<Point11kv> getAll(){
		ArrayList<Point11kv> list = new ArrayList<Point11kv>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_11kv_point");
			ResultSet resultSet = ps.executeQuery();
			Point11kvMapper point11kvMapper = new Point11kvMapper();
			while(resultSet.next()){
				list.add(point11kvMapper.point11kvMapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [getAll] "+e);
		} finally{
			
		}
		
		return list;
		
	}
	
	public Point11kv getbyID(String id){
		Point11kv point11kv = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_11kv_point where id=?");
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();
			Point11kvMapper point11kvMapper = new Point11kvMapper();
			while(resultSet.next()){
				point11kv = point11kvMapper.point11kvMapper(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [getbyID] "+e);
		} finally{
			
		}
		
		return point11kv;
	}
	
	public void updatePoint11kv(Point11kv point11kv){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into mms_11kv_point(id,point,cat1,cat2,status) VALUES(?,?,?,?,?)");
			ps.setString(1,point11kv.getId());
			ps.setString(2,point11kv.getPoint());
			ps.setString(2,point11kv.getCat1());
			ps.setString(2,point11kv.getCat2());
			ps.setString(2,point11kv.getStatus());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updatePoint11kv] "+e);
		}
	}
}
