package driver;
import pkgRenderer.JMsRenderEngine;
import pkgUtils.JMsWindowManager;
public class JMsDriver {
    public static void main(String[] my_args) {
        JMsRenderEngine my_re = new JMsRenderEngine();
        JMsWindowManager.get().initGLFWWindow(WIN_WIDTH, WIN_HEIGHT, "CSUS CSC133");
        my_re.initOpenGL(JMsWindowManager.get());
        my_re.render();
    } // public static void main(String[] my_args)
} // public class csc133Driver(...)