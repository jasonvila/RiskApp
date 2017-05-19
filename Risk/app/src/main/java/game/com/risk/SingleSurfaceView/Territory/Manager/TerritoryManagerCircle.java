package game.com.risk.SingleSurfaceView.Territory.Manager;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

            connectTerritoriesWithLines();

        } catch(Exception e){
            Log.e(TAG, e.getMessage());
        }
    }

    private Boolean connectTerritoriesWithLines() throws Exception {

        float[] args = new float[5];

        Set<TerritoryPositionCircle> keySet1 = positionToTerritories.keySet();
        Set<TerritoryPositionCircle> keySet2;

        Boolean inTree = false;
        ArrayList<HashMap<String, TerritoryPositionCircle>> lines = new ArrayList<HashMap<String, TerritoryPositionCircle>>();
        ArrayList<Integer> mapMergeList = new ArrayList<Integer>();
        Integer i = 0;

        for(TerritoryPositionCircle k1 : keySet1){
            Boolean hasNeighbor = false;

            while(!hasNeighbor) {

                keySet2 = positionToTerritories.keySet();

                for (TerritoryPositionCircle k2 : keySet2) {
                    if (!k1.equals(k2) && k2.isNeighbor(k1)) {

                        hasNeighbor = true;

                        if(stringToLine.get(k1.toString() + "_" + k2.toString()) == null
                                && stringToLine.get(k2.toString() + "_" + k1.toString()) == null){

                            for(HashMap<String, TerritoryPositionCircle> h : lines){
                                if( h.get(k2.toString()) != null && h.get(k2.toString()) != null){
                                    inTree = true;
                                } else if(h.get(k1.toString()) == null && h.get(k2.toString()) != null){
                                    h.put(k1.toString(), k1);
                                    inTree = true;
                                } else if(h.get(k2.toString()) == null && h.get(k1.toString()) != null){
                                    h.put(k2.toString(), k2);
                                    inTree = true;
                                }
//                                if(inTree){
//
//                                    // break;
//
//                                    // new
//                                    Log.d(TAG, k1.toString() + " has neighbor " + k2.toString());
//                                    mapMergeList.add(i);
//                                }
//                                i++;
                            }

//                            i = 0;
//
//                            if(!inTree){
//                                HashMap<String, TerritoryPositionCircle> h = new HashMap<String, TerritoryPositionCircle>();
//                                h.put(k1.toString(), k1);
//                                h.put(k2.toString(), k2);
//                                lines.add(h);
//                            } else {
//                                System.out.println();
//                                // new
//
//                                HashMap<String, TerritoryPositionCircle> h = new HashMap<String, TerritoryPositionCircle>();
//
//                                ArrayList<HashMap<String, TerritoryPositionCircle>> lTemp = new ArrayList<HashMap<String, TerritoryPositionCircle>>();
//
//                                for(Integer k : mapMergeList){
//
//                                    HashMap<String, TerritoryPositionCircle> temp = lines.get(k);
//                                    for(String s : temp.keySet()){
//                                        h.put(s,temp.get(s));
//                                    }
//                                    lTemp.add(temp);
//                                }
//
//                                Log.d(TAG, "Merging hashmaps : " + k1.toString());
//
//                                lines.add(h);
//
//                                for(HashMap<String, TerritoryPositionCircle> temp : lTemp){
//                                    lines.remove(temp);
//                                }
//
//                                lTemp.clear();
//                                mapMergeList.clear();
//
//                                Log.d(TAG, "lines size : " + lines.size());
//
//                                for(HashMap<String, TerritoryPositionCircle> h2 : lines){
//                                    Log.d(TAG, "Hash: "  + h2.toString());
//                                    Log.d(TAG, "Hash: "  + h2.keySet().size());
//                                    for(String s : h2.keySet()){
//                                        Log.d(TAG, "Position: " + h2.get(s));
//                                    }
//                                }
//                            }

                            inTree = false;

                            args[0] = k1.getPosition()[0];
                            args[1] = k1.getPosition()[1];
                            args[2] = k2.getPosition()[0];
                            args[3] = k2.getPosition()[1];
                            TerritoryShapeLine t = new TerritoryShapeLineToNeighbor(args);

                            stringToLine.put(k1.toString() + "_" + k2.toString(), t);
                        }
                    }
                }

                if(!hasNeighbor) {
                    k1.increaseNeighborRadius();
                }
            }
        }

//        Log.d(TAG, "Number of territories: " + stringToTerritories.keySet().size());
//
//        Log.d(TAG, "Number of continents: " + lines.size());
//
//        for(HashMap<String, TerritoryPositionCircle> h : lines){
//            Log.d(TAG, "Hash: "  + h.toString());
//            Log.d(TAG, "Hash: "  + h.keySet().size());
//            for(String s : h.keySet()){
//                Log.d(TAG, "Position: " + h.get(s));
//            }
//        }
//
//        connectContinents(lines, args);

        return true;
    }

    public Boolean connectContinents(ArrayList<HashMap<String, TerritoryPositionCircle>> lines, float[] args) throws Exception {

        ArrayList<TerritoryPositionCircle> pList = new ArrayList<TerritoryPositionCircle>();
        String pString = "";
        for(HashMap<String, TerritoryPositionCircle> h : lines){
            pString = (String)h.keySet().toArray()[0];
            if(!pString.isEmpty()){
                pList.add(h.get(pString));
            }
        }

        TerritoryPositionCircle pPrev = null;
        TerritoryPositionCircle pCur = null;
        for(TerritoryPositionCircle p : pList){
            if(pPrev == null) {
                pPrev = p;
            } else {
                pCur = p;

                args = new float[5];
                args[0] = pPrev.getPosition()[0];
                args[1] = pPrev.getPosition()[1];
                args[2] = pCur.getPosition()[0];
                args[3] = pCur.getPosition()[1];
                TerritoryShapeLine t = new TerritoryShapeLineToNeighbor(args);

                stringToLine.put(pPrev.toString() + "_" + pCur.toString(), t);



                pPrev = p;
            }
        }
        return false;
    }

}
