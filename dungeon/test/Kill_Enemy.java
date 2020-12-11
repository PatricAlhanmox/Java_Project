package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;

public class Kill_Enemy {
    @Test
    void kill_withNothing()
    {
        Dungeon d = new Dungeon(5,5);
        Player p =new Player(d, 0, 0);
    }

    
    
}