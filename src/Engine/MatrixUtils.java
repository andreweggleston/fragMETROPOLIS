package Engine;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

/**
 * Created by andrew_eggleston on 6/12/16.
 */
public class MatrixUtils {

    public static Matrix4f createTransformMatrix(Vector3f translate, float rotx, float roty, float rotz, float scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
        Matrix4f.translate(translate, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rotx), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(roty), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rotz), new Vector3f(0, 0, 1), matrix, matrix);
        return matrix;
    }

    public static FloatBuffer MatrixToFloatBuffer(Matrix4f matrix) {
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);
        matrix.store(floatBuffer);
        floatBuffer.flip();
        return floatBuffer;
    }

}
