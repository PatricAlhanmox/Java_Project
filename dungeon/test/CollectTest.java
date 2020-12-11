package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;
public class CollectTest {
    @Test
    void CollectTreasure() {
        Dungeon d = new Dungeon(5,5);
        Player p =new Player(d, 0, 0);

        Treasure treasure1 = new Treasure(3,1);
        Treasure treasure2 = new Treasure(3,0);

        d.addEntity(p);
        d.addEntity(treasure1);
        d.addEntity(treasure2);

        p.moveDown();
        p.moveRight();
        p.moveRight();
        p.moveRight();
        p.PickUp();
        p.moveUp();
        p.PickUp();

        assertEquals(p.getTotalTreasure(), 2);
    }

    @Test
    void collectSword() {
        Dungeon d = new Dungeon(5,5);
        Player p =new Player(d, 0, 0);
        Sword s = new Sword(2,1);

        d.addEntity(p);
        d.addEntity(s);

        p.moveDown();
        p.moveRight();
        p.moveRight();
        p.PickUp();

        assertEquals(p.getSword().getHit(), 5);
    }

    @Test
    void collectPortion() {
        Dungeon d = new Dungeon(5,5);
        Player p =new Player(d, 0, 0);
        Portion por = new Portion(2,1);

        d.addEntity(p);
        d.addEntity(por);

        p.moveDown();
        p.moveRight();
        p.moveRight();
        p.PickUp();

        assertEquals(p.getPortion().get_invicibleMove(), 10);
    }
}