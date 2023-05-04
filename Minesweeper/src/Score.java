import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Score extends HBox {
	private int mines;
	private ImageView digit1;
	private ImageView digit2;
	private ImageView digit3;

	public Score(int diff) {
		digit1 = new ImageView("digits/0.png");
		digit3 = new ImageView("digits/0.png");
		if(diff == 0) {
			mines = 10;
			digit2 = new ImageView("digits/1.png");
			getChildren().addAll(digit1, digit2, digit3);
		}else if(diff == 1) {
			mines = 40;
			digit2 = new ImageView("digits/4.png");
			getChildren().addAll(digit1, digit2, digit3);
		}else {
			mines = 99;
			digit2 = new ImageView("digits/9.png");
			digit3 = new ImageView("digits/9.png");
			getChildren().addAll(digit1, digit2, digit3);
		}
	}

	public void removeFlag() {
		mines++;
		if(mines >= 0) {
			digit1 = new ImageView("digits/0.png");
			digit2 = getDigit(mines / 10);
			digit3 = getDigit(mines % 10);

		}else {
			digit1 = new ImageView("digits/digit-.png");
			digit2 = getDigit(Math.abs((int)(mines / 10)));
			digit3 = getDigit(Math.abs(mines % 10));
		}
		getChildren().clear();
		getChildren().addAll(digit1, digit2, digit3);
	}

	public void putFlag() {
		mines--;
		if(mines >= 0) {
			digit2 = getDigit(mines / 10);
			digit3 = getDigit(mines % 10);
		}else {
			digit1 = new ImageView("digits/digit-.png");
			digit2 = getDigit(Math.abs((int)(mines / 10)));
			digit3 = getDigit(Math.abs(mines % 10));
		}
		getChildren().clear();
		getChildren().addAll(digit1, digit2, digit3);
	}

	public ImageView getDigit(int n) {
		switch(n) {
		case 0:
			return new ImageView("digits/0.png");
		case 1:
			return new ImageView("digits/1.png");
		case 2:
			return new ImageView("digits/2.png");
		case 3:
			return new ImageView("digits/3.png");
		case 4:
			return new ImageView("digits/4.png");
		case 5:
			return new ImageView("digits/5.png");
		case 6:
			return new ImageView("digits/6.png");
		case 7:
			return new ImageView("digits/7.png");
		case 8:
			return new ImageView("digits/8.png");
		case 9:
			return new ImageView("digits/9.png");
		default:
			return new ImageView("digits/0.png");

		}
	}
}

