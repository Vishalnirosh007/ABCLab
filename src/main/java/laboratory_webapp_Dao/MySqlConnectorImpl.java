package laboratory_webapp_Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectorImpl implements  DbConnector{
	
public Connection getDbConnection() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/abc-lab";
		String userName = "root";
		String password = "Vishalll8*";
		
		Connection connection = DriverManager.getConnection(url, userName, password);
		return connection;
		
	}

}
