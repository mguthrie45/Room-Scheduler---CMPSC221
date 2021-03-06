
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class Dates {
    public static void addDate(Date date) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement addD = conn.prepareStatement("INSERT INTO Dates (date) VALUES (?)");
            addD.setDate(1, date);
            addD.executeUpdate();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
    }
    public static ArrayList<Date> getAllDates() {
        ArrayList<Date> dates = new ArrayList();
        ResultSet resultSet = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement getAllD = conn.prepareStatement("SELECT * FROM Dates");
            resultSet = getAllD.executeQuery();
            while(resultSet.next()) {
                dates.add(resultSet.getDate("date"));
            }
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
            }
            catch (SQLException sqlexception) {
                sqlexception.printStackTrace();
            }
        }
        return dates;
    }
}
