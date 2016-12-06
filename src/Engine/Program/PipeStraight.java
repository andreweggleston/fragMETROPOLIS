package Engine.Program;

import Engine.MatrixUtils;
import Engine.Sprite;
import Engine.Model;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by andrew_eggleston on 6/13/16.
 */
public class PipeStraight extends Sprite{

    public PipeStraight(Model model) {
        super(model);
    }

    public PipeStraight(Model model, float x, float y, float z) {
        super(model, x, y, z);
    }

    public PipeStraight(Model model, float x, float y, float z, float xV, float yV) {
        super(model, x, y, z);
    }

    public PipeStraight(Model model, float x, float y, float z, float rx, float ry, float rz) {
        super(model, x, y, z, rx, ry, rz);
    }

    @Override
    protected Matrix4f transformMatrix() {
        Matrix4f transformMat;
        transformMat = MatrixUtils.createTransformMatrix(new Vector3f(getPosition().x, getPosition().y, getPosition().z), getRx(), getRy(), getRz(), getScale());
        return transformMat;
    }
    
}
