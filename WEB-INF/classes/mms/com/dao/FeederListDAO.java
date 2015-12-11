package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.FeederList;
import mms.com.mapper.FeederListMapper;
import mms.com.utility.DatabaseConnection;

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
			ps = connection.prepareStatement("select * from mms_detail_of_3311_ss where RESION=?");
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
			ps = connection.prepareStatement("select * from mms_detail_of_3311_ss where CIRCLE_NAME=?");
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
			ps = connection.prepareStatement("select * from mms_detail_of_3311_ss where DIVISION=?");
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
}
