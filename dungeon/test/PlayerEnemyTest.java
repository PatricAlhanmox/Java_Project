package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;


public class PlayerEnemyTest {
    @Test
    void PlayerSwordKillEnemy () {
        Dungeon d = new Dungeon(5,5);
        Player p =new Player(d, 0, 0);
        Sword s = new Sword(1,0);
        Enemy e1 = new Enemy(3,0,d);
        
        d.addEntity(p);
        d.addEntity(s);
        d.addEntity(e1);


        p.moveRight();
        p.PickUp();
        assertEquals(p.getSword().getHit(),5); // player should have sword ,and sword have 5 hits
        
        assertEquals(e1.getX(), 2); // enemy should move to  2,0
        assertEquals(e1.getY(), 0);
        p.moveRight();


        assertEquals(p.get_num_enemykilled(), 1);
    }
}