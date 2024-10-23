package pkgRenderer;

public class JMsPolygon {
    private float POLYGON_RADIUS = 0.0f;
    private int[] CENTER_COORDS = new int[2];
    private int NUMSIDES = 0;

    public JMsPolygon(float POLYGON_RADIUS, int[] CENTER_COORDS, int NUMSIDES){
        this.POLYGON_RADIUS = POLYGON_RADIUS;
        this.CENTER_COORDS = CENTER_COORDS;
        this.NUMSIDES = NUMSIDES;

    }

}
