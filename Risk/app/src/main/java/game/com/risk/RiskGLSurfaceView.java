package game.com.risk;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by jason on 5/7/2017.
 */

public class RiskGLSurfaceView extends GLSurfaceView{

    private RiskMapRenderer mr;
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    Integer team = 0;

    public RiskGLSurfaceView(Context context){
        super(context);

        setEGLContextClientVersion(2);

        mr = new RiskMapRenderer();
        setRenderer(mr);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = -((e.getX()/(getWidth()))*1.4f - 0.7f);
        float y = -((e.getY()/getHeight())*2 - 1.0f);

        Integer motionaction = e.getAction();

        if((motionaction & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN) {
            System.out.println("Zooming!!");
        } else if(motionaction == MotionEvent.ACTION_UP) {

            if (team > 1) {
                team = 0;
            } else {
                team++;
            }

            mr.changeTeam(team, x, y);
            requestRender();
        }
        return true;
    }


}
