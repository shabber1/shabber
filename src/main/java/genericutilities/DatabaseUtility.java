package genericutilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
	 * this class contains reusable methods to read in database
	 * @author shabber
	 */
public class DatabaseUtility {
	Connection connection;
	Statement statement;
	/**
	 * this method initilizes database
	 * @param url
	 * @param user
	 * @param pwd
	 */
	public void databaseinit(String url, String user, String pwd) {
		try {
			connection=DriverManager.getConnection(url, user, pwd);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	try {
		statement = connection.createStatement();
	}catch(SQLException e2) {
		e2.printStackTrace();
		}
	}
	/**
	 * this method reads data from database column wise
	 * @param query
	 * @param colName
	 * @return List<object>
	 */
	public List<Object> readFromDatabase(String query, String colName){
		ResultSet result = null;
		try {
			result=statement.executeQuery(query);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		List<Object> list = new ArrayList<Object>();
		try {
			while(result.next()) {
				list.add(result.getObject(colName));
			}}
			catch(SQLException e) {
				e.printStackTrace();
			}
		return list;
	}
	/**
	 * this method is used to modify database
	 * @param query
	 * @return
	 */
	public int modifyDatabase(String query) {
	int result =0;
	try {
		result = statement.executeUpdate(query);
	}catch(SQLException e){
		e.printStackTrace();
	}
	return result;
	}
	/**
	 * this method is closes database connection
	 */
	public void closeDatabase() {
	try {
		connection.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	}
	}
