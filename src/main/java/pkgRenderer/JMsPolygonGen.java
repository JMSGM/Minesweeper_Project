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
    private final int MAX_CIRCLES = 100;
    private static final float C_RADIUS = 0.1f;
    private final int TRIANGLES_PER_CIRCLE = 40;
    private final Random myRandom = new Random();
    private JMsWindowManager my_wm;
    private final int UPDATE_INTERVAL = 100; //MILLISECONDS
    private final float[][] rand_coords = new float[MAX_CIRCLES][2];
    private final float[][] rand_colors = new float[MAX_CIRCLES][3];
    @Override
    void generateVertices(int sides, float radius) {
        float delTheta = 2.0f*(float)Math.PI/ sides;
        float theta = 0.0f;

        for(int i = 0; i <= sides; ++i){
            float x = radius * (float)Math.cos(theta);
            float y = radius * (float)Math.sin(theta);
            glVertex3f(x, y, 0.0f);
            theta += delTheta;
        }
    }

    @Override
    void drawPolygons(int numSides) {
        glBegin(GL11.GL_TRIANGLE_FAN);
        generateVertices(numSides,1.0f);
        glEnd();
    }

    @Override
    void generateRandomPolygons(int numberOfPoly) {

    }


    @Override
    void polygonColor() {

    }


    @Override
    public void render(int radius) {

    }

    public void render(int delay, int row, int cols){

    }

    private void drawRandomPoly(float centerX, float centerY, int sides, float radius){
        float delTheta = 2.0f*(float)Math.PI/ sides;
        float theta = 0.0f;
        glBegin(GL_TRIANGLE_FAN);
        for(int i = 0; i <= sides; ++i){
            float x = radius * (float)Math.cos(theta);
            float y = radius * (float)Math.sin(theta);
            glVertex3f(x, y, 0.0f);
            theta += delTheta;
        }glEnd();
    }


}
