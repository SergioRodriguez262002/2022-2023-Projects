package rodriguezProject3;

public class RodriguezProject3 {

	public static void main(String[] args) {
		Maze maze = new Maze(51,51);
		maze.generateMaze();
		maze.draw();
		maze.solveMaze();
		
		
	}

}
