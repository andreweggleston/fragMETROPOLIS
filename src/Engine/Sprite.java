package Engine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

/**
 * Created by andrew_eggleston on 6/12/16.
 */
public class Sprite {
    private Model model;
    private Vector3f position = new Vector3f(0,0,0);
    private Vector3f velocities = new Vector3f(0,0,0);
    private float rx = 0, ry = 0, rz = 0;
    private float scale = 1;
    public Vector4f color = new Vector4f(1,1,1,1);
    protected float lowX, lowY, highX, highY;
    public boolean dead = false;

    public Sprite(Model model) {
        this.model = model;
        lowX = model.getLowX();
        lowY = model.getLowY();
        highX = model.getHighX();
        highY = model.getHighY();
    }

    public Sprite(Model model, float x, float y, float z) {
        this.model = model;
        position.x = x;
        position.y = y;
        position.z = z;
        lowX = model.getLowX() + x;
        lowY = model.getLowY() + y;
        highX = model.getHighX() + x;
        highY = model.getHighY() + y;
    }

    public Sprite(Model model, float x, float y, float z, float rx, float ry, float rz) {
        this.model = model;
        position.x = x;
        position.y = y;
        position.z = z;
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
    }

    public void move() {
        position.x += velocities.x;
        position.y += velocities.y;
        position.z += velocities.z;
        incXYDimensions(velocities.x, velocities.y);
    }

    public Vector3f getVelocities() {
        return velocities;
    }

    public void setVelocities(float x, float y, float z) {
        velocities = new Vector3f(x, y, z);
    }

    public void translate(float x, float y, float z) {
        position.x += x;
        position.y += y;
        position.z += z;
        incXYDimensions(x, y);
    }

    protected void incXYDimensions(float x, float y) {
        lowX += x;
        highX += x;
        lowY += y;
        highY += y;
    }
    
    public void rotate(float rx, float ry, float rz) {
        this.rx += rx;
        this.ry += ry;
        this.rz += rz;
    }

    public void scale(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

    protected Matrix4f transformMatrix() {
        return MatrixUtils.createTransformMatrix(position, rx, ry, rz, scale);
    }

    public void render(Shader shader, Camera camera) {
        shader.loadVector4f(shader.colorID, color);
        shader.loadMatrix(shader.viewMatrixID, camera.createViewMatrix());
        shader.loadMatrix(shader.transformMatrixID, transformMatrix());
        shader.loadFloat(shader.scaleID, scale);
        model.render();
    }

    public boolean checkCollision(Sprite other) {
        return other.getPosition().x >= lowX && other.getPosition().x <= highX && other.getPosition().y <= highY && other.getPosition().y >= lowY;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Model getModel() {
        return model;
    }

    public float getRx() {
        return rx;
    }

    public float getRy() {
        return ry;
    }

    public float getRz() {
        return rz;
    }


    public float getLowX() {
        return lowX;
    }

    public float getLowY() {
        return lowY;
    }

    public float getHighX() {
        return highX;
    }

    public float getHighY() {
        return highY;
    }
}
