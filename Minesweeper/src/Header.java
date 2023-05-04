import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class Header extends BorderPane{
	public Score score;
	public static Time time;
	private ImageView smile;

	public Header() {
		score = new Score(TheStage.lvl);
		time = new Time();
		smile = new ImageView("face-smile.png");
		smile.setOnMouseClicked(e -> {
			//resetGame();
			Minesweeper.restart();
		});

		setMargin(score, new Insets(15, 15, 15, 15));
		setMargin(time, new Insets(15, 15, 15, 15));
		score.setStyle("-fx-border-width:2px;"+ "-fx-border-color: #7b7b7b white white #7b7b7b;"+ "-fx-border-style: solid;"+ "-fx-border-radius: 0.01;");
		time.setStyle("-fx-border-width:2px;"+ "-fx-border-color: #7b7b7b white white #7b7b7b;"+ "-fx-border-style: solid;"+ "-fx-border-radius: 0.01;");

		setLeft(score);
		setRight(time);
		setCenter(smile);	
	}
	
	public void smileFace() {
		smile = new ImageView("face-smile.png");//Smile face
		setCenter(smile);

		smile.setOnMouseClicked(e -> {
			reset();
		});
	}

	public void deadFace() {
		smile = new ImageView("face-dead.png");//dead face
		setCenter(smile);

		Minesweeper.checkFlags();

		smile.setOnMouseClicked(e -> {
			reset();
		});
	}

	public void winFace() {
		smile = new ImageView("face-win.png");//win face
		setCenter(smile);

		smile.setOnMouseClicked(e -> {
			reset();
		});
	}
	
	public void faceO() {
		smile = new ImageView("face-O.png");//face O
		setCenter(smile);

		smile.setOnMouseClicked(e -> {
			reset();
		});
	}

	public void reset() {
		SmileButton.isFirst = true;
		SmileButton.gameOver = false;
 
		smile = new ImageView("face-smile.png");
		setCenter(smile);

		score = new Score(TheStage.lvl);
		time = new Time();

		setMargin(score, new Insets(15, 15, 15, 15));
		setMargin(time, new Insets(15, 15, 15, 15));
		score.setStyle("-fx-border-width:2px;"+ "-fx-border-color: #7b7b7b white white #7b7b7b;"+ "-fx-border-style: solid;"+ "-fx-border-radius: 0.01;");
		time.setStyle("-fx-border-width:2px;"+ "-fx-border-color: #7b7b7b white white #7b7b7b;"+ "-fx-border-style: solid;"+ "-fx-border-radius: 0.01;");

		setLeft(score);
		setRight(time);

		//reset the board 
		Minesweeper.restart();

		smile.setOnMouseClicked(e -> {
			reset();
		});
	}

	public Time getTime() {
		return time;
	}
}

