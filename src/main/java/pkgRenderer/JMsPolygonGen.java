package pkgRenderer;

import pkgUtils.JMsWindowManager;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_TRIANGLE_FAN;

public class JMsPolygonGen extends JMsRenderEngine{
    //Field
    private final int DEFAULT_SIDES = 25;
    private  final float RADIUS = 0.05f;
    private final int Z0 = 0;
    private final int MAX_CIRCLES = 100;
    private final int TRIANGLES_PER_CIRCLE = 40;
    private final Random myRandom = new Random();
    private JMsWindowManager MY_WM;
    private final int UPDATE_INTERVAL = 200; //MILLISECONDS
    private final float[][] RAND_COORD = new float[MAX_CIRCLES][2];
    private final float[][] RAND_COLORS = new float[MAX_CIRCLES][3];
    private JMsPolygon[][] polygons;

    //Creates and Draws: //TODO convert centers into array
    @Override
    void renderPolygons(float cx, float cy, int sides, float radius) {
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

    //Random Polygons Methods:
    @Override
    void renderRandomPolygons(int polyAmount) {
        updateRandValues();
        for(int i = 0; i < polyAmount; i++){
            glColor4f(RAND_COLORS[i][0], RAND_COLORS[i][1], RAND_COLORS[i][2], 1.0f);
            vertForRandomPoly(RAND_COORD[i][0], RAND_COORD[i][1], TRIANGLES_PER_CIRCLE, RADIUS);
        }
    }
    void renderRandomPolygons(int polyAmount, int sides) {
        updateRandValues();
        for(int i = 0; i < polyAmount; i++){
            glColor4f(RAND_COLORS[i][0], RAND_COLORS[i][1], RAND_COLORS[i][2], 1.0f);
            vertForRandomPoly(RAND_COORD[i][0], RAND_COORD[i][1], sides, RADIUS);
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
            float maxX = 1.0f - RADIUS;
            float maxY = 1.0f - RADIUS;
            RAND_COORD[i][0] = myRandom.nextFloat() * 2.0f * maxX - maxX;
            RAND_COORD[i][1] = myRandom.nextFloat() * 2.0f * maxY - maxY;
        }
    }
    //Generate Polygon Array Methods:
    /*Calculate horizontal/vertical space between polygons then use to offset center
    and avoid overlap, calculate radius based on min space for apropriate polygon size,
    for loop starts with [-1,-1] in NDC space and generates center left to right from bottom to up*/
    @Override
    protected void generatePolygonArray(int rows, int cols, int sides){
        float xSpace = 2.0f / cols;
        float ySpace = 2.0f / rows;
        float radius = Math.min(xSpace, ySpace)/2.0f*0.9f;

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                float cx = -1.0f + xSpace * (col + 0.5f);
                float cy = -1.0f + ySpace * (row + 0.5f);

                renderPolygons(cx, cy, sides, radius);
            }
        }
    }

    @Override
     protected void generatePolygonArray(float radius, int sides){
        //Divide ndc space with radius i.e only 20 rows and cols can fit with polygons size 0.05
        int cols = (int)(2.0f / (2.0f * radius));
        int rows = (int)(2.0f / (2.0f * radius));

        float xSpace = 2.0f / cols;
        float ySpace = 2.0f / rows;

        radius = radius * 0.9f;

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                float cx = -1.0f + xSpace * (col + 0.5f);
                float cy = -1.0f + ySpace * (row + 0.5f);

                renderPolygons(cx, cy, sides, radius);
            }
        }
    }
    @Override
     protected void generatePolygonArray(int rows, int cols){
        float xSpace = 2.0f / cols;
        float ySpace = 2.0f / rows;
        float radius = Math.min(xSpace, ySpace)/2.0f*0.9f;

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                float cx = -1.0f + xSpace * (col + 0.05f);
                float cy = -1.0f + ySpace * (row + 0.05f);

                renderPolygons(cx, cy, DEFAULT_SIDES, radius);
            }
        }
    }
    private void generatePolygonArray(int rows, int cols, float radius){
        float xSpace = 2.0f / cols;
        float ySpace = 2.0f / rows;

        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                float cx = -1.0f + xSpace * (col + 0.5f);
                float cy = -1.0f + ySpace * (row + 0.5f);

                renderPolygons(cx, cy, DEFAULT_SIDES, radius);
            }
        }
    }

    //Generate Random Object
    @Override
     protected JMsPolygon createRandomObject(){
        int objSides = myRandom.nextInt(10) + 3;
        float objRadius = myRandom.nextFloat() * 0.5f + 0.1f;

        float[] objCenter = new float[2];
        float maxX = 1.0f - objRadius;
        float maxY = 1.0f - objRadius;
        objCenter[0] = myRandom.nextFloat() * 2.0f * maxX - maxX;
        objCenter[1] = myRandom.nextFloat() * 2.0f * maxY - maxY;

        JMsPolygon PolyObj = new JMsPolygon(objRadius, objCenter, objSides);
        return PolyObj;
    }
    @Override
    protected void renderRandomObject(JMsPolygon polygonObj){
        renderPolygons(polygonObj.CENTER_COORDS[0], polygonObj.CENTER_COORDS[1], polygonObj.NUMSIDES, polygonObj.POLYGON_RADIUS);
    }


}
