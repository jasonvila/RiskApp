package game.com.risk.SingleSurfaceView.Territory.Manager;

import android.util.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import game.com.risk.SingleSurfaceView.Territory.Position.TerritoryPositionCircle;
import game.com.risk.SingleSurfaceView.Territory.Shape.TerritoryShapeLine;
import game.com.risk.SingleSurfaceView.Territory.Shape.TerritoryShapeLineToNeighbor;
import game.com.risk.SingleSurfaceView.Territory.Territory;

/**
 * Created by jason on 5/7/2017.
 */

public class TerritoryManagerCircle extends TerritoryManager{

    private float[] xRand = new float[65];
    private float[] yRand = new float[65];


    public TerritoryManagerCircle(Integer numTeams, Integer numTerritories){

        super(numTeams, numTerritories);

        float curX = 0.8f;
        float curY = 0.8f;

        for(int i =0; i < coord.length; i++){

            if(curX >= -0.4f && curY >= -0.8f){
                curX = curX - 0.2f;
            } else if(curY >= -0.6f) {
                curX = 0.6f;
                curY = curY - 0.2f;
            } else {
                break;
            }

            if(curX == 0.0f && curY == 0.0f){
                System.out.println("Territory at Center!!!");
            }

            xRand[i] = curX;
            yRand[i] = curY;
        }

        try {
            float[] args = new float[2];

            for (int i = 0; i < numTerritories; i++) {
                args[0] = xRand[coord[i]];
                args[1] = yRand[coord[i]];
                Territory t = new Territory(args);
                TerritoryPositionCircle tP = new TerritoryPositionCircle(xRand[coord[i]], yRand[coord[i]], 0.1f);
                stringToTerritories.put(t.toString(), t);
                positionToTerritories.put(tP, t.toString());
            }


            int idx = 0;
            Set<String> keySet = stringToTerritories.keySet();
            for (String k : keySet) {

                if (idx == numTeams) {
                    idx = 0;
                }

                stringToTerritories.get(k).changeTeams(idx);
                idx++;
            }

            Set<TerritoryPositionCircle> keySet1 = positionToTerritories.keySet();
            Set<TerritoryPositionCircle> keySet2 = positionToTerritories.keySet();

            for(TerritoryPositionCircle k1 : keySet1){
                Boolean hasNeighbor = false;

                while(!hasNeighbor) {

                    for (TerritoryPositionCircle k2 : keySet2) {
                        if (!k1.equals(k2) && k1.isNeighbor(k2)) {
                            Log.d(TAG, "Has neighbor " + k1.toString());
                            hasNeighbor = true;
                            args = new float[5];
                            args[0] = k1.getPosition()[0];
                            args[1] = k1.getPosition()[1];
                            args[2] = k2.getPosition()[0];
                            args[3] = k2.getPosition()[1];
                            TerritoryShapeLine t = new TerritoryShapeLineToNeighbor(args);
                            stringToLine.put(t.toString(), t);
                        }
                    }
                    if(!hasNeighbor) {
                        if(k1.getRadius() > 0.8f){
                            hasNeighbor = true;
                        }
                        k1.increaseNeighborRadius();
                    }
                }
            }

            Log.d(TAG, "Number of territories: " + stringToTerritories.keySet().size());

        } catch(Exception e){
            Log.e(TAG, e.getMessage());
        }
    }

}
