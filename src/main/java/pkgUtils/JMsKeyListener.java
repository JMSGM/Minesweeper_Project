package pkgUtils;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class JMsKeyListener {


    public static void keyCallback(long my_window, int key, int scancode, int action, int modifier_key) {
        if (action == GLFW_PRESS) {
            get().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE){
            get().keyPressed[key] = false;
        }
    }
    public static boolean isKeyPressed(int keyCode){
        if (keyCode < get().keyPressed.length) {
            return get().keyPressed[keyCode];
        } else {
            return false;
        }
    }
    // Call this function to receive one event for repeated presses:
    public static void resetKeypressEvent(int keyCode) {
        if (my_instance != null && keyCode < get().keyPressed.length) {
            my_instance.keyPressed[keyCode] = false;
        }
    }

}
