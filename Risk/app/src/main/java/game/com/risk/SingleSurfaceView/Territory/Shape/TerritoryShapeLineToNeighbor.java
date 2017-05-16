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

    public Boolean containsBothNodes(float x1, float x2, float y1, float y2){
        if((vertices[0] == x1 && vertices[1] == y1 && vertices[2] == x2 && vertices[3] == y2)
                || (vertices[0] == x2 && vertices[1] == y2 && vertices[2] == x1 && vertices[3] == y1)){
            return true;
        }
        return false;
    }
}
