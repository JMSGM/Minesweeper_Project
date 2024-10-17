package pkgUtils;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class JMsWindowManager {
    //Field
    int Win_Width = 0;
    int Win_Height = 0;
    private static long window = 0;
    private static JMsWindowManager singleWindow = null;
    //Private Constructor
    private JMsWindowManager(){
    }
    //Singleton class to access class
    public static JMsWindowManager get() {
        if(singleWindow == null){
            singleWindow = new JMsWindowManager();
        }
        return singleWindow;
    }
    //Initializes Window
    public long initGLFWWindow(int win_width, int win_height, String title){
        Win_Width = win_width;
        Win_Height = win_height;
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            throw new IllegalStateException("Could not initialize GLFW");
        }
        //Default
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);
        //Create window Singleton
        if (window == 0) {
            window = glfwCreateWindow(win_width, win_height, title, NULL, NULL);
            if (window == NULL) {
                throw new RuntimeException("Failed to create the GLFW window");
            }
        }
        return window;
    }
    //Destroy window
    public void destroyGLFWWindow(){
        glfwDestroyWindow(window);
    }
    //swapBuffers
    public void swapBuffers(){
        glfwSwapBuffers(window);
    }
    //isWindowClosed
    public boolean isGlfwWindowClosed(){
        return glfwWindowShouldClose(window);
    }
    //updateContext
    public void updateContextToThis(){
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        float CC_RED = 0.0f, CC_GREEN = 0.0f, CC_BLUE = 1.0f, CC_ALPHA = 1.0f;
        glClearColor(CC_RED, CC_GREEN, CC_BLUE, CC_ALPHA);
    }
    //getCurrentWindowSize TODO

    //getWindowSize TODO
    public int[] getWindowSize(){
        int winSize[] = new int[2];
        winSize[0] = Win_Width;
        winSize[1] = Win_Height;
        return winSize;
    }
}


