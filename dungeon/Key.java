package unsw.dungeon;

public class Key extends Entity {

    private int ID;

    public Key(int x, int y, int ID) {
        super(x, y);
        this.ID = ID;
        iscollectable = new Collectable();
        ispushable = new Not_pushable();
    }
    
    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return "Key";
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
