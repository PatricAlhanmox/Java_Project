package unsw.dungeon;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
        iscollectable = new Collectable();
        ispushable = new Not_pushable();
    }

    public String getName() {
        return "Wall";
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
