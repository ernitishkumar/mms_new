package mms.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mms.com.beans.LineReport11kv;

public class LineReport11kvMapper {

	public LineReport11kv lineReport11kvMapper(ResultSet resultSet) {

		LineReport11kv lineReport11kv = new LineReport11kv();
		try {
			lineReport11kv.setReportId(resultSet.getString(1));
			lineReport11kv.setDateofmt(resultSet.getString(2));
			lineReport11kv.setLenofline(resultSet.getString(3));
			lineReport11kv.setDivision(resultSet.getString(4));
			lineReport11kv.setPermitno(resultSet.getString(5));
			lineReport11kv.setEhvss(resultSet.getString(6));
			lineReport11kv.setDurationsuppoff(resultSet.getString(7));
			lineReport11kv.setPermittaker(resultSet.getString(8));
			lineReport11kv.setSubmittedby(resultSet.getString(9));
			lineReport11kv.setFeedername(resultSet.getString(10));
			lineReport11kv.setFeedercode(resultSet.getString(11));
			lineReport11kv.setResp(resultSet.getString(12));
			lineReport11kv.setAse(resultSet.getString(13));
			lineReport11kv.setDpr(resultSet.getString(14));
			lineReport11kv.setDsr(resultSet.getString(15));
			lineReport11kv.setRos(resultSet.getString(16));
			lineReport11kv.setCar(resultSet.getString(17));
			lineReport11kv.setTcr(resultSet.getString(18));
			lineReport11kv.setDir(resultSet.getString(19));
			lineReport11kv.setPir(resultSet.getString(20));
			lineReport11kv.setJr(resultSet.getString(21));
			lineReport11kv.setNoltc(resultSet.getString(22));
			lineReport11kv.setGr(resultSet.getString(23));
			lineReport11kv.setLar(resultSet.getString(24));
			lineReport11kv.setEr(resultSet.getString(25));
			lineReport11kv.setAbscr(resultSet.getString(26));
			lineReport11kv.setAbsr(resultSet.getString(27));
			lineReport11kv.setDocr(resultSet.getString(28));
			lineReport11kv.setDour(resultSet.getString(29));
			lineReport11kv.setAny(resultSet.getString(30));
			lineReport11kv.setMlf(resultSet.getString(31));
			lineReport11kv.setC16sc(resultSet.getString(32));
			lineReport11kv.setC25sc(resultSet.getString(33));
			lineReport11kv.setC70sc(resultSet.getString(34));
			lineReport11kv.setC150sc(resultSet.getString(35));
			lineReport11kv.setC300sc(resultSet.getString(36));
			lineReport11kv.setKvdi11(resultSet.getString(37));
			lineReport11kv.setKvppI11(resultSet.getString(38));
			lineReport11kv.setKvpi11(resultSet.getString(39));
			lineReport11kv.setGwi(resultSet.getString(40));
			lineReport11kv.setGisw(resultSet.getString(41));
			lineReport11kv.setGiss(resultSet.getString(42));
			lineReport11kv.setG11KVDO(resultSet.getString(43));
			lineReport11kv.setSc(resultSet.getString(44));
			lineReport11kv.setKvdof11(resultSet.getString(45));
			lineReport11kv.setMfc(resultSet.getString(46));
			lineReport11kv.setRfu(resultSet.getString(47));
			lineReport11kv.setTcf(resultSet.getString(48));
			lineReport11kv.setKvla911(resultSet.getString(49));
			lineReport11kv.setGip(resultSet.getString(50));
			lineReport11kv.setToil(resultSet.getString(51));
			lineReport11kv.setApe(resultSet.getString(52));
			lineReport11kv.setTml(resultSet.getString(53));
			lineReport11kv.setType(resultSet.getString(54));
			lineReport11kv.setStatus(resultSet.getString(55));
			lineReport11kv.setCircle(resultSet.getString(56));
			lineReport11kv.setCategory(resultSet.getString(57));
			lineReport11kv.setStart_time_hr(resultSet.getString(58));
			lineReport11kv.setStart_time_min(resultSet.getString(59));
			lineReport11kv.setEnd_time_hr(resultSet.getString(60));

			lineReport11kv.setEnd_time_min(resultSet.getString(61));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in [lineReport11kvMapper]"+e);
		}
		return lineReport11kv;
	}
}
