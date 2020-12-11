package unsw.dungeon;

public class Portal extends Entity{
    private int id;
    public Portal(int x, int y,int id) {
        super(x, y);
        this.id = id;
        iscollectable = new Collectable();
        ispushable = new Not_pushable();
    }
    
    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public int get_id()
    {
        return this.id;
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