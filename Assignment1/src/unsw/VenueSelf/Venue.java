package unsw.VenueSelf;

import java.time.LocalDate;
import java.util.ArrayList;
import unsw.Room.Room;

public class Venue {
    private String venueName;
    private ArrayList<Room> rooms;

    public Venue(String name) {
        this.venueName = name;
        this.rooms = new ArrayList<Room> ();
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRooms(Room r) {
        rooms.add(r);
    }
       
    public ArrayList<Room> searchRooms(String size, LocalDate Start, LocalDate End) {
        ArrayList<Room> fittedRoom = new ArrayList<Room> ();
        for (Room r: rooms) {
            if (r.getSize().equals(size)) {
                if (r.getReservation().isEmpty() && (r.checkRooms(Start, End))) {
                    fittedRoom.add(r);
                } else if (r.checkRooms(Start, End)) {
                    fittedRoom.add(r);
                }
            }
        }
        return fittedRoom;
    }

    public int countSmallRooms(ArrayList<Room> R) {
        int countSmall = 0;
        for(Room rs: R) {
            if (rs.getSize().equals("small")) {
                countSmall = countSmall + 1;
            }
        }
        return countSmall;
    }

    public int countMediumRooms(ArrayList<Room> R) {
        int countMedium = 0;
        for(Room rs: R) {
            if (rs.getSize().equals("medium")) {
                countMedium = countMedium + 1;
            }
        }
        return countMedium;
    }

    public int countLargeRooms(ArrayList<Room> R) {
        int countLarge = 0;
        for(Room rs: R) {
            if (rs.getSize().equals("large")) {
                countLarge = countLarge + 1;
            }
        }
        return countLarge;
    }
}