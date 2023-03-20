package rodriguezProject3;

import java.util.*;
import edu.du.dudraw.Draw;

public class Maze {
	// Private instance variables for rows and collumns of the size of the maze and
	// the pixel
	// radius of each cell on screen. The canvas uses the draw type variable. Theres
	// also the stack for the
	private int columns, rows;
	private int cellRadius = 15;

	Draw draw = new Draw();

	// Enum type for the values of the cells in the maze array, the maze array holds
	// the values of the maze in the maze
	public enum CellValue {
		WALL, OPEN, EXPLORED
	}

	CellValue[][] maze;

	public Maze(int x, int y) {
		// Maze constructor that assigns the total rows to the y parameter and the cols
		// to the y parameter.
		columns = x;
		rows = y;

		// Maze array is initialized
		maze = new CellValue[rows][columns];

		// Maze array is assigned to all enum values of wall
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				maze[i][j] = CellValue.WALL;
			}
		}

		// Canvas is created using draw, the size and scale is set
		draw.setCanvasSize(x * cellRadius, y * cellRadius);
		draw.setXscale(0, columns * cellRadius * 2.5);
		draw.setYscale(0, rows * cellRadius * 2.5);

	}

	public void draw() {
		// Draw function that clears the draws every square on screen according to the
		// values in the maze array
		draw.clear();
		draw.enableDoubleBuffering();
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				if (maze[x][y] == CellValue.WALL) {
					draw.setPenColor(draw.BLACK);
				}
				if (maze[x][y] == CellValue.OPEN) {
					draw.setPenColor(draw.WHITE);
				}
				if (maze[x][y] == CellValue.EXPLORED) {
					draw.setPenColor(draw.GREEN);
				}
				draw.filledSquare(x * cellRadius * 2 + cellRadius * 2, y * cellRadius * 2 + cellRadius * 3, cellRadius);
			}
		}
		draw.show();
	}

	public void generateMaze() {
		// Maze generating function that uses a stack to store open cells.
		DLLStack<Maze.Cell> stack = new DLLStack<Maze.Cell>();
		int row, col;
		// While loop that generates two random odd starting cell values.
		do {
			row = (int) (Math.random() * ((rows - 3) - 3) + 3);
			col = (int) (Math.random() * ((columns - 3) - 3) + 3);
		} while (row % 2 != 0 && col % 2 != 0);

		row = 36;
		col = 10;

		// Current cell instance is assigned to a cell of odd row col values and the
		// maze value at the odd row col is set to open, this is the starting point for
		// maze generation.

		Cell currentCell = new Cell(row, col);
		maze[row][col] = CellValue.OPEN;

		// Starter cell is pushed to the stack
		stack.push(currentCell);

		// While the stack is not empty, continue making the maze. An empty stack means
		// the maze has been generated.
		while (!stack.isEmpty()) {
			// Popping the stack gives the most current open cell, if there is neighboring
			// wall cells a the popped cell that could be opened, then their opened.
			currentCell = stack.pop();

			// This array stores all the opened neighboring wall cells to latter be shuffled
			// randomly and added to the stack.
			ArrayList<Cell> neighbors = new ArrayList<Cell>();
			Cell c;

			if (currentCell.getRow() >= 1 && currentCell.getRow() <= rows - 2) { // This conditional makes sure the
																					// outer bounds of the maze array
																					// aren't checked and cause a crash

				// The following if statements check if the neighboring walls can be opened
				if (maze[currentCell.getRow() + 2][currentCell.getCol() + 0] == CellValue.WALL) {
					c = new Cell(currentCell.getRow() + 2, currentCell.getCol() + 0);
					neighbors.add(c);
					maze[currentCell.getRow() + 2][currentCell.getCol() + 0] = CellValue.OPEN;
					maze[currentCell.getRow() + 1][currentCell.getCol() + 0] = CellValue.OPEN;
				}
			}

			if (currentCell.getCol() >= 1 && currentCell.getCol() <= rows - 2) {
				if (maze[currentCell.getRow() + 0][currentCell.getCol() + 2] == CellValue.WALL) {
					c = new Cell(currentCell.getRow() + 0, currentCell.getCol() + 2);
					neighbors.add(c);
					maze[currentCell.getRow() + 0][currentCell.getCol() + 2] = CellValue.OPEN;
					maze[currentCell.getRow() + 0][currentCell.getCol() + 1] = CellValue.OPEN;
				}
			}

			if (currentCell.getRow() >= 1 && currentCell.getRow() <= rows - 2) {
				if (maze[currentCell.getRow() - 2][currentCell.getCol() + 0] == CellValue.WALL) {
					c = new Cell(currentCell.getRow() - 2, currentCell.getCol() + 0);
					neighbors.add(c);
					maze[currentCell.getRow() - 2][currentCell.getCol() + 0] = CellValue.OPEN;
					maze[currentCell.getRow() - 1][currentCell.getCol() + 0] = CellValue.OPEN;
				}
			}

			if (currentCell.getCol() >= 1 && currentCell.getCol() <= rows - 2) {
				if (maze[currentCell.getRow() + 0][currentCell.getCol() - 2] == CellValue.WALL) {
					c = new Cell(currentCell.getRow() + 0, currentCell.getCol() - 2);
					neighbors.add(c);
					maze[currentCell.getRow() + 0][currentCell.getCol() - 2] = CellValue.OPEN;
					maze[currentCell.getRow() + 0][currentCell.getCol() - 1] = CellValue.OPEN;
				}
			}

			// Once the recently opened wall cells have been developed and added to the
			// neighbor array, the array is randomly shuffled and added to the stack. The
			// top of the stack is therefore a random wall cell and the cycle repeats. Even
			// when nothing is added every wall cell will be popped and possible neighbors.
			// opened ensuring the whole maze is created.
			Collections.shuffle(neighbors);

			Iterator iter = neighbors.iterator();
			while (iter.hasNext()) {
				stack.push((Cell) iter.next());
			}

			// draw();

		}

		// Generate border
		for (int i = 0; i < rows; i++) {
			maze[i][0] = CellValue.WALL;
			maze[i][columns - 1] = CellValue.WALL;
		}
		for (int i = 0; i < columns; i++) {
			maze[0][i] = CellValue.WALL;
			maze[rows - 1][i] = CellValue.WALL;
		}
		draw();

	}

	public void solveMaze() {
		// Start of the maze is set at the lower left corner of the maze and the goal is
		// the top right corner of the maze.
		Cell start = new Cell(2, 2);
		Cell goal = new Cell(columns - 3, rows - 3);
		Cell currentCell = start;
		maze[2][2] = CellValue.EXPLORED;

		// The queue will be used to store all the explored cells, similar to the stack,
		// the dequeued element will check to see if theres anything left to explore,
		// explores the open cells and adds it to the queue, the cycle continues.
		DLLQueue<Cell> queue = new DLLQueue<Cell>();
		queue.enqueue(currentCell);
		Cell c;
		while (!queue.isEmpty()) {
			currentCell = queue.dequeue();

			if (!currentCell.equals(goal)) { // If the goal is not reached keep exploring at the queued cell and addint
												// explored cells the queue.
				for (int i = -1; i < 2; i++) { // The nested for loop checks to see if neighboring cells that are one
												// away are open and explores them
					for (int j = -1; j < 2; j++) {
						if (maze[currentCell.getRow() + i][currentCell.getCol() + j] == CellValue.OPEN) {
							c = new Cell(currentCell.getRow() + i, currentCell.getCol() + j);
							queue.enqueue(c);
							maze[currentCell.getRow() + i][currentCell.getCol() + j] = CellValue.EXPLORED;
						}
					}
				}
			} else {
				return; // Inducing an end to the while loop while if the goal is reached. 
			}
			draw();
		}
	}

	public class Cell {
		// Cell class that stores the row and column of cells.
		public int row, column;

		public Cell(int x, int y) { // Contructor assigning the cells row col.
			row = x;
			column = y;
		}

		public String toString() {
			// To string printing the row col of the cell
			return "Row:" + row + " Column:" + column;
		}

		public int getRow() {
			return row;
		}

		public int getCol() {
			return column;
		}

		public boolean equals(Cell c) {
			return this.row == c.row && this.column == c.column;
		}

	}

}
