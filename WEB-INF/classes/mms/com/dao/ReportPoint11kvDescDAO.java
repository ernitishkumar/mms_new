package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.ReportPoint11kvDesc;
import mms.com.mapper.ReportPoint11kvDescMapper;
import mms.com.utility.DatabaseConnection;

public class ReportPoint11kvDescDAO {

	Connection connection = DatabaseConnection.getConnection();
	public ArrayList<ReportPoint11kvDesc> getAll(){
		ArrayList<ReportPoint11kvDesc> list = new ArrayList<ReportPoint11kvDesc>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_11kv_report_point_desc");
			ResultSet resultSet = ps.executeQuery();
			ReportPoint11kvDescMapper reportPoint11kvDescMapper = new ReportPoint11kvDescMapper();
			while(resultSet.next()){
				list.add(reportPoint11kvDescMapper.reportPoint11kvDescMapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [getAll] "+e);
		} finally{
			
		}
		
		return list;
		
	}
	
	public ReportPoint11kvDesc getbyReportID(String reportID){
		ReportPoint11kvDesc reportPoint11kvDesc = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_11kv_report_point_desc where report_id=?");
			ps.setString(1, reportID);
			ResultSet resultSet = ps.executeQuery();
			ReportPoint11kvDescMapper reportPoint11kvDescMapper = new ReportPoint11kvDescMapper();
			while(resultSet.next()){
				reportPoint11kvDesc = reportPoint11kvDescMapper.reportPoint11kvDescMapper(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [getbyReportID] "+e);
		} finally{
			
		}
		
		return reportPoint11kvDesc;
	}
	
	public ReportPoint11kvDesc getbyPointID(String pointID){
		ReportPoint11kvDesc reportPoint11kvDesc = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_11kv_report_point_desc where point_id=?");
			ps.setString(1, pointID);
			ResultSet resultSet = ps.executeQuery();
			ReportPoint11kvDescMapper reportPoint11kvDescMapper = new ReportPoint11kvDescMapper();
			while(resultSet.next()){
				reportPoint11kvDesc = reportPoint11kvDescMapper.reportPoint11kvDescMapper(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [getbyPointID] "+e);
		} finally{
			
		}
		
		return reportPoint11kvDesc;
	}
	
	public void update11kvReportPoint(ReportPoint11kvDesc reportPoint11kvDesc){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into mms_11kv_report_point_desc(id,point_id,point,mt_desc,report_id) VALUES(?,?,?,?,?)");
			ps.setString(1,reportPoint11kvDesc.getId());
			ps.setString(2,reportPoint11kvDesc.getPoint_id());
			ps.setString(3,reportPoint11kvDesc.getPoint());
			ps.setString(4,reportPoint11kvDesc.getMt_desc());
			ps.setString(5,reportPoint11kvDesc.getReport_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [update11kvReportPoint] "+e);
		}
	}
}
