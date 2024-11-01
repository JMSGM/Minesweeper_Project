package pkgUtils;

import java.util.Random;

public class JMsPingPong {
    //Field
    private final Random myRandom = new Random();
    private int[][] LIVE_ARR;
    private int[][] NEXT_ARR;

    //Constructors
    JMsPingPong(int rows, int cols){
        LIVE_ARR = new int[rows][cols];
        NEXT_ARR = new int[rows][cols];
    }
    JMsPingPong(int rows, int cols, int value){
        LIVE_ARR = new int[rows][cols];
        NEXT_ARR = new int[rows][cols];
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                LIVE_ARR[row][col] = value;
                }
            }
    }
    JMsPingPong(int rows, int cols, int lower, int upper){
        LIVE_ARR = new int[rows][cols];
        NEXT_ARR = new int[rows][cols];
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                LIVE_ARR[row][col] = myRandom.nextInt(upper-lower) + lower;
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

    }
    int countNearestNeighbor(){
       return 0;
    }
    int countNextNearestNeighbor(){
        return 0;
    }
    void resetBoardBetween(int val1, int val2){

    }
    void resetBoardToBinary(){

    }
    void printLiveArray(){

    }


}
