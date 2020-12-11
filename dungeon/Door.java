package unsw.dungeon;

public class Door extends Entity {

    private int doorID;
    private boolean DoorOPCL;

    public Door(int x, int y, int doorID) {
        super(x, y);
        this.doorID = doorID;
        this.DoorOPCL = false;
        iscollectable = new Not_collectable();
        ispushable = new Not_pushable();
    }
    
    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public int getDoorID() {
        return doorID;
    }

    public boolean getDoorOPCL() {
        return DoorOPCL;
    }

    public boolean get_collect()
    {
        return iscollectable.iscollect();
    }

    public boolean get_push()
    {
        return ispushable.isPush();
    }

    public void DoorOpen(Key key) {
        if(this.doorID == key.getID())
        {
            this.DoorOPCL= true;
        }

    }
}
