package game.com.risk.SingleSurfaceView.Territory.Shape;

import java.util.Random;

/**
 * Created by jason on 5/15/2017.
 */

public class TerritoryShapeLineToNeighbor extends TerritoryShapeLine {

    private static final String TAG = "TerritoryShapeLineToNeighbor";

    public TerritoryShapeLineToNeighbor(float[] args) throws Exception {

        super();

        if(args.length < 5){
            throw new Exception(TAG + " error: constructor args.length = " + args.length
                    + ", needs to be at least 2.");
        }

        int idx = 0;

        vertices[0] = args[0];
        vertices[1] = args[1];
        vertices[2] = args[2];
        vertices[3] = args[3];

        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }
}
