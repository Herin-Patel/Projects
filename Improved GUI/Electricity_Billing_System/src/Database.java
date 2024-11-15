import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    Connection myConnection;
    Statement myStatement;

    Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bill_System", "root", "HerinPatel@2001");
//            myConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Bill_System", "root", "HerinPatel@2001");
            myStatement = myConnection.createStatement();

        } catch (SQLException expobj) {
            System.out.printf("Error in MySQL Connection : %s\n", expobj.getMessage());
            expobj.printStackTrace();
        } catch (ClassNotFoundException expobj) {
            System.out.println("MySQL JDBC Driver not found.");
            expobj.printStackTrace();
        }
    }
}
