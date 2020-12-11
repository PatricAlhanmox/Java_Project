package unsw.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import unsw.Reservation.Reservation;

public class Room {
    private String size;
    private String name;
    private ArrayList<Reservation> Reserve;

    public Room(String name, String size) {
        this.name = name;
        this.size = size;
        this.Reserve = new ArrayList<Reservation>();
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public void sortArray(ArrayList<Reservation> Reservation) {
        //Use collection sort to sort the arrayList
        Collections.sort(Reservation, new Comparator<Reservation>() {

			@Override
			public int compare(Reservation o1, Reservation o2) {
                return o1.getStart().compareTo(o2.getStart());
			}
        });
    }

    public ArrayList<Reservation> getReservation() {
        return Reserve;
    }

    public void addReservation(Reservation R) {
        Reserve.add(R);
        sortArray(Reserve);
    }

    public void delReservation(Reservation R) {
        Reserve.remove(R);
    }

    public boolean checkRooms(LocalDate Start, LocalDate End) {
        boolean pf = false;
        if (Start.compareTo(End) <= 0) {
            if (Reserve.isEmpty()) {
                pf = true;
            } 
            else {
                for (Reservation res : Reserve) {
                    if (res.getStart().compareTo(End) > 0) {
                        pf = true;
                    } else if (res.getEnd().compareTo(Start) < 0) {
                        pf = true;
                    } else {
                        pf = false;
                        break;
                    }
                }
            }
            return pf;
        } else {
            return pf;
        }
        
    }

}