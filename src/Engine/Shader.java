package Engine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by andrew_eggleston on 6/12/16.
 */
public class Shader {
    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;

    public int transformMatrixID;
    public int projectionMatrixID;
    public int viewMatrixID;
    public int scaleID;
    public int colorID;

    public Shader(String vertexShaderPath, String fragmentShaderPath) {

        //Generate the shader's
        vertexShaderID = loadShader(vertexShaderPath, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentShaderPath, GL20.GL_FRAGMENT_SHADER);

        //Create the basic program.
        programID = GL20.glCreateProgram();

        //Attach the shader's to the program.
        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);

        //Make sure all the shader variables know where they are supposed to come from.
        bindAttributes();

        //Link our program
        GL20.glLinkProgram(programID);
        programLinkCheck();

        //Validate that our program will run in the current environment.
        GL20.glValidateProgram(programID);
        programValidateCheck();

        //Find all the locations of the uniforms in our shaders.
        findAllUniforms();
    }

    private void findAllUniforms() {
        transformMatrixID = GL20.glGetUniformLocation(programID, "transMatrix");
        projectionMatrixID = GL20.glGetUniformLocation(programID, "projectMatrix");
        viewMatrixID = GL20.glGetUniformLocation(programID, "viewMatrix");
        scaleID = GL20.glGetUniformLocation(programID, "scale");
        colorID = GL20.glGetUniformLocation(programID, "color");
    }

    protected void loadMatrix(int matrixLocationID, Matrix4f matrix) {
        GL20.glUniformMatrix4(matrixLocationID, false, MatrixUtils.MatrixToFloatBuffer(matrix));
    }

    protected void loadFloat(int floatID, float data) {
        GL20.glUniform1f(floatID, data);
    }

    protected void loadVector4f(int vector3fID, Vector4f data) {
        GL20.glUniform4f(vector3fID, data.x, data.y, data.z, data.w);
    }

    public void start() {
        GL20.glUseProgram(programID);
    }

    public void stop() {
        GL20.glUseProgram(0);
    }

    public void destroy() {
        stop();
        //Detaching both the shader's and getting ready to safely destroy them
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        //Deleting the shader's so they don't make a mess of memory.
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        //Finally delete the program.
        GL20.glDeleteProgram(programID);
    }

    private void bindAttributes() {
        //Vertices will always come from VBO 0.
        bindAttribute(0, "position");
        bindAttribute(1, "uvs");
    }

    private void bindAttribute(int attribute, String variableName) {
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }

    private StringBuilder readFile(String filePath) {
        StringBuilder source = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = reader.readLine()) != null) {
                source.append(line).append('\n');
            }

        } catch (java.io.IOException e) {
            System.out.println("File could not be read!");
            e.printStackTrace();
            System.exit(1);
        }
        return source;
    }

    private int loadShader(String filePath, int type) {
        StringBuilder source = readFile(filePath);
        //Create the specific shader and get the handle.
        int shaderID = GL20.glCreateShader(type);
        //Add the source to the shader.
        GL20.glShaderSource(shaderID, source);
        //Compile that shader.
        GL20.glCompileShader(shaderID);
        shaderCompileCheck(shaderID);
        return shaderID;
    }

    // ---- Error Checks ---- \\
    private void shaderCompileCheck(int shaderID) {
        if( GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.out.println("Could not compile Engine.Shader!");
            System.exit(1);
        }
    }

    private void programLinkCheck() {
        if( GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            System.out.println("Linking Error");
            System.out.println(GL20.glGetProgramInfoLog(programID, 500));
            System.exit(1);
        }
    }

    private void programValidateCheck() {
        if( GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
            System.out.println("Validation Error");
            System.out.println(GL20.glGetProgramInfoLog(programID, 500));
            System.exit(1);
        }
    }
}
