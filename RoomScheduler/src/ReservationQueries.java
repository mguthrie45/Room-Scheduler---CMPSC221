
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
public class ReservationQueries {
    public static ArrayList<ReservationEntry> getReservationsByDate(Date date) {
        ResultSet resultSet = null;
        ArrayList<ReservationEntry> reservations = new ArrayList();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement selectReservationsByDate = conn.prepareStatement("SELECT * FROM Reservations WHERE date=?");
            selectReservationsByDate.setDate(1, date);
            resultSet = selectReservationsByDate.executeQuery();
            while(resultSet.next()) {
                reservations.add(new ReservationEntry(resultSet.getString("faculty"),
                resultSet.getString("room"),
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
        return reservations;
    }
    public static ArrayList<RoomEntry> getRoomsByReservedDate(Date date) {
        ArrayList<RoomEntry> rooms = new ArrayList();
        ResultSet resultSet = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement selectRoomsByDate = conn.prepareStatement("SELECT room, seats FROM Reservations WHERE date=? ORDER BY seats");
            selectRoomsByDate.setDate(1, date);
            resultSet = selectRoomsByDate.executeQuery();
            while(resultSet.next()) {
                rooms.add(new RoomEntry(resultSet.getString("room"),
                resultSet.getInt("seats")));
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
        return rooms;
    }
    public static void addReservationEntry(ReservationEntry entry) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement addRE = conn.prepareStatement("INSERT INTO Reservations (faculty, room, date, seats, timestamp)"
            + " VALUES (?, ?, ?, ?, ?)");
            addRE.setString(1, entry.getFaculty());
            addRE.setString(2, entry.getRoom());
            addRE.setDate(3, entry.getDate());
            addRE.setInt(4, entry.getSeats());
            addRE.setTimestamp(5, entry.getTimestamp());
            addRE.executeUpdate();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
    }
    public static ArrayList<ReservationEntry> getReservationsByFaculty(String faculty) {
        ArrayList<ReservationEntry> reservations = new ArrayList();
        ResultSet resultSet = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement selectRoomsByDate = conn.prepareStatement("SELECT * FROM Reservations WHERE faculty=?");
            selectRoomsByDate.setString(1, faculty);
            resultSet = selectRoomsByDate.executeQuery();
            while(resultSet.next()) {
                reservations.add(new ReservationEntry(resultSet.getString("faculty"),
                resultSet.getString("room"),
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
        return reservations;
    }
    public static void deleteReservation(ReservationEntry entry) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement addRE = conn.prepareStatement("DELETE FROM Reservations WHERE faculty=? AND room=? AND date=? AND seats=? AND timestamp=?");
            addRE.setString(1, entry.getFaculty());
            addRE.setString(2, entry.getRoom());
            addRE.setDate(3, entry.getDate());
            addRE.setInt(4, entry.getSeats());
            addRE.setTimestamp(5, entry.getTimestamp());
            addRE.executeUpdate();
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
            PreparedStatement selectAll = conn.prepareStatement("SELECT * FROM Reservations");
            ResultSet resultSet = selectAll.executeQuery();
            resultSetMetaData = resultSet.getMetaData();
            columnCount = resultSetMetaData.getColumnCount();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        return columnCount;
    }
    public static String[][] convertRowsToArrays(ArrayList<ReservationEntry> data) {
        System.out.println(data);
        System.out.println(getColumnCount());
        String[][] rows = new String[data.size()][getColumnCount()];
        for (int i = 0; i < data.size(); i++) {
            ReservationEntry entry = data.get(i);
            rows[i][0] = entry.getFaculty();
            rows[i][1] = entry.getRoom();
            rows[i][2] = entry.getDate().toString();
            rows[i][3] = String.valueOf(entry.getSeats());
            rows[i][4] = entry.getTimestamp().toString();
        }
        System.out.println(rows);
        return rows;
    }
}
