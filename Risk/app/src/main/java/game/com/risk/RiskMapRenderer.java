package game.com.risk;

import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by jason on 5/7/2017.
 */

public class RiskMapRenderer implements GLSurfaceView.Renderer {

    private static final String TAG = "RiskMapRenderer";

    // mMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mRotationMatrix = new float[16];

    private float mAngle;

    private TerritoryManager tm;
    private Territory t1;
    private Territory t2;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        // Set the background frame color
        GLES20.glEnable(GLES20.GL_BLEND);// you enable blending function
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        GLES20.glClearColor(0.9f, 0.9f, 0.9f, 0.9f);

        tm = new TerritoryManager(8,65);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // Set the camera position (View matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        float[] newMMVPMatrix = new float[mMVPMatrix.length];
        for(int i = 0; i < newMMVPMatrix.length; i++){
            newMMVPMatrix[i] = mMVPMatrix[i];
        }

        tm.draw(mMVPMatrix);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height){
        GameConstants.GAME_CONSTANTS.updateGameDimensions(width, height);
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width/height;
        System.out.println("width: " + width + "height: " + height);
//        gl.glMatrixMode(GL10.GL_PROJECTION);
//        gl.glLoadIdentity();

        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

    public void changeTeam(Integer team, float x, float y){
        tm.changeTeam(team, x, y);
    }
}
