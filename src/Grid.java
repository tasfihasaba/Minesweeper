import java.util.Random;

public class Grid {
	// member value
	private boolean[][] bombGrid;
	private int[][] countGrid;
	private int numRows;
	private int numColumns;
	private int numBombs;
	
	// Default constructor
	public Grid() {
		this.numRows = 10;
		this.numColumns = 10;
		this.numBombs = 25;
		createBombGrid();
		createCountGrid();
	}
	
	// Constructor takes rows and columns
	public Grid(int rows, int columns) {
		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = 25;	
		createBombGrid();
		createCountGrid();
	}
	
	// Constructor takes row, columns and numBombs
	public Grid(int rows, int columns, int numBombs) {
		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = numBombs;	
		createBombGrid();
		createCountGrid();
	}	
	
	// return private variables
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public int getNumBombs() {
		return numBombs;
	}
	
	// return copy of bombgrid
	public boolean[][] getBombGrid() {
		boolean[][] copy = new boolean[numRows][numColumns];
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				copy[i][j] = bombGrid[i][j];
			}
		}
		return copy;
	}
	
	// return copy of bomb count
	public int[][] getCountGrid() {
		int[][] copy = new int[numRows][numColumns];
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				copy[i][j] = countGrid[i][j];
			}
		}
		return copy;
	}
	
	// return true if bomb is present in that location
	public boolean isBombAtLocation(int row, int column) {
		return bombGrid[row][column];
	}
	
	// return count at this location
	public int getCountAtLocation(int row, int column) {
		return countGrid[row][column];
	}
	
	// create and initialize bomb
	private void createBombGrid() {
		// create the boolean table
		bombGrid = new boolean[numRows][numColumns];
		// create the random variable generator with seed
		Random rand = new Random(System.currentTimeMillis());
		// create the numBombs
		for(int i = 0; i < numBombs; i++) {
			// get random location
			int idx = rand.nextInt(numColumns * numRows);
			// split the location into x and y
			int row = idx / numColumns;
			int col = idx % numColumns;
			// if the bomb is already print there then continue again 
			if(bombGrid[row][col])
				i--;
			else	// else plant the bomb
				bombGrid[row][col] = true;
		}
	}
	
	// create the count at each location
	private void createCountGrid() {
		// create count table
		countGrid = new int[numRows][numColumns];
		// for each location
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				
				int count = 0;
				// if bomb present in the cell and it's neighbouring cell then add to the count
				if(bombGrid[i][j])
					count++;
				// bound check the i and j so it won't access the element out of range
				if(((i - 1) >= 0) && bombGrid[i - 1][j])
					count++;
				if(((i + 1) < numRows) && bombGrid[i + 1][j])
					count++;
				if(((j - 1) >= 0) && bombGrid[i][j - 1])
					count++;
				if(((j + 1) < numColumns) && bombGrid[i][j + 1])
					count++;
				if(((i - 1) >= 0) && ((j - 1) >= 0) && bombGrid[i - 1][j - 1])
					count++;
				if(((i + 1) < numRows) && ((j - 1) >= 0) && bombGrid[i + 1][j - 1])
					count++;
				if(((i - 1) >= 0) && ((j + 1) < numColumns) && bombGrid[i - 1][j + 1])
					count++;
				if(((i + 1) < numRows) && ((j + 1) < numColumns) && bombGrid[i + 1][j + 1])
					count++;
				// finally update the count value
				countGrid[i][j] = count;
			}
		}
	}
}
