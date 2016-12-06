package Engine.Program;

import Engine.Camera;
import Engine.ResourceHandler;
import Engine.Shader;
import Engine.Sprite;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;

/**
 * Created by andrew for sure on the day its due.
 */
public class Main {

    private static boolean cameraLock = true;
    private static final int BACK = 1, FORWARD = -1, LEFT = -2, RIGHT = 2, UP = 3, DOWN = -3;
    private static ArrayList<PipeStraight> pipes;



    public static void main(String[] args) {
        ModelHandler modelHandler = new ModelHandler();
        SpriteHandler spriteHandler = new SpriteHandler(modelHandler);
        Screen screen = new Screen();
        pipes = new ArrayList<PipeStraight>();

        //Class that controls the movement of the player

        //Macs require you to have a VAO bound when making the shader, I have no clue why.
        GL30.glBindVertexArray(spriteHandler.getPipeStraight().getModel().getVaoID());
        Shader shader = new Shader("shaders/vertexShader.vshr", "shaders/fragmentShader.fshr");
        GL30.glBindVertexArray(0);
        //End of that Mac Crap

        //Creates a Engine.Camera that will control the View Matrix
        Camera camera = new Camera(shader);

        PipeHandler pipeHandler = new PipeHandler(screen, 1);



        int count = 0;
//        float x, y, z, rx, ry, rz;
//        x = 0;
//        y = 0;
//        z = 0;
//        rx = 0;
//        ry = 0;
//        rz = 0;


        while (!Display.isCloseRequested()) {
            //Making use of the Depth Buffer.
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            //Clear and prep the Engine.Window for Rendering.
            ResourceHandler.WINDOW.clean();
            //Start the Engine.Shader, this way everything will be handled by this shader.
            shader.start();
            //Stop the Rendering process.

            count++;
            PipeBent pipeBent = new PipeBent(modelHandler.getPipeBent(), 0f, 0f, 0f);
//            PipeStraight pipeStraight = new PipeStraight(modelHandler.getPipeStraight(), 2f, 0f, -2f, 0f, 90f, 90f);



            if (count % 30 == 0) {
                count = 1;

                Point4D newPoint = pipeHandler.chooseNext();
                int x = newPoint.x;
                int y = newPoint.y;
                int z = newPoint.z;
                int dir = newPoint.dir;

                if (dir == pipeHandler.getDirection() || dir == -pipeHandler.getDirection()) {
                    if (dir == RIGHT || dir == LEFT) {

                        pipes.add(new PipeStraight(modelHandler.getPipeStraight(), (float) x, (float) y, (float) z, 0f, 90f, 0f));
                    } else if (dir == UP || dir == DOWN) {

                        pipes.add(new PipeStraight(modelHandler.getPipeStraight(), (float) x, (float) y, (float) z, 90f, 0f, 0f));
//                        pipeStraight.render(shader, camera);
                    } else if (dir == FORWARD || dir == BACK) {

                        pipes.add(new PipeStraight(modelHandler.getPipeStraight(), (float) x, (float) y, (float) z, 0f, 0f, 0f));
//                        pipeStraight.render(shader, camera);
                    }
                } else if (dir == UP && pipeHandler.getDirection() != UP) {

                } else if (dir == DOWN && pipeHandler.getDirection() != DOWN) {

                } else if (dir == LEFT && pipeHandler.getDirection() != LEFT) {

                } else if (dir == RIGHT && pipeHandler.getDirection() != RIGHT) {

                } else if (dir == FORWARD && pipeHandler.getDirection() != FORWARD) {

                } else if (dir == BACK && pipeHandler.getDirection() != BACK) {

                }


                System.out.println(pipes.size());
            }

               for(Sprite pipe: pipes){
                   pipe.render(shader, camera);
               }
                pipeBent.render(shader, camera);



                shader.stop();
                //Update the Engine.Window and switch the buffers.
                ResourceHandler.WINDOW.update();
                //Poll for events
                while (Keyboard.next()) {
                    if (Keyboard.getEventKeyState()) {
                        if (Keyboard.getEventKey() == Keyboard.KEY_E) {
                            if (cameraLock) {
                                cameraLock = false;
                            } else {
                                cameraLock = true;
                            }
                        }
                        if (Keyboard.getEventKey() == Keyboard.KEY_W) {
                            if (!cameraLock) {
                                camera.move(0, 0, -1);
                            }
                        }
                        if (Keyboard.getEventKey() == Keyboard.KEY_S) {
                            if (!cameraLock) {
                                camera.move(0, 0, 1);
                            }
                        }
                        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
                            if (!cameraLock) {
                                camera.move(-1, 0, 0);
                            }
                        }
                        if (Keyboard.getEventKey() == Keyboard.KEY_D) {
                            if (!cameraLock) {
                                camera.move(1, 0, 0);
                            }
                        }
                        if (Keyboard.getEventKey() == Keyboard.KEY_Q) {
                            if (!cameraLock) {
                                camera.move(0, 1, 0);
                            }
                        }
                        if (Keyboard.getEventKey() == Keyboard.KEY_Q) {
                            if (!cameraLock) {
                                camera.move(0, -1, 0);
                            }
                        }
                    }
                }
                while (Mouse.next()) {
                    if (!cameraLock) {
                        camera.rotate(-Mouse.getDY() / 3, Mouse.getDX() / 3, 0);
                    }
                }
        }

        shader.destroy();
        Display.destroy();
    }
}