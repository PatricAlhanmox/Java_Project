package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;

import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    private Text text_enemy;

    private Text text_treasure;

    private Text text_boulder;

    private Text text_invinText;

    private DungeonView dungeonScreen;

    private Frontend frontend;

    private ViewWin viewwin;

    private ViewLost viewlost;

    private ViewGuide viewGuide;


    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        dungeon.setDungeonControl(this);
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());
        Image treasure = new Image((new File("images/gold_pile.png")).toURI().toString());
        Image enemy = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());
        Image boulder = new Image((new File("images/boulder.png")).toURI().toString());
        Image exit = new Image((new File("images/exit.png")).toURI().toString());
        Image portion = new Image((new File("images/bubbly.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y <= dungeon.getHeight()+1; y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities){
            squares.getChildren().add(entity);
        }

        squares.add(new ImageView(treasure), 0, dungeon.getHeight());
        squares.add(new ImageView(enemy), 3, dungeon.getHeight());
        squares.add(new ImageView(boulder), 0, dungeon.getHeight()+1);
        squares.add(new ImageView(exit), 3, dungeon.getHeight()+1);
        squares.add(new ImageView(portion), 6, dungeon.getHeight());

        SetTreasureText(1, dungeon.getHeight());
        SetEnemyText(4, dungeon.getHeight());
        SetBoulderText(1, dungeon.getHeight()+1);
        SetExitText(4, dungeon.getHeight()+1);
        SetInvinMoveText(7, dungeon.getHeight());
    }



    public void SetTreasureText(int x, int y) {
        Text totalTreasure = new Text("/ " + Integer.toString(dungeon.getTotoalTreasure()));
        squares.getChildren().remove(text_treasure);
        text_treasure = new Text(Integer.toString(dungeon.getPlayer().getTotalTreasure()));
        text_treasure.setFill(Color.RED);
        text_treasure.setFont(new Font(16));
        squares.add(totalTreasure, x+1, y);
        squares.add(text_treasure, x, y);
    }

    public void SetEnemyText(int x, int y) {
        Text totalEnemy = new Text("/ " + Integer.toString(dungeon.getTotoalEnemy()));
        squares.getChildren().remove(text_enemy);
        text_enemy = new Text(Integer.toString(dungeon.getPlayer().get_num_enemykilled()));
        text_enemy.setFill(Color.RED);
        text_enemy.setFont(new Font(16));
        squares.add(totalEnemy, x+1, y);
        squares.add(text_enemy, x, y);
    }


    public void SetBoulderText(int x, int y) {
        Text totalSwitch = new Text("/ " + Integer.toString(dungeon.getTotoalSwitch()));
        squares.getChildren().remove(text_boulder);
        text_boulder = new Text(Integer.toString(dungeon.getPlayer().get_switch_opened()));
        text_boulder.setFill(Color.RED);
        text_boulder.setFont(new Font(16));
        squares.add(totalSwitch, x+1, y);
        squares.add(text_boulder, x, y);
    }

    public void SetInvinMoveText(int x, int y) {
        Text totalInvin = new Text("/ " + Integer.toString(9));
        squares.getChildren().remove(text_invinText);
        text_invinText = new Text(Integer.toString(dungeon.getPlayer().get_invinMove()));
        text_invinText.setFill(Color.RED);
        text_invinText.setFont(new Font(16));
        squares.add(totalInvin, x+1, y);
        squares.add(text_invinText, x, y);
    }

    public void SetExitText(int x, int y) {
        Text totalExit = new Text(Integer.toString(dungeon.totalExit()));
        totalExit.setFill(Color.BLUE);
        totalExit.setFont(new Font(16));
        squares.add(totalExit, x, y);
    }

    @FXML
    public void addImage(ImageView K, int x, int y) {
		this.squares.add(K, x, y);
	}

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            System.out.println("enemy killed is "+ player.get_num_enemykilled()+"/Treasure collected"+player.getTotalTreasure()+"/switch opened "+ player.get_switch_opened()+"/ at exit "+player.get_atExit());
            if(dungeon.get_goal_list().is_succeed(player.get_num_enemykilled(), player.getTotalTreasure(), player.get_switch_opened(), player.get_atExit()))
            {
                setWinScreen(viewwin);
                viewwin.show();
            }
            if (dungeon.getPlayer().get_num_enemykilled() <= dungeon.getTotoalEnemy()) {
                SetEnemyText(4, dungeon.getHeight());
            }
            if (dungeon.getPlayer().get_switch_opened() < dungeon.getTotoalSwitch()){
                SetBoulderText(1, dungeon.getHeight()+1);
            }
            if (dungeon.getPlayer().getPortion() != null) {
                SetInvinMoveText(7, dungeon.getHeight());
            }
            if (!dungeon.getPlayer().get_alive_dead()) {
                setLostScreen(viewlost);
                viewlost.show();
            }
            break;
        case DOWN:
            player.moveDown();
            System.out.println("enemy killed is "+ player.get_num_enemykilled()+"/Treasure collected"+player.getTotalTreasure()+"/switch opened "+ player.get_switch_opened()+"/ at exit "+player.get_atExit());
            if(dungeon.get_goal_list().is_succeed(player.get_num_enemykilled(), player.getTotalTreasure(), player.get_switch_opened(), player.get_atExit()))
            {
                setWinScreen(viewwin);
                viewwin.show();
            }
            if (dungeon.getPlayer().get_num_enemykilled() <= dungeon.getTotoalEnemy()) {
                SetEnemyText(4, dungeon.getHeight());
            }
            if (dungeon.getPlayer().get_switch_opened() < dungeon.getTotoalSwitch()){
                SetBoulderText(1, dungeon.getHeight()+1);
            }
            if (dungeon.getPlayer().getPortion() != null) {
                SetInvinMoveText(7, dungeon.getHeight());
            }
            if (!dungeon.getPlayer().get_alive_dead()) {
                setLostScreen(viewlost);
                viewlost.show();
            }
            break;
        case LEFT:
            player.moveLeft();
            System.out.println("enemy killed is "+ player.get_num_enemykilled()+"/Treasure collected"+player.getTotalTreasure()+"/switch opened "+ player.get_switch_opened()+"/ at exit "+player.get_atExit());
            if(dungeon.get_goal_list().is_succeed(player.get_num_enemykilled(), player.getTotalTreasure(), player.get_switch_opened(), player.get_atExit()))
            {
                setWinScreen(viewwin);
                viewwin.show();
            }
            if (dungeon.getPlayer().get_num_enemykilled() <= dungeon.getTotoalEnemy()) {
                SetEnemyText(4, dungeon.getHeight());
            }
            if (dungeon.getPlayer().get_switch_opened() < dungeon.getTotoalSwitch()){
                SetBoulderText(1, dungeon.getHeight()+1);
            }
            if (dungeon.getPlayer().getPortion() != null) {
                SetInvinMoveText(7, dungeon.getHeight());
            }
            if (!dungeon.getPlayer().get_alive_dead()) {
                setLostScreen(viewlost);
                viewlost.show();
            }
            break;
        case RIGHT:
            player.moveRight();
            System.out.println("enemy killed is "+ player.get_num_enemykilled()+"/Treasure collected"+player.getTotalTreasure()+"/switch opened "+ player.get_switch_opened()+"/ at exit "+player.get_atExit());
            if(dungeon.get_goal_list().is_succeed(player.get_num_enemykilled(), player.getTotalTreasure(), player.get_switch_opened(), player.get_atExit()))
            {
                setWinScreen(viewwin);
                viewwin.show();
            }
            if (dungeon.getPlayer().get_num_enemykilled() <= dungeon.getTotoalEnemy()) {
                SetEnemyText(4, dungeon.getHeight());
            }
            if (dungeon.getPlayer().get_switch_opened() < dungeon.getTotoalSwitch()){
                SetBoulderText(1, dungeon.getHeight()+1);
            }
            if (dungeon.getPlayer().getPortion() != null) {
                SetInvinMoveText(7, dungeon.getHeight());
            }
            if (!dungeon.getPlayer().get_alive_dead()) {
                setLostScreen(viewlost);
                viewlost.show();
            }
            break;
        case SPACE:
            player.PickUp();
            System.out.println("enemy killed is "+ player.get_num_enemykilled()+"/Treasure collected"+player.getTotalTreasure()+"/switch opened "+ player.get_switch_opened()+"/ at exit "+player.get_atExit());
            if(dungeon.get_goal_list().is_succeed(player.get_num_enemykilled(), player.getTotalTreasure(), player.get_switch_opened(), player.get_atExit()))
            {
                setWinScreen(viewwin);
                viewwin.show();
            }
            SetTreasureText(1, dungeon.getHeight());
            if (!dungeon.getPlayer().get_alive_dead()) {
                setLostScreen(viewlost);
                viewlost.show();
            }
            break;
        default:
            break;
        }

    }

    @FXML
    public void setScreen(DungeonView dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

    @FXML
    public void setMenuScreen(Frontend frontend) {
        this.frontend = frontend;
    }

    @FXML
    public void setWinScreen(ViewWin view) {
        this.viewwin = view;
    }

    @FXML
    public void setLostScreen(ViewLost view) {
        this.viewlost = view;
    }

    @FXML
    public void setGuideScreen(ViewGuide view) {
        this.viewGuide = view;
    }
}

