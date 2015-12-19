package mms.com.utility;
import java.util.ArrayList;
import java.sql.*;
import java.io.Serializable;
import mms.com.utility.DatabaseConnection;
public class DBMigration {

	//private Connection connection = DatabaseConnection.getConnection("mms_new");
	
	public void migrateEHVSSTable(){
		FeederListDAO feederListDAO = new FeederListDAO();
		ArrayList<String> ehvssCodelist = feederListDAO.getAllDistinctEHVSSCode();
		for(int i = 0;i < ehvssCodelist.size();i++){
			EHVSS ehvss = new EHVSS(); 
			ArrayList<FeederList> list = feederListDAO.getByCode(ehvssCodelist.get(i));
			ehvss.setCircle(list.get(0).getCIRCLE_NAME());
			ehvss.setCode(list.get(0).getEHV_SS_CODE());
			ehvss.setDivision(list.get(0).getDIVISION());
			ehvss.setLocation("Dummy");
			ehvss.setName(list.get(0).getEHV_SS_NAME());
			ehvss.setRegion(list.get(0).getRESION());
			EhvssDAO ehvssDAO = new EhvssDAO();
			ehvssDAO.addEHVSS(ehvss);
		}
	}
	
	
	public void migrate33kvFeederTable(){
		FeederListDAO feederListDAO = new FeederListDAO();
		EhvssDAO ehvssDAO = new EhvssDAO();
		ArrayList<String> kv33FeederCodelist = feederListDAO.getAllDistinct33kvFeederCode();
		for(int i = 0;i < kv33FeederCodelist.size();i++){
			KV33Feeder kv33Feeder = new KV33Feeder(); 
			ArrayList<FeederList> list = feederListDAO.getBykv33FeederCode(kv33FeederCodelist.get(i));
			kv33Feeder.setCircle(list.get(0).getCIRCLE_NAME());
			kv33Feeder.setCode(list.get(0).getKV33_FDR_NAME());
			kv33Feeder.setDivision(list.get(0).getDIVISION());
			kv33Feeder.setLocation("Dummy");
			kv33Feeder.setName(list.get(0).getKV33_FDR_CODE());
			kv33Feeder.setRegion(list.get(0).getRESION());
			kv33Feeder.setEhvssID(ehvssDAO.getByEhvssCode(list.get(0).getEHV_SS_CODE()).getId());
			KV33FeederDAO kv33FeederDAO = new KV33FeederDAO();
			kv33FeederDAO.addKV33Feeder(kv33Feeder);
		}
	}
	
	public void migrateSubstationTable(){
		FeederListDAO feederListDAO = new FeederListDAO();
		SubstationDAO substationDAO = new SubstationDAO();
		KV33FeederDAO kv33FeederDAO = new KV33FeederDAO();
		ArrayList<String> substationCodeList = feederListDAO.getAllDistinctSubstationCode();
		for(int i = 0;i < substationCodeList.size();i++){
			Substation substation = new Substation();
			//System.out.println("Substation code : "+substationCodeList.get(i)); 
			ArrayList<FeederList> list = feederListDAO.getBySubstationCode(substationCodeList.get(i));
			substation.setCircle(list.get(0).getCIRCLE_NAME());
			substation.setCode(list.get(0).getKV_SUBSTATION_CODE_33());
			substation.setDivision(list.get(0).getDIVISION());
			substation.setLocation("Dummy");
			substation.setName(list.get(0).getSUBSTATION_NAME_33_11());
			substation.setRegion(list.get(0).getRESION());
			substation.setDc("Dummy");
			substation.setKv33FeederID(kv33FeederDAO.getBykv33FeederCode(list.get(0).getKV33_FDR_NAME()).getId());
			substationDAO.addSubstation(substation);
		}
	}
	
	public void migrate11kvFeederTable(){
		FeederListDAO feederListDAO = new FeederListDAO();
		SubstationDAO substationDAO = new SubstationDAO();
		KV11FeederDAO kv11FeederDAO = new KV11FeederDAO();
		ArrayList<String> kv11Feederlist = feederListDAO.getAllDistinct11kvFeederCode();
		for(int i = 0;i < kv11Feederlist.size();i++){
			KV11Feeder kv11Feeder = new KV11Feeder(); 
			ArrayList<FeederList> list = feederListDAO.getBykv11FeederCode(kv11Feederlist.get(i));
			kv11Feeder.setCircle(list.get(0).getCIRCLE_NAME());
			kv11Feeder.setCode(list.get(0).getKV11FDR_CODE());
			kv11Feeder.setDivision(list.get(0).getDIVISION());
			kv11Feeder.setLocation("Dummy");
			kv11Feeder.setDc("Dummy");
			kv11Feeder.setName(list.get(0).getKV11FDR_NAME());
			kv11Feeder.setRegion(list.get(0).getRESION());
			if(list.get(0).getCATEGORY()==null){
				kv11Feeder.setFeederType("DUMMY");	
			}else{
				kv11Feeder.setFeederType(list.get(0).getCATEGORY());
			}
			
			kv11Feeder.setSubstationID(substationDAO.getBySubstationCode(list.get(0).getKV_SUBSTATION_CODE_33()).getId());
			kv11FeederDAO.add11KVFeeder(kv11Feeder);
		}
	}
	
	
	public static void main(String[] args) {
		DBMigration dbMigration = new DBMigration();
		dbMigration.migrateEHVSSTable();
		System.out.println("Migration Successful for EHVSS");
		dbMigration.migrate33kvFeederTable();
		System.out.println("Migration Successful for 33KV");
		dbMigration.migrateSubstationTable();
		System.out.println("Migration Successful for substation");
		dbMigration.migrate11kvFeederTable();
		System.out.println("Migration Successful for 11KV");
	}
}
class EHVSS {

