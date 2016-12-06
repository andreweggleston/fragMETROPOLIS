package Engine;

import org.lwjgl.opengl.*;

/**
 * Created by andrew_eggleston on 6/12/16.
 */
public class Model {
    private int vaoID;
    private int vertexCount;
    private int indicesBufferID;
    private Texture texture;

    private float lowX = 0, lowY = 0, highX = 0, highY = 0;

    public Model(int vaoID, int indicesBufferID, int vertexCount, Texture texture) {
        this.indicesBufferID = indicesBufferID;
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
        this.texture = texture;
    }

    public Model(int vaoID, int indicesBufferID, int vertexCount, Texture texture, float lowX, float lowY, float highX, float highY) {
        this.indicesBufferID = indicesBufferID;
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
        this.texture = texture;
        this.lowX = lowX;
        this.lowY = lowY;
        this.highX = highX;
        this.highY = highY;
    }

    public void scaleXY(float scale) {
        lowX *= scale;
        lowY *= scale;
        highX *= scale;
        highY *= scale;
    }

    public void incXYDimensions(float x, float y) {
        lowX += x;
        highX += x;
        lowY += y;
        highY += y;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void render() {
        //Bind the VAO so we can access the Vertices
        GL30.glBindVertexArray(vaoID);
        //Bind the first attribute because that's were we put the vertices.
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        //Bind the Indices which are in a separate VBO
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferID);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER ,GL11.GL_NEAREST);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        //Draw the elements using the indices to access the correct vertices.
        GL11.glDrawElements(GL11.GL_TRIANGLES, vertexCount, GL11.GL_UNSIGNED_INT, 0);
        //Unbind the Indices
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        //Unbind the vertex vbo
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(0);
        //Unbind the VAO
        GL30.glBindVertexArray(0);
    }

    public float getWidth() {
        return highX - lowX;
    }

    public float getHeight() {
        return highY - lowY;
    }

    public int getVaoID() {
        return vaoID;
    }

    public float getLowX() {
        return lowX;
    }

    public void setLowX(float lowX) {
        this.lowX += lowX;
    }

    public float getLowY() {
        return lowY;
    }

    public void setLowY(float lowY) {
        this.lowY += lowY;
    }

    public float getHighX() {
        return highX;
    }

    public void setHighX(float highX) {
        this.highX += highX;
    }

    public float getHighY() {
        return highY;
    }

    public void setHighY(float highY) {
        this.highY += highY;
    }
}
