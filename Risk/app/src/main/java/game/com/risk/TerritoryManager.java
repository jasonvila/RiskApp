package game.com.risk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by jason on 5/7/2017.
 */

public class TerritoryManager {

    private Integer numTeams;
    private Integer numTerritories;

    private HashMap<String, Territory> stringToTerritories = new HashMap<String, Territory>();
    private HashMap<TerritoryPosition, String> positionToTerritories = new HashMap<TerritoryPosition, String>();
    private float[] xRand = new float[65];
    private float[] yRand = new float[65];
    private Integer[] coord = new Integer[65];


    public TerritoryManager(Integer numTeams, Integer numTerritories){

        for(int i = 0; i < coord.length; i++){
            coord[i] = i;
        }

        Collections.shuffle(Arrays.asList(coord));

        this.numTeams = numTeams;
        this.numTerritories = numTerritories;

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

        for(int i = 0; i < numTerritories; i++){
            Territory t = new Territory(xRand[coord[i]], yRand[coord[i]]);
            TerritoryPosition tP = new TerritoryPosition(xRand[coord[i]], yRand[coord[i]], 0.1f);
            stringToTerritories.put(t.toString(), t);
            positionToTerritories.put(tP, t.toString());
        }

        int idx = 0;
        Set<String> keySet = stringToTerritories.keySet();
        for(String k : keySet){

            if(idx == numTeams){
                idx = 0;
            }

            stringToTerritories.get(k).changeTeams(idx);
            idx ++;
        }
    }

    public void draw(float[] m){

        Set<String> key = stringToTerritories.keySet();
        for(String tN : key){
            stringToTerritories.get(tN).draw(m);
        }
    }

    public void changeTeam(Integer team, float x, float y){
        Set<TerritoryPosition> keySet = positionToTerritories.keySet();

        System.out.println("x: " + x + ", y: " + y);

        for(TerritoryPosition k : keySet){
            if(k.isInTerritory(x, y)){
                System.out.println("Found changing teams!!!!!!!");
                stringToTerritories.get(positionToTerritories.get(k)).changeTeams(team);
                break;
            }
        }

    }

}
