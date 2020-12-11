package unsw.dungeon;

public class Armour extends Entity {
    private int shell;
    private String name;

    public Armour(int x, int y) {
        super(x, y);
        // TODO Auto-generated constructor stub
    
        this.shell =3;
        this.name = "Armour";
        iscollectable = new Collectable();
        ispushable = new Not_pushable();
        
    }

    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public int get_shell()
    {
        return this.shell;
    }
    public void use_armour(){
        this.shell -= 1;
    }


    public boolean UsableArmour(){

        if(this.shell > 0)
        {
            return true;
        }
        return false;

    }

}
