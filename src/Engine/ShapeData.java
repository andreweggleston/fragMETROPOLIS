package Engine;

/**
 * Created by andrew_eggleston on 6/12/16.
 */
public class ShapeData {
    public static Model getFlatSquareModel(String texturePath, String textureType) {
        return ResourceHandler.MODEL_LOADER.createModel(FlatRectangle.vertices, FlatRectangle.textureCoords, FlatRectangle.indices, new Texture(texturePath, textureType));
    }
}
