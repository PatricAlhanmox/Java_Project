package unsw.dungeon;

public class Boulder extends Entity {

    public Boulder(int x, int y) {
        super(x, y);
        iscollectable = new Not_collectable();
        ispushable = new Pushable();
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
