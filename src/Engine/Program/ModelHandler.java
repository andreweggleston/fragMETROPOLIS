package Engine.Program;

import Engine.Model;
import Engine.ResourceHandler;

/**
 * Created by my dad on 1/27/1952.
 */
public class ModelHandler {

    private Model pipeStraight;
    private Model pipeBent;

    public ModelHandler() {
        //Loading all the Models we need
        pipeStraight = ResourceHandler.OBJ_LOADER.loadObjModel("res/models/pipeStraight.obj", "res/textures/blank_tex.png", "PNG");
        pipeBent = ResourceHandler.OBJ_LOADER.loadObjModel("res/models/pipeBent.obj", "res/textures/blank_tex.png", "PNG");
    }


    public Model getPipeStraight() {
        return pipeStraight;
    }

    public Model getPipeBent() {
        return pipeBent;
    }
}