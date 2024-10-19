package pkgRenderer;

import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11C.glClear;

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
    void polygonColor() {

    }

    @Override
    public void render() {
        while (!my_wm.isGlfwWindowClosed()) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);
            glColor4f(2, 32, 12, 1.0f);
            drawPolygons(3);
            my_wm.swapBuffers();
        }
        my_wm.destroyGLFWWindow();
    }

    @Override
    public void render(int radius) {

    }

    public void render(int delay, int row, int cols){

    }




}
