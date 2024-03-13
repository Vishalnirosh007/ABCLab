package laboratory_webapp_Dao;

import laboratory_webapp_Dao.DbConnector; 
import  laboratory_webapp_Dao.MySqlConnectorImpl;

public class MySqlDbConnectorFactoryImpl implements DbConnectorFactory{
	
	@Override
	public DbConnector getDbConnector() {
		
		return new MySqlConnectorImpl();
		
	}

}
