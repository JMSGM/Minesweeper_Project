package pkgRenderer;

import pkgUtils.JMsPingPong;

public class JMsGameOfLife {

    public JMsGameOfLife(int defaultRows, int defaultCols) {
    }

    public void createPingPong(int rows, int cols) {
        System.out.println("\n\nArray bounded 0 - 1 :");
        JMsPingPong pp = new JMsPingPong(rows, cols, 0, 1);
        pp.printLiveArray();

        System.out.println("\n\nNearest Neighbor :");
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int neighborCount = pp.countNearestNeighbor(row, col);
                pp.setNEXTVal(row, col, neighborCount);
            }
        }

        pp.swapArrays();
        pp.printLiveArray();
        pp.swapArrays();

        System.out.println("\n\nNext Nearest Neighbor :");
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int neighborCount = pp.countNextNearestNeighbor(row, col);
                pp.setNEXTVal(row, col, neighborCount);
            }
        }
        pp.swapArrays();
        pp.printLiveArray();
    }

}
