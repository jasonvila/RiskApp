package game.com.risk.SingleSurfaceView.Territory.Shape;

import java.util.Random;

/**
 * Created by jason on 5/7/2017.
 */

public class TerritoryShapeCircle extends TerritoryShape{

    private static final String TAG = "TerritoryShapeCircle";

    private float radius = 0.02f;
    private float center_x = 0.0f;
    private float center_y = 0.0f;

    public TerritoryShapeCircle(float[] args) throws Exception {

        super();

        if(args.length < 2){
            throw new Exception(TAG + " error: constructor args.length = " + args.length
                    + ", needs to be at least 2.");
        }

        this.center_x = args[0];
        this.center_y = args[1];

        int idx = 0;

        Random r = new Random();

        for(int i = 0; i < vertexCount; i++){
            float part = (i / (float) (vertexCount));
            float rad = part * 2 * (float) Math.PI;
            float outer_x = center_x + radius * (float) Math.cos(rad);
            float outer_y = center_y + radius * (float) Math.sin(rad);

            vertices[idx++] = outer_x;
            vertices[idx++] = outer_y;
        }

        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }


}
