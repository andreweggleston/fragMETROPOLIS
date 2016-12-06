package Engine.Program;

import Engine.ShapeData;
import Engine.Sprite;
import org.lwjgl.util.vector.Vector4f;

import java.util.ArrayList;

/**
 * Created by andy wang on 3/23/2016.
 */
public class SpriteHandler {

    private Sprite pipeStraight, pipeBent;

    public SpriteHandler(ModelHandler modelHandler) {
        pipeStraight = new Sprite(modelHandler.getPipeStraight());
        pipeBent = new Sprite(modelHandler.getPipeBent());
    }

    public Sprite getPipeStraight() {
        return pipeStraight;
    }

    public Sprite getPipeBent() {
        return pipeBent;
    }
}