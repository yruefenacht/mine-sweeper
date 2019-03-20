package field;

import calc.MineFieldMatrix;
import settings.Settings;
import java.util.ArrayList;

public class MineField {

    private static ArrayList<Cell> cells;


    public static void init(int width) {

        cells = new ArrayList<>();
        MineFieldMatrix mineFieldMatrix = new MineFieldMatrix();
        int[][] matrix = mineFieldMatrix.generateMatrix();

        boolean isDark = true;

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < width; y++) {

                Cell cell = new Cell(x, y, matrix[x][y], isDark);
                cells.add(cell);

                isDark = !isDark;
            }
            if(Settings.CELL_COUNT % 2 == 0)
                isDark = !isDark;
        }
    }


    public static ArrayList<Cell> getCells() {

        return cells;
    }


    static Cell getCellAt(int x, int y) {

        for(Cell c : cells) {
            if (c.getX() == x && c.getY() == y)
                return c;
        }
        return null;
    }


    static void uncoverAllBombs() {

        for(Cell c : cells)
            if(c.getValue() == -1)
                c.uncover();
    }


    static boolean allCellsAreUncovered() {

        for(Cell c : cells) {
            if(c.getValue() != -1 && !c.isUncovered())
                return false;
        }
        return true;
    }

}
