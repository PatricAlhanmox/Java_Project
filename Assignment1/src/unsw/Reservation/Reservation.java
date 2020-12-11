package unsw.Reservation;
import unsw.Room.Room;

import java.time.LocalDate;
import java.util.ArrayList;


public class Reservation {
    private String ID;
    private int small;
    private int medium;
    private int large;
    private LocalDate Start;
    private LocalDate End;
    private ArrayList<Room> Rooms;

    public Reservation(String ID, int small, int medium, int large, LocalDate Start, LocalDate End) {
        this.ID =ID;
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.Start = Start;
        this.End = End;
        this.Rooms = new ArrayList<Room> ();
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public int getSmall() {
        return small;
    }

    public void setSmall(int small) {
        this.small = small;
    }

    public int getMiddle() {
        return medium;
    }

    public void setMiddle(int medium) {
        this.medium = medium;
    }

    public int getBig() {
        return large;
    }

    public void setBig(int large) {
        this.large = large;
    }

    public LocalDate getStart() {
        return Start;
    }

    public void setStart(LocalDate start) {
        Start = start;
    }

    public LocalDate getEnd() {
        return End;
    }

    public void setEnd(LocalDate end) {
        End = end;
    }

    public ArrayList<Room> getRoom() {
        return Rooms;
    }

    public void addRoom(Room r) {
        Rooms.add(r);
    }

    public void delRoom(Room r) {
        Rooms.remove(r);
    }

    @Override
    public String toString() {
        System.out.print(getID() + " " + getBig() + " " + getMiddle() + " " + getSmall() + " " + getRoom() + " " + getStart()+ " " + getEnd());
        return getID();
    }

	public int getLarge() {
		return 0;
	}
}