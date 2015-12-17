package mms.com.utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static Connection connection = null;

	public static Connection getConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if(connection==null){
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mpeb_new","root","kumar");	
			}
		} catch (SQLException exception) {
			System.out.println("Not able to connect to the Database "+exception.getMessage());
			exception.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found "+e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Connection getConnection(String dbName)
	{
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mms_new","root","kumar");	
		} catch (SQLException exception) {
			System.out.println("Not able to connect to the Database "+exception.getMessage());
			exception.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found "+e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}
}
