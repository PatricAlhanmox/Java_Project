package unsw.dungeon;

public class Treasure_goal implements Goal{

    private int treasure_must_collect;
    private String name = "treasure";

    public Treasure_goal(int num)
    {
        this.treasure_must_collect=num;
    }
    @Override
    public boolean is_completed(int input) {
        if(input == this.treasure_must_collect)
        {
            return true;
        }
        return false;
    }
    
    public String get_name()
    {
        return this.name;
    }
}