	private String id;
	private String name;
	private String code;
	private String location;
	private String region;
	private String circle;
	private String division;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public EHVSS(String id, String name, String code, String location,
		String region, String circle, String division) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.location = location;
		this.region = region;
		this.circle = circle;
		this.division = division;
	}
	public EHVSS(String name, String code, String location, String region,
		String circle, String division) {
		this.name = name;
		this.code = code;
		this.location = location;
		this.region = region;
		this.circle = circle;
		this.division = division;
	}
	public EHVSS() {
	}
}
class FeederList {

	private String RESION; 
	private String CIRCLE_NAME; 
	private String DIVISION;
	private String EHV_SS_CODE;
	private String EHV_SS_NAME; 
	private String KV33_FDR_CODE; 
	private String KV33_FDR_NAME; 
	private String GROUP_ALLOTED; 
	private String SUBSTATION_NAME_33_11; 
	private String KV_SUBSTATION_CODE_33; 
	private String KV11FDR_NAME; 
	private String KV11FDR_CODE; 
	private String CATEGORY; 
	private String TYPE; 
	private String UID;
	public String getRESION() {
		return RESION;
	}
	public void setRESION(String rESION) {
		RESION = rESION;
	}
	public String getCIRCLE_NAME() {
		return CIRCLE_NAME;
	}
	public void setCIRCLE_NAME(String cIRCLE_NAME) {
		CIRCLE_NAME = cIRCLE_NAME;
	}
	public String getDIVISION() {
		return DIVISION;
	}
	public void setDIVISION(String dIVISION) {
		DIVISION = dIVISION;
	}
	public String getEHV_SS_CODE() {
		return EHV_SS_CODE;
	}
	public void setEHV_SS_CODE(String eHV_SS_CODE) {
		EHV_SS_CODE = eHV_SS_CODE;
	}
	public String getEHV_SS_NAME() {
		return EHV_SS_NAME;
	}
	public void setEHV_SS_NAME(String eHV_SS_NAME) {
		EHV_SS_NAME = eHV_SS_NAME;
	}
	public String getKV33_FDR_CODE() {
		return KV33_FDR_CODE;
	}
	public void setKV33_FDR_CODE(String kV33_FDR_CODE) {
		KV33_FDR_CODE = kV33_FDR_CODE;
	}
	public String getKV33_FDR_NAME() {
		return KV33_FDR_NAME;
	}
	public void setKV33_FDR_NAME(String kV33_FDR_NAME) {
		KV33_FDR_NAME = kV33_FDR_NAME;
	}
	public String getGROUP_ALLOTED() {
		return GROUP_ALLOTED;
	}
	public void setGROUP_ALLOTED(String gROUP_ALLOTED) {
		GROUP_ALLOTED = gROUP_ALLOTED;
	}
	public String getSUBSTATION_NAME_33_11() {
		return SUBSTATION_NAME_33_11;
	}
	public void setSUBSTATION_NAME_33_11(String sUBSTATION_NAME_33_11) {
		SUBSTATION_NAME_33_11 = sUBSTATION_NAME_33_11;
	}
	public String getKV_SUBSTATION_CODE_33() {
		return KV_SUBSTATION_CODE_33;
	}
	public void setKV_SUBSTATION_CODE_33(String kV_SUBSTATION_CODE_33) {
		KV_SUBSTATION_CODE_33 = kV_SUBSTATION_CODE_33;
	}
	public String getKV11FDR_NAME() {
		return KV11FDR_NAME;
	}
	public void setKV11FDR_NAME(String kV11FDR_NAME) {
		KV11FDR_NAME = kV11FDR_NAME;
	}
	public String getKV11FDR_CODE() {
		return KV11FDR_CODE;
	}
	public void setKV11FDR_CODE(String kV11FDR_CODE) {
		KV11FDR_CODE = kV11FDR_CODE;
	}
	public String getCATEGORY() {
		return CATEGORY;
	}
	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public String toString() {
		return "FeederList [RESION=" + RESION + ", CIRCLE_NAME=" + CIRCLE_NAME
				+ ", DIVISION=" + DIVISION + ", EHV_SS_CODE=" + EHV_SS_CODE
				+ ", EHV_SS_NAME=" + EHV_SS_NAME + ", KV33_FDR_CODE="
				+ KV33_FDR_CODE + ", KV33_FDR_NAME=" + KV33_FDR_NAME
				+ ", GROUP_ALLOTED=" + GROUP_ALLOTED
				+ ", SUBSTATION_NAME_33_11=" + SUBSTATION_NAME_33_11
				+ ", KV_SUBSTATION_CODE_33=" + KV_SUBSTATION_CODE_33
				+ ", KV11FDR_NAME=" + KV11FDR_NAME + ", KV11FDR_CODE="
				+ KV11FDR_CODE + ", CATEGORY=" + CATEGORY + ", TYPE=" + TYPE
				+ ", UID=" + UID + "]";
	}
	public FeederList(String rESION, String cIRCLE_NAME, String dIVISION,
			String eHV_SS_CODE, String eHV_SS_NAME, String kV33_FDR_CODE,
			String kV33_FDR_NAME, String gROUP_ALLOTED,
			String sUBSTATION_NAME_33_11, String kV_SUBSTATION_CODE_33,
			String kV11FDR_NAME, String kV11FDR_CODE, String cATEGORY,
			String tYPE, String uID) {
		
		RESION = rESION;
		CIRCLE_NAME = cIRCLE_NAME;
		DIVISION = dIVISION;
		EHV_SS_CODE = eHV_SS_CODE;
		EHV_SS_NAME = eHV_SS_NAME;
		KV33_FDR_CODE = kV33_FDR_CODE;
		KV33_FDR_NAME = kV33_FDR_NAME;
		GROUP_ALLOTED = gROUP_ALLOTED;
		SUBSTATION_NAME_33_11 = sUBSTATION_NAME_33_11;
		KV_SUBSTATION_CODE_33 = kV_SUBSTATION_CODE_33;
		KV11FDR_NAME = kV11FDR_NAME;
		KV11FDR_CODE = kV11FDR_CODE;
		CATEGORY = cATEGORY;
		TYPE = tYPE;
		UID = uID;
	}
	public FeederList() {
	
	}
	
	
}

