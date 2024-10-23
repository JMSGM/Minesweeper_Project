package pkgRenderer;

import pkgUtils.JMsWindowManager;

import java.util.Random;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.glClear;

public abstract class JMsRenderEngine {
    private final int DEFAULT_DELAY = 200;
    private final int DEFAULT_ROWS = 30;
    private final int DEFAULT_COLS = 30;
    private final int DEFAULT_SIDES = 20;
    JMsWindowManager my_wm;
    private final Random myRandom = new Random();
    public void initOpenGL(JMsWindowManager my_wm) {
        this.my_wm = my_wm;
        my_wm.updateContextToThis();
    }
    public void render() {
        // Generate the random polygon once, before entering the rendering loop
        JMsPolygon randomPolygon = createRandomObject();

        while (!my_wm.isGlfwWindowClosed()) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);

            // Render the previously created random polygon
            drawRandomObject(randomPolygon);

            my_wm.swapBuffers();
        }

        my_wm.destroyGLFWWindow();
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
    protected abstract void generatePolygonArray(int row, int col);
    protected  abstract void drawRandomObject(JMsPolygon polygonObj);
    protected abstract JMsPolygon createRandomObject();





}


