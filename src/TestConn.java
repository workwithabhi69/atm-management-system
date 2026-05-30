public class TestConn {
    public static void main(String[] args) {
        try {
            java.sql.Connection conn = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bankdb",
                "root",
                "root@123"
            );
            System.out.println("Connection successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
