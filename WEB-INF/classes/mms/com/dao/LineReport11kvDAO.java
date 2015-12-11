package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.LineReport11kv;
import mms.com.mapper.LineReport11kvMapper;
import mms.com.utility.DatabaseConnection;

public class LineReport11kvDAO {

	Connection connection = DatabaseConnection.getConnection();
	public ArrayList<LineReport11kv> getAll(){
		ArrayList<LineReport11kv> list = new ArrayList<LineReport11kv>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_11kv_line_report");
			ResultSet resultSet = ps.executeQuery();
			LineReport11kvMapper mapper = new LineReport11kvMapper();
			while(resultSet.next()){
				list.add(mapper.lineReport11kvMapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
		}
		
		return list;
		
	}
	
	public LineReport11kv getbyReportID(String reportID){
		LineReport11kv lineReport11kv = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_11kv_line_report where reportid=?");
			ps.setString(1, reportID);
			ResultSet resultSet = ps.executeQuery();
			LineReport11kvMapper mapper = new LineReport11kvMapper();
			while(resultSet.next())
			lineReport11kv = mapper.lineReport11kvMapper(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
		}
		
		return lineReport11kv;
	}
	
	public void updateUser(LineReport11kv lineReport11kv){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into mms_11kv_line_report(reportid, dateofmt, lenofline, division, permitno, ehvss, durationsuppoff, permittaker, submittedby, feedername, feedercode, RESP, ASE, DPR, DSR, ROS, CAR, TCR, DIR, PIR, JR, NOLTC, GR, LAR, ER, ABSCR, ABSR, DOCR, DOUR, ANY, MLF, c16sc, c25sc, c70sc, c150sc, c300sc, 11KVDI, 11KVPPI, 11KVPI, GWI, GISW, GISS, G11KVDO, SC, 11KVDOF, MFC, RFU, TCF, 911KVLA, GIP, TOIL, APE, tml, type, status, circle, category, start_time_hr, start_time_min, end_time_hr, end_time_min) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,lineReport11kv.getReportId());
			ps.setString(2,lineReport11kv.getDateofmt());
			ps.setString(3,lineReport11kv.getLenofline());
			ps.setString(4,lineReport11kv.getDivision());
			ps.setString(5,lineReport11kv.getPermitno());
			ps.setString(6,lineReport11kv.getEhvss());
			ps.setString(7,lineReport11kv.getDurationsuppoff());
			ps.setString(8,lineReport11kv.getPermittaker());
			ps.setString(9,lineReport11kv.getSubmittedby());
			ps.setString(10,lineReport11kv.getFeedername());
			ps.setString(11,lineReport11kv.getFeedercode());
			ps.setString(12,lineReport11kv.getResp());
			ps.setString(13,lineReport11kv.getAse());
			ps.setString(14,lineReport11kv.getDpr());
			ps.setString(15,lineReport11kv.getDsr());
			ps.setString(16,lineReport11kv.getRos());
			ps.setString(17,lineReport11kv.getCar());
			ps.setString(18,lineReport11kv.getTcr());
			ps.setString(19,lineReport11kv.getDir());
			ps.setString(20,lineReport11kv.getPir());
			ps.setString(21,lineReport11kv.getJr());
			ps.setString(22,lineReport11kv.getNoltc());
			ps.setString(23,lineReport11kv.getGr());
			ps.setString(24,lineReport11kv.getLar());
			ps.setString(25,lineReport11kv.getEr());
			ps.setString(26,lineReport11kv.getAbscr());
			ps.setString(27,lineReport11kv.getAbsr());
			ps.setString(28,lineReport11kv.getDocr());
			ps.setString(29,lineReport11kv.getDour());
			ps.setString(30,lineReport11kv.getAny());
			ps.setString(31,lineReport11kv.getMlf());
			ps.setString(32,lineReport11kv.getC16sc());
			ps.setString(33,lineReport11kv.getC25sc());
			ps.setString(34,lineReport11kv.getC70sc());
			ps.setString(35,lineReport11kv.getC150sc());
			ps.setString(36,lineReport11kv.getC300sc());
			ps.setString(37,lineReport11kv.getKvdi11());
			ps.setString(38,lineReport11kv.getKvppI11());
			ps.setString(39,lineReport11kv.getKvpi11());
			ps.setString(40,lineReport11kv.getGwi());
			ps.setString(41,lineReport11kv.getGisw());
			ps.setString(42,lineReport11kv.getGiss());
			ps.setString(43,lineReport11kv.getG11KVDO());
			ps.setString(44,lineReport11kv.getSc());
			ps.setString(45,lineReport11kv.getKvdof11());
			ps.setString(46,lineReport11kv.getMfc());
			ps.setString(47,lineReport11kv.getRfu());
			ps.setString(48,lineReport11kv.getTcf());
			ps.setString(49,lineReport11kv.getKvla911());
			ps.setString(50,lineReport11kv.getGip());
			ps.setString(51,lineReport11kv.getToil());
			ps.setString(52,lineReport11kv.getApe());
			ps.setString(53,lineReport11kv.getTml());
			ps.setString(54,lineReport11kv.getType());
			ps.setString(55,lineReport11kv.getStatus());
			ps.setString(56,lineReport11kv.getCircle());
			ps.setString(57,lineReport11kv.getCategory());
			ps.setString(58,lineReport11kv.getStart_time_hr());
			ps.setString(59,lineReport11kv.getStart_time_min());
			ps.setString(60,lineReport11kv.getEnd_time_hr());
			ps.setString(61,lineReport11kv.getEnd_time_min());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
