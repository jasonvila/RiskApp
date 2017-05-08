package game.com.risk;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by jason on 5/7/2017.
 */

public class Territory {
    private TerritoryShape s;
    private HashMap<Integer, Long> tMilitary = new HashMap<Integer, Long>();
    private HashMap<Integer, Long> tBuildings = new HashMap<Integer, Long>();
    private Integer tTypes;
    private Boolean isCapital;
    private Integer team;


    public Territory(){
        team = 0;
        isCapital = false;
        s = new TerritoryShape();
        tTypes = GameConstants.GAME_CONSTANTS.numTTypes;
        for(int i = 0; i < tTypes; i++){
            tMilitary.put(i, 0L);
        }
    }

    public TerritoryShape getTerritoryShape(){
        return s;
    }

    public void draw(float[] m){
        s.draw(m);
    }

    public void setCapital(Boolean isCapital){
        this.isCapital = isCapital;
    }

    public void changeTeams(Integer team){
        this.team = team;
        Set<Integer> m = tMilitary.keySet();
        for(Integer t: m){
            tMilitary.put(t, 0L);
        }

        m = tBuildings.keySet();
        for(Integer t: m){
            tMilitary.put(t, 0L);
        }
    }

    public void addTroopType(Integer tType, Long numTroops){
        tMilitary.put(tType, tMilitary.get(tType) + numTroops);
    }

    public void addBuildingType(Integer bType, Long numBuildings){
        tBuildings.put(bType, tBuildings.get(bType) + numBuildings);
    }

}
