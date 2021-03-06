
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author guthr
 */
public class DBConnection {
    private static final String URL = "jdbc:derby://localhost:1527/RoomSchedulerDBMaxGuthrie974486477";
    private static final String USER = "java";
    private static final String PASS = "java";
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
            System.exit(1);
        }
        return conn;
    }
}
