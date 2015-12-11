package mms.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mms.com.beans.LineReport33kv;

public class LineReport33kvMapper {

	public LineReport33kv lineReport33kvMapper(ResultSet resultSet) {
		LineReport33kv lineReport33kv = new LineReport33kv();
		try {
			lineReport33kv.setReportId(resultSet.getString(1));
			lineReport33kv.setDateoformat(resultSet.getString(2));
			lineReport33kv.setLengthofLine(resultSet.getString(3));
			lineReport33kv.setNameofDivision(resultSet.getString(4));
			lineReport33kv.setPermitno(resultSet.getString(5));
			lineReport33kv.setNameofEHVSS(resultSet.getString(6));
			lineReport33kv.setDurationSupplyOff(resultSet.getString(7));
			lineReport33kv.setPermitTaker(resultSet.getString(8));
			lineReport33kv.setReportSubmittedby(resultSet.getString(9));
			lineReport33kv.setFeederName(resultSet.getString(10));
			lineReport33kv.setFeederCode(resultSet.getString(11));
			lineReport33kv.setC16sc(resultSet.getString(12));
			lineReport33kv.setC25sc(resultSet.getString(13));
			lineReport33kv.setC70sc(resultSet.getString(14));
			lineReport33kv.setC150sc(resultSet.getString(15));
			lineReport33kv.setC300sc(resultSet.getString(16));
			lineReport33kv.setKvdi33(resultSet.getString(17));
			lineReport33kv.setKvppi33(resultSet.getString(18));
			lineReport33kv.setKvpi33(resultSet.getString(19));
			lineReport33kv.setGiw(resultSet.getString(20));
			lineReport33kv.setGisw(resultSet.getString(21));
			lineReport33kv.setGiss(resultSet.getString(22));
			lineReport33kv.setKvdo33(resultSet.getString(23));
			lineReport33kv.setSc(resultSet.getString(24));
			lineReport33kv.setKvdof33(resultSet.getString(25));
			lineReport33kv.setMfc(resultSet.getString(26));
			lineReport33kv.setRfu(resultSet.getString(27));
			lineReport33kv.setTcf(resultSet.getString(28));
			lineReport33kv.setGi(resultSet.getString(29));
			lineReport33kv.setToil(resultSet.getString(30));
			lineReport33kv.setRsp(resultSet.getString(31));
			lineReport33kv.setApe(resultSet.getString(32));
			lineReport33kv.setAse(resultSet.getString(33));
			lineReport33kv.setDpp(resultSet.getString(34));
			lineReport33kv.setDsp(resultSet.getString(35));
			lineReport33kv.setRol(resultSet.getString(36));
			lineReport33kv.setCar(resultSet.getString(37));
			lineReport33kv.setTcr(resultSet.getString(38));
			lineReport33kv.setDir(resultSet.getString(39));
			lineReport33kv.setPir(resultSet.getString(40));
			lineReport33kv.setJr(resultSet.getString(41));
			lineReport33kv.setNol(resultSet.getString(42));
			lineReport33kv.setGr(resultSet.getString(43));
			lineReport33kv.setLar(resultSet.getString(44));
			lineReport33kv.setEr(resultSet.getString(45));
			lineReport33kv.setAbscr(resultSet.getString(46));
			lineReport33kv.setAbsr(resultSet.getString(47));
			lineReport33kv.setDogf(resultSet.getString(48));
			lineReport33kv.setDohg(resultSet.getString(49));
			lineReport33kv.setAny(resultSet.getString(50));
			lineReport33kv.setTml(resultSet.getString(51));
			lineReport33kv.setLa911(resultSet.getString(52));
			lineReport33kv.setType(resultSet.getString(53));
			lineReport33kv.setStatus(resultSet.getString(54));
			lineReport33kv.setCircle(resultSet.getString(55));
			lineReport33kv.setCategory(resultSet.getString(56));
			lineReport33kv.setStart_time_hr(resultSet.getString(57));
			lineReport33kv.setStart_time_min(resultSet.getString(58));
			lineReport33kv.setEnd_time_hr(resultSet.getString(59));

			lineReport33kv.setEnd_time_min(resultSet.getString(60));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in [lineReport33kvMapper] "
					+ e);
		}

		return lineReport33kv;

	}
}
