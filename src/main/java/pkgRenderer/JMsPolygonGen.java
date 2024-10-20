package pkgRenderer;

import org.lwjgl.opengl.GL11;
import pkgUtils.JMsWindowManager;

import java.util.Random;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11C.glClear;

public class JMsPolygonGen extends JMsRenderEngine{
    //Field
    private final int NUM_3D_COORDS = 0;
    private final int z0 = 0;
    private final int MAX_CIRCLES = 100;
    private static final float C_RADIUS = 0.05f;
    private final int TRIANGLES_PER_CIRCLE = 40;
    private final Random myRandom = new Random();
    private JMsWindowManager my_wm;
    private final int UPDATE_INTERVAL = 100; //MILLISECONDS
    private final float[][] rand_coords = new float[MAX_CIRCLES][2];
    private final float[][] rand_colors = new float[MAX_CIRCLES][3];

    @Override
    public void initOpenGL(JMsWindowManager wm) {
        super.initOpenGL(wm);
        this.my_wm = wm;
    }
    @Override
    public void render(int radius) {

    }

    @Override
    public void render(int delay, int row, int cols) {
        while (!my_wm.isGlfwWindowClosed()) {

            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);

            generatePolygonArray(row, cols);

            my_wm.swapBuffers();
            //DELAY
            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        my_wm.destroyGLFWWindow();
    }
    @Override
    void generateVertices(int sides, float radius) {
        float delTheta = 2.0f*(float)Math.PI/ sides;
        float theta = 0.0f;
        glBegin(GL_TRIANGLE_FAN);
        for(int i = 0; i <= sides; ++i){
            float x = radius * (float)Math.cos(theta);
            float y = radius * (float)Math.sin(theta);
            glVertex3f(x, y, z0);
            theta += delTheta;
        }glEnd();
    }

    @Override
    void drawPolygons(float cx, float cy, int sides, float radius) {
        float delTheta = 2.0f*(float)Math.PI/ sides;
        float theta = 0.0f;
        glBegin(GL_TRIANGLE_FAN);
        glVertex3f(cx, cy, z0);
        for(int i = 0; i <= sides; ++i){
            float x = cx + radius * (float)Math.cos(theta);
            float y = cy + radius * (float)Math.sin(theta);
            glVertex3f(x, y, z0);
            theta += delTheta;
        }glEnd();
    }

    @Override
    void polygonColor() {
        float r = myRandom.nextFloat();
        float g = myRandom.nextFloat();
        float b = myRandom.nextFloat();
        float opac = 0.0f;
        glColor4f(r, g, b, opac);
    }

    private void generatePolygonArray(int rows, int cols){
        float xSpace = 2.0f / cols;
        float ySpace = 2.0f / rows;
        float radius = Math.min(xSpace, ySpace)/2.0f*0.9f;

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
             float cx = -1.0f + xSpace * (col + 0.5f);
             float cy = -1.0f + ySpace * (row + 0.5f);
             polygonColor();
             drawPolygons(cx, cy, 40, radius);
            }
        }
    }

    private void generatePolygonArray(int rows, int cols, int sides){
        float xSpace = 2.0f / cols;
        float ySpace = 2.0f / rows;
        float radius = Math.min(xSpace, ySpace)/2.0f*0.9f;

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                float cx = -1.0f + xSpace * (col + 0.5f);
                float cy = -1.0f + ySpace * (row + 0.5f);
                polygonColor();
                drawPolygons(cx, cy, sides, radius);
            }
        }
    }

    @Override
    void renderRandomPolygons(int polyAmount) {
        updateRandValues();
        for(int i = 0; i < polyAmount; i++){
            glColor4f(rand_colors[i][0], rand_colors[i][1], rand_colors[i][2], 1.0f);
            vertForRandomPoly(rand_coords[i][0], rand_coords[i][1], TRIANGLES_PER_CIRCLE, C_RADIUS);
        }
    }
    private void vertForRandomPoly(float centerX, float centerY, int sides, float radius){
        float delTheta = 2.0f*(float)Math.PI/ sides;
        float theta = 0.0f;
        glBegin(GL_TRIANGLE_FAN);
        for(int i = 0; i <= sides; ++i){
            float x = centerX + radius * (float)Math.cos(theta);
            float y = centerY + radius * (float)Math.sin(theta);
            glVertex3f(x, y, z0);
            theta += delTheta;
        }glEnd();
    }
    private void updateRandValues(){
        for(int i = 0; i < MAX_CIRCLES; i++) {
            rand_colors[i][0] = myRandom.nextFloat();;
            rand_colors[i][1] = myRandom.nextFloat();;
            rand_colors[i][2] = myRandom.nextFloat();;
            float maxX = 1.0f - C_RADIUS;
            float maxY = 1.0f - C_RADIUS;
            rand_coords[i][0] = myRandom.nextFloat() * 2.0f * maxX - maxX;
            rand_coords[i][1] = myRandom.nextFloat() * 2.0f * maxY - maxY;
        }
    }


}
