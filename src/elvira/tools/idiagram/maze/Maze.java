/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools.idiagram.maze;

/**
 *
 * @author rcabanas
 */
public class Maze {

    private int sizeX;
    private int sizeY;
    private boolean walls[][];
    private int goalX, goalY;

    public Maze() {
    }

    public Maze(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        walls = new boolean[sizeY][sizeX];

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                walls[i][j] = false;
            }
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public boolean[][] getWalls() {
        return walls;
    }

    public void setWalls(boolean[][] walls) {
        this.walls = walls;
        this.sizeY = walls.length;
        this.sizeX = walls[0].length;

    }

    public void setWalls(int[][] walls) {

        int maxX = walls[0].length;
        int maxY = walls.length;
        boolean w2[][] = new boolean[maxY][maxX];
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                w2[i][j] = walls[i][j] == 1;

            }
        }
        
        setWalls(w2);

    }

    public void setWall(int x, int y) {
        walls[y][x] = true;

    }

    public boolean isWall(int x, int y) {
        return walls[y][x];
    }
    // n e s w

    public int[] getNeighbourWalls(int x, int y) {
        int w[] = {0, 0, 0, 0};

        if (!isWall(x, y, 'n')) {
            w[0] = 1;
        }
        if (!isWall(x, y, 'e')) {
            w[1] = 1;
        }
        if (!isWall(x, y, 's')) {
            w[2] = 1;
        }
        if (!isWall(x, y, 'w')) {
            w[3] = 1;
        }

        return w;
    }

    public boolean isWall(int x, int y, char direction) {
        boolean w = false;
        if (direction == 'n') {
            w = isWallNorth(x, y);
        } else if (direction == 's') {
            w = isWallSouth(x, y);
        } else if (direction == 'w') {
            w = isWallWest(x, y);
        } else if (direction == 'e') {
            w = isWallEast(x, y);
        }

        return w;
    }

    public boolean isWallNorth(int x, int y) {
        if (y == 0) {
            return true;
        }
        return walls[y - 1][x];
    }

    public boolean isWallSouth(int x, int y) {
        if (y == sizeY - 1) {
            return true;
        }
        return walls[y + 1][x];
    }

    public boolean isWallWest(int x, int y) {
        if (x == 0) {
            return true;
        }
        return walls[y][x - 1];
    }

    public boolean isWallEast(int x, int y) {
        if (x == sizeX - 1) {
            return true;
        }
        return walls[y][x + 1];
    }

    public void printMaze() {
        for (int j = 0; j < sizeX; j++) {
            System.out.print(" _");
        }

        System.out.println();
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                if (walls[i][j]) {
                    System.out.print("|#");
                } else {

                    if (j == goalX && i == goalY) {
                        System.out.print("|*");
                    } else {
                        System.out.print("|_");
                    }
                }
            }
            System.out.print("|\n");
        }

    }

    public int getGoalX() {
        return goalX;
    }

    public void setGoalX(int goalX) {
        this.goalX = goalX;
    }

    public int getGoalY() {
        return goalY;
    }

    public void setGoalY(int goalY) {
        this.goalY = goalY;
    }

    public void setGoal(int goalX, int goalY) {
        this.goalY = goalY;
        this.goalX = goalX;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Maze m = new Maze();



        boolean w[][] = {{true, true, true, true, true, true, true, true, true},
            {true, true, false, false, false, false, false, true, true},
            {true, false, false, true, false, true, false, false, true},
            {true, true, false, true, false, true, false, true, true},
            {true, false, false, true, false, true, false, false, true},
            {true, true, false, false, false, false, false, true, true},
            {true, true, true, true, true, true, true, true, true},};

        m.setWalls(w);

        m.setGoal(7, 2);

        m.printMaze();
    }
}
