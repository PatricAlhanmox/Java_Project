/**
 *
 */
package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import unsw.Room.Room;
import unsw.Reservation.Reservation;
import unsw.VenueSelf.Venue;
 

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 * @author Robert Clifton-Everest
 *
 */
public class VenueHireSystem {

    private final ArrayList<Room> rooms;
    private final ArrayList<Reservation> reservations;
    private final ArrayList<Venue> ven;

    /**
     * Constructs a venue hire system. Initially, the system contains no venues,
     * rooms, or bookings.
     */
    public VenueHireSystem() {
        this.rooms = new ArrayList<Room>();
        this.reservations = new ArrayList<Reservation>();
        this.ven = new ArrayList<Venue>();
    }

    private void processCommand(final JSONObject json) {
        switch (json.getString("command")) {

            case "room":
                final String venue = json.getString("venue");
                final String room = json.getString("room");
                final String size = json.getString("size");
                addRoom(venue, room, size);
                break;

            case "request":
                final String id = json.getString("id");
                final LocalDate start = LocalDate.parse(json.getString("start"));
                final LocalDate end = LocalDate.parse(json.getString("end"));
                final int small = json.getInt("small");
                final int medium = json.getInt("medium");
                final int large = json.getInt("large");

                final JSONObject result = request(id, start, end, small, medium, large);

                System.out.println(result.toString(2));
                break;

            case "change":
                final String Cid = json.getString("id");
                final LocalDate Cstart = LocalDate.parse(json.getString("start"));
                final LocalDate Cend = LocalDate.parse(json.getString("end"));
                final int Csmall = json.getInt("small");
                final int Cmedium = json.getInt("medium");
                final int Clarge = json.getInt("large");
                JSONObject changes = change(Cid, Cstart, Cend, Csmall, Cmedium, Clarge);
                System.out.println(changes.toString(2));
                break;

            case "cancel" :
                String cancelID = json.getString("id");
                cancel(cancelID);
                break;
            
            case "list":
                String listVenue = json.getString("venue");
                JSONArray listarray = new JSONArray();
                listarray = lists(listVenue);
                System.out.println(listarray.toString(2));
                break;
        }
    }

    private void addRoom(final String venue, final String room, final String size) {
        boolean pf = false;
        if (ven.isEmpty()) {
            final Venue vp = new Venue(venue);
            final Room r = new Room(room, size);
            vp.addRooms(r);
            ven.add(vp);
            rooms.add(r);
        } else {
            boolean findName = true;
            for (final Venue v : ven) {
                if (v.getVenueName().equals(venue)) {
                    for (final Room r : v.getRooms()) {
                        // If there are the same name room in venue beeing created then return false
                        if (r.getName().equals(room)) {
                            pf = true;
                            r.setName("Error the room name is duplicated");
                        }
                    }
                    // if no then we add the room into venue
                    if (pf == false) {
                        final Room rw = new Room(room, size);
                        v.addRooms(rw);
                        rooms.add(rw);
                    }
                } else {
                    findName = false;
                }

            }
            // If there are no venue then we add venue
            if (findName == false) {
                final Venue vpr = new Venue(venue);
                ven.add(vpr);
                final Room rpp = new Room(room, size);
                vpr.addRooms(rpp);
                rooms.add(rpp);
            }
        }
    }

