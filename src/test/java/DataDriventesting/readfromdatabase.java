package DataDriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class readfromdatabase {

	public static void main(String[] args) throws SQLException {
		//step 1
		/*
		 * Driver dbDriver=new Driver();
		 * 
		 * DriverManager.registerDriver(dbDriver);
		 */
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel","root","root");
		Statement statement=connection.createStatement();
		ResultSet result=statement.executeQuery("select * from abc;");
		while(result.next()) {
			System.out.println(result.getInt("sid")+"\t"+result.getString("sname")+"\t"+result.getString("phno"));}
			connection.close();
		}
	}

