
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author guthr
 */
public class Faculty {
    public static void addFaculty(String name) {
        try {
            Connection conn = DBConnection.getConnection();conn = DBConnection.getConnection();
            PreparedStatement addFac = conn.prepareStatement("INSERT INTO Faculty (name) VALUES (?)");
            addFac.setString(1, name);
            addFac.executeUpdate();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
    }
    public static ArrayList<String> getAllFaculty() {
        ArrayList<String> faculty = new ArrayList<String>();
        ResultSet resultSet = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement getAllFac = conn.prepareStatement("SELECT * FROM Faculty");
            resultSet = getAllFac.executeQuery();
            while(resultSet.next()) {
                faculty.add(resultSet.getString("name"));
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
        return faculty;
    }
}
