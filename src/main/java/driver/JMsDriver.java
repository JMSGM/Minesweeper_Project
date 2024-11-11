package driver;
import pkgRenderer.JMsPolygonGen;
import pkgRenderer.JMsRenderEngine;
import pkgUtils.JMsPingPong;
import pkgUtils.JMsWindowManager;


import static driver.JMsSpot.WIN_HEIGHT;
import static driver.JMsSpot.WIN_WIDTH;

public class JMsDriver {
    public static void main(String[] my_args) {
        JMsRenderEngine my_re = new JMsPolygonGen();
        JMsWindowManager.get().initGLFWWindow(WIN_WIDTH, WIN_HEIGHT, "CSUS CSC133");
        my_re.initOpenGL(JMsWindowManager.get());
        final float RADIUS = 0.05f;
        final int FRAME_DELAY = 500, NUM_ROWS = 100, NUM_COLS = 100;
        my_re.render();

    } // public static void main(String[] my_args)
} // public class csc133Driver(...)