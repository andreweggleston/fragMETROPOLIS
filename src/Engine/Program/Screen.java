package Engine.Program;

/**
 * Created by george_jiang on 5/17/16.
 */
public class Screen
{
    private final int x = 20;
    private final int y = 10;
    private final int z = 30;

    private int[][][] Box;
//    private int testing = 1;

    public Screen()
    {
        Box = new int[x][y][z];

    }

    private void clearBox()
    {
        Box = new int[x][y][z];
    }



    public int getSpot(int x, int y, int z)
    {
        //checks if spot is out of bounds
//        if(x>=Box.length||x<0||
//           y>=Box[0].length||y<=-1||
//           z>=Box[0][0].length||z<=-1) {
//
//            return 1;
//            //This method treats any out of bounds as occupied by a pipe. hopps pls give me an A
//        }
        if(x >= Box.length){
            System.out.println("x is too big");
            return 1;
        }
        if(x<0)
        {
            System.out.println("x is too small");
            return 1;
        }
        
        if(y>=Box[0].length){
            System.out.println("y is too big");
            return 1;
        }
        if(y<0){
            System.out.println("y is too small");
            return 1;
        }
        if(z>=Box[0][0].length){
            System.out.println("z is too big");
            return 1;
        }
        if(z<0){
            System.out.println("z is too small");
            return 1;
        }
        System.out.println("it's not taken. x: "+ x + " y: " + y + " z: " + z);
        return Box[x][y][z];
//        return 0;
    }

    public int[][][] getBox()
    {

        return Box;
    }

//    public void setFromPoint(Point3D point)
//    {
//        Box[point.x][point.y][point.z] = 1;
//    }

    public void setFromPoint(Point4D point)
    {
//        Box[point.x][point.y][point.z] = testing;
//        testing++;

        Box[point.x][point.y][point.z] = 1;

    }
}
