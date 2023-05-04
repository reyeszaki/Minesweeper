import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

public class SmileButton extends ImageView {
	private Header header;
	private Time time;
	
	private int row;
	private int col;
	private boolean flag; 
	private boolean firstClick;//if first click
	private boolean show; 
	private boolean mine; 
	private int state; //state of cells
	public static boolean isFirst = true; //first click 
	public static boolean gameOver = false;
	
	
	public SmileButton(Header header, int row, int col) {
		this.header = header;
		this.row = row;
		this.col = col;
		flag = false;
		firstClick = false;
		show = false;
		mine = false;
		gameOver = false;
		
		setImage(new Image("cover.png"));
		
		setOnMousePressed(e -> {
			if(!gameOver) {
				header.faceO();
			}
		});
		setOnMouseReleased(e -> {
			if(!gameOver) {
				header.smileFace();
			}
		});
		
		setOnMouseClicked(e -> {
			if(e.getButton() == MouseButton.PRIMARY && flag == false && !gameOver) {
				
				if(isFirst) {
					firstClick = true;
					setImage(new Image("0.png"));
					Minesweeper.reset();
					Header.time.startTime();
					Minesweeper.recurse(row, col);
					isFirst = false;
				}else if(show){
					if(Minesweeper.correctFlags(row, col, state)) {
						Minesweeper.openEmpty(row, col);
					}
					
				}else {
					changeState();
					Minesweeper.winFace();
					
					if(state == 0) {
						Minesweeper.recurse(row, col);
					}
					
					if(mine) {
						flag = true;
						header.deadFace();
						Header.time.stop();
						Minesweeper.showMines();
						gameOver = true;
					}
				}
				
			}else if(e.getButton() == MouseButton.SECONDARY && !gameOver) {
				
				if(flag) {
					setImage(new Image("cover.png"));
					flag = false;
					header.score.removeFlag();
					if(mine)
						Minesweeper.correctFlags --;
				}else {
					setImage(new Image("flag.png"));
					flag = true;
					header.score.putFlag();
					if(mine)
						Minesweeper.correctFlags ++;
				}
			}
		});
	}
	
	public void updateTime(Time time) {
		this.time = time;
	}
	
	public void setState(int state) {
		this.state = state;
		if(state == 9)
			mine = true;
	}
	
	public boolean getFlag() {
		return flag;
	}
	
	public void displayGrey() {
		setImage(new Image("mine-grey.png"));
	}
	
	public boolean getFirstClick() {
		return firstClick;
	}
	
	public void setMisflagged() {
		setImage(new Image("mine-misflagged.png"));
	}
	
	public int getState() {
		return state;
	}
	
	public boolean getShow() {
		return show;
	}
	
	public boolean getMine() {
		return mine;
	}
	//cell states
		public void changeState() {
			gameOver = false;
			show = true;
			if(state == 0) {
				setImage(new Image("0.png"));
			}else if(state == 1) {
				setImage(new Image("1.png"));
			}else if(state == 2) {
				setImage(new Image("2.png"));
			}else if(state == 3) {
				setImage(new Image("3.png"));
			}else if(state == 4) {
				setImage(new Image("4.png"));
			}else if(state == 5) {
				setImage(new Image("5.png"));
			}else if(state == 6) {
				setImage(new Image("6.png"));
			}else if(state == 7) {
				setImage(new Image("7.png")); 
			}else if(state == 8) {
				setImage(new Image("8.png"));
			}else if(state == 9) {
				setImage(new Image("mine-red.png"));
				flag = true;
				header.deadFace();
				Header.time.stop();
				Minesweeper.showMines();
				gameOver = true;
			}
		}
}
