import java.sql.*;

import static java.lang.System.out;
import  java.util.Scanner;


public class Program {
	
	public static final String DRIVER_NAME = 
			"com.mysql.jdbc.Driver"; 
	public static final String CONNECTION_STRING = 
		"jdbc:mysql://localhost:3306/web?user=root&password=demo";
	

	public static void main(String[] args) 
		throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME);
		
		Scanner sc = new Scanner(System.in);
		out.print("Название курса: ");
		String title = sc.nextLine().trim();
		out.print("Длительность курса: ");
		int length = sc.nextInt();
		
		if (sc.hasNextLine()) sc.nextLine();
		
		out.print("Описание  курса: ");
		String description = sc.nextLine().trim();
		
		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING))
		{
			try {
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				
				String sql = "INSERT INTO Courses (title,length, description)"
						+ " VALUES (?, ?, ?)";
				PreparedStatement cmd = 
						conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				
				cmd.setString(1, title);
				cmd.setInt(2, length);
				cmd.setString(3, description);
				
				if (cmd.executeUpdate() == 1)
				{
					conn.commit();
					ResultSet ids = cmd.getGeneratedKeys();
					
					if (ids.next()) {
						int id = ids.getInt(1);
						out.printf("Курс добавлен. Id: %d\n", id);
					}
				}
			}
			catch(SQLException ex) {
				conn.rollback();
				System.out.println(ex.getMessage());
			}
		}
		
		
		//conn.close();
		
		

	}

}
