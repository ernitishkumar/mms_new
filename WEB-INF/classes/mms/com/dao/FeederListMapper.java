package mms.com.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import mms.com.beans.FeederList;
public class FeederListMapper {

	public FeederList feederListMapper(ResultSet resultSet)
	{
		FeederList feederList = new FeederList();
		try {
			feederList.setRESION(resultSet.getString(1));
			feederList.setCIRCLE_NAME(resultSet.getString(2));
			feederList.setDIVISION(resultSet.getString(3));
			feederList.setEHV_SS_CODE(resultSet.getString(4));
			feederList.setEHV_SS_NAME(resultSet.getString(5));
			feederList.setKV33_FDR_CODE(resultSet.getString(6));
			feederList.setKV33_FDR_NAME(resultSet.getString(7));
			feederList.setGROUP_ALLOTED(resultSet.getString(8));
			feederList.setSUBSTATION_NAME_33_11(resultSet.getString(9));
			feederList.setKV_SUBSTATION_CODE_33(resultSet.getString(10));
			feederList.setKV11FDR_NAME(resultSet.getString(11));
			feederList.setKV11FDR_CODE(resultSet.getString(12));
			feederList.setCATEGORY(resultSet.getString(13));
			feederList.setTYPE(resultSet.getString(14));
			feederList.setUID(resultSet.getString(15));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [feederListMapper] "+e);
		}
		return feederList;
	}
}
