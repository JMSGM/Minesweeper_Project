package pkgRenderer;

import pkgUtils.JMsWindowManager;

import java.util.Random;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.glClear;

public abstract class JMsRenderEngine {
    JMsWindowManager my_wm;
    private final Random myRandom = new Random();
    public void initOpenGL(JMsWindowManager my_wm) {
        this.my_wm = my_wm;
        my_wm.updateContextToThis();

    }
    public void render(){
        while (!my_wm.isGlfwWindowClosed()) {
            glfwPollEvents();
            my_wm.swapBuffers();
        }
        my_wm.destroyGLFWWindow();
    }
    public abstract void render(float radius);
    public abstract void render(int delay, int row, int cols);
    abstract void drawPolygons(float cx, float cy, int sides, float radius);
    abstract void renderRandomPolygons(int polyAmount);
    protected void polygonColors(){
        float R = myRandom.nextFloat();
        float G = myRandom.nextFloat();
        float B = myRandom.nextFloat();
        float Opac = myRandom.nextFloat();
        glColor4f(R, G, B, Opac);

    }





}


