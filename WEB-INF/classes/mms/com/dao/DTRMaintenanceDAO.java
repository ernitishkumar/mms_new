package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.DTRMaintenance;
import mms.com.mapper.DTRMaintenanceMapper;
import mms.com.utility.DatabaseConnection;

public class DTRMaintenanceDAO {

	Connection connection = DatabaseConnection.getConnection();
	public ArrayList<DTRMaintenance> getAll(){
		ArrayList<DTRMaintenance> list = new ArrayList<DTRMaintenance>();
		try {
			DTRMaintenanceMapper dtrMaintenanceMapper = new DTRMaintenanceMapper();
			PreparedStatement ps=connection.prepareStatement("select * from dtrmaintenance");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				list.add(dtrMaintenanceMapper.dtrMaintenanceMapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [getAll]"+e);
		} finally{
			
		}
		
		return list;
		
	}
	
	public DTRMaintenance getbyReportID(String reportID){
		DTRMaintenance dtrMaintenance = null;
		try {
			PreparedStatement ps=connection.prepareStatement("select * from dtrmaintenance where reportid=?");
			ps.setString(1, reportID);
			ResultSet resultSet = ps.executeQuery();
			DTRMaintenanceMapper dtrMaintenanceMapper = new DTRMaintenanceMapper();
			while(resultSet.next()){
				dtrMaintenance = dtrMaintenanceMapper.dtrMaintenanceMapper(resultSet);
			}
		} catch (SQLException e) {
			System.out.println("Exception in [getReportBy]"+e);
			e.printStackTrace();
		} finally{
			
		}
		
		return dtrMaintenance;
	}
	
	public void updateUser(DTRMaintenance dtrMaintenance){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into dtrmaintenance(reportid, locatiocode, dom, cot, 11kvfeedercont, nameofdczone, doso, 11kvpermitno, permittaker, lightingarstr, abswitchmail, doset, abswitchpart, dofuse, htbusing, brithersilica, transfermroil, ltbusing, robust, ething, ltbusingrod, trnsfermacln, oillekage, ltkm, treecuting, galelose, dulup, phasesptr, tsnfrmrfedder, polesidhe, statetight, khrbstate, dymndgarding, lambegaleme, linecondtr, ltcable, type, anyother, start_time_hr, start_time_min, end_time_hr, end_time_min, anyother1) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,dtrMaintenance.getReportid());
			ps.setString(2,dtrMaintenance.getLocatioCode());
			ps.setString(3,dtrMaintenance.getDom());
			ps.setString(4,dtrMaintenance.getCot());
			ps.setString(5,dtrMaintenance.getFeedercont11kv());
			ps.setString(6,dtrMaintenance.getNameofdczone());
			ps.setString(7,dtrMaintenance.getDoso());
			ps.setString(8,dtrMaintenance.getPermitno11kv());
			ps.setString(9,dtrMaintenance.getPermittaker());
			ps.setString(10,dtrMaintenance.getLighteningarstr());
			ps.setString(11,dtrMaintenance.getAbswitchmail());
			ps.setString(12,dtrMaintenance.getDoset());
			ps.setString(13,dtrMaintenance.getAbswitchpart());
			ps.setString(14,dtrMaintenance.getDofuse());
			ps.setString(15,dtrMaintenance.getHtbusing());
			ps.setString(16,dtrMaintenance.getBrithersilica());
			ps.setString(17,dtrMaintenance.getTransfermroil());
			ps.setString(18,dtrMaintenance.getLtbusing());
			ps.setString(19,dtrMaintenance.getRobust());
			ps.setString(20,dtrMaintenance.getEthing());
			ps.setString(21,dtrMaintenance.getLtbusingrod());
			ps.setString(22,dtrMaintenance.getTrnsfermacln());
			ps.setString(23,dtrMaintenance.getOillekage());
			ps.setString(24,dtrMaintenance.getLtkm());
			ps.setString(25,dtrMaintenance.getTreecuting());
			ps.setString(26,dtrMaintenance.getGalelose());
			ps.setString(27,dtrMaintenance.getDulup());
			ps.setString(28,dtrMaintenance.getPhasesptr());
			ps.setString(29,dtrMaintenance.getTsnfrmrfedder());
			ps.setString(30,dtrMaintenance.getPolesidhe());
			ps.setString(31,dtrMaintenance.getStatetight());
			ps.setString(32,dtrMaintenance.getKhrbstate());
			ps.setString(33,dtrMaintenance.getDymndgarding());
			ps.setString(34,dtrMaintenance.getLambegaleme());
			ps.setString(35,dtrMaintenance.getLinecondtr());
			ps.setString(36,dtrMaintenance.getLtcable());
			ps.setString(37,dtrMaintenance.getType());
			ps.setString(38,dtrMaintenance.getAnyother());
			ps.setString(39,dtrMaintenance.getStart_time_hr());
			ps.setString(40,dtrMaintenance.getStart_time_min());
			ps.setString(41,dtrMaintenance.getEnd_time_hr());
			ps.setString(42,dtrMaintenance.getEnd_time_min());
			ps.setString(43, dtrMaintenance.getAnyother1());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}
	}
}
