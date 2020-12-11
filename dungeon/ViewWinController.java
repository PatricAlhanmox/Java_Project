package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * A JavaFX controller for the level screen.
 * @author Harvey
 *
 */
public class ViewWinController {

	@FXML
	private Button BACKTOMENUE;


	@FXML
	public void JumpBack(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
		Frontend front = new Frontend(primaryStage, "Menue");
		ViewLost viewL = new ViewLost(primaryStage,"Lost");
		ViewWin viewW = new ViewWin(primaryStage,"Win");
		front.getController().setScreen(front);
		front.getController().setViewLost(viewL);
		front.getController().setViewWin(viewW);
		front.show();
	}

}
