import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
  static Connection conn;

  public db() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");

      String url = "jdbc:mysql://localhost:3306/surya";
      String user = "root";
      String password = "root";

      conn = DriverManager.getConnection(url, user, password);

      System.out.println("Database connected successfully!");

    } catch (ClassNotFoundException e) {
      System.out.println("MySQL JDBC Driver not found.");
      e.printStackTrace();
    } catch (SQLException e) {
      System.out.println("Connection failed.");
      e.printStackTrace();
    }
  }

  public static Connection getConnection() {
    return conn;
  }
}
