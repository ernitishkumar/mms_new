
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import mms.com.utility.DatabaseConnection;
//import mms.com.beans.FeederList;
public class FeederListDAO {

    Connection connection = DatabaseConnection.getConnection();
	public ArrayList<FeederList> getAll(){
		ArrayList<FeederList> list = new ArrayList<FeederList>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_feeder_list");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				list.add((FeederList) resultSet.getArray(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [getAll] "+e);
		} finally{
			
		}
		
		return list;
		
	}
	public void add(FeederList feederList){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into mms_feeder_list(RESION,CIRCLE_NAME,DIVISION,EHV_SS_CODE,EHV_SS_NAME,KV33_FDR_CODE,KV33_FDR_NAME,GROUP_ALLOTED,SUBSTATION_NAME_33_11,KV_SUBSTATION_CODE_33,KV11FDR_NAME,KV11FDR_CODE,CATEGORY,TYPE,UID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, feederList.getRESION());
			ps.setString(2, feederList.getCIRCLE_NAME());
			ps.setString(3, feederList.getDIVISION());
			ps.setString(4, feederList.getEHV_SS_CODE());
			ps.setString(5, feederList.getEHV_SS_NAME());
			ps.setString(6, feederList.getKV33_FDR_CODE());
			ps.setString(7, feederList.getKV33_FDR_NAME());
			ps.setString(8, feederList.getGROUP_ALLOTED());
			ps.setString(9, feederList.getSUBSTATION_NAME_33_11());
			ps.setString(10, feederList.getKV_SUBSTATION_CODE_33());
			ps.setString(11, feederList.getKV11FDR_NAME());
			ps.setString(12, feederList.getKV11FDR_CODE());
			ps.setString(13, feederList.getCATEGORY());
			ps.setString(14, feederList.getTYPE());
			ps.setString(15, feederList.getUID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [add] method "+e);
		}
		
	}
	
	public FeederList getbyRegion(String region){	
		PreparedStatement ps;
		FeederList feederList = null;
		try {
			ps = connection.prepareStatement("select * from mms_feeder_list where RESION=?");
			ps.setString(1, region);
			ResultSet resultSet = ps.executeQuery();
			FeederListMapper mapper = new FeederListMapper();
			while(resultSet.next()){
				feederList = mapper.feederListMapper(resultSet);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [getbyRegion] "+e);
		}
		
		return feederList;
	}
	
	public FeederList getbyCircleName(String circleName){	
		PreparedStatement ps;
		FeederList feederList = null;
		try {
			ps = connection.prepareStatement("select * from mms_feeder_list where CIRCLE_NAME=?");
			ps.setString(1, circleName);
			ResultSet resultSet = ps.executeQuery();
			FeederListMapper mapper = new FeederListMapper();
			while(resultSet.next()){
				feederList = mapper.feederListMapper(resultSet);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [getbyCircleName] "+e);
		}
		
		return feederList;
	}
	
	public FeederList getbyDivision(String devision){	
		PreparedStatement ps;
		FeederList feederList = null;
		try {
			ps = connection.prepareStatement("select * from mms_feeder_list where DIVISION=?");
			ps.setString(1, devision);
			ResultSet resultSet = ps.executeQuery();
			FeederListMapper mapper = new FeederListMapper();
			while(resultSet.next()){
				feederList = mapper.feederListMapper(resultSet);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [getbyDivision] "+e);
		}
		
		return feederList;
	}
	
	public ArrayList<FeederList> getByCode(String code){	
		PreparedStatement ps;
		ArrayList<FeederList> feederListArray = new ArrayList<FeederList>();
		FeederList list = new FeederList();
		try {
			ps = connection.prepareStatement("select * from mms_feeder_list where ehv_ss_code=?");
			ps.setString(1, code);
			ResultSet resultSet = ps.executeQuery();
			FeederListMapper mapper = new FeederListMapper();
			while(resultSet.next()){
				list = mapper.feederListMapper(resultSet);
				feederListArray.add(list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [getbyDivision] "+e);
		}
		
		return feederListArray;
	}
	
	public ArrayList<String> getAllDistinctEHVSSCode(){	
		PreparedStatement ps;
		ArrayList<String> ehvssCodeList = new ArrayList<String>();
		try {
			ps = connection.prepareStatement("select distinct(EHV_SS_CODE) from mms_feeder_list");
			ResultSet resultSet = ps.executeQuery();
			//FeederListMapper mapper = new FeederListMapper();
			while(resultSet.next()){
				ehvssCodeList.add(resultSet.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [getbyDivision] "+e);
		}
		
		return ehvssCodeList;
	}
	public ArrayList<String> getAllDistinct33kvFeederCode() {
		PreparedStatement ps;
		ArrayList<String> kv33FeederCodeList = new ArrayList<String>();
		try {
			ps = connection.prepareStatement("select distinct(KV33_FDR_CODE) from mms_feeder_list");
			ResultSet resultSet = ps.executeQuery();
			//FeederListMapper mapper = new FeederListMapper();
			while(resultSet.next()){
				kv33FeederCodeList.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [getbyDivision] "+e);
		}
		return kv33FeederCodeList;
	}
	
	public ArrayList<FeederList> getBykv33FeederCode(String code){	
		PreparedStatement ps;
		ArrayList<FeederList> feederListArray = new ArrayList<FeederList>();
		FeederList list = new FeederList();
		try {
			ps = connection.prepareStatement("select * from mms_feeder_list where KV33_FDR_NAME=?");
			ps.setString(1, code);
			ResultSet resultSet = ps.executeQuery();
			FeederListMapper mapper = new FeederListMapper();
			while(resultSet.next()){
				list = mapper.feederListMapper(resultSet);
				feederListArray.add(list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [getbyDivision] "+e);
		}
		
		return feederListArray;
	}
	public ArrayList<String> getAllDistinctSubstationCode() {
		PreparedStatement ps;
		ArrayList<String> substationCodeList = new ArrayList<String>();
		try {
			ps = connection.prepareStatement("select distinct(KV_SUBSTATION_CODE_33) from mms_feeder_list");
			ResultSet resultSet = ps.executeQuery();
			//FeederListMapper mapper = new FeederListMapper();
			while(resultSet.next()){
				substationCodeList.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [getAllDistinctSubstationCode] "+e);
		}
		return substationCodeList;
	}
	public ArrayList<FeederList> getBySubstationCode(String code){	
		PreparedStatement ps;
		ArrayList<FeederList> feederListArray = new ArrayList<FeederList>();
		FeederList list = new FeederList();
		try {
			ps = connection.prepareStatement("select * from mms_feeder_list where KV_SUBSTATION_CODE_33=?");
			ps.setString(1, code);
			ResultSet resultSet = ps.executeQuery();
			FeederListMapper mapper = new FeederListMapper();
			while(resultSet.next()){
				list = mapper.feederListMapper(resultSet);
				feederListArray.add(list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [getbyDivision] "+e);
		}
		
		return feederListArray;
	}
	public ArrayList<String> getAllDistinct11kvFeederCode() {
		PreparedStatement ps;
		ArrayList<String> kv11FeederCodeList = new ArrayList<String>();
		try {
			ps = connection.prepareStatement("select distinct(KV11FDR_CODE) from mms_feeder_list");
			ResultSet resultSet = ps.executeQuery();
			//FeederListMapper mapper = new FeederListMapper();
			while(resultSet.next()){
				kv11FeederCodeList.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [getAllDistinct11kvFeederCode] "+e);
		}
		return kv11FeederCodeList;
	}
	
	public ArrayList<FeederList> getBykv11FeederCode(String code){	
		PreparedStatement ps;
		ArrayList<FeederList> feederListArray = new ArrayList<FeederList>();
		FeederList list = new FeederList();
		try {
			ps = connection.prepareStatement("select * from mms_feeder_list where KV11FDR_CODE=?");
			ps.setString(1, code);
			ResultSet resultSet = ps.executeQuery();
			FeederListMapper mapper = new FeederListMapper();
			while(resultSet.next()){
				list = mapper.feederListMapper(resultSet);
				feederListArray.add(list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [getBykv11FeederCode] "+e);
		}
		
		return feederListArray;
	}
}
