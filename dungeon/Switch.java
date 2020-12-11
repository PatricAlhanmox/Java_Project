package unsw.dungeon;

public class Switch extends Entity {

    private boolean state;

    public Switch(int x, int y) {
        super(x, y);
        this.state = false;
        ispushable = new Not_pushable();
        iscollectable = new Not_collectable();
        // TODO Auto-generated constructor stub
    }

    public void trigger_on()
    {
        this.state = true;
    }

    public void trigger_off()
    {
        this.state = false;
    }
    
    public boolean get_state()
    {
        return this.state;
    }
    public boolean get_collect()
    {
        return iscollectable.iscollect();
    }

    public boolean get_push()
    {
        return ispushable.isPush();
    }

    public boolean checkBoulderOn(Dungeon d) {
        for (Entity e: d.getEntityList()) {
            if (e.getX() == getX() && e.getY() == getY() && e instanceof Boulder) {
                trigger_on();
                return true;
            }
        }
        trigger_off();
        return false;
    }
    
}