class KV11Feeder {

	private String id;
	private String name;
	private String code;
	private String location;
	private String region;
	private String circle;
	private String division;
	private String dc;
	private String substationID;
	private String feederType;

	public KV11Feeder(String id, String name, String code, String location,
			String region, String circle, String division, String dc,
			String substationID,String feederType) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.location = location;
		this.region = region;
		this.circle = circle;
		this.division = division;
		this.dc = dc;
		this.substationID = substationID;
		this.feederType=feederType;
	}
	public KV11Feeder(String name, String code, String location, String region,
			String circle, String division, String dc, String substationID,String feederType) {
		this.name = name;
		this.code = code;
		this.location = location;
		this.region = region;
		this.circle = circle;
		this.division = division;
		this.dc = dc;
		this.substationID = substationID;
		this.feederType=feederType;
	}
	public KV11Feeder() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDc() {
		return dc;
	}
	public void setDc(String dc) {
		this.dc = dc;
	}
	public String getSubstationID() {
		return substationID;
	}
	public void setSubstationID(String substationID) {
		this.substationID = substationID;
	}
	
	public String getFeederType() {
		return feederType;
	}
	public void setFeederType(String feederType) {
		this.feederType = feederType;
	}
	
}

class KV33Feeder {

	private String id;
	private String name;
	private String code;
	private String location;
	private String region;
	private String circle;
	private String division;
	private String ehvssID;
	public KV33Feeder(String id, String name, String code, String location,
			String region, String circle, String division, String ehvssID) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.location = location;
		this.region = region;
		this.circle = circle;
		this.division = division;
		this.ehvssID = ehvssID;
	}
	public KV33Feeder(String name, String code, String location, String region,
			String circle, String division, String ehvssID) {
		this.name = name;
		this.code = code;
		this.location = location;
		this.region = region;
		this.circle = circle;
		this.division = division;
		this.ehvssID = ehvssID;
	}
	public KV33Feeder() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getEhvssID() {
		return ehvssID;
	}
	public void setEhvssID(String ehvssID) {
		this.ehvssID = ehvssID;
	}
}

class Substation {

	private String id;
	private String name;
	private String code;
	private String location;
	private String region;
	private String circle;
	private String division;
	private String dc;
	private String kv33FeederID;
	public Substation(String id, String name, String code, String location,
			String region, String circle, String division, String dc,
			String kv33FeederID) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.location = location;
		this.region = region;
		this.circle = circle;
		this.division = division;
		this.dc = dc;
		this.kv33FeederID = kv33FeederID;
	}
	public Substation(String name, String code, String location, String region,
			String circle, String division, String dc, String kv33FeederID) {
		this.name = name;
		this.code = code;
		this.location = location;
		this.region = region;
		this.circle = circle;
		this.division = division;
		this.dc = dc;
		this.kv33FeederID = kv33FeederID;
	}
	public Substation() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDc() {
		return dc;
	}
	public void setDc(String dc) {
		this.dc = dc;
	}
	public String getKv33FeederID() {
		return kv33FeederID;
	}
	public void setKv33FeederID(String kv33FeederID) {
		this.kv33FeederID = kv33FeederID;
	}
	
	
}

class EhvssDAO {
	//private Connection connection = DatabaseConnection.getConnection("mms_new");
	
