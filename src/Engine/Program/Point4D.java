package Engine.Program;

/**
 * Created by andrew_eggleston on 6/13/16.
 */
public class Point4D extends Point3D {

    int x, y, z, dir;
    public Point4D(int x, int y, int z, int dir){
        super(x, y, z);
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Point4D(Point3D point3D, int dir){
        super(point3D.x, point3D.y, point3D.z);
        this.dir = dir;
        this.x = point3D.x;
        this.y = point3D.y;
        this.z = point3D.z;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setZ(int z) {
        this.z = z;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
}
