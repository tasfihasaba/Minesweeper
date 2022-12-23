import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Minesweeper extends JFrame implements ActionListener {
	private static final int CELLSIZE = 40;
	private static final int ROWS = 10;
	private static final int COLS = 10;
	private static final int BOMBS = 25;
			
	private Grid grid;
	private int rows;
	private int cols;
	private int cellsCleared;
	private boolean[][] visited;
	
	private JButton[][] cells;
	private Container container;
	
	// default constructor initializes the grid and the game
	public Minesweeper() {
		initializeCells();	
		setSize(cols * CELLSIZE, rows * CELLSIZE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	// public method creates the layout and places buttons in each cell and add listener to each button
	public void initializeCells() {
		// create a create with ROWS x COLS and places BOMBS numbers of bombs
		grid = new Grid(ROWS, COLS, BOMBS);	
		// member holds the current size of cells
		cols = grid.getNumColumns();
		rows = grid.getNumRows();
		// holds the count of cells that are cleared
		cellsCleared = 0;
		// matrix holds whether the particular cells is already cleared or not
		// used to clear the adject cells of the cell containing zero
		visited = new boolean[rows][cols];
		
		// get the container for the application
		container = getContentPane();		
		// set grid layout in the container
		container.setLayout(new GridLayout(rows, cols, 1, 1));
		// create the matrix that holds button corresponding to cells
		cells = new JButton[rows][cols];
		// create the button and initialize the action listener and add to the container
		for(int i = 0; i < grid.getNumRows(); i++) {
			for(int j = 0; j < grid.getNumColumns(); j++) {
				//String t = grid.isBombAtLocation(i, j) ? "T":"F";
				cells[i][j] = new JButton();
				cells[i][j].addActionListener(this);
				container.add(cells[i][j]);
			}
		}
	}
	
	public static void main(String[] args) {
		Minesweeper game = new Minesweeper();
		game.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// Action listener, this function is called everytime when a button is pressed
	@Override
	public void actionPerformed(ActionEvent e) {
		// loop through each button in the matrix and find the one that called this function
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				// check source object with the button
				if(e.getSource().equals(cells[i][j])) {
					// Once the button and it's location is found, check if it has bomb
					if(grid.isBombAtLocation(i, j)) {
						// if it has bomb, then declare game is lost and prompt the player for another game
						int retVal = JOptionPane.showConfirmDialog(null, "Oops!! You Stepped on Bomb, You LOST\nDo you like to play another Game ?", "Minesweeper", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
						if(retVal == JOptionPane.YES_OPTION) {
							// if the player wants another game, remove all elements
							container.removeAll();
							// and initialize them
							initializeCells();
						} // else close the game
						else if(retVal == JOptionPane.NO_OPTION)
							System.exit(0);
					}
					// if the location doesn't contain the bomb, then
					else {
						// add the index of the location to the list
						ArrayList<Integer> cell = new ArrayList<>();
						cell.add( i * cols + j);
						
						// iterate till all the element in the list is processed
						while(!cell.isEmpty()) {
							// remove the first element and calculate the location
							int idx = cell.remove(0);
							int x = idx / cols;
							int y = idx % cols;
							// if this location already cleared then skip this location
							if(visited[x][y])
								continue;
							// else mark this location cleared and increment the count on cleared cells
							visited[x][y] = true; 
							cellsCleared++;
							// create new label to replace the button
							JLabel label = new JLabel();
							// if the count is 0 at this location then don't print the number
							if(grid.getCountAtLocation(x, y) != 0) // else print the count at this location
								label.setText(String.valueOf(grid.getCountAtLocation(x, y)));
							// set the text alignment
							label.setHorizontalAlignment(SwingConstants.CENTER);
							label.setVerticalAlignment(SwingConstants.CENTER);
							// remove the button at this location
							container.remove(idx);
							// add the label to this location
							container.add(label, idx);
							
							// if current location count is 0, then add it's neighbouring cells to the list
							if(grid.getCountAtLocation(x, y) == 0) {
								if((x - 1) >= 0)							cell.add((x - 1) * cols + y - 0);
								if((x + 1) < rows) 							cell.add((x + 1) * cols + y + 0);
								if((y - 1) >= 0)							cell.add((x - 0) * cols + y - 1);
								if((y + 1) < cols)							cell.add((x + 0) * cols + y + 1);
								if(((x - 1) >= 0) 	&& ((y - 1) >= 0))		cell.add((x - 1) * cols + y - 1);
								if(((x + 1) < rows) && ((y - 1) >= 0))		cell.add((x + 1) * cols + y - 1);
								if(((x - 1) >= 0) 	&& ((y + 1) < cols)) 	cell.add((x - 1) * cols + y + 1);
								if(((x + 1) < rows) && ((y + 1) < cols))	cell.add((x + 1) * cols + y + 1);
							}
						}
						// if cleared cell count is reached, declare winning and prompt for continounation 
						if(cellsCleared == ((rows * cols) - BOMBS)) {
							int retVal = JOptionPane.showConfirmDialog(null, "Hurray!! You Successfully cleared all Bombs, You WON\nDo you like to play another Game ?", "Minesweeper", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
							if(retVal == JOptionPane.YES_OPTION) {
								container.removeAll();
								initializeCells();
							}
							else if(retVal == JOptionPane.NO_OPTION)
								System.exit(0);
						}						
					}
					// update the container
					container.validate();
					return;
				}
			}
		}
	}
}
