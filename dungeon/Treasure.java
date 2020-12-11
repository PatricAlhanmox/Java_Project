package unsw.dungeon;

public class Treasure extends Entity{

    private int amount;
    private String name;

    public Treasure(int x, int y) {
        super(x, y);
        this.amount = 1;
        this.name = "Treasure";
        iscollectable = new Collectable();
        ispushable = new Not_pushable();
    }
    
    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public int getAmount() {
        return this.amount;
    }

    public String getName() {
        return this.name;
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