    public JSONObject request(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
        JSONObject result = new JSONObject();
        JSONArray arrRoom = new JSONArray();

        ArrayList<Room> smallRoom = new ArrayList<Room> ();
        ArrayList<Room> mediumRoom = new ArrayList<Room> ();
        ArrayList<Room> largeRoom = new ArrayList<Room> ();

        int copysmall = small;
        int copymedium = medium;
        int copylarge = large;

        int countVenue = 0;
        int sRoom = 0;
        int mRoom = 0;
        int lRoom = 0;


        //Create a venue to store the correct venue
        Venue VReal = null;

        //Search through list to find the venue that are avaliable for requested room and lock the correct venue
        for (Venue v: ven) {

            //First Make sure each type of rooms have the requested room
            if (v.countSmallRooms(v.getRooms()) >= small && v.countMediumRooms(v.getRooms()) >= medium && v.countLargeRooms(v.getRooms()) >= large) {
                //Use three room list to store the avaliable room
                if (small != 0) {
                    smallRoom = v.searchRooms("small", start, end);
                }
                if (medium != 0) {
                    mediumRoom = v.searchRooms("medium", start, end);
                }
                if (large != 0) {
                    largeRoom = v.searchRooms("large", start, end);
                }

                if (smallRoom.size() >= small && mediumRoom.size() >= medium && largeRoom.size() >= large) {
                    VReal = v;
                    break;
                } else {
                    System.out.println("Can not locate");
                }
            }
        }

        if (VReal==null) {
            result.put("status", "rejected");
            return result;
        }

        Reservation reserve = new Reservation(id, small, medium, large, start, end);

        String correctRoomName;
        while (small > 0) {
            for (Room smallr : VReal.getRooms()) {
                if (smallr.getSize().equals("small")) {

                    //If there is a valid room then label its name as correctname
                    correctRoomName = smallr.getName();

                    for (Room rsst: VReal.getRooms()) {
                        if (rsst.getName().equals(correctRoomName)) {
                            rsst.addReservation(reserve);
                            break;
                        }
                    }

                    reservations.add(reserve);
                    small--;
                    sRoom++;
                    arrRoom.put(smallr.getName());
                    if (small == 0) break;
                }
            }
        }
        
        while (medium > 0) {
            for (Room mediumr : VReal.getRooms()) {
                if (mediumr.getSize().equals("medium")) {
                    correctRoomName = mediumr.getName();

                    for (Room rsst: VReal.getRooms()) {
                        if (rsst.getName().equals(correctRoomName)) {
                            rsst.addReservation(reserve);
                            break;
                        }
                    }

                    reservations.add(reserve);
                    medium--;
                    mRoom++;
                    arrRoom.put(mediumr.getName());
                    if (medium == 0) break;
                }
            }
        }

        while (large > 0) {
            for ( Room larger : largeRoom) {
                if (larger.getSize().equals("large")) {
                    correctRoomName = larger.getName();

                    for (Room rsst: VReal.getRooms()) {
                        if (rsst.getName().equals(correctRoomName)) {
                            rsst.addReservation(reserve);
                            break;
                        }
                    }

                    reservations.add(reserve);
                    large--;
                    lRoom++;
                    arrRoom.put(larger.getName());
                    if (large == 0) break;
                }
            }
        }

        result.put("venue", VReal.getVenueName());
        result.put("rooms", arrRoom);
        countVenue++;

        if (sRoom == copysmall && mRoom == copymedium && lRoom == copylarge) {
            result.put("status", "success");
        } 
        else if (!(sRoom == small && mRoom == medium && lRoom == large) && countVenue == ven.size()-1) {
            result.put("status", "rejected");
        } 
        else {
            System.out.println("Unknow Error");
        }
    
        return result;
    }



    public JSONObject change(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
        JSONObject result = new JSONObject();

        //inital a copy of the original reserve just in case failed delete
        Reservation oldReserve = null;
        
        Reservation aimReserve = null;
        

        for (Venue vs: ven) {
            for (Room r: vs.getRooms()) {
                for(Reservation res: r.getReservation()) {

                    //If the id are correct then regard change as a request statement, if request statuts successed
                    //then change the details of reservation
                    if (res.getID().equals(id)) {
                        aimReserve = res;
                        break;
                    }
                }
            }
        }

        oldReserve = aimReserve;


        //Check all the venue each room every reservation to find the ID that need to be corrected
        for (Venue vs: ven) {
            for (Room r: vs.getRooms()) {
                for(Reservation res: r.getReservation()) {

                    //If the id are correct then regard change as a request statement, if request statuts successed
                    //then change the details of reservation
                    if (res.getID().equals(id)) {
                        //Totally this room will have small+medium+large times reservation so that remove all of it
                        for (int i = 0; i <= r.getReservation().size(); i++) {
                            r.delReservation(res);
                        }
                        break;
                    } else {
                        ;
                    }
                }
            }
        }

        if (oldReserve.getID().equals("null")) {
            result.put("status", "rejected");
            request(oldReserve.getID(), oldReserve.getStart(), oldReserve.getEnd(), oldReserve.getSmall(), oldReserve.getMiddle(), oldReserve.getLarge());
            return result; 
        } else {
            result = request(id, start, end, small, medium, large);
        }



        return result;
    }


    public void cancel(String id) {
        for (Venue vs: ven) {
            for (Room r: vs.getRooms()) {
                for(Reservation res: r.getReservation()) {

                    //If the id are correct then regard change as a request statement, if request statuts successed
                    //then change the details of reservation
                    if (res.getID().equals(id)) {
                        //Totally this room will have small+medium+large times reservation so that remove all of it
                        r.delReservation(res);
                        break;
                    } else {
                        ;
                    }
                }
            }
        }
    }


    public JSONArray lists(String venue) {
        JSONArray result = new JSONArray();
        Venue veL = null;
        for (Venue veu : ven) {
            if (veu.getVenueName().equals(venue)) {
                veL = veu;
                break;
            }
        }

        if (veL == null) {
            return null;
        } else {
            for (Room rv: veL.getRooms()) {
                JSONObject venueResult = new JSONObject();
                JSONArray ReservationArray = new JSONArray();
                for (Reservation resv: rv.getReservation()) {
                    JSONObject PackUp = new JSONObject();
                    PackUp.put("start", resv.getStart());
                    PackUp.put("end", resv.getEnd());
                    PackUp.put("id", resv.getID());
                    ReservationArray.put(PackUp);
                }
                venueResult.put("reservation", ReservationArray);
                venueResult.put("room", rv.getName());
                result.put(venueResult);
            }
        }

        return result;
    }



    public static void main(final String[] args) {
        final VenueHireSystem system = new VenueHireSystem();

        final Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            final String line = sc.nextLine();
            if (!line.trim().equals("")) {
                final JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }

}