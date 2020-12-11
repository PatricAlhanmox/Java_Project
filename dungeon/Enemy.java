package unsw.dungeon;

import java.util.List;
import java.util.ListIterator;

public class Enemy extends Entity {

    //31/7
    public int freeze=0;

    public int player_x;
    public int player_y;
    public int invin_move;
    private Dungeon dungeon;
    private EnemyMotion em = new EnemyMotion();

    public Enemy(int x, int y, Dungeon dungeon) {
        super(x, y);
        this.dungeon = dungeon;
    }
    
    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public int getCircle() {
        return dungeon.getWidth();
    }

    public int get_invinMove() {
        return invin_move;
    }
    //added 18/7
    public void update (Player p)
    {
        this.player_x = p.getX();
        this.player_y = p.getY();
        this.invin_move = p.get_invinMove();
        em.moveSystem(this);
    }
    //added
    public int get_playerY()
    {
        return this.player_y;
    }
    //added
    public int get_playerX()
    {
        return this.player_x;

    }

    public boolean enemyMovable(int x, int y) {

        Entity en = dungeon.findItemOnMap(x, y);
        if (en instanceof Wall || en instanceof Boulder) {
            return false;
        } else if (this.player_x == x && this.player_y == y){

            // System.out.println("encounter player");
            Player p = dungeon.findPlayerOnMap(this.player_x,this.player_y);
            // p.encounter_enemy(this);
            p.encounter_enemy = this;
            return true;
        }else if(en instanceof Door)
        {
            Door d = (Door)en;
            if(d.getDoorOPCL()==false)
            {
                return false;
            }
        }

       return true;
    }


    public static void main(String[] args) {
        Dungeon d = new Dungeon(10,10);
        Player p =new Player(d, 0, 0); // x= 0 ,y=0
        Armour ar = new Armour(1, 0);
        
        Enemy e1 = new Enemy(4,0,d); // x= 2 , y 0

        d.addEntity(p);
        d.addEntity(ar);
        d.addEntity(e1);

        //p.PickUp();
        System.out.print("first move to right");
        p.moveRight();
        p.PickUp();
        // System.out.println("enemy locat at " +e1.getX() +" " + e1.getY());
        // System.out.println("player locat at " +p.getX() +" " + p.getY());
        // p.moveRight();
        System.out.println("enemy locat at " +e1.getX() +" " + e1.getY());
        System.out.println("player locat at " +p.getX() +" " + p.getY());
        p.moveRight();
        System.out.println("enemy locat at " +e1.getX() +" " + e1.getY());
        System.out.println("player locat at " +p.getX() +" " + p.getY());
        p.moveRight();
        System.out.println("enemy locat at " +e1.getX() +" " + e1.getY());
        System.out.println("player locat at " +p.getX() +" " + p.getY());
        p.moveRight();
        System.out.println("enemy locat at " +e1.getX() +" " + e1.getY());
        System.out.println("player locat at " +p.getX() +" " + p.getY());
        p.moveRight();
        System.out.println("enemy locat at " +e1.getX() +" " + e1.getY());
        System.out.println("player locat at " +p.getX() +" " + p.getY());
        // System.out.print("player should not be dead");
        // System.out.println(p.get_alive_dead());
        // System.out.println(p.get_num_enemykilled());
        // p.moveRight();
        // System.out.println("enemy locat at " +e1.getX() +" " + e1.getY());
        // System.out.println("player locat at " +p.getX() +" " + p.getY());
        
   

    }
}
