import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
//Jean Zachary Reyes 300329378

public class Minesweeper {
	private static SmileButton[][] state;
	private static Header header;
	private static GridPane cells; 
	private static SmileButton smileButton;
	private static Time time;

	private static int sizeY;
	private static int sizeX;
	private static int[][] states;
	private static int minesLeft;
	public static int correctFlags;

	public Minesweeper(int diff, Header header, Time time) {
		if(diff == 0) {
			sizeY = 8;
			sizeX = 8;
			minesLeft = 10;
			Minesweeper.header = header;
			Minesweeper.time = time;
		}else if(diff == 1) {
			sizeY = 16;
			sizeX = 16;
			minesLeft = 40;
			Minesweeper.header = header;
			Minesweeper.time = time;
		}else {
			sizeY = 32;
			sizeX = 16;
			minesLeft = 99;
			Minesweeper.header = header;
			Minesweeper.time = time;
		}
		cells = new GridPane();
	}


	public static void restart() {
		SmileButton cover;
		state = new SmileButton[sizeX][sizeY];
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {
				cover = new SmileButton(header, i, j);
				state[i][j] = cover;
				cells.add(cover, j, i);
			}
		}
	}

	public static void reset() {
		time = Header.time; 
		states = new int[sizeX][sizeY]; 
		int[][] mines = new int[minesLeft][2]; 
		int firstRow = 0;
		int firstCol = 0;

		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {
				if(state[i][j].getFirstClick()) {
					firstRow = i;
					firstCol = j;
				}
			}
		}

		int[][] noMine = getNoMines(firstRow, firstCol);
		
		int mineIndex = 0;
		while(mineIndex < minesLeft) { //random mines
			boolean add = true;
			int row = (int)(Math.random() * sizeX);
			int col = (int)(Math.random() * sizeY);

			for(int i = 0; i < noMine.length; i++) {
				if(row == noMine[i][0] && col == noMine[i][1])
					add = false;
			}

			if(add) {

				for(int j = 0; j < minesLeft; j++) {
					if(row == mines[j][0] && col == mines[j][1]) 
						add = false;
				}
			}

			if(add) {
				mines[mineIndex][0] = row;
				mines[mineIndex][1] = col;
				mineIndex++;
			}

		}

		for(int i = 0; i < minesLeft; i++) {

			int row = mines[i][0];
			int col = mines[i][1];
			states[row][col] = 9;

			for(int j = -1; j <= 1; j++) {
				for(int k = -1; k <= 1; k++) {

					if(isValid(row + j, col + k)) {

						if(states[row + j][col + k] != 9) 
							states[row + j][col + k]++;
					}
				}
			}

		}

		for(int i = 0; i < sizeX; i ++) {
			for(int j = 0; j < sizeY; j++) {

				state[i][j].setState(states[i][j]);

			}
		}	
	}

	private static int[][] getNoMines(int row, int col) {
		int[][] noMines = new int[9][2];
		int k = 0;
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {

				if(isValid((row + i), (col + j))) {
					noMines[k][0] = row + i;
					noMines[k][1] = col + j;
				}else {
					noMines[k][0] = -1;
					noMines[k][1] = -1;
				}

				k++;	
			}
		}
		return noMines;
	}

	public static void openEmpty(int row, int col) {
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {

				if(isValid(row + i, col + j)) {
					if(!state[row + i][col + j].getFlag())
						state[row + i][col + j].changeState();
					state[row + i][col + j].setMouseTransparent(true);
				}
			}
		}
	}
	
	private static boolean isValid(int row, int col) {
		if(row >= 0 && col >= 0) {

			if(row < sizeX && col < sizeY) {
				return true;
			}
		}
		return false;
	}

	public static boolean correctFlags(int row, int col, int mines) {
		int count = 0;
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {

				if(isValid((row + i), (col + j))) {

					if(state[row + i][col + j].getFlag()) {
						count++;
					}
				}
			}
		}

		if(count == mines) {
			return true;
		}

		return false;
	}

	//win game
	public static void winFace() {
		boolean win = true;
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {
				if(!state[i][j].getMine() && !state[i][j].getShow()) {
					win = false;
				}
			}
		}

		if(correctFlags == minesLeft)
			win = true;

		if(win) {

			Header.time.stop();
			header.winFace();
			SmileButton.gameOver = true; 
		}
	}
	
	public static void recurse(int row, int col) {
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(isValid((row + i), (col + j)) && !state[row + i][col + j].getShow()) {
					if(state[row + i][col + j].getState() == 0 && !state[row + i][col + j].getShow()) {
						state[row + i][col + j].changeState();
						recurse((row + i), (col + j));
						//state[row + i][col + j].setMouseTransparent(true);
					}else if(state[row + i][col + j].getState() != 0 && !state[row + i][col + j].getShow()) 
						state[row + i][col + j].changeState();
					state[row + i][col + j].setMouseTransparent(true);
				}
			}
		}
	}

	public static void checkFlags() {
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {

				if(state[i][j].getFlag() && !state[i][j].getMine()) {
					state[i][j].setMisflagged();
				}
			}
		}
	}
	
	public static void showMines() {
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {
				if(states[i][j] == 9 && !state[i][j].getFlag()) {
					state[i][j].displayGrey();
				}
			}
		}
	}

	public static GridPane getCells() {
		return cells;
	}
}



