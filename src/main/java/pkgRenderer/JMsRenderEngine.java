package pkgRenderer;

import pkgUtils.JMsPingPong;
import pkgUtils.JMsWindowManager;

import java.util.Random;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.glClear;

public abstract class JMsRenderEngine {
    //Fields
    private int DEFAULT_DELAY = 500;
    private int DEFAULT_ROWS = 30;
    private int DEFAULT_COLS = 30;
    private int DEFAULT_SIDES = 4;
    private int DEFAULT_POLYGON_AMOUNT = 20;
    JMsPingPong pp;
    JMsWindowManager my_wm;
    private final Random myRandom = new Random();
    //Methods
    abstract void renderPolygons(float cx, float cy, int sides, float radius);
    abstract void renderRandomPolygons(int polyAmount);
    protected abstract void generatePolygonArray(int row, int cols, int sides);
    protected abstract void generatePolygonArray(float radius, int sides);
    protected abstract void generatePolygonArray(int row, int col);
    protected  abstract void renderRandomObject(JMsPolygon polygonObj);
    protected abstract JMsPolygon createRandomObject();
    public void initOpenGL(JMsWindowManager my_wm) {
        this.my_wm = my_wm;
        my_wm.updateContextToThis();
    }
    //Render Methods
    public void render() {

        System.out.println("\n\nArray bounded 0 - 1 :");
        JMsPingPong pp = new JMsPingPong(DEFAULT_ROWS, DEFAULT_COLS, 0, 1);
        pp.printLiveArray();

        System.out.println("\n\nNearest Neighbor :");
        for(int row = 0; row < DEFAULT_ROWS; row++){
            for(int col = 0; col < DEFAULT_COLS; col++){
                int neighborCount = pp.countNearestNeighbor(row, col);
                pp.setNEXTVal(row, col, neighborCount);
            }
        }

        pp.swapArrays();
        pp.printLiveArray();
        pp.swapArrays();

        System.out.println("\n\nNext Nearest Neighbor :");
        for(int row = 0; row < DEFAULT_ROWS; row++){
            for(int col = 0; col < DEFAULT_COLS; col++){
                int neighborCount = pp.countNextNearestNeighbor(row, col);
                pp.setNEXTVal(row, col, neighborCount);
            }
        }
        pp.swapArrays();
        pp.printLiveArray();

        while (!my_wm.isGlfwWindowClosed()) {

            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);
            boolean isBlack = false;

                glClear(GL_COLOR_BUFFER_BIT);
                if(isBlack) {
                    float R = 0;
                    float G = 0;
                    float B = 0;
                    float OPAC = 1.0f;
                    glColor4f(R, G, B, OPAC);
                }else{
                    float R = 0;
                    float G = 255;
                    float B = 0;
                    float OPAC = myRandom.nextFloat();
                    glColor4f(R, G, B, OPAC);
                }
                generatePolygonArray(DEFAULT_ROWS, DEFAULT_COLS, DEFAULT_SIDES);

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


}


