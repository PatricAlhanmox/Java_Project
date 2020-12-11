package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private final int width, height;
    private final List<Entity> entities;
    private Player player;
    private int totalEnemy;
    private int totalTreasure;
    private int totalSwitch;
    private int totalBoulder;
    private int totalExit;
    private DungeonController dgc = null;
    private Goal_list gl;

    public Dungeon(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.totalEnemy = 0;
        this.totalTreasure = 0;
        this.totalSwitch = 0;
        this.totalBoulder = 0;
        this.totalExit =0;
    }

    public int totalExit()
    {
        return this.totalExit;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void set_goal_list(Goal_list input)
    {
        this.gl = input;
    }

    public Goal_list get_goal_list()
    {
        return this.gl;
    }


    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDungeonControl(DungeonController dgc) {
        this.dgc = dgc;
    }


    public List <Entity> getEntityList() {
        return entities;
    }




    //Add 7.20
    public int getTotoalTreasure() {
        return totalTreasure;
    }

    public int getTotoalEnemy() {
        return totalEnemy;
    }

    public int getTotoalSwitch() {
        return totalSwitch;
    }

    public int getTotalBoulder() {
        return totalBoulder;
    }
    

    public void addEntity( Entity entity) {
        entities.add(entity);
        
        if (entity instanceof Treasure) {
            this.totalTreasure += 1;
        } else if (entity instanceof Enemy) {
            this.totalEnemy += 1;
            this.player.add_enemy((Enemy) entity);
        } else if (entity instanceof Switch) {
            this.totalSwitch += 1;
        }else if(entity instanceof Player)
        {
            this.player = (Player) entity;
        } else if (entity instanceof Boulder) {
            this.totalBoulder += 1;
        }else if(entity instanceof Exits)
        {
            this.totalExit += 1;
        }
    }

    //Picked up form Dungeon
    public void removeEntity(final Entity entity) {
        entities.remove(entity);
    }

    public Object findEntity(int x, int y) {
        Entity ee = null;
        System.out.println(entities.size());
        for (Entity e : entities) {
            if(e != null)
            {
                if (e.getX() == x && e.getY() == y) {
                    if (e instanceof Key) {
                        ee = e;
                        return (Key) ee;
                    }
                    if (e instanceof Sword) {
                        ee = e;
                        return (Sword) ee;
                    }
                    if (e instanceof Portion)  {
                        ee = e;
                        return (Portion) ee;
                    }
                    if (e instanceof Treasure) {
                        ee = e;
                        return (Treasure) ee;
                    }
                    if (e instanceof Boulder)  {
                        ee = e;
                        return (Boulder) ee;
                    }
                    if (e instanceof Door)  {
                        ee = e;
                        return (Door) ee;
                    }
                    if (e instanceof Wall)  {
                        ee = e;
                        return (Wall) ee;
                    }
                    if (e instanceof Portal)  {
                        ee = e;
                        return (Portal) ee;
                    }
                    if (e instanceof Switch)  {
                        ee = e;
                        return (Switch) ee;
                    }
                    if (e instanceof Exits)  {
                        ee = e;
                        return (Exits) ee;
                    }
                    if (e instanceof Player)  {
                        ee = e;
                        return (Player) ee;
                    }
                    
                    if (e instanceof Enemy)  {
                        ee = e;
                        return (Enemy) ee;
                    }
                }
            }
        
        }
        return null;
    }

    public Entity findItemOnMap(int x, int y) {
        Entity en = null;
        int size = getEntityList().size();
        for (int i = 0; i < size; i++) {
            if(getEntityList().get(i) != null)
            {
            
                if (getEntityList().get(i).getX() == x && getEntityList().get(i).getY() == y) {
                    en = getEntityList().get(i);
                }
            }
        }
        return en;
    }
    public Entity findBoulderOnMap(int x, int y) {
        Entity en = null;
        int size = getEntityList().size();
        for (int i = 0; i < size; i++) {
            if(getEntityList().get(i) != null)
            {
                if (getEntityList().get(i).getX() == x && getEntityList().get(i).getY() == y &&getEntityList().get(i) instanceof Boulder ) {
                    en = getEntityList().get(i);
                }
            }
        }
        return en;
    }

    public Entity findDoorOnMap(int x, int y) {
        Entity en = null;
        int size = getEntityList().size();
        for (int i = 0; i < size; i++) {
            if(getEntityList().get(i) != null)
            {
                if (getEntityList().get(i).getX() == x && getEntityList().get(i).getY() == y &&getEntityList().get(i) instanceof Door ) {
                    en = getEntityList().get(i);
                }
            }
        }
        return en;
    }

    public Entity findPortalOnMap(int x, int y) {
        Entity en = null;
        int size = getEntityList().size();
        for (int i = 0; i < size; i++) {
            if(getEntityList().get(i) != null)
            {
                if (getEntityList().get(i).getX() == x && getEntityList().get(i).getY() == y &&getEntityList().get(i) instanceof Portal ) {
                    en = getEntityList().get(i);
                }
            }
        }
        return en;
    }

    
    public Entity findEnemyOnMap(int x, int y) {
        Entity en = null;
        int size = getEntityList().size();
        for (int i = 0; i < size; i++) {
            if(getEntityList().get(i) != null)
            {
                if (getEntityList().get(i).getX() == x && getEntityList().get(i).getY() == y &&getEntityList().get(i) instanceof Enemy ) {
                    en = getEntityList().get(i);
                }
            }
        }
        return en;
    }

    public Player findPlayerOnMap(int x, int y) {
        Player en = null;
        int size = getEntityList().size();
        for (int i = 0; i < size; i++) {
            if(getEntityList().get(i) != null){
                if (getEntityList().get(i).getX() == x && getEntityList().get(i).getY() == y &&getEntityList().get(i) instanceof Player ) {
                    en = (Player)getEntityList().get(i);
                }
            }
        }
        return en;
    }

    public Exits findExitOnMap(int x, int y) {
        Exits en = null;
        int size = getEntityList().size();
        for (int i = 0; i < size; i++) {
            if(getEntityList().get(i) != null){
                if (getEntityList().get(i).getX() == x && getEntityList().get(i).getY() == y &&getEntityList().get(i) instanceof Exits ) {
                    en = (Exits)getEntityList().get(i);
                }
            }
        }
        return en;
    }

    public void check_switches()
    {
        int counter =0;
        for(Entity e : getEntityList())
        {
            if(e instanceof Switch)
            {
                Switch sw = (Switch)e;
                if(sw.checkBoulderOn(this))
                {
                    counter += 1;
                }
            }
        }
        player.switch_opened = counter;

    }

    public Entity findSystem(int x, int y, int command) {
        Entity e = null;
        if (command == 1) {
            e = findBoulderOnMap(x, y);
        } else if (command == 2) {
            e = findEnemyOnMap(x, y);
        } else if (command == 3) {
            findPlayerOnMap(x, y);
        } else if (command == 4) {
            e = findDoorOnMap(x, y);
        } else if (command == 5) {
            e = findPortalOnMap(x, y);
        }else if( command == 6)
        {
            e = findExitOnMap(x,y);
        }
        return e;
    }


    public void addImage(ImageView K, int x, int y) {
        dgc.addImage(K, x, y);
    }
}
