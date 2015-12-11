package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.ReportPoint33kvDesc;
import mms.com.mapper.ReportPoint33kvDescMapper;
import mms.com.utility.DatabaseConnection;

public class ReportPoint33kvDescDAO {

	Connection connection = DatabaseConnection.getConnection();
	public ArrayList<ReportPoint33kvDesc> getAll(){
		ArrayList<ReportPoint33kvDesc> list = new ArrayList<ReportPoint33kvDesc>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mss_33kv_report_point_desc");
			ResultSet resultSet = ps.executeQuery();
			ReportPoint33kvDescMapper reportPoint33kvDescMapper = new ReportPoint33kvDescMapper();
			while(resultSet.next()){
				list.add(reportPoint33kvDescMapper.repotPoint33kvDescMapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
		}
		
		return list;
		
	}
	
	public ReportPoint33kvDesc getbyReportID(String reportID){
		ReportPoint33kvDesc reportPoint33kvDesc = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mss_33kv_report_point_desc where report_id=?");
			ps.setString(1, reportID);
			ResultSet resultSet = ps.executeQuery();
			ReportPoint33kvDescMapper reportPoint33kvDescMapper = new ReportPoint33kvDescMapper();
			while(resultSet.next()){
				reportPoint33kvDesc = reportPoint33kvDescMapper.repotPoint33kvDescMapper(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
		}
		
		return reportPoint33kvDesc;
	}
	
	public ReportPoint33kvDesc getbyPointID(String pointID){
		ReportPoint33kvDesc reportPoint33kvDesc = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mss_33kv_report_point_desc where point_id=?");
			ps.setString(1, pointID);
			ResultSet resultSet = ps.executeQuery();
			ReportPoint33kvDescMapper reportPoint33kvDescMapper = new ReportPoint33kvDescMapper();
			while(resultSet.next()){
				reportPoint33kvDesc = reportPoint33kvDescMapper.repotPoint33kvDescMapper(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
		}
		
		return reportPoint33kvDesc;
	}
	
	public void updateUser(ReportPoint33kvDesc reportPoint33kvDesc){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into mss_33kv_report_point_desc(id,point_id,point,mt_desc,report_id) VALUES(?,?,?,?,?)");
			ps.setString(1,reportPoint33kvDesc.getId());
			ps.setString(2,reportPoint33kvDesc.getPoint_id());
			ps.setString(3,reportPoint33kvDesc.getPoint());
			ps.setString(4,reportPoint33kvDesc.getMt_desc());
			ps.setString(5,reportPoint33kvDesc.getReport_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
