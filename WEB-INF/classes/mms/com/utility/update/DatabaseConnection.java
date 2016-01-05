
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static Connection connection = null;
	private static Connection connectionWithSource=null;
	public static Connection getConnection()
	{
		try {
			if(connection==null){
				Class.forName("com.mysql.jdbc.Driver");
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
		System.out.println("Aaya "+dbName);
		try {
			if(connectionWithSource==null){
			Class.forName("com.mysql.jdbc.Driver");
			connectionWithSource=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName,"root","kumar");		
			}
		} catch (SQLException exception) {
			System.out.println("Not able to connect to the Database "+exception.getMessage());
			exception.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found "+e.getMessage());
			e.printStackTrace();
		}
		return connectionWithSource;
	}
}
