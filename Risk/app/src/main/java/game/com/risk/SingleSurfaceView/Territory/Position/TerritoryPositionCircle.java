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

        this.nLeftXBound = leftXBound - radius*1.5f;
        this.nRightXBound = rightXBound + radius*1.5f;
        this.nUpperYBound = upperYBound + radius*1.5f;
        this.nLowerYBound = lowerYBound - radius*1.5f;
    }

    public Boolean isInTerritory(float x, float y){
        if ((x <= rightXBound && x >= leftXBound && y <= upperYBound && y >= lowerYBound)) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean isNeighbor(float x, float y){
        if((x <= nRightXBound && x >= nLeftXBound && y <= nUpperYBound && y >= nLowerYBound)){
            return true;
        } else {
            return false;
        }
    }

    public Boolean isNeighbor(TerritoryPositionCircle t){
        return t.isNeighbor(center_x, center_y);
    }

    public void increaseNeighborRadius(){
        this.nLeftXBound = nLeftXBound - radius*2.5f;
        this.nRightXBound = nRightXBound + radius*2.5f;
        this.nUpperYBound = nUpperYBound + radius*2.5f;
        this.nLowerYBound = nLowerYBound - radius*2.5f;
    }

    public float[] getPosition(){
        float[] p = {center_x, center_y};
        return p;
    }

    public float getRadius(){
        return -(nLeftXBound - leftXBound);
    }
}
