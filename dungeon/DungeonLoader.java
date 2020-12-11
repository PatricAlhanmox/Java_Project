package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        JSONObject jsonObject = json.getJSONObject("goal-condition");
        Goal_list gL = new Goal_list();
        loadGoal(dungeon, jsonObject, gL);
        dungeon.set_goal_list(gL);
        gL.print_result();
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id;

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "armour":
            Armour armour = new Armour(x,y);
            onLoad(armour);
            entity = armour;
            break;
        case "boulder":
            Boulder boulder = new Boulder(x,y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "door":
            id = json.getInt("id");
            Door door = new Door(x,y,id);
            onLoad(door);
            entity = door;
            break;
        case "exit":
            Exits exits = new Exits(x,y);
            onLoad(exits);
            entity = exits;
            break;  
        case "enemy":
            Enemy enemy = new Enemy(x,y,dungeon);
            onLoad(enemy);
            entity = enemy;
            break;
        case "key":
            id = json.getInt("id");
            Key key = new Key(x,y,id);
            onLoad(key);
            entity = key;
            break;
        case "invincibility":
            Portion portion = new Portion(x,y);
            onLoad(portion);
            entity = portion;
            break;
        case "portal":
            id = json.getInt("id");
            Portal portal = new Portal(x,y,id);
            onLoad(portal);
            entity = portal;
            break;
        case "switch":
            Switch Switch = new Switch(x, y);
            onLoad(Switch);
            entity = Switch;
            break;
        case "sword":
            Sword sword = new Sword(x, y);
            onLoad(sword);
            entity = sword;
            break;
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            onLoad(treasure);
            entity = treasure;
            break;
        }
        dungeon.addEntity(entity);
    }

    public void loadGoal(Dungeon dungeon, JSONObject json, Goal_list gl) {
        Exit_goal GoalExits = new Exit_goal();
        Enemy_goal GoalEnemy = new Enemy_goal(dungeon.getTotoalEnemy());
        Switch_goal GoalSwitch = new Switch_goal(dungeon.getTotoalSwitch());
        Treasure_goal GoalTreasure = new Treasure_goal(dungeon.getTotoalTreasure());
        String command = json.getString("goal");
        switch(command) {
        case "exit":
            gl.add_goals(GoalExits);
            break;
        case "enemies":
            gl.add_goals(GoalEnemy);
            break;
        case "boulders":
            gl.add_goals(GoalSwitch);
            break;
        case "treasure":
            gl.add_goals(GoalTreasure);
            break;
        case "OR":
            if(gl.get_goal_added() == 0)
            {
                gl.set_command("OR");
                JSONArray subGoals = json.getJSONArray("subgoals");
                int size = subGoals.length();
                for (int i = 0; i < size; i++) {
                    loadGoal(dungeon, subGoals.getJSONObject(i), gl);
                }
            }else
            {

                Goal_list OR = new Goal_list();
                OR.set_command("OR");
                JSONArray subGoals = json.getJSONArray("subgoals");
                int size = subGoals.length();
                for (int i = 0; i < size; i++) {
                    loadGoal(dungeon, subGoals.getJSONObject(i), OR);
                }
                gl.add_subGoal(OR);
            }
            break;
        case "AND":
        if(gl.get_goal_added() == 0)
        {
            gl.set_command("AND");
            JSONArray subGoals = json.getJSONArray("subgoals");
            int size = subGoals.length();
            for (int i = 0; i < size; i++) {
                loadGoal(dungeon, subGoals.getJSONObject(i), gl);
            }
        }else
        {
            Goal_list AND = new Goal_list();
            AND.set_command("AND");
            JSONArray subGoals = json.getJSONArray("subgoals");
            int size = subGoals.length();
            for (int i = 0; i < size; i++) {
                loadGoal(dungeon, subGoals.getJSONObject(i), AND);
            }
            gl.add_subGoal(AND);
        }
            break;
        }
        
    }



    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Armour armour);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(Switch Switch);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(Exits exits);

    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(Portion portion);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Door door);

}
