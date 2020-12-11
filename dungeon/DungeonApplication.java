package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        Frontend Menu = new Frontend(primaryStage,"Menu");
        ViewWin viewW = new ViewWin(primaryStage,"Win");
        ViewLost viewL = new ViewLost(primaryStage, "Lost");
        ViewGuide viewG = new ViewGuide(primaryStage, "Guide");
    	Menu.getController().setScreen(Menu);
        Menu.getController().setViewWin(viewW);
        Menu.getController().setViewLost(viewL);
        Menu.getController().setViewGuide(viewG);
    	Menu.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
