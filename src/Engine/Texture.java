package Engine;

import org.newdawn.slick.opengl.TextureLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by andrew_eggleston on 6/12/16.
 */
public class Texture {

    int textureID;

    public Texture(String fileName, String type) {
        getTextureID(fileName, type);
    }

    public Texture(BufferedImage img) {
        try {
            ImageIO.write(img, "png", new File("texture"));
            textureID = TextureLoader.getTexture("png", new FileInputStream("texture")).getTextureID();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getTextureID(String fileName, String type) {
        try {
            textureID = TextureLoader.getTexture(type, new FileInputStream(fileName)).getTextureID();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTextureID() {
        return textureID;
    }
}