	public void addEHVSS(EHVSS ehvss){
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("insert into EHVSS(code, name, location, region, circle, division) VALUES(?,?,?,?,?,?)");
			ps.setString(1,ehvss.getCode());
			ps.setString(2,ehvss.getName());
			ps.setString(3,"DUMMY");
			ps.setString(4,ehvss.getRegion());
			ps.setString(5,"DUMMY");
			ps.setString(6,"DUMMY");
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Exception in [updateUser]"+e);
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean addEHVSS(EHVSS ehvss,ErrorBean errorBean){
		Connection connection = DatabaseConnection.getConnection("mms_new");
		ArrayList<EHVSS> ehvssList=new ArrayList<EHVSS>();
		boolean added;
		try {
			ehvssList=getByCode(ehvss.getCode());
			if(ehvssList==null || ehvssList.size()==0){
				PreparedStatement ps = connection.prepareStatement("insert into EHVSS(code, name, location, region, circle, division) VALUES(?,?,?,?,?,?)");
				ps.setString(1,ehvss.getCode());
				ps.setString(2,ehvss.getName());
				ps.setString(3,"DUMMY");
				ps.setString(4,ehvss.getRegion());
				ps.setString(5,"DUMMY");
				ps.setString(6,"DUMMY");
				ps.executeUpdate();
				added=true;
			}else{
				errorBean.setErrorMessage("EHVSS Code Already Exist. Please provide Different EHVSS Code");
				added=false;
			}
		} catch (SQLException e) {
			added=false;
			System.out.println("Exception in [updateUser]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return added;
	}

	public ArrayList<String> get(){
		ArrayList<String> ehvssNames=null;
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT name FROM ehvss");
			ResultSet rs=ps.executeQuery();
			ehvssNames=new ArrayList<String>();
			while(rs.next()){
				ehvssNames.add(rs.getString(1).trim());
			}
			System.out.println("Number of Ehvss Locations :"+ehvssNames.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [get]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ehvssNames;
	}

	public ArrayList<EHVSS> getAll(){
		ArrayList<EHVSS> ehvssNames=null;
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss");
			ResultSet rs=ps.executeQuery();
			ehvssNames=new ArrayList<EHVSS>();
			while(rs.next()){
				EHVSS ehvss=new EHVSS();
				ehvss.setId(String.valueOf(rs.getInt(1)));
				ehvss.setName(rs.getString(3).trim());
				ehvss.setCode(rs.getString(2).trim());
				ehvss.setLocation(rs.getString(4).trim());
				ehvss.setRegion(rs.getString(5).trim());
				ehvss.setCircle(rs.getString(6).trim());
				ehvss.setDivision(rs.getString(7).trim());
				ehvssNames.add(ehvss);
			}
			System.out.println("Number of Ehvss Locations :"+ehvssNames.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getAll]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getAll]"+exp);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ehvssNames;
	}

	public ArrayList<EHVSS> getByCode(String code){
		System.out.println("GetByCode called with code : "+code);
		Connection connection = DatabaseConnection.getConnection("mms_new");
		ArrayList<EHVSS> ehvssNames=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss where code=?");
			ps.setString(1,code);
			ResultSet rs=ps.executeQuery();
			ehvssNames=new ArrayList<EHVSS>();
			while(rs.next()){
				EHVSS ehvss=new EHVSS();
				ehvss.setId(String.valueOf(rs.getInt(1)));
				ehvss.setName(rs.getString(3).trim());
				ehvss.setCode(rs.getString(2).trim());
				ehvss.setLocation(rs.getString(4).trim());
				ehvss.setRegion(rs.getString(5).trim());
				ehvss.setCircle(rs.getString(6).trim());
				ehvss.setDivision(rs.getString(7).trim());
				ehvssNames.add(ehvss);
			}
			System.out.println("Number of Ehvss Locations :"+ehvssNames.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getByCode]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getByCode]"+exp);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ehvssNames;
	}

	public ArrayList<EHVSS> getByRegion(String region){
		System.out.println("GetByCode called with region : "+region);
		Connection connection = DatabaseConnection.getConnection("mms_new");
		ArrayList<EHVSS> ehvssNames=null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss where region=?");
			ps.setString(1,region);
			ResultSet rs=ps.executeQuery();
			ehvssNames=new ArrayList<EHVSS>();
			while(rs.next()){
				EHVSS ehvss=new EHVSS();
				ehvss.setId(String.valueOf(rs.getInt(1)));
				ehvss.setName(rs.getString(3).trim());
				ehvss.setCode(rs.getString(2).trim());
				ehvss.setLocation(rs.getString(4).trim());
				ehvss.setRegion(rs.getString(5).trim());
				ehvss.setCircle(rs.getString(6).trim());
				ehvss.setDivision(rs.getString(7).trim());
				ehvssNames.add(ehvss);
			}
			System.out.println("Number of Ehvss Locations for region :"+region+"  is :"+ehvssNames.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getByRegion]"+e);
		}catch (Exception exp) {
			System.out.println("Exception in class : EhvssDAO : method : [getByRegion]"+exp);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ehvssNames;
	}
	
	public EHVSS getByEhvssCode(String code){
		Connection connection = DatabaseConnection.getConnection("mms_new");
		EHVSS ehvss = new EHVSS();
		
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM ehvss where code=?");
			ps.setString(1, code);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ehvss.setId(String.valueOf(rs.getInt(1)));
				ehvss.setName(rs.getString(3).trim());
				ehvss.setCode(rs.getString(2).trim());
				ehvss.setLocation(rs.getString(4).trim());
				ehvss.setRegion(rs.getString(5).trim());
				ehvss.setCircle(rs.getString(6).trim());
				ehvss.setDivision(rs.getString(7).trim());
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : EhvssDAO : method : [getByEhvssCode]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ehvss;
	}
}

class FeederListDAO {

    //Connection connection = DatabaseConnection.getConnection("mpeb_new");
	public ArrayList<FeederList> getAll(){
		ArrayList<FeederList> list = new ArrayList<FeederList>();
		Connection connection = DatabaseConnection.getConnection("mpeb_new");
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
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	public void add(FeederList feederList){
		Connection connection = DatabaseConnection.getConnection("mpeb_new");
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
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public FeederList getbyRegion(String region){	
		PreparedStatement ps;
		Connection connection = DatabaseConnection.getConnection("mpeb_new");
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
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return feederList;
	}
	
	public FeederList getbyCircleName(String circleName){	
		PreparedStatement ps;
		Connection connection = DatabaseConnection.getConnection("mpeb_new");
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
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return feederList;
	}
	
	public FeederList getbyDivision(String devision){	
		PreparedStatement ps;
		Connection connection = DatabaseConnection.getConnection("mpeb_new");
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
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return feederList;
	}
	
	public ArrayList<FeederList> getByCode(String code){	
		PreparedStatement ps;
		Connection connection = DatabaseConnection.getConnection("mpeb_new");
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
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return feederListArray;
	}
	
	public ArrayList<String> getAllDistinctEHVSSCode(){	
		PreparedStatement ps;
		Connection connection = DatabaseConnection.getConnection("mpeb_new");
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
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ehvssCodeList;
	}
	public ArrayList<String> getAllDistinct33kvFeederCode() {
		PreparedStatement ps;
		Connection connection = DatabaseConnection.getConnection("mpeb_new");
		ArrayList<String> kv33FeederCodeList = new ArrayList<String>();
		try {
			ps = connection.prepareStatement("select distinct(KV33_FDR_NAME) from mms_feeder_list");
			ResultSet resultSet = ps.executeQuery();
			//FeederListMapper mapper = new FeederListMapper();
			while(resultSet.next()){
				kv33FeederCodeList.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception Occured in [getbyDivision] "+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return kv33FeederCodeList;
	}
	
	public ArrayList<FeederList> getBykv33FeederCode(String code){	
		PreparedStatement ps;
		Connection connection = DatabaseConnection.getConnection("mpeb_new");
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
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return feederListArray;
	}
	public ArrayList<String> getAllDistinctSubstationCode() {
		PreparedStatement ps;
		Connection connection = DatabaseConnection.getConnection("mpeb_new");
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
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return substationCodeList;
	}
	public ArrayList<FeederList> getBySubstationCode(String code){	
		PreparedStatement ps;
		Connection connection = DatabaseConnection.getConnection("mpeb_new");
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
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return feederListArray;
	}
	public ArrayList<String> getAllDistinct11kvFeederCode() {
		PreparedStatement ps;
		Connection connection = DatabaseConnection.getConnection("mpeb_new");
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
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return kv11FeederCodeList;
	}
	
	public ArrayList<FeederList> getBykv11FeederCode(String code){	
		PreparedStatement ps;
		Connection connection = DatabaseConnection.getConnection("mpeb_new");
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
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return feederListArray;
	}
}

class FeederListMapper {

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

class KV11FeederDAO {

	//private Connection connection = DatabaseConnection.getConnection("mms_new");
	public void add11KVFeeder(KV11Feeder kv11Feeder){
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("insert into KV11FEEDER(code, name, location, region, circle, division, dc, substation_id,feeder_type) VALUES(?,?,?,?,?,?,?,?,?)");
			ps.setString(1,kv11Feeder.getCode());
			ps.setString(2,kv11Feeder.getName());
			ps.setString(3,"DUMMY");
			ps.setString(4,kv11Feeder.getRegion());
			ps.setString(5,kv11Feeder.getCircle());
			ps.setString(6,kv11Feeder.getDivision());
			ps.setString(7,kv11Feeder.getDc());
			ps.setString(8,kv11Feeder.getSubstationID());
			ps.setString(9,kv11Feeder.getFeederType());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean add11KVFeeder(KV11Feeder kv11Feeder,ErrorBean errorBean){
		ArrayList<KV11Feeder> kv11Feeders=new ArrayList<KV11Feeder> ();
		Connection connection = DatabaseConnection.getConnection("mms_new");
		boolean added;
		try {
			kv11Feeders=getByCode(kv11Feeder.getCode());
			if(kv11Feeders==null || kv11Feeders.size()==0){
				PreparedStatement ps = connection.prepareStatement("insert into KV11FEEDER(code, name, location, region, circle, division, dc, substation_id,feeder_type) VALUES(?,?,?,?,?,?,?,?,?)");
				ps.setString(1,kv11Feeder.getCode());
				ps.setString(2,kv11Feeder.getName());
				ps.setString(3,"DUMMY");
				ps.setString(4,kv11Feeder.getRegion());
				ps.setString(5,kv11Feeder.getCircle());
				ps.setString(6,kv11Feeder.getDivision());
				ps.setString(7,kv11Feeder.getDc());
				ps.setString(8,kv11Feeder.getSubstationID());
				ps.setString(9,kv11Feeder.getFeederType());
				ps.executeUpdate();	
				added=true;
			}else{
				errorBean.setErrorMessage("11KV Feeder Code Already Exist. Please provide Different 11KV Feeder Code");
				added=false;
			}
		} catch (SQLException e) {
			added=false;
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return added;
	}

	public ArrayList<KV11Feeder> getByCode(String code){
		System.out.println("Get Feeder by code started for code : "+code);
		Connection connection = DatabaseConnection.getConnection("mms_new");
		ArrayList<KV11Feeder> kv11Feeders=null;
		try{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv11feeder where code=?");
			ps.setString(1,code);
			ResultSet rs=ps.executeQuery();
			kv11Feeders=new ArrayList<KV11Feeder> ();
			while(rs.next()){
				KV11Feeder kv11Feeder = new KV11Feeder();
				kv11Feeder.setId(String.valueOf(rs.getInt(1)));
				kv11Feeder.setName(rs.getString(3).trim());
				kv11Feeder.setCode(rs.getString(2).trim());
				kv11Feeder.setLocation(rs.getString(4).trim());
				kv11Feeder.setRegion(rs.getString(5).trim());
				kv11Feeder.setCircle(rs.getString(6).trim());
				kv11Feeder.setDivision(rs.getString(7).trim());
				kv11Feeder.setDc(rs.getString(8).trim());
				kv11Feeder.setSubstationID(rs.getString(9).trim());
				kv11Feeder.setFeederType(rs.getString(10).trim());
				kv11Feeders.add(kv11Feeder);
			}
			System.out.println("Number of 11 KV Feeder for code : "+code+" are :"+kv11Feeders.size());
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Exception in class : KV11FeederDAO : method :getByCode()"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return kv11Feeders;
	}
}

class KV33FeederDAO {

	//Connection connection = DatabaseConnection.getConnection("mms_new");
	public void addKV33Feeder(KV33Feeder kv33Feeder){
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("insert into KV33Feeder(code, name, location, region, circle, division, ehvss_id) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1,kv33Feeder.getCode());
			ps.setString(2,kv33Feeder.getName());
			ps.setString(3,"DUMMY");
			ps.setString(4,kv33Feeder.getRegion());
			ps.setString(5,kv33Feeder.getCircle());
			ps.setString(6,"DUMMY");
			ps.setString(7,kv33Feeder.getEhvssID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean addKV33Feeder(KV33Feeder kv33Feeder,ErrorBean errorBean){
		ArrayList<KV33Feeder> kv33Feeders=new ArrayList<KV33Feeder>();
		boolean added;
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			kv33Feeders=getByCode(kv33Feeder.getCode());
			if(kv33Feeders==null || kv33Feeders.size()==0){
				PreparedStatement ps = connection.prepareStatement("insert into KV33Feeder(code, name, location, region, circle, division, ehvss_id) VALUES(?,?,?,?,?,?,?)");
				ps.setString(1,kv33Feeder.getCode());
				ps.setString(2,kv33Feeder.getName());
				ps.setString(3,"DUMMY");
				ps.setString(4,kv33Feeder.getRegion());
				ps.setString(5,kv33Feeder.getCircle());
				ps.setString(6,"DUMMY");
				ps.setString(7,kv33Feeder.getEhvssID());
				ps.executeUpdate();
				added=true;	
			}else{
				errorBean.setErrorMessage("33KV Feeder Code Already Exist. Please provide Different 33KV Feeder Code");
				added=false;
			}
		} catch (SQLException e) {
			added=false;
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return added;
	}

	public ArrayList<KV33Feeder> getAll() {
		ArrayList<KV33Feeder> kv33Feeders=null;
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv33feeder");
			ResultSet rs=ps.executeQuery();
			kv33Feeders=new ArrayList<KV33Feeder>();
			while(rs.next()){
				KV33Feeder kv33Feeder = new KV33Feeder();
				kv33Feeder.setId(String.valueOf(rs.getInt(1)));
				kv33Feeder.setName(rs.getString(3).trim());
				kv33Feeder.setCode(rs.getString(2).trim());
				kv33Feeder.setLocation(rs.getString(4).trim());
				kv33Feeder.setRegion(rs.getString(5).trim());
				kv33Feeder.setCircle(rs.getString(6).trim());
				kv33Feeder.setDivision(rs.getString(7).trim());
				kv33Feeder.setEhvssID(rs.getString(8).trim());
				kv33Feeders.add(kv33Feeder);
			}
			System.out.println("Number of 33KV Feeders :"+kv33Feeders.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getAll]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return kv33Feeders;
	}

	public ArrayList<KV33Feeder> getByCode(String code) {
		ArrayList<KV33Feeder> kv33Feeders=null;
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv33feeder where code=?");
			ps.setString(1,code);
			ResultSet rs=ps.executeQuery();
			kv33Feeders=new ArrayList<KV33Feeder>();
			while(rs.next()){
				KV33Feeder kv33Feeder = new KV33Feeder();
				kv33Feeder.setId(String.valueOf(rs.getInt(1)));
				kv33Feeder.setName(rs.getString(3).trim());
				kv33Feeder.setCode(rs.getString(2).trim());
				kv33Feeder.setLocation(rs.getString(4).trim());
				kv33Feeder.setRegion(rs.getString(5).trim());
				kv33Feeder.setCircle(rs.getString(6).trim());
				kv33Feeder.setDivision(rs.getString(7).trim());
				kv33Feeder.setEhvssID(rs.getString(8).trim());
				kv33Feeders.add(kv33Feeder);
			}
			System.out.println("Number of 33KV Feeders  for code :"+code+" is : "+kv33Feeders.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getByCode]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return kv33Feeders;
	}

	public ArrayList<KV33Feeder> getByCircle(String circle) {
		ArrayList<KV33Feeder> kv33Feeders=null;
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM kv33feeder where circle=?");
			ps.setString(1,circle);
			ResultSet rs=ps.executeQuery();
			kv33Feeders=new ArrayList<KV33Feeder>();
			while(rs.next()){
				KV33Feeder kv33Feeder = new KV33Feeder();
				kv33Feeder.setId(String.valueOf(rs.getInt(1)));
				kv33Feeder.setName(rs.getString(3).trim());
				kv33Feeder.setCode(rs.getString(2).trim());
				kv33Feeder.setLocation(rs.getString(4).trim());
				kv33Feeder.setRegion(rs.getString(5).trim());
				kv33Feeder.setCircle(rs.getString(6).trim());
				kv33Feeder.setDivision(rs.getString(7).trim());
				kv33Feeder.setEhvssID(rs.getString(8).trim());
				kv33Feeders.add(kv33Feeder);
			}
			System.out.println("Number of 33KV Feeders  for circle :"+circle+" is : "+kv33Feeders.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getByCircle]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return kv33Feeders;
	}
	
	public KV33Feeder getBykv33FeederCode(String code){
		KV33Feeder kv33Feeder = new KV33Feeder();
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM KV33Feeder where code=?");
			ps.setString(1, code);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				kv33Feeder.setId(String.valueOf(rs.getInt(1)));
				kv33Feeder.setName(rs.getString(3).trim());
				kv33Feeder.setCode(rs.getString(2).trim());
				kv33Feeder.setLocation(rs.getString(4).trim());
				kv33Feeder.setRegion(rs.getString(5).trim());
				kv33Feeder.setCircle(rs.getString(6).trim());
				kv33Feeder.setDivision(rs.getString(7).trim());
				kv33Feeder.setEhvssID(rs.getString(8).trim());
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getBykv33FeederCode]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return kv33Feeder;
	}
}

class SubstationDAO {

	//private Connection connection = DatabaseConnection.getConnection("mms_new");
	public void addSubstation(Substation substation){
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("insert into substation(code, name, location, region, circle, division, dc, kv33feeder_id) VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1,substation.getCode());
			ps.setString(2,substation.getName());
			ps.setString(3,"DUMMY");
			ps.setString(4,substation.getRegion());
			ps.setString(5,substation.getCircle());
			ps.setString(6,substation.getDivision());
			ps.setString(7,"DUMMY");
			ps.setString(8,substation.getKv33FeederID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean addSubstation(Substation substation,ErrorBean errorBean){
		ArrayList<Substation> substations=new ArrayList<Substation>();
		boolean added;
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			substations=getByCode(substation.getCode());
			if(substations==null || substations.size()==0){
			PreparedStatement ps = connection.prepareStatement("insert into substation(code, name, location, region, circle, division, dc, kv33feeder_id) VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1,substation.getCode());
			ps.setString(2,substation.getName());
			ps.setString(3,"DUMMY");
			ps.setString(4,substation.getRegion());
			ps.setString(5,substation.getCircle());
			ps.setString(6,substation.getDivision());
			ps.setString(7,"DUMMY");
			ps.setString(8,substation.getKv33FeederID());
			ps.executeUpdate();
			added=true;	
			}else{
				errorBean.setErrorMessage("Substation Code Already Exist. Please provide Different Substation Code");
				added=false;
			}
		} catch (SQLException e) {
			added=false;
			e.printStackTrace();
			System.out.println("Exception in [updateUser]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return added;
	}

	public ArrayList<Substation> getAll() {
		ArrayList<Substation> substations=null;
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM substation");
			ResultSet rs=ps.executeQuery();
			substations=new ArrayList<Substation>();
			while(rs.next()){
				Substation substation = new Substation();
				substation.setId(String.valueOf(rs.getInt(1)));
				substation.setName(rs.getString(3).trim());
				substation.setCode(rs.getString(2).trim());
				substation.setLocation(rs.getString(4).trim());
				substation.setRegion(rs.getString(5).trim());
				substation.setCircle(rs.getString(6).trim());
				substation.setDivision(rs.getString(7).trim());
				substation.setDc(rs.getString(8).trim());
				substation.setKv33FeederID(rs.getString(9).trim());
				substations.add(substation);
			}
			System.out.println("Number of Substations:"+substations.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : SubstationDAO : method : [getAll]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return substations;
	}

	public ArrayList<Substation> getByCode(String code) {
		ArrayList<Substation> substations=null;
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM substation where code=?");
			ps.setString(1,code);
			ResultSet rs=ps.executeQuery();
			substations=new ArrayList<Substation>();
			while(rs.next()){
				Substation substation = new Substation();
				substation.setId(String.valueOf(rs.getInt(1)));
				substation.setName(rs.getString(3).trim());
				substation.setCode(rs.getString(2).trim());
				substation.setLocation(rs.getString(4).trim());
				substation.setRegion(rs.getString(5).trim());
				substation.setCircle(rs.getString(6).trim());
				substation.setDivision(rs.getString(7).trim());
				substation.setDc(rs.getString(8).trim());
				substation.setKv33FeederID(rs.getString(9).trim());
				substations.add(substation);
			}
			System.out.println("Number of Substations for code : "+code+" are :"+substations.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : SubstationDAO : method : [getByCode]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return substations;
	}

	public ArrayList<Substation> getByDivision(String division) {
		ArrayList<Substation> substations=null;
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM substation where division=?");
			ps.setString(1,division);
			ResultSet rs=ps.executeQuery();
			substations=new ArrayList<Substation>();
			while(rs.next()){
				Substation substation = new Substation();
				substation.setId(String.valueOf(rs.getInt(1)));
				substation.setName(rs.getString(3).trim());
				substation.setCode(rs.getString(2).trim());
				substation.setLocation(rs.getString(4).trim());
				substation.setRegion(rs.getString(5).trim());
				substation.setCircle(rs.getString(6).trim());
				substation.setDivision(rs.getString(7).trim());
				substation.setDc(rs.getString(8).trim());
				substation.setKv33FeederID(rs.getString(9).trim());
				substations.add(substation);
			}
			System.out.println("Number of Substations for division : "+division+" are :"+substations.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : SubstationDAO : method : [getByCode]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return substations;
	}

	public ArrayList<Substation> getByCircle(String circle) {
		ArrayList<Substation> substations=null;
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM substation where circle=?");
			ps.setString(1,circle);
			ResultSet rs=ps.executeQuery();
			substations=new ArrayList<Substation>();
			while(rs.next()){
				Substation substation = new Substation();
				substation.setId(String.valueOf(rs.getInt(1)));
				substation.setName(rs.getString(3).trim());
				substation.setCode(rs.getString(2).trim());
				substation.setLocation(rs.getString(4).trim());
				substation.setRegion(rs.getString(5).trim());
				substation.setCircle(rs.getString(6).trim());
				substation.setDivision(rs.getString(7).trim());
				substation.setDc(rs.getString(8).trim());
				substation.setKv33FeederID(rs.getString(9).trim());
				substations.add(substation);
			}
			System.out.println("Number of Substations for circle : "+circle+" are :"+substations.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : SubstationDAO : method : [getByCircle]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return substations;
	}

	public ArrayList<Substation> getByRegion(String region) {
		ArrayList<Substation> substations=null;
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM substation where region=?");
			ps.setString(1,region);
			ResultSet rs=ps.executeQuery();
			substations=new ArrayList<Substation>();
			while(rs.next()){
				Substation substation = new Substation();
				substation.setId(String.valueOf(rs.getInt(1)));
				substation.setName(rs.getString(3).trim());
				substation.setCode(rs.getString(2).trim());
				substation.setLocation(rs.getString(4).trim());
				substation.setRegion(rs.getString(5).trim());
				substation.setCircle(rs.getString(6).trim());
				substation.setDivision(rs.getString(7).trim());
				substation.setDc(rs.getString(8).trim());
				substation.setKv33FeederID(rs.getString(9).trim());
				substations.add(substation);
			}
			System.out.println("Number of Substations for region : "+region+" are :"+substations.size());
		} catch (SQLException e) {
			System.out.println("Exception in class : SubstationDAO : method : [getByRegion]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return substations;
	}
	
	public Substation getBySubstationCode(String code){
		Substation substation = new Substation();
		Connection connection = DatabaseConnection.getConnection("mms_new");
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM substation where code=?");
			ps.setString(1, code);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				substation.setId(String.valueOf(rs.getInt(1)));
				substation.setName(rs.getString(3).trim());
				substation.setCode(rs.getString(2).trim());
				substation.setLocation(rs.getString(4).trim());
				substation.setRegion(rs.getString(5).trim());
				substation.setCircle(rs.getString(6).trim());
				substation.setDivision(rs.getString(7).trim());
				substation.setDc(rs.getString(8).trim());
				substation.setKv33FeederID(rs.getString(9).trim());
			}
		} catch (SQLException e) {
			System.out.println("Exception in class : KV33FeederDAO : method : [getBykv33FeederCode]"+e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return substation;
	}
}



class ErrorBean implements Serializable {

    private String errorMessage = "";
    public String getErrorMessage() {
        return errorMessage;
    }

    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorBean() {
    }

    public ErrorBean(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
