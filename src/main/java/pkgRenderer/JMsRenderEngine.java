package pkgRenderer;

import pkgUtils.JMsKeyListener;
import pkgUtils.JMsPingPong;
import pkgUtils.JMsWindowManager;

import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.glClear;

public abstract class JMsRenderEngine {
    //Fields
    private JMsKeyListener input = JMsKeyListener.get();
    private int DEFAULT_DELAY = 1;
    private int DEFAULT_ROWS = 100;
    private int DEFAULT_COLS = 100;
    private int DEFAULT_SIDES = 4;
    private int DEFAULT_POLYGON_AMOUNT = 20;
    private int[][] POLYGONS = new int [DEFAULT_ROWS][DEFAULT_COLS];
    private JMsWindowManager my_wm;
    private final Random myRandom = new Random();
    protected JMsPingPong pp;
    //Methods
    abstract void renderPolygons(float cx, float cy, int sides, float radius);
    abstract void renderRandomPolygons(int polyAmount);
    protected abstract void generatePolygonArray(int row, int cols, int sides);
    protected abstract void generatePolygonArray(float radius, int sides);
    protected abstract void generatePolygonArray(int row, int col);
    protected abstract void generateGameOfLife(int rows, int cols);
    protected  abstract void renderRandomObject(JMsPolygon polygonObj);
    protected abstract JMsPolygon createRandomObject();
    public void initOpenGL(JMsWindowManager my_wm) {
        this.my_wm = my_wm;

        my_wm.updateContextToThis();


    }
    //Render Methods
    public void render() {
        createPingPong(); //initializes the ping pong array

        while (!my_wm.isGlfwWindowClosed()) { //check keyboard input

            handleInput();

            glfwPollEvents();

            updateGeneration(); //checks every cell and updates next generation

            generateGameOfLife(DEFAULT_ROWS, DEFAULT_COLS); //renders each cell, black if 0, green if 1

            pp.swapArrays(); // swaps new generation with live array, cont. loop...

            my_wm.swapBuffers();

            glfwPollEvents();

                    if (DEFAULT_DELAY > 0) {
                    try {
                        Thread.sleep(DEFAULT_DELAY);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }


            }my_wm.destroyGLFWWindow();

        }

    public void render(float radius) {
        while (!my_wm.isGlfwWindowClosed()) {

            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);

            for (int sides = 3; sides <= DEFAULT_SIDES; sides++) {
                // Check if the window should close during each frame
                if (my_wm.isGlfwWindowClosed()) {
                    break;
                }

                glClear(GL_COLOR_BUFFER_BIT);

                float R = myRandom.nextFloat();
                float G = myRandom.nextFloat();
                float B = myRandom.nextFloat();
                float OPAC = myRandom.nextFloat();
                glColor4f(R, G, B, OPAC);

                generatePolygonArray(radius, sides);

                my_wm.swapBuffers();

                glfwPollEvents();

                if (DEFAULT_DELAY > 0) {
                    try {
                        Thread.sleep(DEFAULT_DELAY);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        my_wm.destroyGLFWWindow();
    }
    public void render(int delay, int rows, int cols){
        while (!my_wm.isGlfwWindowClosed()) {

            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);

            for (int sides = 3; sides <= DEFAULT_SIDES; sides++) {
                // Check if the window should close during each frame
                if (my_wm.isGlfwWindowClosed()) {
                    break;
                }

                glClear(GL_COLOR_BUFFER_BIT);

                float R = myRandom.nextFloat();
                float G = myRandom.nextFloat();
                float B = myRandom.nextFloat();
                float OPAC = myRandom.nextFloat();
                glColor4f(R, G, B, OPAC);

                generatePolygonArray(rows, cols, DEFAULT_SIDES);

                my_wm.swapBuffers();

                glfwPollEvents();


                if (delay > 0) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        my_wm.destroyGLFWWindow();
    }
    //Game of Life
    protected void createPingPong(){
        System.out.println("\n\nKeyBLogs:");
        this.pp = new JMsPingPong(DEFAULT_ROWS, DEFAULT_COLS, 0, 1);

    }
    protected void updateGeneration(){
        for (int row = 0; row < DEFAULT_ROWS; row++) {
            for (int col = 0; col < DEFAULT_COLS; col++) {
                int neighborCount = pp.countNextNearestNeighbor(row, col);
                int currentstate = pp.getLIVEArray(row, col);
                if(currentstate == 1){//if state is alive
                    if(neighborCount < 2){ //rule 1 loneliness
                        pp.setNEXTVal(row, col, 0);
                    }
                    else if(neighborCount == 2 || neighborCount == 3){ //rule 2 retain perfect condition
                        pp.setNEXTVal(row, col, 1);
                    }
                    else if (neighborCount > 3) { // rule 3 overpopulation
                        pp.setNEXTVal(row, col, 0);
                    }
                }else{ //cell is dead
                    if(neighborCount == 3){ //rule 4 dead cell with three neighbor
                        pp.setNEXTVal(row, col,1);
                    } else {
                        pp.setNEXTVal(row, col, 0);
                    }
                }
            }
        }
    }
    //input handling
    protected void handleInput(){
        boolean KeepRunning = false;

        if (input.isKeyPressed(GLFW_KEY_I) ) { //if I is pressed

            DEFAULT_DELAY += 500;
            System.out.println("+++ Frame delay is now: " + DEFAULT_DELAY + " ms!");
            KeepRunning = true;
            input.resetKeypressEvent(GLFW_KEY_I);
            input.resetKeypressEvent(GLFW_KEY_LEFT_SHIFT);
        } else if(input.isKeyPressed(GLFW_KEY_D)){ // if D is pressed
            KeepRunning = false;
            DEFAULT_DELAY -= 500;
            if(DEFAULT_DELAY < 0){
                DEFAULT_DELAY = 0;
                System.out.println("--- Frame delay is now: " + DEFAULT_DELAY + " ms!");
            }else
                System.out.println("--- Frame delay is now: " + DEFAULT_DELAY + " ms!");
            KeepRunning = true;
            input.resetKeypressEvent(GLFW_KEY_D);
            input.resetKeypressEvent(GLFW_KEY_LEFT_SHIFT);
        }else if(input.isKeyPressed(GLFW_KEY_R)){
            pp.resetBoardToBinary();
            System.out.println("Board Reset!");
            input.resetKeypressEvent(GLFW_KEY_R);
            input.resetKeypressEvent(GLFW_KEY_LEFT_SHIFT);
        }

    }
    //setter and getters for default values
    protected void setDEFAULT_ROWS(int rows){
        this.DEFAULT_ROWS = rows;
    }
    protected void setDEFAULT_COLS(int cols){
        this.DEFAULT_COLS = cols;
    }
    protected void setDEFAULT_DELAY(int delay){
        this.DEFAULT_DELAY = delay;
    }
    protected void setDEFAULT_POLYGON_AMOUNT(int polyAmount){
        this.DEFAULT_POLYGON_AMOUNT = polyAmount;
    }
    protected void setDEFAULT_SIDES(int sides){
        this.DEFAULT_SIDES = sides;
    }
    protected int[][] getPOLYGONS(){
        return this.POLYGONS;
    }


}


