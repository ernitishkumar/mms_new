package mms.com.dao;
import mms.com.beans.LineReport11kv;
import mms.com.utility.DatabaseConnection;
import java.util.ArrayList;
import java.sql.*;
public class LineReport11kvDAO{

	Connection connection=null;
	Statement statement=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;

	public void add(LineReport11kv lineReport11kv){
		try{
			connection=DatabaseConnection.getConnection();
			preparedStatement=connection.prepareStatement("insert into mms_11kv_line_report(dateofmt,lenofline,division,permitno,ehvss,durationsuppoff,permittaker,submittedby,feedername,feedercode,RESP,ASE,DPR,DSR,ROS,CAR,TCR,DIR,PIR,JR,NOLTC,GR,LAR,ER,ABSCR,ABSR,DOCR,DOUR,ANY,MLF,c16sc,c25sc,c70sc,c150sc,c300sc,11KVDI,11KVPPI,11KVPI,GWI,GISW,GISS,G11KVDO,SC,11KVDOF,MFC,RFU,TCF,911KVLA,GIP,TOIL,APE,tml,type,circle,category,start_time_hr,start_time_min,end_time_hr,end_time_min) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");	
		}catch(SQLException sqlException){
			System.out.println("Exception in LineReport11kvDAO class : add() : "+sqlException.getMessage());
		}
	}
}