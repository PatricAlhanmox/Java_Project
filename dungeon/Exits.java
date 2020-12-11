package unsw.dungeon;

public class Exits extends Entity{
    public Exits(int x, int y) {
        super(x, y);
        //added 19/7
        iscollectable = new Not_collectable();
        ispushable = new Not_pushable();
    }
    
    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public boolean get_collect()
    {
        return iscollectable.iscollect();
    }

    public boolean get_push()
    {
        return ispushable.isPush();
    }
}