package mms.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mms.com.beans.ReportPoint33kvDesc;

public class ReportPoint33kvDescMapper {

	public ReportPoint33kvDesc repotPoint33kvDescMapper(ResultSet resultSet) {
		ReportPoint33kvDesc reportPoint33kvDesc = new ReportPoint33kvDesc();
		try {
			reportPoint33kvDesc.setId(resultSet.getString(1));
			reportPoint33kvDesc.setPoint_id(resultSet.getString(2));
			reportPoint33kvDesc.setPoint(resultSet.getString(3));
			reportPoint33kvDesc.setMt_desc(resultSet.getString(4));
			reportPoint33kvDesc.setReport_id(resultSet.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [repotPoint33kvDescMapper] " + e);
		}
		return reportPoint33kvDesc;
	}
}
