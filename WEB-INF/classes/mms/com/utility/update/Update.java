import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Update {

	public static void main (String args[]){
		Update update = new Update();
		update.updateKV33Feeder();
		update.updateEHVSS();
	}

	public void updateKV33Feeder(){
		try{
			FeederListDAO feederList = new FeederListDAO();
			Connection connection = DatabaseConnection.getConnection("mms_new");
			PreparedStatement ps=connection.prepareStatement("select code from kv33feeder");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				ArrayList<FeederList> array = feederList.getBykv33FeederCode(resultSet.getString(1));
				if(array.size()==0){
					System.out.println("Skipping 33feeder");
					continue;	
				} 
				PreparedStatement ps1=connection.prepareStatement("Update kv33feeder set division= ? where code=?");
				ps1.setString(1,array.get(0).getDIVISION());
				ps1.setString(2,resultSet.getString(1).trim());
				ps1.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in getAll "+e);
		}
	}


	public void updateEHVSS(){
		try{
			FeederListDAO feederList = new FeederListDAO();
			Connection connection = DatabaseConnection.getConnection("mms_new");
			PreparedStatement ps=connection.prepareStatement("select code from ehvss");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				ArrayList<FeederList> array = feederList.getByCode(resultSet.getString(1));
				if(array.size()==0){
					System.out.println("Skipping ehvss");
					continue;	
				} 
	//System.out.println("Circle NAme: "+array.get(0).getCIRCLE_NAME()+" and Division Name: "+array.get(0).getDIVISION());
	//System.out.println("Code : "+resultSet.getString(1).trim());
				PreparedStatement ps1=connection.prepareStatement("Update ehvss set circle= ? ,division= ? where code=?");
				ps1.setString(1,array.get(0).getCIRCLE_NAME());
				ps1.setString(2,array.get(0).getDIVISION());
				ps1.setString(3,resultSet.getString(1).trim());
				ps1.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in getAll "+e);
		}	
	}
}