package Engine.Program;

import org.lwjgl.util.Color;

/**
 * Created by george_jiang on 5/17/16.
 */
public class PipeHandler {
    int x, y, z, xLen, yLen, zLen;
    private Color color;


    Point4D pipePos;
    Screen screen;
    int[][][] Box;

    private static final int BACK = 1, FORWARD = -1, LEFT = -2, RIGHT = 2, UP = 3, DOWN = -3;

    private int direction;

    //TODO Figure out color
    public PipeHandler(/*Color color, */Screen scren, int direction) {
        screen = scren;
        Box = screen.getBox();
        xLen = Box.length;
        yLen = Box[0].length;
        zLen = Box[0][0].length;


        //Chooses a random initial position for the pipe
        x = (int) (Math.random() * xLen);
        y = (int) (Math.random() * yLen);
        z = (int) (Math.random() * zLen);
        pipePos = new Point4D(x, y, z, direction);
        screen.setFromPoint(pipePos);
        System.out.println("x:" + x + " " + "y:" + y + " " + "z:" + z);
        //----------------------------------------------


        this.direction = direction;
    }


    public int getDirection() {
        return direction;
    }

    public Point4D chooseNext() {
        //initial roll for direction
        int randomDir = (int) (Math.random() * 8 - 4);

        while (/*spotTaken*/screen.getSpot(directionToCoordinate(randomDir).x, directionToCoordinate(randomDir).y, directionToCoordinate(randomDir).z) != 0 || randomDir == 0) {
            randomDir = (int) ((Math.random() * 8) - 4);
        }

        System.out.println("accepted RandomDir: " + randomDir);
//        pipePos = directionToCoordinate(randomDir);
//        x = directionToCoordinate(randomDir).x;
//        y=directionToCoordinate(randomDir).y;
//        z = directionToCoordinate(randomDir).z;
//        screen.setFromPoint(directionToCoordinate(randomDir));

        pipePos = directionToCoordinate(randomDir);

        x = pipePos.x;
        y = pipePos.y;
        z = pipePos.z;
        screen.setFromPoint(pipePos);
        direction = randomDir;
        return pipePos;

    }


//    public Point3D chooseNextGood() {
//
//        int ex = 1, why = 1, zee = 1;
//
//        int chooser = (int) (Math.random() * 6 + 1);
//
//        if (chooser == -direction) {
//            return chooseNextGood();
//        } else {
//            if (chooser == FORWARD) {
//                for (int i = z + 1; i < zLen; i++) {
//                    if (spotTaken(new Point3D(x, y, i))) {
//                        return chooseNextGood();
//                    }
//                }
//                int newZ = (int) (Math.random() * (zLen - z) + 1);
//                direction = FORWARD;
//            } else if (chooser == BACK) {
//                int newZ = (int) (Math.random() * (zLen - z) + 1);
//                direction = FORWARD;
//            } else {
//            }
//
//
//            return new Point3D(ex, why, zee);
//        }
//    }

    private boolean spotTaken(Point3D point) {
        return screen.getSpot(point.x, point.y, point.z) != 0;
    }


