package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * A JavaFX controller for the level screen.
 * @author Harvey
 *
 */
public class ViewGuideController {

    private Frontend frontend;

    @FXML
    private Button BACKTOMENUE;
    
    @FXML
    private ImageView im;


	@FXML
	public void JumpBack(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
		Frontend front = new Frontend(primaryStage, "Menue");
		ViewLost viewL = new ViewLost(primaryStage,"Lost");
        ViewWin viewW = new ViewWin(primaryStage,"Win");
        ViewGuide viewGuide = new ViewGuide(primaryStage, "Guide");
		front.getController().setScreen(front);
		front.getController().setViewLost(viewL);
        front.getController().setViewWin(viewW);
        front.getController().setViewGuide(viewGuide);
		front.show();
    }

    public void setMenuScreen(Frontend frontend) {
        this.frontend = frontend;
    }

}
