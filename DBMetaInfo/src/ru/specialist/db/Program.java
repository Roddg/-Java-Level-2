package ru.specialist.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Program
{
	public static final String DRIVER_NAME = 
			"com.mysql.jdbc.Driver";
	public static final String CONNECTION_STRING = 
			"jdbc:mysql://localhost:3306/web?user=root&password=demo";

	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Class.forName(DRIVER_NAME);
		
		Connection conn = 
				DriverManager.getConnection(CONNECTION_STRING);
		
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet result = meta.getTables("web", "web", null, null);
		while(result.next())
		{
			String tableName = result.getString("TABLE_NAME");
			System.out.println(tableName);
		}
		
		result.close();
		
		conn.close();

	}

}
