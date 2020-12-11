package unsw.dungeon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsInstanceOf;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Key key;
    private Sword sword;
    private Treasure treasure;
    private Portion portion;
    
    private int invin_move;
    private boolean canKill;
    private int at_exit =0;
    private ArrayList<Enemy> Enemy_list = new ArrayList<Enemy>();

    //added 19/7
    private  boolean alive_dead;

    //added 20/7
    public int num_enemyKilled ;
    public int switch_opened ;
    public int treasureHave;

    public Enemy encounter_enemy = null;

    //added 31/7
    public Armour have_armour = null;
    


    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.alive_dead = true; // added 19/7
        this.dungeon = dungeon;
        this.key = null;
        this.sword = null;
        this.treasure = null;
        this.portion = null;
        this.canKill = false;
        this.treasureHave = 0;
        this.invin_move = 0;
        this.num_enemyKilled = 0 ;
        this.switch_opened = 0;
    }

    public int  get_num_enemykilled()
    {
        return this.num_enemyKilled;
    }
    public int  get_switch_opened()
    {
        return this.switch_opened;
    }
    

    //added 18/7  for observer
    public int get_invinMove()
    {
        return this.invin_move;
    }
    //added
    public void add_enemy(Enemy e)
    {
        this.Enemy_list.add(e);
        // this.notifyEnemies();
    }
    //added
    public void remove_enemy(Enemy e)
    {
        this.Enemy_list.remove(e);
    }
    //added
    public void notifyEnemies() {
        for(Enemy e : Enemy_list) {
            e.update(this);
        }
    }

    //
    public void teleport(Portal p)
    {
        List<Entity> list_e= dungeon.getEntityList();

        for(Entity e: list_e)
        {
            if(e instanceof Portal && e.getX() != p.getX() && e.getY() != p.getY())
            {
                Portal pr = (Portal) e;
                if (pr.get_id() == p.get_id()){
                    x().set(e.getX());
                    y().set(e.getY());
                }
            }
        }
        notifyEnemies();
    }

    //added 19/7

    public void encounter_boulder(int command)
    {
        if(boulderCanMove(command))
        {
            Push(command);
        }
    }
    //added 19/7
    public boolean get_canKill()
    {
        return this.canKill;
    }

    //added 19/7
    public boolean encounter_door(Door d)
    {
        try{
            System.out.println("door state is " +d.getDoorOPCL()+ "/door id is " +  d.getDoorID()+ " key id is " + getKey().getID() );
        }catch(Exception e){
            // System.out.println("door state is " +d.getDoorOPCL()+ "/door id is " +  d.getDoorID()+ " no key " );
            System.out.println("door state is " +d.getDoorOPCL()+ "/door id is " +  " no key " );
        }
        if (d.getDoorOPCL() == true) {
            System.out.println("door is opened");
            return true;
        } else {
            if (getKey() != null) {
                if (getKey().getID() == d.getDoorID()){
                    d.DoorOpen(getKey());
                    System.out.println("open door with key");
                    setKey(null);
                    int origin_Y = d.getY();
                    d.y().set(dungeon.getHeight()+10);
                    Image OpenImage = new Image((new File("images/open_door.png")).toURI().toString());
                    ImageView Open = new ImageView(OpenImage);
                    dungeon.addImage(Open, d.getX(), origin_Y);
                    return true;
                }  
                
            }
            else  {
                System.out.println("Find Key first");
            }
        }
        return false;
    }
    //added 19/7
    public void encounter_portal (Portal p)
    {
        teleport(p);
    }

    //added 19/7
    public void encounter_enemy(Enemy e)
    {
        System.out.println("can kill is " + canKill);
        if(canKill == true)
        {
            kill_With_invinmove_or_sword(e);
            int origin_Y = e.getY();
            e.y().set(dungeon.getHeight()+10);
            Image OpenImage = new Image((new File("images/enemy_dead.png")).toURI().toString());
            ImageView Open = new ImageView(OpenImage);
            dungeon.addImage(Open, e.getX(), origin_Y);
            this.num_enemyKilled += 1;

        }else{
            if(this.have_armour != null)
            {
                this.have_armour.use_armour();
                e.freeze = 4; // freeze enemy for 2 rounds
                if(!this.have_armour.UsableArmour())
                {
                    this.have_armour = null;
                }
                
            }else{


                System.out.println("player dead!!!!!!");
                this.alive_dead = false;
                int origin_Y = getY();
                y().set(dungeon.getHeight()+10);
                Image OpenImage = new Image((new File("images/player_dead.png")).toURI().toString());
                ImageView Open = new ImageView(OpenImage);
                dungeon.addImage(Open, getX(), origin_Y);
        
        
            }
        }
        
    }

    //added 19/7
    public void check_exit(Exits e)
    {

        if(e != null )
        {
            this.at_exit = 1;
        }else{
            this.at_exit = 0;
        }
    }

    //added 19/7
    public int get_atExit()
    {
        return this.at_exit;
    }

    //added 19/7
    //added 19/7
    public void consume_portion(Portion p)
    {
        this.invin_move = p.get_invicibleMove();
        this.canKill = true;
        dungeon.removeEntity((Entity) p);
    }
    //added 19/7
    public void kill_With_invinmove_or_sword(Enemy e) // try use invinmove to kill enemy, true is user invinmove to kill
    {
        if(get_invinMove()>0) // try invin first
        {
            
            if(get_invinMove() == 0)
            {
                if(getSword() == null) // if invinmove runs out and there is not sword, player cant kill
                {
                    this.canKill = false;
                }
            }
        
        }else{
            if(getSword() != null)
            {
                if(getSword().use_sword() == false) // kill enemy and sword has no more hit
                {
                    this.canKill = false;
                    // dungeon.removeEntity((Entity)getSword());
                    this.sword = null;
                    
                }
            }else
            {
                this.alive_dead = false;
            }
            
        }
        Enemy_list.remove(e); // remove killed enemy from enemy list
        dungeon.removeEntity((Entity)e); // remove killed enemy from dungeon

    }
    //added 19/7
    public boolean get_alive_dead()
    {
        return this.alive_dead;
    }
  


    public boolean boulderCanMove(int z) {
        if (z == 1) {
            if (getY() - 2 <= dungeon.getHeight()) {
                if(dungeon.findBoulderOnMap(getX(), getY()-2) != null)
                {
                    return false;
                }

                if(dungeon.findEntity(getX(), getY()-2) instanceof Switch || dungeon.findEntity(getX(), getY()-2) == null) {
                    if (dungeon.findEntity(getX(), getY()-2) instanceof Switch) {
                        switch_opened += 1;
                    } 
                    return true;
                } else if (dungeon.findEntity(getX(), getY()-2) instanceof Door) {
                    Door d = (Door)dungeon.findItemOnMap(getX(), getY()-2);
                    if (d.getDoorOPCL() == true) {
                        return true;
                    } 
                    else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else if (z == 2) {
            if (getY() + 2 <= dungeon.getHeight()) {
                if(dungeon.findBoulderOnMap(getX(), getY()+2) != null)
                {
                    return false;
                }

                if(dungeon.findEntity(getX(), getY()+2) instanceof Switch || dungeon.findEntity(getX(), getY()+2) == null) {
                    if(dungeon.findEntity(getX(), getY()+2) instanceof Switch) {
                        switch_opened += 1;
                    }
                    return true;
                } else if (dungeon.findEntity(getX(), getY()+2) instanceof Door) {
                    Door d = (Door)dungeon.findItemOnMap(getX(), getY()+2);
                    if (d.getDoorOPCL() == true) {
                        return true;
                    } else {
                        return false;
                    }
                }  else {
                    return false;
                }
            }
        } else if (z == 3) {
            if(dungeon.findBoulderOnMap(getX()-2, getY()) != null)
            {
                return false;
            }
            if (getX() - 2 <= dungeon.getWidth()) {
                if(dungeon.findEntity(getX()-2, getY()) instanceof Switch || dungeon.findEntity(getX()-2, getY()) == null) {
                    if(dungeon.findEntity(getX()-2, getY()) instanceof Switch) {
                        switch_opened += 1;
                    }
                    return true;
                } else if (dungeon.findEntity(getX()-2, getY()) instanceof Door) {
                    Door d = (Door)dungeon.findItemOnMap(getX()-2, getY());
                    if (d.getDoorOPCL() == true) {
                        return true;
                    } else {
                        return false;
                    }
                }  else {
                    return false;
                }
            }
        } else if (z == 4) {
            if(dungeon.findBoulderOnMap(getX()+2, getY()) != null)
            {
                return false;
            }
            if (getX() + 2 <= dungeon.getWidth()) {
                if(dungeon.findEntity(getX()+2, getY()) instanceof Switch || dungeon.findEntity(getX()+2, getY()) == null) {
                    if(dungeon.findEntity(getX()+2, getY()) instanceof Switch) {
                        switch_opened += 1;
                    }
                    return true;
                } else if (dungeon.findEntity(getX()+2, getY()) instanceof Door) {
                    Door d = (Door)dungeon.findItemOnMap(getX()+2, getY());
                    if (d.getDoorOPCL() == true) {
                        return true;
                    } else {
                        return false;
                    }
                }  else {
                    return false;
                }
            }
        }
        return false;

    }


    //Add 7.20
    //all changed up down left right 19/7
    public void moveUp() {
        if (getY() > 0 && !(dungeon.findEntity(getX(), getY()-1)  instanceof Wall)) 
        {
           
            if (dungeon.findSystem(getX(), getY()-1, 1) instanceof Boulder && getY()-1 >= 0) {
                encounter_boulder(1);
               
            } else if(dungeon.findSystem(getX(), getY()-1, 2) instanceof Enemy)
            {
                System.out.println("player encounter enemy");
                Enemy e = (Enemy) dungeon.findSystem(getX(), getY()-1, 2);
                encounter_enemy(e);
                y().set(getY() - 1);
            
            
            }else if (dungeon.findSystem(getX(), getY()-1, 4) instanceof Door) {
                Door d = (Door) dungeon.findSystem(getX(), getY()-1, 4);
                if(encounter_door(d))
                {
                    y().set(getY() - 1);
                }

            } else if(dungeon.findSystem(getX(), getY()-1, 5) instanceof Portal)
            {
                
                Portal p = (Portal)dungeon.findSystem(getX(), getY()-1, 5);
                encounter_portal(p);

        
            } else {
                y().set(getY() - 1);
            }
            check_exit((Exits) dungeon.findSystem(getX(), getY(), 6)); // checking if player at exit   
        }
        if (this.invin_move > 0) {
            this.invin_move -= 1;
        }
        dungeon.check_switches();
        notifyEnemies();

        if(encounter_enemy != null)
        {

            encounter_enemy(encounter_enemy);
            encounter_enemy = null;
        }
    }

    //Add 7.20
    public void moveDown() {
        if (getY() < dungeon.getHeight()-1 && !(dungeon.findEntity(getX(), getY()+1)  instanceof Wall))
        {
           
            if (dungeon.findSystem(getX(), getY()+1, 1) instanceof Boulder && getY()+1 < dungeon.getHeight()-1) {
                encounter_boulder(2);
               
            } else if(dungeon.findSystem(getX(), getY()+1, 2) instanceof Enemy)
            {
                System.out.println("player encounter enemy");
                Enemy e = (Enemy) dungeon.findSystem(getX(), getY()+1, 2);
                encounter_enemy(e);
                y().set(getY()+1);
            
            
            }else if (dungeon.findSystem(getX(), getY()+1, 4) instanceof Door) {
                Door d = (Door) dungeon.findSystem(getX(), getY()+1, 4);
                if(encounter_door(d))
                {
                    System.out.println("encounter door adn i move ");
                    y().set(getY()+1);
                }

            } else if(dungeon.findSystem(getX(), getY()+1, 5) instanceof Portal)
            {
                
                Portal p = (Portal)dungeon.findSystem(getX(), getY()+1, 5);
                encounter_portal(p);

        
            } else {
                y().set(getY()+1);
            }
            check_exit((Exits) dungeon.findSystem(getX(), getY(), 6)); // checking if player at exit   
        }
        if (this.invin_move > 0) {
            this.invin_move -= 1;
        }
        dungeon.check_switches();
        notifyEnemies();

        if(encounter_enemy != null)
        {

            encounter_enemy(encounter_enemy);
            encounter_enemy = null;
        }
    }

    //Add 7.20
    public void moveLeft(){
        if (getX() > 0 && !(dungeon.findEntity(getX()-1,getY()) instanceof Wall))
        {
           
            if (dungeon.findSystem(getX()-1,getY(), 1) instanceof Boulder &&  getX()-1 >= 0) {
                encounter_boulder(3);
               
            } else if(dungeon.findSystem(getX()-1,getY(), 2) instanceof Enemy)
            {
                System.out.println("player encounter enemy");
                Enemy e = (Enemy) dungeon.findSystem(getX()-1,getY(), 2);
                encounter_enemy(e);
                x().set(getX() -1);
            
            
            }else if (dungeon.findSystem(getX()-1,getY(), 4) instanceof Door) {
                Door d = (Door) dungeon.findSystem(getX()-1,getY(), 4);
                if(encounter_door(d))
                {
                    x().set(getX() -1);
                }

            } else if(dungeon.findSystem(getX()-1,getY(), 5) instanceof Portal)
            {
                
                Portal p = (Portal)dungeon.findSystem(getX()-1,getY(), 5);
                encounter_portal(p);

        
            } else {
                x().set(getX() -1);
            }
            check_exit((Exits) dungeon.findSystem(getX(),getY(), 6)); // checking if player at exit   
        }
        if (this.invin_move > 0) {
            this.invin_move -= 1;
        }
        dungeon.check_switches();
        notifyEnemies();

        if(encounter_enemy != null)
        {
            encounter_enemy(encounter_enemy);
            encounter_enemy = null;
        }

    }

    //Add 7.20
    public void moveRight()
    {
        if (getX() < dungeon.getWidth()-1 && !(dungeon.findEntity(getX()+1,getY()) instanceof Wall))
        {
           
            if (dungeon.findSystem(getX()+1,getY(), 1) instanceof Boulder &&  getX()+1 < dungeon.getWidth()-1) {
                encounter_boulder(4);
               
            } else if(dungeon.findSystem(getX()+1,getY(), 2) instanceof Enemy)
            {
                System.out.println("player encounter enemy");
                Enemy e = (Enemy) dungeon.findSystem(getX()+1,getY(), 2);
                encounter_enemy(e);
                x().set(getX()+1);
            
            
            }else if (dungeon.findSystem(getX()+1,getY(), 4) instanceof Door) {
                Door d = (Door) dungeon.findSystem(getX()+1,getY(), 4);
                if(encounter_door(d))
                {
                    x().set(getX()+1);
                }

            } else if(dungeon.findSystem(getX()+1,getY(), 5) instanceof Portal)
            {
                Portal p = (Portal)dungeon.findSystem(getX()+1,getY(), 5);
                encounter_portal(p);
            } else {
                x().set(getX()+1);
            }
            check_exit((Exits) dungeon.findSystem(getX(),getY(), 6)); // checking if player at exit   
        }
        if (this.invin_move > 0) {
            this.invin_move -= 1;
        }
        dungeon.check_switches();
        notifyEnemies();

        if(encounter_enemy != null)
        {

            encounter_enemy(encounter_enemy);
            encounter_enemy = null;
        }


    }


    //Check if there is a key on player's bag
    public void setKey(Entity E) {
        this.key = (Key) E;
    }
    public Key getKey() {
        return key;
    }
    public int getIntKey(Key key) {
        return key.getID();
    }

    //added 19/7
    public void setSword(Entity E) {
        this.sword = (Sword) E;
        this.canKill = true;
    }
    public Sword getSword() {
        return this.sword;
    }


    public Portion getPortion() {
        return this.portion;
    }

    public void setPortion(Entity E) {
        this.portion = (Portion) E;
    }


    public int getTotalTreasure() { 
        return this.treasureHave;
    }
    public void addTreasure(Entity E) {
        this.treasure = (Treasure) E;
        treasureHave += treasure.getAmount();
    }


    public void PickUp () {
        Entity E = dungeon.findItemOnMap(getX(), getY());
        if (dungeon.findEntity(getX(), getY()) != null ){
            if (E instanceof Key && (E.getX()==getX()&&E.getY()==getY())) {
                    if (getKey() == null) {
                        setKey((Key) E);
                        dungeon.removeEntity(E);
                        E.y().set(100);
                    }
            } else if (E instanceof Sword) {
                
                    setSword((Sword) E);
                    dungeon.removeEntity(E);
                    E.y().set(100);
                    //added 19/7
                    this.canKill = true;
                
            } else if (E instanceof Treasure) { 
                addTreasure((Treasure) E);
                dungeon.removeEntity(E);
                E.y().set(100);
            } else if(E instanceof Portion) // added 19/7
            {
                setPortion((Portion)E);
                consume_portion((Portion)E);
                E.y().set(100);
            }else if(E instanceof Armour) // added 31/7
            {
                this.have_armour = (Armour)E;
                E.y().set(100);
            }
        }
       
    }
    


    // 1 stand for going up, 2 stand for going down, 3stand for going left, 4 stand for going right
    public void Push(int z) {
        Entity en = null;

        if (z == 1) {
            en = dungeon.findBoulderOnMap(getX(), getY()-1);
            y().set(getY()-1);
            en.y().set(en.getY()-1);
        } else if (z == 2) {
            en = dungeon.findBoulderOnMap(getX(), getY()+1);
            y().set(getY()+1);
            en.y().set(en.getY()+1);
        } else if (z == 3) {
            en = dungeon.findBoulderOnMap(getX()-1, getY());
            x().set(getX()-1);
            en.x().set(en.getX()-1);
        } else if (z == 4) {
            System.out.println("right");
            en =dungeon.findBoulderOnMap(getX()+1, getY());
            x().set(getX()+1);
            en.x().set( en.getX() +1);
        }
        
    }
    
}
