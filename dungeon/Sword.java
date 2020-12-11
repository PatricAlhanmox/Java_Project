package unsw.dungeon;

public class Sword extends Entity {

    private int hit;
    private String name;

    public Sword(int x, int y) {
        super(x, y);
        this.hit = 5;
        this.name = "Sword";
        iscollectable = new Collectable();
        ispushable = new Not_pushable();
    }
    
    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public int getHit() {
        return hit;
    }


    public boolean UsableSword() {
        if (this.hit > 0) {
            return true;
        } else {
            return false;
        }
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

    public boolean use_sword()
    {
        this.hit -= 1;
        if(this.hit == 0)
        {
            return false;
        }
        return true;
    }
}
