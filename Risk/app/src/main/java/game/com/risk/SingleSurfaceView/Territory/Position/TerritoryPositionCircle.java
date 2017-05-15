package game.com.risk.SingleSurfaceView.Territory.Position;

import java.util.PriorityQueue;

/**
 * Created by jason on 5/8/2017.
 */

public class TerritoryPositionCircle {

    private static final String TAG = "TerritoryPositionCircle";

    private float center_x, center_y, radius;
    private float leftXBound, rightXBound, upperYBound, lowerYBound;
    private float nLeftXBound, nRightXBound, nUpperYBound, nLowerYBound;


    public TerritoryPositionCircle(float center_x, float center_y, float radius){
        this.center_x = center_x;
        this.center_y = center_y;
        this.radius = radius;

        this.leftXBound = center_x - radius;
        this.rightXBound = center_x + radius;
        this.upperYBound = center_y + radius;
        this.lowerYBound = center_y - radius;

        this.nLeftXBound = leftXBound - radius*2;
        this.nRightXBound = rightXBound + radius*2;
        this.nUpperYBound = upperYBound + radius*2;
        this.nLowerYBound = lowerYBound - radius*2;
    }

    public Boolean isInTerritory(float x, float y){
        if ((x <= rightXBound && x >= leftXBound && y <= upperYBound && y >= lowerYBound)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isNeighbor(float x, float y){

        if((x <= nRightXBound && x >= nLeftXBound && y <= nUpperYBound && y >= nLowerYBound)){
            return true;
        } else {
            return false;
        }
    }

}
