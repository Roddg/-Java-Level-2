package ru.specialist;

import java.sql.*;
import java.util.Scanner;

public class Program {
	
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String CONNECTION_STRING  =
			"jdbc:mysql://localhost:3306/web?user=root&password=demo";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// Строчка поиска
		// SELECT title, length FROM courses 
		// WHERE title LIKE '%web%'
		// ORDER BY title
		Class.forName(DRIVER_NAME);
		
		// title
		// duration
		// description
		// INSERT INTO courses (title, length, description) VALUES ( ? , ? , ?)
		// setString
		// setInt
		// executeUpdate()
		
		
		
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
			
			CallableStatement sp = conn.prepareCall("call totalCourses(?)");
			// IN, INOUT
			// sp.set..
			sp.execute();
			
			// OUT, INOUT
			int total = sp.getInt(1);
			
			System.out.printf("Всего курсов: %d\n", total);
			
			
			
			
			Scanner sc = new Scanner(System.in);
			System.out.print("Поиск курса: ");
			String search = sc.nextLine().trim(); // ' and
			
			/*
			Statement cmd = conn.createStatement();
			String sql = "SELECT title, length FROM courses "
					+ "WHERE title LIKE '%"+search+"%' " // bad
					+ "ORDER BY title";
			ResultSet result = cmd.executeQuery(sql);*/
			
			String sql = "SELECT title, length FROM courses "
					+ "WHERE title LIKE ? "
					+ "ORDER BY title";
			PreparedStatement cmd = conn.prepareStatement(sql);
			cmd.setString( 1, "%" + search + "%");
			ResultSet result = cmd.executeQuery();
			
			while (result.next()) {
				String title = result.getString("title");
				int length = result.getInt("length");
				
				System.out.printf("%-40s  %d\n", title, length);
			}
			
			result.close();
		} //conn.close()
		
		
		

	}

}
