package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * A JavaFX controller for the level screen.
 * @author Harvey
 *
 */
public class FrontendController {

	@FXML
	private Button MAZESECTION;

	@FXML
	private Button ADVANCEDSECTION;
	
	@FXML
	private Button BOULDERSECTION;
	
	@FXML
	private Button PORTALSECTION;

	@FXML
	private Button DOORSECTION;
	
	@FXML
	private Button SUPERADVANCESECTION;

	@FXML
	private Button NoobGuide;

	private Frontend frontend;
	
	private ViewWin win;
	
	private ViewLost lost;
	
	private ViewGuide Guide;
	
	@FXML
	public void MapExits(ActionEvent event) throws IOException {
		String map = "maze.json";
    	DungeonView dungeonScreen = new DungeonView(frontend.getStage(), "Find Exit", map);
    	dungeonScreen.getController().setScreen(dungeonScreen);
    	ViewWin vw = new ViewWin(win.getStage(), "Win");
    	dungeonScreen.getController().setWinScreen(vw);
		dungeonScreen.getController().setMenuScreen(frontend);
    	dungeonScreen.show();
	}
	
	
	@FXML
	public void PlayBoulders(ActionEvent event) throws IOException {
		String map = "boulders.json";
    	DungeonView dungeonScreen = new DungeonView(frontend.getStage(), "Push Boulders", map);
    	dungeonScreen.getController().setScreen(dungeonScreen);
		ViewWin vw = new ViewWin(win.getStage(), "Win");
    	dungeonScreen.getController().setWinScreen(vw);
    	dungeonScreen.getController().setMenuScreen(frontend);
    	dungeonScreen.show();
	}
	
	@FXML
	public void PlayKillEnemy(ActionEvent event) throws IOException {
		String map = "advanced.json";
    	DungeonView dungeonScreen = new DungeonView(frontend.getStage(), "Advanced Map", map);
    	dungeonScreen.getController().setScreen(dungeonScreen);
		ViewWin vw = new ViewWin(win.getStage(), "Win");
		ViewLost vl = new ViewLost(lost.getStage(), "Win");
		dungeonScreen.getController().setWinScreen(vw);
		dungeonScreen.getController().setLostScreen(vl);
    	dungeonScreen.getController().setMenuScreen(frontend);
    	dungeonScreen.show();
	}

	@FXML
	public void OpenDoors(ActionEvent event) throws IOException {
		String map = "Door.json";
    	DungeonView dungeonScreen = new DungeonView(frontend.getStage(), "Open all the Door", map);
    	dungeonScreen.getController().setScreen(dungeonScreen);
		ViewWin vw = new ViewWin(win.getStage(), "Win");
    	dungeonScreen.getController().setWinScreen(vw);
		dungeonScreen.getController().setMenuScreen(frontend);
		dungeonScreen.show();
	}
    

	@FXML
	public void PortalTeleport(ActionEvent event) throws IOException {
		String map = "Portal.json";
    	DungeonView dungeonScreen = new DungeonView(frontend.getStage(), "Go to exit by portal", map);
    	dungeonScreen.getController().setScreen(dungeonScreen);
		ViewWin vw = new ViewWin(win.getStage(), "Win");
    	dungeonScreen.getController().setWinScreen(vw);
    	dungeonScreen.show();
	}

	@FXML
	public void PlayExtension(ActionEvent event) throws IOException {
		String map = "Extension.json";
    	DungeonView dungeonScreen = new DungeonView(frontend.getStage(), "Extension Map", map);
    	dungeonScreen.getController().setScreen(dungeonScreen);
		ViewWin vw = new ViewWin(win.getStage(), "Win");
		ViewLost vl = new ViewLost(lost.getStage(), "Win");
		dungeonScreen.getController().setWinScreen(vw);
		dungeonScreen.getController().setLostScreen(vl);
    	dungeonScreen.getController().setMenuScreen(frontend);
    	dungeonScreen.show();
	}
	
	// @FXML
	// public void Introduction(ActionEvent event) throws IOException {
    // 	Introduction Introduction = new Introduction(challengeMenu.getStage(),"Introduction");
    // 	Introduction.getController().setMenuScreen(challengeMenu);
    // 	Introduction.show();
	// }

	@FXML
	public void PlayerGuide(ActionEvent event) throws IOException {
		ViewGuide VG = new ViewGuide(frontend.getStage(), "Guide");
		VG.getController().setMenuScreen(frontend);
    	VG.show();
	}
	

	public void setScreen(Frontend forntend) {
		this.frontend = forntend;
	}
	
	public void setViewWin (ViewWin win) {
		this.win = win;
	}

	public void setViewLost (ViewLost los) {
		this.lost = los;
	}
	
	public void setViewGuide (ViewGuide vg) {
		this.Guide = vg;
	}
}
