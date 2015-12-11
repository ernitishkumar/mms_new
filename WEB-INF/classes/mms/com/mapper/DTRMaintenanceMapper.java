package mms.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mms.com.beans.DTRMaintenance;

public class DTRMaintenanceMapper {

	public DTRMaintenance dtrMaintenanceMapper(ResultSet resultSet){
	
		DTRMaintenance dtrMaintenance = new DTRMaintenance();
		try {
		dtrMaintenance.setReportid(resultSet.getString(1));
		dtrMaintenance.setLocatioCode(resultSet.getString(2));
		dtrMaintenance.setDom(resultSet.getString(3));
		dtrMaintenance.setCot(resultSet.getString(4));
		dtrMaintenance.setFeedercont11kv(resultSet.getString(5));
		dtrMaintenance.setNameofdczone(resultSet.getString(6));
		dtrMaintenance.setDoso(resultSet.getString(7));
		dtrMaintenance.setPermitno11kv(resultSet.getString(8));
		dtrMaintenance.setPermittaker(resultSet.getString(9));
		dtrMaintenance.setLighteningarstr(resultSet.getString(10));
		dtrMaintenance.setAbswitchmail(resultSet.getString(11));
		dtrMaintenance.setDoset(resultSet.getString(12));
		dtrMaintenance.setAbswitchpart(resultSet.getString(13));
		dtrMaintenance.setDofuse(resultSet.getString(14));
		dtrMaintenance.setHtbusing(resultSet.getString(15));
		dtrMaintenance.setBrithersilica(resultSet.getString(16));
		dtrMaintenance.setTransfermroil(resultSet.getString(17));
		dtrMaintenance.setLtbusing(resultSet.getString(18));
		dtrMaintenance.setRobust(resultSet.getString(19));
		dtrMaintenance.setEthing(resultSet.getString(20));
		dtrMaintenance.setLtbusingrod(resultSet.getString(21));
		dtrMaintenance.setTrnsfermacln(resultSet.getString(22));
		dtrMaintenance.setOillekage(resultSet.getString(23));
		dtrMaintenance.setLtkm(resultSet.getString(24));
		dtrMaintenance.setTreecuting(resultSet.getString(25));
		dtrMaintenance.setGalelose(resultSet.getString(26));
		dtrMaintenance.setDulup(resultSet.getString(27));
		dtrMaintenance.setPhasesptr(resultSet.getString(28));
		dtrMaintenance.setTsnfrmrfedder(resultSet.getString(29));
		dtrMaintenance.setPolesidhe(resultSet.getString(30));
		dtrMaintenance.setStatetight(resultSet.getString(31));
		dtrMaintenance.setKhrbstate(resultSet.getString(32));
		dtrMaintenance.setDymndgarding(resultSet.getString(33));
		dtrMaintenance.setLambegaleme(resultSet.getString(34));
		dtrMaintenance.setLinecondtr(resultSet.getString(35));
		dtrMaintenance.setLtcable(resultSet.getString(36));
		dtrMaintenance.setType(resultSet.getString(37));
		dtrMaintenance.setAnyother(resultSet.getString(38));
		dtrMaintenance.setStart_time_hr(resultSet.getString(39));
		dtrMaintenance.setStart_time_min(resultSet.getString(40));
		dtrMaintenance.setEnd_time_hr(resultSet.getString(41));
		dtrMaintenance.setEnd_time_min(resultSet.getString(42));
		dtrMaintenance.setAnyother1(resultSet.getString(43));
		} catch (SQLException e) {
			System.out.println("Exception in [dtrMaintenanceMapper]"+e);
			e.printStackTrace();
		}
		return dtrMaintenance;
	}
}
