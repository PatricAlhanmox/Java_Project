package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonView {
    private Stage stage;
    private String title;
    private Scene scene;
	private String map;
	private DungeonController controller;
	private Frontend front;
	
	public DungeonView(Stage stage, String title, String map) throws IOException{
		this.stage = stage;
		this.title = title;
		this.map = map;
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(map);
		controller = dungeonLoader.loadController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
	}
	

	public void show() {
		stage.setTitle(this.title);
		stage.setScene(scene);
		stage.show();
    }
    
    public Stage getStage() {
		return stage;
	}
	
	public String getPath() {
		return map;
	}
	
	public String getTitle() {
		return title;
	}
	
	public DungeonController getController() {
		return controller;
	}
	
}
