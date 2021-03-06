
import java.sql.Timestamp;
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author guthr
 */
public class ReservationEntry {
    private String faculty;
    private String room;
    private Date date;
    private int seats;
    private Timestamp timestamp;
    public ReservationEntry(String faculty, String room, Date date, int seats,
            Timestamp timestamp) {
        this.faculty = faculty;
        this.room = room;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
    }
    public String getFaculty() {
        return faculty;
    }
    public String getRoom() {
        return room;
    }
    public Date getDate() {
        return date;
    }
    public int getSeats() {
        return seats;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public static String[] getColumnLabels() {
        String[] labels = {"Faculty", "Room", "Date", "Seats", "Timestamp"};
        return labels;
    }
}
