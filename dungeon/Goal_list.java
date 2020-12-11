package unsw.dungeon;

import java.util.ArrayList;

public class Goal_list implements Goal{


    private ArrayList<Goal> goalList = new ArrayList<Goal>();
    public  Goal_list subgoals = null;
    private int num_goals_completed =0;
    private int num_goals_added =0;
    private String name = "GoalList";
    private String requirment = "NONE";


    public void print_result()
    {
        System.out.println(this.requirment + "");
        for(Goal g : goalList)
        {
            System.out.println(" "+g.get_name()+" ");
        } 
        if(subgoals != null)
        {
            System.out.println("subgoal is " + subgoals.get_requirement());
            for(Goal g : subgoals.get_goal_list())
            {
                System.out.println(" "+g.get_name()+" ");
            }
        }

    }

    public String get_requirement()
    {
        return this.requirment;
    }
    public ArrayList<Goal> get_goal_list()
    {
        return this.goalList;
    }
    public void set_command(String command)
    {
        this.requirment = command;
    }

    public void add_subGoal(Goal_list sub)
    {
        this.subgoals = sub;
    }
    public int get_goal_added()
    {
        return num_goals_added;
    }

    @Override
    public boolean is_completed(int input) {
        // TODO Auto-generated method stub
        return false;
    }

    public void  add_goals(Goal obj)
    {
      
        this.goalList.add(obj);
        this.num_goals_added += 1;
    }
    
    public void remove_goals(Goal obj)
    {
        this.num_goals_added -= 1;
        this.goalList.remove(obj);
    }

    public  boolean is_succeed(int num_enemies_killed,int num_treassure_collected,int num_switch_triggered,int at_exit){
        this.num_goals_completed =0;
        for(Goal obj : this.goalList)
        {
            if(obj.get_name().equals("exit"))
            {
                if(obj.is_completed(at_exit))
                {

                    this.num_goals_completed += 1;

                }
            }else if(obj.get_name().equals("treasure"))
            {
                if(obj.is_completed(num_treassure_collected))
                {
                    System.out.println("treasure collected");
                    this.num_goals_completed += 1;
                }
            }else if(obj.get_name().equals("enemies"))
            {
                if(obj.is_completed(num_enemies_killed))
                {
                    this.num_goals_completed += 1;
                }
            }else if(obj.get_name().equals("boulders"))
            {
                if(obj.is_completed(num_switch_triggered))
                {
                    this.num_goals_completed += 1;
                }
            }

        }

        if(this.subgoals == null) // this is subgoal or no subgoal , checking subgoal
        {
            if(this.requirment.equals("OR")) //or
            {
                if(this.num_goals_completed > 0)
                {
                    num_goals_completed =0;
                
                    return true;
                }
            }else if(this.requirment.equals("AND"))
            {
                
                if(this.num_goals_added == this.num_goals_completed)
                {
                    System.out.println("1 added" + this.num_goals_added);
                    num_goals_completed =0;
                    
                    return true;
                }
            }else if(this.requirment.equals("NONE"))
            {
                if(this.num_goals_added == this.num_goals_completed)
                {
                    num_goals_completed =0;
                   
                    return true;
                }
    
            }
            return false;

        }else 
        {
            System.out.println("2");
            if(this.requirment.equals("OR")) //or
            {
                if(this.num_goals_completed > 0 || this.subgoals.is_succeed( num_enemies_killed,num_treassure_collected, num_switch_triggered,at_exit))
                {
                    num_goals_completed =0;
                
                    return true;
                }
            }else if(this.requirment.equals("AND"))
            {
                if(this.num_goals_added == this.num_goals_completed && this.subgoals.is_succeed( num_enemies_killed,num_treassure_collected, num_switch_triggered,at_exit))
                {
                    num_goals_completed =0;
                    
                    return true;
                }
            }else if(this.requirment.equals("NONE"))
            {
                if(this.num_goals_added == this.num_goals_completed)
                {
                    num_goals_completed =0;
                
                    return true;
                }

            }
        }


        return false;    

    }

    @Override
    public String get_name() {
        // TODO Auto-generated method stub
        return this.name;
    }

}
