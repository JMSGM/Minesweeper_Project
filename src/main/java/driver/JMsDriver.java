package driver;
import pkgUtils.JMsPingPong;

public class JMsDriver {
    public static void main(String[] my_args) {
        /*JMsRenderEngine my_re = new JMsPolygonGen();
        JMsWindowManager.get().initGLFWWindow(WIN_WIDTH, WIN_HEIGHT, "CSUS CSC133");
        my_re.initOpenGL(JMsWindowManager.get());
        final float RADIUS = 0.05f;
        final int FRAME_DELAY = 500, NUM_ROWS = 20, NUM_COLS = 20;
        my_re.render(RADIUS);
         */
        final int ROWS = 16;
        final int COLS = 16;
        System.out.println("Array bounded 0 - 9 :");
        JMsPingPong pp1To9 = new JMsPingPong(ROWS, COLS, 0, 9);
        pp1To9.printLiveArray();

        System.out.println("\n\nArray bounded 0 - 1 :");
        JMsPingPong pp = new JMsPingPong(ROWS, COLS, 0, 1);
        pp.printLiveArray();

        System.out.println("\n\nNearest Neighbor :");
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS; col++){
                int neighborCount = pp.countNearestNeighbor(row, col);
                pp.setNEXTVal(row, col, neighborCount);
            }
        }

        pp.swapArrays();
        pp.printLiveArray();
        pp.swapArrays();

        System.out.println("\n\nNext Nearest Neighbor :");
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS; col++){
                int neighborCount = pp.countNextNearestNeighbor(row, col);
                pp.setNEXTVal(row, col, neighborCount);
            }
        }
        pp.swapArrays();
        pp.printLiveArray();





    } // public static void main(String[] my_args)
} // public class csc133Driver(...)