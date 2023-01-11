# Minesweeper

In this game of Minesweeper, a player searches for hidden bombs on a rectangular grid.
The game board is represented by a grid of booleans marking bomb locations. 
A grid value is true if there is a bomb at that location, false otherwise.
A user can click on any cell they choose. 
The game is lost when the user clicks on a cell containing a bomb. 
The game is won when all cells not containing bombs have been opened and the only remaining cells are those containing bombs.

Given such a grid of bomb locations, the method createCountGrid() constructs a new grid of integers storing the count of bombs in each neighborhood. 
The neighborhood for a location includes the location itself and its eight adjacent locations. 
In the returned grid, each value will be a number from 0 to 9. 


If passed the boolean grid on the left, createCountGrid() returns the grid of int values on the right:

Here are the example grids:


<img width="701" alt="Example Grids " src="https://user-images.githubusercontent.com/77076887/211902246-2b114ab2-ffa6-435d-a77e-d5d22e3717bf.png">

### The examples below demonstrate how to compute the countGrid from the bombGrid.


A. In “Example A” one can see the cell [0][0] has a count of 1 because the only adjacent cell containing a bomb is [1][1]. 

B. In “Example B” one can see the cell [1][2] has a count of 0 because there are no adjacent cells containing a bomb.

C. In “Example C” one can see the cell [1][1] has a count of 4 because there are 4 adjacent cells containing a bomb. [0][0] , [0][2], [2][0], [2][1]

D. In “Example D” one can see the cell [1][1] has a count of 3 because there are 3 adjacent cells containing a bomb (including the cell itself). [1][1] , [2][0], [2][2]

<img width="605" alt="Examples " src="https://user-images.githubusercontent.com/77076887/211904289-166d78fc-c540-44a6-8bdd-99b694fd5d5b.png">


# OBJECTIVES 

1. Implemented the Grid class shown below in the UML. <br    />
a. Implemented the private methods **createBombGrid()** and **createCountGrid()** as follows:  <br />
       
*    **createBombGrid()** Creates the boolean 10 x 10 bombGrid shown on the left and randomly places 25 bombs in the grid. 
*    **createCountGrid()** Creates the int 10 x 10 countGrid shown on the right based on the bomb placement in the bombGrid.

b. Implemented the default constructor so that it initializes the variables and calls the 2 private methods **createBombGrid()** and **createCountGrid()** <br />
c. Implemented the **getNumRows()** , **getNumColumns()** , **getNumBombs()**  <br />
d. Implemented **getBombGrid()**, which returns a copy of the 2 dimensional boolean array.  <br />
e. Implemented **getCountGrid()**, which returns a copy of the 2 dimensional int array. <br />
f. Implemented **isBombAtLocation(int row, int column)** so that it returns true if a bomb is in the cell, false otherwise. <br />
g. Implemented **getCountAtLocation(int row, int column)** so that it returns the sum of the surrounding adjacent bombs counted by retrieving it from the countGrid. <br />
 <br />
 <br />
 
2. Created a GUI using the java Swing package so that it behaves as follows: <br />
a. Displayed the grid, matching the dimensions as specified in the Grid class, hiding the content of all the cells.
b. Each cell in the GUI’s grid is a JButton with its own ActionListener.
c. When a cell (aka JButton) is clicked there should be one of the following actions triggered.
* If there is a bomb, the game is over and the entire content of the grid is revealed to the player, so, that the cells containing bombs display a bomb, and cells without bombs show their count.
* If there is no bomb in the cell, the cell’s count is revealed from the countGrid to display the number surrounding adjacent bombs.
* If the cell is the last one without a bomb to be revealed, the game has been won.<br />
d. When the game is over the user should be informed via JOptionPane whether they won or lost and be offered the option to play again.
* If the user chooses “Yes” to play again, a new instance of the Grid should be created and displayed to play again from step 2a.
* If the user chooses “No” to not play again, all windows are closed and the application exits.


  

