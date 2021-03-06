
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
public class WaitlistQueries {
    public static ArrayList<WaitlistEntry> getAllWaitlistEntries() {
        ArrayList<WaitlistEntry> waitlist = new ArrayList();
        ResultSet resultSet = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement selectAllEntries = conn.prepareStatement("SELECT * FROM Waitlists ORDER BY Timestamp, Date");
            resultSet = selectAllEntries.executeQuery();
            while (resultSet.next()) {
                waitlist.add(new WaitlistEntry(resultSet.getString("faculty"),
                        resultSet.getDate("date"),
                        resultSet.getInt("seats"),
                        resultSet.getTimestamp("timestamp")));
            }
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        return waitlist;
    }
    public static ArrayList<WaitlistEntry> getWaitlistByDate(Date date) {
        ArrayList<WaitlistEntry> waitlist = new ArrayList();
        ResultSet resultSet = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement selectWaitlistByDate = conn.prepareStatement("SELECT * FROM Waitlists WHERE date=? ORDER BY Timestamp");
            selectWaitlistByDate.setDate(1, date);
            resultSet = selectWaitlistByDate.executeQuery();
            while(resultSet.next()) {
                waitlist.add(new WaitlistEntry(resultSet.getString("faculty"),
                resultSet.getDate("date"),
                resultSet.getInt("seats"),
                resultSet.getTimestamp("timestamp")));
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
        return waitlist;
    }
    public static ArrayList<WaitlistEntry> getWaitlistByFaculty(String faculty) {
        ArrayList<WaitlistEntry> waitlist = new ArrayList();
        ResultSet resultSet = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement selectWaitlistByFaculty = conn.prepareStatement("SELECT * FROM Waitlists WHERE faculty=?");
            selectWaitlistByFaculty.setString(1, faculty);
            resultSet = selectWaitlistByFaculty.executeQuery();
            while(resultSet.next()) {
                waitlist.add(new WaitlistEntry(resultSet.getString("faculty"),
                resultSet.getDate("date"),
                resultSet.getInt("seats"),
                resultSet.getTimestamp("timestamp")));
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
        return waitlist;
    }
    public static void addWaitlistEntry(WaitlistEntry entry) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement addWE = conn.prepareStatement("INSERT INTO Waitlists (faculty, date, seats, timestamp)"
            + " VALUES (?, ?, ?, ?)");
            addWE.setString(1, entry.getFaculty());
            addWE.setDate(2, entry.getDate());
            addWE.setInt(3, entry.getSeats());
            addWE.setTimestamp(4, entry.getTimestamp());
            addWE.executeUpdate();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
    }
    public static void deleteWaitlistEntry(WaitlistEntry entry) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement addWE = conn.prepareStatement("DELETE FROM Waitlists WHERE faculty=? AND date=? AND seats=? AND timestamp=?");
            addWE.setString(1, entry.getFaculty());
            addWE.setDate(2, entry.getDate());
            addWE.setInt(3, entry.getSeats());
            addWE.setTimestamp(4, entry.getTimestamp());
            addWE.executeUpdate();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
    }
    public static int getColumnCount() {
        ResultSetMetaData resultSetMetaData = null;
        int columnCount = 0;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement selectAll = conn.prepareStatement("SELECT * FROM Waitlists");
            ResultSet resultSet = selectAll.executeQuery();
            resultSetMetaData = resultSet.getMetaData();
            columnCount = resultSetMetaData.getColumnCount();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        return columnCount;
    }
    public static String[][] convertRowsToArrays(ArrayList<WaitlistEntry> data) {
        System.out.println(data);
        System.out.println(getColumnCount());
        String[][] rows = new String[data.size()][getColumnCount()];
        for (int i = 0; i < data.size(); i++) {
            WaitlistEntry entry = data.get(i);
            rows[i][0] = entry.getFaculty();
            rows[i][1] = entry.getDate().toString();
            rows[i][2] = String.valueOf(entry.getSeats());
            rows[i][3] = entry.getTimestamp().toString();
        }
        System.out.println(rows);
        return rows;
    }
}
