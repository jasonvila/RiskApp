package game.com.risk.SingleSurfaceView.Territory.Manager;

import android.util.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import game.com.risk.SingleSurfaceView.Territory.Position.TerritoryPositionCircle;
import game.com.risk.SingleSurfaceView.Territory.Territory;

/**
 * Created by jason on 5/15/2017.
 */

public abstract class TerritoryManager {

    protected static final String TAG = "TerritoryManagerCircle";

    protected Integer numTeams;
    protected Integer numTerritories;

    protected HashMap<String, Territory> stringToTerritories = new HashMap<String, Territory>();
    protected HashMap<TerritoryPositionCircle, String> positionToTerritories = new HashMap<TerritoryPositionCircle, String>();

    protected Integer[] coord = new Integer[65];

    protected Territory selectedTerritory;

    public TerritoryManager(Integer numTeams, Integer numTerritories){

        for(int i = 0; i < coord.length; i++){
            coord[i] = i;
        }

        Collections.shuffle(Arrays.asList(coord));

        this.numTeams = numTeams;
        this.numTerritories = numTerritories;

    }

    public void draw(float[] m){

        Set<String> key = stringToTerritories.keySet();
        for(String tN : key){
            stringToTerritories.get(tN).draw(m);
        }
    }

    public void selectTerritory(float x, float y){
        Set<TerritoryPositionCircle> keySet = positionToTerritories.keySet();

        Territory newSelectedTerritory;

        for(TerritoryPositionCircle k : keySet){
            if(k.isInTerritory(x, y)){
                newSelectedTerritory = stringToTerritories.get(positionToTerritories.get(k));

                if(selectedTerritory != null && !newSelectedTerritory.equals(selectedTerritory)){
                    selectedTerritory.setIsSelected();
                }

                selectedTerritory = newSelectedTerritory;

                selectedTerritory.setIsSelected();
                break;
            }
        }
    }

    public void changeTeam(Integer team, float x, float y){
        Set<TerritoryPositionCircle> keySet = positionToTerritories.keySet();

        Log.d(TAG, "Changing to team: " + team + ", territory at position: " + x + ", " + y);

        for(TerritoryPositionCircle k : keySet){
            if(k.isInTerritory(x, y)){
                stringToTerritories.get(positionToTerritories.get(k)).changeTeams(team);
                break;
            }
        }

    }
}
