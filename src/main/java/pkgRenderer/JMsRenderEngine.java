package pkgRenderer;

import pkgUtils.JMsWindowManager;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11C.glClear;

public class JMsRenderEngine {
    JMsWindowManager my_wm;
    public void initOpenGL(JMsWindowManager my_wm) {
        this.my_wm = my_wm;
        my_wm.updateContextToThis();

    }

    public void render() {
        while (!my_wm.isGlfwWindowClosed()) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);

            my_wm.swapBuffers();
        }
        my_wm.destroyGLFWWindow();
    }
}
