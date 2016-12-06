package Engine;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by andrew_eggleston on 6/12/16.
 */
public class Camera {

    private final int FOV = 103;
    private final float nearPlane = 0.5f;
    private final float farPlane = 10000;
    public Vector3f position = new Vector3f(0,0,0);
    private float pitch;
    private float yaw;
    private float roll;
    protected float aspectRatio;

    public Camera(Shader shader) {
        shader.start();
        shader.loadMatrix(shader.projectionMatrixID, createProjectionMatrix());
        shader.stop();
        yaw = 90;
    }

    private Matrix4f createProjectionMatrix(){
        aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV/2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = farPlane - nearPlane;

        Matrix4f projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((farPlane + nearPlane) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * nearPlane * farPlane) / frustum_length);
        projectionMatrix.m33 = 0;
        return projectionMatrix;
    }

    public Matrix4f createViewMatrix() {
       return MatrixUtils.createTransformMatrix(new Vector3f(-position.x, -position.y, -position.z), pitch, yaw, roll, 1);
    }

    public void move(float x, float y, float z) {
        position.x += x;
        position.y += y;
        position.z += z;
    }

    public void rotate(float rx, float ry, float rz) {
        pitch += rx;
        yaw += ry;
        roll += rz;
    }

    public void center() {
        position = new Vector3f(0,0,0);
        pitch = 0;
        yaw = 0;
        roll = 0;
    }

    public float getRotationY() {
        return yaw;
    }

    public float getFOV() {
        return FOV;
    }
}
