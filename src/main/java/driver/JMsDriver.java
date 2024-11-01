package driver;
import pkgRenderer.JMsPolygonGen;
import pkgRenderer.JMsRenderEngine;
import pkgUtils.JMsWindowManager;
import pkgUtils.JMsPingPong;
import static driver.JMsSpot.*;

public class JMsDriver {
    public static void main(String[] my_args) {
        /*JMsRenderEngine my_re = new JMsPolygonGen();
        JMsWindowManager.get().initGLFWWindow(WIN_WIDTH, WIN_HEIGHT, "CSUS CSC133");
        my_re.initOpenGL(JMsWindowManager.get());
        final float RADIUS = 0.05f;
        final int FRAME_DELAY = 500, NUM_ROWS = 20, NUM_COLS = 20;
        my_re.render(RADIUS);
         */

        System.out.println("Array bounded 0 - 9 :");
        JMsPingPong pp1To9 = new JMsPingPong(16, 16, 0, 9);
        pp1To9.printLiveArray();

        System.out.println("\n\nArray bounded 0 - 1 :");
        JMsPingPong pp = new JMsPingPong(16, 16, 0, 1);
        pp.printLiveArray();








    } // public static void main(String[] my_args)
} // public class csc133Driver(...)