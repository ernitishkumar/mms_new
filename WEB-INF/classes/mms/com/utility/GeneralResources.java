package mms.com.utility;
import java.sql.*;
public class GeneralResources{
	private static Connection connection;
	public static Connection getConnection(){
		if(connection==null){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mpeb_new","root","kumar");
			}catch(Exception e){
				System.out.println("Exception in GeneralResources : getConnection() : "+e);
			}
		}
		return connection;
	}
}