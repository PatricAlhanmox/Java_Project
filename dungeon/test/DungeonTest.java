package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;

public class DungeonTest {
    
    @Test
    public void WidthHeight(){
        Dungeon d = new Dungeon(4,4);
        assertEquals(d.getWidth(), 4);
        assertEquals(d.getHeight(), 4);
    }

    @Test
    void playerMoving() {
        Dungeon d = new Dungeon(5,5);
        Player p =new Player(d, 0, 0);


        Wall wall1 = new Wall(1,2);
        Wall wall2 = new Wall(3,2);
        //Boulder boulder = new Boulder(0, 3);/

        //Treasure treasure1 = new Treasure(3,1);
        //Treasure treasure2 = new Treasure(3,0);

        d.addEntity(p);
        d.addEntity(wall1);
        d.addEntity(wall2);

        //Move boundry
        p.moveUp();
        assertEquals(0, p.getX());
        assertEquals(0, p.getY());
        p.moveLeft();
	    assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 0);
        
        //Move
        p.moveDown();
        p.moveRight();

        //Walk on Wall
        p.moveDown();
		assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p.moveRight();
        p.moveRight();
        p.moveRight();
        p.moveDown();
        p.moveLeft();

        assertEquals(p.getX(), 4);
        assertEquals(p.getY(), 2);


        //Move to boundry
        p.moveDown();
        p.moveDown();
        p.moveRight();
        assertEquals(p.getX(), 4);
        assertEquals(p.getY(), 4);
        p.moveDown();
        assertEquals(p.getX(), 4);
        assertEquals(p.getY(), 4);
    }


    @Test
    void PlayerTeleport () {
        Dungeon d = new Dungeon(5,5);
        Player p =new Player(d, 0, 0);

        Portal pot1 = new Portal(1, 2, 1);
        Portal pot2 = new Portal(3, 4, 1);
        d.addEntity(pot1);
        d.addEntity(pot2);
        d.addEntity(p);


        p.moveDown();
        p.moveDown();
        p.moveRight();
        assertEquals(p.getX(), 3);
        assertEquals(p.getY(), 4);
    }


    @Test
    void PushBoulder() {
        Dungeon d = new Dungeon(5,5);
        Player p =new Player(d, 0, 0);


        Wall wall1 = new Wall(1,2);
        Wall wall2 = new Wall(3,2);
        Wall wall3 = new Wall(1,1);
        Boulder boulder1 = new Boulder(0, 3);
        Boulder boulder2 = new Boulder(2, 2);

        d.addEntity(boulder1);
        d.addEntity(boulder2);
        d.addEntity(wall1);
        d.addEntity(wall2);
        d.addEntity(wall3);
        d.addEntity(p);
        
        p.moveDown();
        p.moveDown();
        p.moveDown();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 3);
        assertEquals(boulder1.getX(), 0);
        assertEquals(boulder1.getY(), 4);

        //can not push further than boulder
        p.moveDown();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 3);
        assertEquals(boulder1.getX(), 0);
        assertEquals(boulder1.getY(), 4);

        p.moveRight();
        p.moveRight();
        p.moveUp();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 2);
        assertEquals(boulder2.getX(), 2);
        assertEquals(boulder2.getY(), 1);

        //Push boulder to a wall
        p.moveDown();
        p.moveRight();
        p.moveRight();
        p.moveUp();
        p.moveUp();
        p.moveLeft();
        p.moveLeft();
        assertEquals(p.getX(), 3);
        assertEquals(p.getY(), 1);
        assertEquals(boulder2.getX(), 2);
        assertEquals(boulder2.getY(), 1);

    }

    @Test
    void PushBoulderOnSwitch() {
        Dungeon d = new Dungeon(5,5);
        Player p =new Player(d, 0, 0);

        Switch Switch = new Switch(1,2);
        Boulder boulder1 = new Boulder(1,1);

        d.addEntity(boulder1);
        d.addEntity(Switch);
        d.addEntity(p);

        p.moveRight();
        p.moveDown();
        assertEquals(boulder1.getX(), 1);
        assertEquals(boulder1.getY(), 2);
    }


    @Test
    void BoulderSwitch() {
        Dungeon d = new Dungeon(5,5);
        Player p =new Player(d, 0, 0);

        Switch Switch1 = new Switch(1,2);
        Switch Switch2 = new Switch(2,2);
        Boulder boulder1 = new Boulder(1,1);
        Boulder boulder2 = new Boulder(2,1);

        d.addEntity(p);
        d.addEntity(Switch1);
        d.addEntity(Switch2);
        d.addEntity(boulder1);
        d.addEntity(boulder2);

        p.moveDown();
        p.moveRight();
        assertEquals(boulder1.getX(), 1);
        assertEquals(boulder1.getY(), 1);

        p.moveUp();
        p.moveRight();
        p.moveDown();
        assertEquals(p.get_switch_opened(), 1);
        
        p.moveUp();
        p.moveRight();
        p.moveDown();
        assertEquals(p.get_switch_opened(), 2);
    }
}   