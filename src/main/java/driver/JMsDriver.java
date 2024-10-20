package driver;
import pkgRenderer.JMsPolygonGen;
import pkgRenderer.JMsRenderEngine;
import pkgUtils.JMsWindowManager;

import static driver.JMsSpot.*;

public class JMsDriver {
    public static void main(String[] my_args) {
        JMsRenderEngine my_re = new JMsPolygonGen();
        JMsWindowManager.get().initGLFWWindow(WIN_WIDTH, WIN_HEIGHT, "CSUS CSC133");
        my_re.initOpenGL(JMsWindowManager.get());

        final int FRAME_DELAY = 100, NUM_ROWS = 10, NUM_COLS = 10;
        my_re.render(FRAME_DELAY, NUM_ROWS, NUM_COLS);
    } // public static void main(String[] my_args)
} // public class csc133Driver(...)