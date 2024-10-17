package pkgRenderer;

import pkgUtils.JMsWindowManager;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.glClear;

public abstract class JMsRenderEngine {
    JMsWindowManager my_wm;
    public void initOpenGL(JMsWindowManager my_wm) {
        this.my_wm = my_wm;
        my_wm.updateContextToThis();

    }

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
    abstract void generateVertices(int sides, float radius);
    abstract void drawPolygons(int numSides);
    abstract void generateRandomPolygons(int numberOfPoly);
    abstract void generatepolygonArrays(float radius);
    abstract void generatepolygonArrays(int rows, int cols);
    abstract void generatepolygonArrays(int rows, int cols, int numofSides);
    abstract void polygonColor();





}


