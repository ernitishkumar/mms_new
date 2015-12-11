package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.LineReport33kv;
import mms.com.mapper.LineReport33kvMapper;
import mms.com.utility.DatabaseConnection;

public class LineReport33kvDAO {

	Connection connection = DatabaseConnection.getConnection();

	public ArrayList<LineReport33kv> getAll() {
		ArrayList<LineReport33kv> list = new ArrayList<LineReport33kv>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from mms_33_kv_line_report");
			ResultSet resultSet = ps.executeQuery();
			LineReport33kvMapper lineReport33kvMapper = new LineReport33kvMapper();
			while (resultSet.next()) {
				list.add(lineReport33kvMapper.lineReport33kvMapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [getAll] "+e);
		} finally {

		}

		return list;

	}

	public LineReport33kv getbyReportID(String reportID) {
		LineReport33kv lineReport33kv = null;
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from mms_33_kv_line_report where reportid=?");
			ps.setString(1, reportID);
			ResultSet resultSet = ps.executeQuery();
			LineReport33kvMapper lineReport33kvMapper = new LineReport33kvMapper();
			while(resultSet.next()){
				lineReport33kv = lineReport33kvMapper.lineReport33kvMapper(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [getbyReportID] "+e);
		} finally {

		}

		return lineReport33kv;
	}

	public void updateLineReport(LineReport33kv lineReport33kv) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("insert into mms_33_kv_line_report(reportid, dateofmt, lengthofline, nameofdivision, permitno, nameofEHVSS, durationsupplyoff, permittaker, reportsubmittedby, feedername, feedercode, C16SC, C25SC, C70SC, C150SC, C300SC, 33KVDI, 33KVPPI, 33KVPI, GIW, GISW, GISS, 33KVDO, SC, 33KVDOF, MFC, RFU, TCF, GI, TOIL, RSP, APE, ASE, DPP, DSP, ROL, CAR, TCR, DIR, PIR, JR, NOL, GR, LAR, ER, ABSCR, ABSR, DOGF, DOHG, ANY, TML, 911LA, type, status, circle, category, start_time_hr, start_time_min, end_time_hr, end_time_min) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, lineReport33kv.getReportId());
			ps.setString(2, lineReport33kv.getDateoformat());
			ps.setString(3, lineReport33kv.getLengthofLine());
			ps.setString(4, lineReport33kv.getNameofDivision());
			ps.setString(5, lineReport33kv.getPermitno());
			ps.setString(6, lineReport33kv.getNameofEHVSS());
			ps.setString(7, lineReport33kv.getDurationSupplyOff());
			ps.setString(8, lineReport33kv.getPermitTaker());
			ps.setString(9, lineReport33kv.getReportSubmittedby());
			ps.setString(10, lineReport33kv.getFeederName());
			ps.setString(11, lineReport33kv.getC16sc());
			ps.setString(12, lineReport33kv.getC25sc());
			ps.setString(13, lineReport33kv.getC70sc());
			ps.setString(14, lineReport33kv.getC150sc());
			ps.setString(15, lineReport33kv.getC300sc());
			ps.setString(16, lineReport33kv.getKvdi33());
			ps.setString(17, lineReport33kv.getKvppi33());
			ps.setString(18, lineReport33kv.getKvpi33());
			ps.setString(19, lineReport33kv.getGiw());
			ps.setString(20, lineReport33kv.getGisw());
			ps.setString(21, lineReport33kv.getGiss());
			ps.setString(22, lineReport33kv.getKvdo33());
			ps.setString(23, lineReport33kv.getSc());
			ps.setString(24, lineReport33kv.getKvdof33());
			ps.setString(25, lineReport33kv.getMfc());
			ps.setString(26, lineReport33kv.getRfu());
			ps.setString(27, lineReport33kv.getTcf());
			ps.setString(28, lineReport33kv.getGi());
			ps.setString(29, lineReport33kv.getToil());
			ps.setString(30, lineReport33kv.getRsp());
			ps.setString(31, lineReport33kv.getApe());
			ps.setString(32, lineReport33kv.getAse());
			ps.setString(33, lineReport33kv.getDpp());
			ps.setString(34, lineReport33kv.getDsp());
			ps.setString(35, lineReport33kv.getRol());
			ps.setString(36, lineReport33kv.getCar());
			ps.setString(37, lineReport33kv.getTcr());
			ps.setString(38, lineReport33kv.getDir());
			ps.setString(39, lineReport33kv.getPir());
			ps.setString(40, lineReport33kv.getJr());
			ps.setString(41, lineReport33kv.getNol());
			ps.setString(42, lineReport33kv.getGr());
			ps.setString(43, lineReport33kv.getLar());
			ps.setString(44, lineReport33kv.getEr());
			ps.setString(45, lineReport33kv.getAbscr());
			ps.setString(46, lineReport33kv.getAbsr());
			ps.setString(47, lineReport33kv.getDogf());
			ps.setString(48, lineReport33kv.getDohg());
			ps.setString(49, lineReport33kv.getAny());
			ps.setString(50, lineReport33kv.getTml());
			ps.setString(51, lineReport33kv.getLa911());
			ps.setString(52, lineReport33kv.getType());
			ps.setString(53, lineReport33kv.getStatus());
			ps.setString(54, lineReport33kv.getCircle());
			ps.setString(55, lineReport33kv.getCategory());
			ps.setString(56, lineReport33kv.getStart_time_hr());
			ps.setString(57, lineReport33kv.getStart_time_min());
			ps.setString(58, lineReport33kv.getEnd_time_hr());
			ps.setString(59, lineReport33kv.getEnd_time_min());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updateLineReport] "+e);
		}
	}
}
