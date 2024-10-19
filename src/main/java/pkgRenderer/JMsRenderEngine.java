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
    public abstract void render();
    public abstract void render(int radius);
    public abstract void render(int delay, int row, int cols);
    abstract void generateVertices(int sides, float radius);
    abstract void drawPolygons(int numSides);
    abstract void generateRandomPolygons(int numberOfPoly);
    abstract void polygonColor();





}


