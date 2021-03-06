
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
public class RoomQueries {
    public static ArrayList<RoomEntry> getAllPossibleRooms() {
        ArrayList<RoomEntry> rooms = new ArrayList();
        ResultSet resultSet = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement getAllR = conn.prepareStatement("SELECT * FROM Rooms ORDER BY seats");
            resultSet = getAllR.executeQuery();
            while (resultSet.next()) {
                rooms.add(new RoomEntry(resultSet.getString("name"),
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
    public static ArrayList<String> getAllPossibleRoomsNames() {
        ArrayList<RoomEntry> allPossibleRooms = getAllPossibleRooms();
        ArrayList<String> names = new ArrayList();
        for (RoomEntry entry: allPossibleRooms) {
            names.add(entry.getName());
        }
        return names;
    }
    public static void addRoom(RoomEntry room) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement addR = conn.prepareStatement("INSERT INTO Rooms (name, seats) VALUES (?, ?)");
            addR.setString(1, room.getName());
            addR.setInt(2, room.getSeats());
            addR.executeUpdate();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
    }
    public static void dropRoom(String name) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement dropR = conn.prepareStatement("DELETE FROM Rooms WHERE name=?");
            dropR.setString(1, name);
            dropR.executeUpdate();
        }
        catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
    }
}
