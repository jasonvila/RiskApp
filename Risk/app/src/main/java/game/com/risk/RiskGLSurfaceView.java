package game.com.risk;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by jason on 5/7/2017.
 */

public class RiskGLSurfaceView extends GLSurfaceView {

    private RiskMapRenderer mr;

    public RiskGLSurfaceView(Context context){
        super(context);

        setEGLContextClientVersion(2);

        mr = new RiskMapRenderer();
        setRenderer(mr);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }


}
