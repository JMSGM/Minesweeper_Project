package pkgRenderer;

import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_TRIANGLE_FAN;

public class JMsPolygonGen extends JMsRenderEngine{

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
    void generatepolygonArrays(float radius) {

    }

    @Override
    void generatepolygonArrays(int rows, int cols) {

    }

    @Override
    void generatepolygonArrays(int rows, int cols, int numofSides) {

    }


    @Override
    void polygonColor() {

    }



}
