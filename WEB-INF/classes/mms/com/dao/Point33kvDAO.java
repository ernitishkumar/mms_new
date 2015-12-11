package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.Point33kv;
import mms.com.mapper.Point33kvMapper;
import mms.com.utility.DatabaseConnection;

public class Point33kvDAO {

	Connection connection = DatabaseConnection.getConnection();
	public ArrayList<Point33kv> getAll(){
		ArrayList<Point33kv> list = new ArrayList<Point33kv>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_33kv_point");
			ResultSet resultSet = ps.executeQuery();
			Point33kvMapper point33kvMapper = new Point33kvMapper();
			while(resultSet.next()){
				list.add(point33kvMapper.point33kvMapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [getAll] "+e);
		} finally{
			
		}
		
		return list;
		
	}
	
	public Point33kv getbyID(String id){
		Point33kv point33kv = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_33kv_point where id=?");
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();
			Point33kvMapper point33kvMapper = new Point33kvMapper();
			while(resultSet.next()){
				point33kv = point33kvMapper.point33kvMapper(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [getbyID] "+e);
		} finally{
			
		}
		
		return point33kv;
	}
	
	public void updatePoint33kv(Point33kv point33kv){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into mms_33kv_point(id,point,cat1,cat2,status) VALUES(?,?,?,?,?)");
			ps.setString(1,point33kv.getId());
			ps.setString(2,point33kv.getPoint());
			ps.setString(2,point33kv.getCat1());
			ps.setString(2,point33kv.getCat2());
			ps.setString(2,point33kv.getStatus());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updatePoint33kv] "+e);
		}
	}
}
