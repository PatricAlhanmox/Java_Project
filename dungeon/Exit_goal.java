package unsw.dungeon;

public class Exit_goal implements Goal{

    private String name = "exit";

    @Override
    public boolean is_completed(int exit) { // 1 = exit , 0 = not at exit
        // TODO Auto-generated method stub
        if(exit==1)
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