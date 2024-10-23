package pkgRenderer;

import pkgUtils.JMsWindowManager;

import java.util.Random;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11C.glClear;

public class JMsPolygonGen extends JMsRenderEngine{
    //Field
    private final int DEFAULT_SIDES = 20;
    private  final float C_RADIUS = 0.05f;
    private final int Z0 = 0;
    private final int MAX_CIRCLES = 100;
    private final int TRIANGLES_PER_CIRCLE = 40;
    private final Random myRandom = new Random();
    private JMsWindowManager MY_WM;
    private final int UPDATE_INTERVAL = 200; //MILLISECONDS
    private final float[][] RAND_COORD = new float[MAX_CIRCLES][2];
    private final float[][] RAND_COLORS = new float[MAX_CIRCLES][3];

    @Override //Creates and Draws
    void drawPolygons(float cx, float cy, int sides, float radius) {
        float delTheta = 2.0f*(float)Math.PI/ sides;
        float theta = 0.0f;
        glBegin(GL_TRIANGLE_FAN);
        glVertex3f(cx, cy, Z0);
        for(int i = 0; i <= sides; ++i){
            float x = cx + radius * (float)Math.cos(theta);
            float y = cy + radius * (float)Math.sin(theta);
            glVertex3f(x, y, Z0);
            theta += delTheta;
        }glEnd();
    }

    //Random Polygons Methods
    @Override
    void renderRandomPolygons(int polyAmount) {
        updateRandValues();
        for(int i = 0; i < polyAmount; i++){
            glColor4f(RAND_COLORS[i][0], RAND_COLORS[i][1], RAND_COLORS[i][2], 1.0f);
            vertForRandomPoly(RAND_COORD[i][0], RAND_COORD[i][1], TRIANGLES_PER_CIRCLE, C_RADIUS);
        }
    }
    private void vertForRandomPoly(float centerX, float centerY, int sides, float radius){
        float delTheta = 2.0f*(float)Math.PI/ sides;
        float theta = 0.0f;
        glBegin(GL_TRIANGLE_FAN);
        for(int i = 0; i <= sides; ++i){
            float x = centerX + radius * (float)Math.cos(theta);
            float y = centerY + radius * (float)Math.sin(theta);
            glVertex3f(x, y, Z0);
            theta += delTheta;
        }glEnd();
    }
    private void updateRandValues(){
        for(int i = 0; i < MAX_CIRCLES; i++) {
            RAND_COLORS[i][0] = myRandom.nextFloat();;
            RAND_COLORS[i][1] = myRandom.nextFloat();;
            RAND_COLORS[i][2] = myRandom.nextFloat();;
            float maxX = 1.0f - C_RADIUS;
            float maxY = 1.0f - C_RADIUS;
            RAND_COORD[i][0] = myRandom.nextFloat() * 2.0f * maxX - maxX;
            RAND_COORD[i][1] = myRandom.nextFloat() * 2.0f * maxY - maxY;
        }
    }
    //Generate Polygon Array Methods
    @Override
    protected void generatePolygonArray(int rows, int cols, int sides){
        float xSpace = 2.0f / cols;
        float ySpace = 2.0f / rows;
        float radius = Math.min(xSpace, ySpace)/2.0f*0.9f;

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                float cx = -1.0f + xSpace * (col + 0.5f);
                float cy = -1.0f + ySpace * (row + 0.5f);

                drawPolygons(cx, cy, sides, radius);
            }
        }
    }
    @Override
     protected void generatePolygonArray(float radius, int sides){

        int cols = (int)(2.0f / (2.0f * radius));
        int rows = (int)(2.0f / (2.0f * radius));

        float xSpace = 2.0f / cols;
        float ySpace = 2.0f / rows;

        float NOLRadius = radius * 0.9f;

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                float cx = -1.0f + xSpace * (col + 0.5f);
                float cy = -1.0f + ySpace * (row + 0.5f);

                drawPolygons(cx, cy, sides, NOLRadius);
            }
        }
    }

    private void generatePolygonArray(int rows, int cols){
        float xSpace = 2.0f / cols;
        float ySpace = 2.0f / rows;
        float radius = Math.min(xSpace, ySpace)/2.0f*0.9f;

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                float cx = -1.0f + xSpace * (col + 0.5f);
                float cy = -1.0f + ySpace * (row + 0.5f);

                drawPolygons(cx, cy, DEFAULT_SIDES, radius);
            }
        }
    }



}
