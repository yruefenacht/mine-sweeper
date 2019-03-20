package calc;

import settings.Settings;
import java.util.Random;

public class MineFieldMatrix {

    private int[][] matrix;
    private Random random;
    private int bombsPlaced = 0;


    public MineFieldMatrix() {

        this.matrix = new int[Settings.CELL_COUNT][Settings.CELL_SIZE];
        this.random = new Random();
    }


    public int[][] generateMatrix() {

        while (this.bombsPlaced < Settings.BOMB_COUNT) {

            int x = this.random.nextInt(Settings.CELL_COUNT);
            int y = this.random.nextInt(Settings.CELL_COUNT);

            placeBomb(x, y);
        }

        return this.matrix;
    }


    private void placeBomb(int x, int y) {

        this.matrix[x][y] = -1;
        this.bombsPlaced++;

        checkInBound(x, y-1);
        checkInBound(x+1, y-1);
        checkInBound(x+1, y);
        checkInBound(x+1, y+1);
        checkInBound(x, y+1);
        checkInBound(x-1, y+1);
        checkInBound(x-1, y);
        checkInBound(x-1, y-1);
    }


    private void checkInBound(int x, int y) {

        try {
            if(this.matrix[x][y] != -1) this.matrix[x][y]++;
        } catch(ArrayIndexOutOfBoundsException ignored){}
    }

}
