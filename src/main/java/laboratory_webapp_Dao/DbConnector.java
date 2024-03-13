package laboratory_webapp_Dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbConnector {
	Connection getDbConnection() throws SQLException, ClassNotFoundException;

	static Connection getConn() {
		// TODO Auto-generated method stub
		return null;
	}
}
