package unsw.dungeon;

public class Switch_goal implements Goal{
    protected int switch_must_triggered;
    private String name = "boulders";

    public Switch_goal(int switch_must_triggered)
    {
        this.switch_must_triggered = switch_must_triggered;
    }

	@Override
	public boolean is_completed(int switch_triggered) {

        if(switch_triggered == this.switch_must_triggered)
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