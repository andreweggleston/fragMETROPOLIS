package Engine;

import org.lwjgl.opengl.Display;

/**
 * Created by andrew_eggleston on 6/12/16.
 * this class loads EVERYTHING
 */

public class ResourceHandler {
    public static final OBJLoader OBJ_LOADER = new OBJLoader();
    public static final ModelLoader MODEL_LOADER = new ModelLoader();
    public static final Window WINDOW = new Window(800, 600, "Windows 98 Pipe Screensaver -- George & Andrew");


    public static void clean() {
        //Clearing the Data out of the Engine.Model Loader which will destroy all of the model data that has been loaded.
        MODEL_LOADER.clearPointers();
        //Destroying the Engine.Window last because it will take the OpenGL functionality / context with it.
        Display.destroy();
    }

}
