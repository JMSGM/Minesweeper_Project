package pkgUtils;

import java.util.Random;

public class JMsPingPong {
    //Field
    private final Random myRandom = new Random();
    private int ROWS;
    private int COLS;
    private int[][] LIVE_ARR;
    private int[][] NEXT_ARR;

    //Constructors
    public JMsPingPong(int rows, int cols){
        this(rows, cols, 0, 1);
    }
    public JMsPingPong(int rows, int cols, int value){
        ROWS = rows;
        COLS = cols;
        LIVE_ARR = new int[rows][cols];
        NEXT_ARR = new int[rows][cols];
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                LIVE_ARR[row][col] = value;
                }
            }
    }
    public JMsPingPong(int rows, int cols, int lower, int upper){
        ROWS = rows;
        COLS = cols;
        LIVE_ARR = new int[rows][cols];
        NEXT_ARR = new int[rows][cols];
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                LIVE_ARR[row][col] = myRandom.nextInt((upper-lower) + 1) + lower;
            }
        }
    }
    //Methods
    void setLIVEArray(int row, int col, int value){
        LIVE_ARR[row][col] = value;
    }
    int getLIVEArray(int row, int col, int value){
        return LIVE_ARR[row][col] = value;
    }
    void swapArrays(){
    int tmp[][] = LIVE_ARR;
    LIVE_ARR = NEXT_ARR;
    NEXT_ARR = tmp;
    }
    //count nearest neighbor of specified cell [row][col]
    int countNearestNeighbor(int row, int col){
        int count = 0;

        int prevRow = (row - 1 + ROWS) % ROWS;
        int nextRow = (row + 1) % ROWS;
        int prevCol = (col - 1 + COLS) % COLS;
        int nextCol = (col + 1) % COLS;

        //Top
        if (LIVE_ARR[prevRow][col] == 1){
            count += 1;
        }
        //Bottom
        if (LIVE_ARR[nextRow][col] == 1){
            count += 1;
        }
        //Left
        if (LIVE_ARR[row][prevCol] == 1){
            count += 1;
        }
        //Right
        if (LIVE_ARR[row][nextCol] == 1){
            count += 1;
        }

       return count;
    }
    int countNextNearestNeighbor(int row, int col){
        int count = 0;

        int prevRow = (row - 1 + ROWS) % ROWS;
        int nextRow = (row + 1) % ROWS;
        int prevCol = (col - 1 + COLS) % COLS;
        int nextCol = (col + 1) % COLS;

        // Top-left
        if (LIVE_ARR[prevRow][prevCol] == 1) {
            count += 1;
        }
        // Top
        if (LIVE_ARR[prevRow][col] == 1) {
            count += 1;
        }
        // Top-right
        if (LIVE_ARR[prevRow][nextCol] == 1) {
            count += 1;
        }
        // Left
        if (LIVE_ARR[row][prevCol] == 1) {
            count += 1;
        }
        // Right
        if (LIVE_ARR[row][nextCol] == 1) {
            count += 1;
        }
        // Bottom-left
        if (LIVE_ARR[nextRow][prevCol] == 1) {
            count += 1;
        }
        // Bottom
        if (LIVE_ARR[nextRow][col] == 1) {
            count += 1;
        }
        // Bottom-right
        if (LIVE_ARR[nextRow][nextCol] == 1) {
            count += 1;
        }

        return count;
    }
    void resetBoardBetween(int val1, int val2){

    }
    void resetBoardToBinary(){

    }
    void printLiveArray(){

    }


}
