package Engine;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

/**
 * Created by andrew_eggleston on 6/12/16.
 */

public class ModelLoader {

    ArrayList<Integer> vbos;
    ArrayList<Integer> vaos;

    public ModelLoader() {
        vaos = new ArrayList<Integer>();
        vbos = new ArrayList<Integer>();
    }

    public Model createModel(float[] data, float[] textures, int[] indices, Texture texture) {
        int vaoID = createVAO();
        storeDataInVBO(data, 0, 3);
        storeDataInVBO(textures, 1, 2);
        unbindVAO();
        int indicesID = storeIndicesInVBO(indices);
        return new Model(vaoID, indicesID, indices.length, texture, 0, 0, 0, 0);
    }

    public Model createModel(float[] data, float[] textures, int[] indices, Texture texture, float lowX, float lowY, float highX, float highY) {
        int vaoID = createVAO();
        storeDataInVBO(data, 0, 3);
        storeDataInVBO(textures, 1, 2);
        unbindVAO();
        int indicesID = storeIndicesInVBO(indices);
        return new Model(vaoID, indicesID, indices.length, texture, lowX, lowY, highX, highY);
    }

    private int createVAO() {
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    private void storeDataInVBO(float[] data, int attribNum, int attribSize) {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, toFloatBuffer(data), GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attribNum, attribSize, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private int storeIndicesInVBO(int[] data) {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, toIntBuffer(data), GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        return vboID;
    }

    private void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    private FloatBuffer toFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    private IntBuffer toIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public void clearPointers() {
        for (int vboID : vbos) {
            GL15.glDeleteBuffers(vboID);
        }
        for (int vaoID : vaos) {
            GL30.glDeleteVertexArrays(vaoID);
        }
    }

}
