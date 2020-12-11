package unsw.dungeon;

public class Enemy_goal implements Goal {

    private int enemies_must_kill;
    private String name = "enemies";

    public Enemy_goal(int num)
    {
        this.enemies_must_kill = num;
    }

    @Override
    public boolean is_completed(int enemies_killed) {
        if(enemies_killed == this.enemies_must_kill)
        {
            return true;
        }
        return false;
    }
    
    public String get_name()
    {
        return this.name;
    }
    
    public static void main(String[] args) {
        
        // Dungeon d = new Dungeon(10,10);
        // Player p =new Player(d, 0, 0);
        // Sword s = new Sword(1,0);
        // Enemy e1 = new Enemy(3,0,d);
        
        // d.addEntity(p);
        // d.addEntity(s);
        // d.addEntity(e1);


        // p.moveRight();//1,0
        // System.out.println(e1.getX()); // enemy should move to  2,0
        // System.out.println(e1.getY());
        // System.out.println("first move");
        // p.PickUp();
        // System.out.println(e1.getX()); // enemy should move to  2,0
        // System.out.println(e1.getY());
        // System.out.println("second move");
        // p.moveRight();//2,0
        
        // System.out.println(p.num_enemyKilled); // enemy should move to  2,0
        // // System.out.println(e1.getY());
        


        // // System.out.println(p.get_num_enemykilled());

        Dungeon d = new Dungeon(10,10);
        Player p =new Player(d, 0, 0);
        Boulder b= new Boulder(1,0);
        Switch s = new Switch(2, 0);
        d.addEntity(p);
        d.addEntity(b);
        d.addEntity(s);
        
        p.moveRight(); // p 1,0, b 2.0
        System.out.println("px is "+ p.getX() + " py is "+p.getY()+"\n");
        System.out.println("bx is "+ b.getX() + " by is "+b.getY()+"\n");
        System.out.println("switch open is "+ p.switch_opened);
        p.moveLeft(); // p 2,0, b 3.0
        System.out.println("px is "+ p.getX() + " py is "+p.getY()+"\n");
        System.out.println("bx is "+ b.getX() + " by is "+b.getY()+"\n");
        System.out.println("switch open is "+ p.switch_opened);

        p.moveDown(); // p 2,0, b 3.0
        System.out.println("px is "+ p.getX() + " py is "+p.getY()+"\n");
        System.out.println("bx is "+ b.getX() + " by is "+b.getY()+"\n");
        System.out.println("switch open is "+ p.switch_opened);
    }
}