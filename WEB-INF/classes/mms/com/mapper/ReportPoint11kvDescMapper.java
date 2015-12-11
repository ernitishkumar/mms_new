package mms.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mms.com.beans.ReportPoint11kvDesc;

public class ReportPoint11kvDescMapper {

	public ReportPoint11kvDesc reportPoint11kvDescMapper(ResultSet resultSet) {
		ReportPoint11kvDesc reportPoint11kvDesc = new ReportPoint11kvDesc();
		try {
			reportPoint11kvDesc.setId(resultSet.getString(1));
			reportPoint11kvDesc.setPoint_id(resultSet.getString(2));
			reportPoint11kvDesc.setPoint(resultSet.getString(3));
			reportPoint11kvDesc.setMt_desc(resultSet.getString(4));
			reportPoint11kvDesc.setReport_id(resultSet.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [reportPoint11kvDescMapper] " + e);
		}
		return reportPoint11kvDesc;
	}
}
