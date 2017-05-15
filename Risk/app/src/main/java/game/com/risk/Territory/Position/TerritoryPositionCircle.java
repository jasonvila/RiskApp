package game.com.risk.Territory.Position;

/**
 * Created by jason on 5/8/2017.
 */

public class TerritoryPositionCircle {

    private static final String TAG = "TerritoryPositionCircle";

    private float center_x, center_y, radius;
    private float leftXBound, rightXBound, upperYBound, lowerYBound;

    public TerritoryPositionCircle(float center_x, float center_y, float radius){
        this.center_x = center_x;
        this.center_y = center_y;
        this.radius = radius;
        this.leftXBound = center_x - radius;
        this.rightXBound = center_x + radius;
        this.upperYBound = center_y + radius;
        this.lowerYBound = center_y - radius;
    }

    public Boolean isInTerritory(float x, float y){
        if ((x <= rightXBound && x >= leftXBound && y <= upperYBound && y >= lowerYBound)) {
            return true;
        } else {
            return false;
        }
    }

}
