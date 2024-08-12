package DataDriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class modifydatabase {

	public static void main(String[] args) throws SQLException {
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel","root","root");
		Statement statement=connection.createStatement();
		int result=statement.executeUpdate("insert into abc(sid,sname,phno)values(105,\"API\",\"995142579\")");
		System.out.println(result);
		connection.close();
	}

}
