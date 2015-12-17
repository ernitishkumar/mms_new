package mms.com.utility;
import java.util.ArrayList;
import mms.com.beans.EHVSS;
import mms.com.beans.FeederList;
import mms.com.beans.KV11Feeder;
import mms.com.beans.KV33Feeder;
import mms.com.beans.Substation;
import mms.com.dao.EhvssDAO;
import mms.com.dao.FeederListDAO;
import mms.com.dao.KV11FeederDAO;
import mms.com.dao.KV33FeederDAO;
import mms.com.dao.SubstationDAO;

public class DBMigration {

	//private Connection connection = DatabaseConnection.getConnection("feeder");
	
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
			/*try {
				PreparedStatement ps = connection.prepareStatement("insert into ehvss (code) values (?)");
				ps.setString(1, ehvssCodelist.get(i));
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
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
			kv33Feeder.setCode(list.get(0).getKV33_FDR_CODE());
			kv33Feeder.setDivision(list.get(0).getDIVISION());
			kv33Feeder.setLocation("Dummy");
			kv33Feeder.setName(list.get(0).getKV33_FDR_NAME());
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
			ArrayList<FeederList> list = feederListDAO.getBySubstationCode(substationCodeList.get(i));
			substation.setCircle(list.get(0).getCIRCLE_NAME());
			substation.setCode(list.get(0).getKV_SUBSTATION_CODE_33());
			substation.setDivision(list.get(0).getDIVISION());
			substation.setLocation("Dummy");
			substation.setName(list.get(0).getSUBSTATION_NAME_33_11());
			substation.setRegion(list.get(0).getRESION());
			substation.setDc("Dummy");
			substation.setKv33FeederID(kv33FeederDAO.getBykv33FeederCode(list.get(0).getKV33_FDR_CODE()).getId());
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
			kv11Feeder.setCode(list.get(0).getKV33_FDR_CODE());
			kv11Feeder.setDivision(list.get(0).getDIVISION());
			kv11Feeder.setLocation("Dummy");
			kv11Feeder.setName(list.get(0).getKV33_FDR_NAME());
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
