package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;

public class OpenDoorTest {

    @Test
    void NoKey() {
         Dungeon d = new Dungeon(5,5);
         Player p =new Player(d, 0, 0);
         Door door1 = new Door(2,2,2);

         d.addEntity(p);
         d.addEntity(door1);

         p.moveDown();
         p.moveRight();
         p.moveDown();
         p.PickUp();
         p.moveRight();

         assertEquals(p.getX(),1);
         assertEquals(p.getY(),2);
    }



    @Test
    void PlayDoorSuccess() {
        Dungeon d = new Dungeon(5,5);
        Player p =new Player(d, 0, 0);
        Door door1 = new Door(2,2,2);
        Key k1 = new Key(1, 2, 2);

        //d.setKey(k1);
        //d.setPlayer(p);
        //d.setDoor(door1);

       d.addEntity(p);
        d.addEntity(door1);
      d.addEntity(k1);

        p.moveDown();
        p.moveRight();
        p.moveDown();
        p.PickUp();
        p.moveRight();

        assertEquals(p.getX(),2);
        assertEquals(p.getY(),2);

    }


    @Test
    void PlayDoorFailed() {
        Dungeon d = new Dungeon(5,5);
        Player p =new Player(d, 0, 0);
        Door door2 = new Door(3,3,1);
        Key k1 = new Key(1, 2, 2);


        d.addEntity(p);
        d.addEntity(door2);
        d.addEntity(k1);

        p.moveDown();
        p.moveRight();
        p.moveDown();
        p.PickUp();
        p.moveRight();
        p.moveRight();
        p.moveDown();

        //Player should stop at (3,2) because he doesn't have the right key to pass door
        assertEquals(p.getX(),3);
        assertEquals(p.getY(),2);
    }


    @Test
    void canOnlyPickOneKey() {
        Dungeon d = new Dungeon(5,5);
        Player p =new Player(d, 0, 0);
        Door door2 = new Door(3,3,1);
        Key k1 = new Key(1, 2, 2);
        Key k2 = new Key(2, 2, 1);

        d.addEntity(p);
        d.addEntity(door2);
        d.addEntity(k1);
        d.addEntity(k2);

        p.moveDown();
        p.moveRight();
        p.moveDown();
        p.PickUp();
        p.moveRight();
        p.PickUp();
        p.moveRight();
        p.moveDown();

        //Player can only move to (3,2) because he didnt pick up matched key
        assertEquals(p.getX(),3);
        assertEquals(p.getY(),2);
    }


    @Test
    void combineOpenDoors() {
        Dungeon d = new Dungeon(5,5);
        Player p =new Player(d, 0, 0);
        Door door1 = new Door(2,3,2);
        Key k1 = new Key(1, 2, 2);
        Key k2 = new Key(2,4,1);

        d.addEntity(p);
        d.addEntity(door1);
        d.addEntity(k1);
        d.addEntity(k2);

        p.moveDown();
        p.moveRight();
        p.moveDown();
        p.PickUp();
        p.moveDown();
        p.moveRight();
        
        assertEquals(p.getX(),2);
        assertEquals(p.getY(),3);

        p.moveUp();
        p.moveDown();
        assertEquals(p.getX(),2);
        assertEquals(p.getY(),3);

        p.moveDown();
        p.moveUp();
        assertEquals(p.getX(),2);
        assertEquals(p.getY(),3);
    }
}
