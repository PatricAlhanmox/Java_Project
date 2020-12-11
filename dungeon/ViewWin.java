package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewWin {
    private Stage stage;
    private String title;
    private Scene scene;
	private ViewWinController viewcontroller;
	private Frontend front;
	
	public ViewWin(Stage stage, String title) throws IOException{
		this.stage = stage;
		this.title = title;
		this.viewcontroller = new ViewWinController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewWin.fxml"));
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
	
	public ViewWinController getController() {
		return viewcontroller;
	}

}