    public Point4D directionToCoordinate(int randomDir) {

        //BACK = 1, FORWARD = -1, LEFT = -2, RIGHT = 2, UP = 3, DOWN = -3

        //TODO do the calls for graphics draws here
        if (direction == 1) {
            if (randomDir == direction) {
                return new Point4D(new Point3D(x, y, z + 1), direction);

            } else if (randomDir == -direction) {
                return new Point4D(new Point3D(x, y, z + 1), direction);
            } else if (randomDir == 2) {
                direction = randomDir;
                return new Point4D(new Point3D(x + 1, y, z), direction);
            } else if (randomDir == -2) {
                direction = randomDir;
                return new Point4D(new Point3D(x - 1, y, z), direction);
            } else if (randomDir == 3) {
                direction = randomDir;
                return new Point4D(new Point3D(x, y + 1, z), direction);
            } else/*-3*/ {
                direction = randomDir;
                return new Point4D(new Point3D(x, y - 1, z), direction);
            }
        }

        if (direction == -1) {
            if (randomDir == direction) {
                return new Point4D(new Point3D(x, y, z - 1), direction);
            } else if (randomDir == -direction) {
                return new Point4D(new Point3D(x, y, z - 1), direction);
            } else if (randomDir == 2) {
                direction = randomDir;
                return new Point4D(new Point3D(x + 1, y, z), direction);
            } else if (randomDir == -2) {
                direction = randomDir;
                return new Point4D(new Point3D(x - 1, y, z), direction);
            } else if (randomDir == 3) {
                direction = randomDir;
                return new Point4D(new Point3D(x, y + 1, z), direction);
            } else/*-3*/ {
                direction = randomDir;
                return new Point4D(new Point3D(x, y - 1, z), direction);
            }
        }

        if (direction == 2) {
            if (randomDir == direction) {
                return new Point4D(new Point3D(x + 1, y, z),direction);
            } else if (randomDir == -direction) {
                return new Point4D(new Point3D(x + 1, y, z),direction);
            } else if (randomDir == 1) {
                direction = randomDir;
                return new Point4D(new Point3D(x, y, z + 1),direction);
            } else if (randomDir == -1) {
                direction = randomDir;
                return new Point4D(new Point3D(x, y, z - 1),direction);
            } else if (randomDir == 3) {
                direction = randomDir;
                return new Point4D(new Point3D(x, y + 1, z),direction);
            } else/*-3*/ {
                direction = randomDir;
                return new Point4D(new Point3D(x, y - 1, z),direction);
            }
        }

        if (direction == -2) {
            if (randomDir == direction) {
                return new Point4D(new Point3D(x - 1, y, z), direction);
            } else if (randomDir == -direction) {
                return new Point4D(new Point3D(x - 1, y, z), direction);
            } else if (randomDir == 1) {
                direction = randomDir;
                return new Point4D(new Point3D(x, y, z + 1), direction);
            } else if (randomDir == -1) {
                direction = randomDir;
                return new Point4D(new Point3D(x, y, z - 1), direction);
            } else if (randomDir == 3) {
                direction = randomDir;
                return new Point4D(new Point3D(x, y + 1, z), direction);
            } else/*-3*/ {
                direction = randomDir;
                return new Point4D(new Point3D(x, y - 1, z), direction);
            }
        }

        if (direction == -3) {
            if (randomDir == direction) {
                return new Point4D(new Point3D(x, y - 1, z), direction);
            } else if (randomDir == -direction) {
                return new Point4D(new Point3D(x, y - 1, z), direction);
            } else if (randomDir == 1) {
                direction = randomDir;
                return new Point4D(new Point3D(x, y, z + 1), direction);
            } else if (randomDir == -1) {
                direction = randomDir;
                return new Point4D(new Point3D(x, y, z - 1), direction);
            } else if (randomDir == 2) {
                direction = randomDir;
                return new Point4D(new Point3D(x + 1, y, z), direction);
            } else/*-3*/ {
                direction = randomDir;
                return new Point4D(new Point3D(x - 1, y, z), direction);
            }
        } else /*direction = 3*/ {
            if (randomDir == direction) {
                direction = randomDir;
                return new Point4D(new Point3D(x, y + 1, z), direction);
            } else if (randomDir == -direction) {
                direction = randomDir;
                return new Point4D(new Point3D(x, y + 1, z), direction);
            } else if (randomDir == 1) {
                direction = randomDir;
                return new Point4D(new Point3D(x, y, z + 1), direction);
            } else if (randomDir == -1) {
                direction = randomDir;
                return new Point4D(new Point3D(x, y, z - 1), direction);
            } else if (randomDir == 2) {
                direction = randomDir;
                return new Point4D(new Point3D(x + 1, y, z), direction);
            } else/*-2*/ {
                direction = randomDir;
                return new Point4D(new Point3D(x - 1, y, z), direction);
            }
        }
    }
}
