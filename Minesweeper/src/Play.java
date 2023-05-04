import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Play extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override 
	public void start(Stage theStage) {

		new DiffMenu();
	}
}

class TheStage extends Stage {
	public static int lvl;

	TheStage(int dif) {
		lvl = dif;
		Header header = new Header();
		new Minesweeper(lvl, header, Header.time);
		header.setStyle("-fx-border-width:4px;"+ "-fx-border-insets: 5 5 5 5;" +"-fx-border-color: #7b7b7b white white #7b7b7b;"+ "-fx-border-style: solid;"+ "-fx-border-radius: 0.01;");
		GridPane gp = Minesweeper.getCells();
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(2, 2, 2, 2));
		gp.setStyle("-fx-border-width:1px;" + "-fx-border-insets: 5 5 5 5;" + "-fx-border-color: #7b7b7b white white #7b7b7b;" + "-fx-border-radius: 0.01;");

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(header);
		borderPane.setCenter(gp);
		borderPane.setStyle("-fx-background-color: #BDBDBD;"+ "-fx-border-width: 5 5 5 5;"+ "-fx-border-color: white #7b7b7b #7b7b7b white;"+ "-fx-border-radius: 0.01;");

		Minesweeper.restart();

		Menu menu = new Menu("Difficulty");
		MenuItem beginner = new MenuItem("Beginner");
		MenuItem intermediate = new MenuItem("Intermediate");
		MenuItem expert = new MenuItem("Expert");

		Scene scene = new Scene(borderPane);
		setScene(scene);
		show();

		beginner.setOnAction(e -> {
			close();
			new TheStage(0);

		});

		intermediate.setOnAction(e -> {
			close();
			new TheStage(1);
		});

		expert.setOnAction(e -> {
			new TheStage(2);
			close();
		});
	}
}

class DiffMenu extends Stage {
	VBox vbox = new VBox();
	Button beginner = new Button("Beginner");
	Button intermediate = new Button("Intermediate");
	Button expert = new Button("Expert");
	Label label = new Label("Difficulty:");

	DiffMenu() {
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(label, beginner, intermediate, expert);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		vbox.setStyle("-fx-background-color: white;"+ "-fx-border-color: #BDBDBD;"+ "-fx-border-width: 2 2 2 2;");
		setTitle("Difficulty");
		setScene(new Scene(vbox));
		show();

		beginner.setOnAction(e -> { 
			new TheStage(0);
			close();
		});

		intermediate.setOnAction(e -> { 
			new TheStage(1); 
			close();
		});

		expert.setOnAction(e -> { 
			new TheStage(2);
			close();
		});
	}
}
