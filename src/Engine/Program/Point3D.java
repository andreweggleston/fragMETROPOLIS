package Engine.Program;

/**
 * Created by george_jiang on 5/17/16.
 */
public class Point3D
{
    int x,y,z;

    public Point3D(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int x()
    {
        return x;
    }

    public int y()
    {
        return y;
    }

    public int z()
    {
        return z;
    }


    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public void setZ(int z)
    {
        this.z = z;
    }
}
