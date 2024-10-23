package pkgRenderer;

import pkgUtils.JMsWindowManager;

import java.util.Random;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.glClear;

public abstract class JMsRenderEngine {
    private final int DEFAULT_DELAY = 500;
    private final int DEFAULT_ROWS = 30;
    private final int DEFAULT_COLS = 30;
    private final int DEFAULT_SIDES = 20;
    JMsWindowManager my_wm;
    private final Random myRandom = new Random();
    public void initOpenGL(JMsWindowManager my_wm) {
        this.my_wm = my_wm;
        my_wm.updateContextToThis();
    }
    public void render(){
        while (!my_wm.isGlfwWindowClosed()) {

            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);

            for (int sides = 3; sides <= 20; sides++) {
                // Check if the window should close during each frame
                if (my_wm.isGlfwWindowClosed()) {
                    break;
                }

                glClear(GL_COLOR_BUFFER_BIT);

                polygonColors();
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
            }
        }
        my_wm.destroyGLFWWindow();
    }
    public void render(float radius) {
        while (!my_wm.isGlfwWindowClosed()) {

            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);

            for (int sides = 3; sides <= 20; sides++) {
                // Check if the window should close during each frame
                if (my_wm.isGlfwWindowClosed()) {
                    break;
                }

                glClear(GL_COLOR_BUFFER_BIT);

                polygonColors();
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

            for (int sides = 3; sides <= 20; sides++) {
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

                generatePolygonArray(rows, cols, sides);

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
    abstract void drawPolygons(float cx, float cy, int sides, float radius);
    abstract void renderRandomPolygons(int polyAmount);
    protected abstract void generatePolygonArray(int row, int cols, int sides);
    protected abstract void generatePolygonArray(float radius, int sides);
    protected void polygonColors(){
        float R = myRandom.nextFloat();
        float G = myRandom.nextFloat();
        float B = myRandom.nextFloat();
        float OPAC = myRandom.nextFloat();
        glColor4f(R, G, B, OPAC);

    }






}


