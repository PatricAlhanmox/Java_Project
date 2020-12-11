package unsw.dungeon;

public class Portion extends Entity {
    private int invincible_move;

    public Portion(int x, int y) {
        super(x, y);
        this.invincible_move =  10;
        iscollectable = new Collectable();
        ispushable = new Not_pushable();
    }
    
    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public int get_invicibleMove()
    {
        return this.invincible_move;
    }
}
