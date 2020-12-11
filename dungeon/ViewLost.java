package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewLost {
    private Stage stage;
    private String title;
    private Scene scene;
	private ViewLostControll viewcontroller;
	private Frontend front;
	
	public ViewLost(Stage stage, String title) throws IOException{
		this.stage = stage;
		this.title = title;
		this.viewcontroller = new ViewLostControll();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewLost.fxml"));
        loader.setController(viewcontroller);
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
	

	public String getTitle() {
		return title;
	}
	
	public ViewLostControll getController() {
		return viewcontroller;
	}

}