package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import unsw.dungeon.Entity;
import unsw.dungeon.Wall;

import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    //Images
    private Image playerImage;
    private Image wallImage;
    private Image armourImage;
    private Image BoulderImage;
    private Image SwitchImage;
    private Image TreasureImage;
    private Image SwordImage;
    private Image ExitImage;
    private Image EnemyImage;
    private Image PortionImage;
    private Image PortalImage;
    private Image DoorImage;
    private Image KeyImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image((new File("images/human_new.png")).toURI().toString());
        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        armourImage = new Image((new File("images/Armour.png")).toURI().toString());
        BoulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        SwitchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
        TreasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
        SwordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        ExitImage = new Image((new File("images/exit.png")).toURI().toString());
        DoorImage = new Image((new File("images/closed_door.png")).toURI().toString());
        EnemyImage = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());
        PortionImage = new Image((new File("images/bubbly.png")).toURI().toString());
        PortalImage = new Image((new File("images/portal.png")).toURI().toString());
        KeyImage = new Image((new File("images/key.png")).toURI().toString());
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Armour armour) {
        ImageView view  = new ImageView(armourImage);
        addEntity(armour, view);
    }

    @Override
    public void onLoad(Boulder boulder) {
        ImageView view  = new ImageView(BoulderImage);
        addEntity(boulder, view);
    }

    @Override
    public void onLoad(Switch Switch) {
        ImageView view = new ImageView(SwitchImage);
        addEntity(Switch, view);
    }

    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(TreasureImage);
        addEntity(treasure, view);
    }

    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(SwordImage);
        addEntity(sword, view);
    }

    @Override
    public void onLoad(Exits exits) {
        ImageView view = new ImageView(ExitImage);
        addEntity(exits, view);
    }

    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(EnemyImage);
        addEntity(enemy, view);
    }

    @Override
    public void onLoad(Portion portion) {
        ImageView view = new ImageView(PortionImage);
        addEntity(portion, view);
    }

    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(PortalImage);
        addEntity(portal, view);
    }

    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(KeyImage);
        addEntity(key, view);
    }

    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(DoorImage);
        addEntity(door, view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

}
