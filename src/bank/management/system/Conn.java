package bank.management.system;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conn {
    Connection connection;
    Statement statement;

    public Conn() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bankdb",
                    "root",
                    "root@123");
            statement = connection.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Database connection error! Please make sure your MySQL database is created properly.\n"
                            + e.toString());
            e.printStackTrace();
        }
    }
}
