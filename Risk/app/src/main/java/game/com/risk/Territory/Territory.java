package game.com.risk.Territory;

import java.util.HashMap;
import java.util.Set;

import game.com.risk.Constants.GameConstants;
import game.com.risk.Territory.Shape.TerritoryShape;
import game.com.risk.Territory.Shape.TerritoryShapeCircle;

/**
 * Created by jason on 5/7/2017.
 */

public class Territory {

    private static final String TAG = "Territory";

    private TerritoryShape s;
    private HashMap<Integer, Long> tMilitary = new HashMap<Integer, Long>();
    private HashMap<Integer, Long> tBuildings = new HashMap<Integer, Long>();
    private Integer tTypes;
    private Boolean isCapital;
    private Integer team;


    public Territory(float[] args)throws Exception {
        team = 0;
        isCapital = false;
        s = new TerritoryShapeCircle(args);
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

        s.setColor(GameConstants.GAME_CONSTANTS.getTeamColor(team));
    }

    public void addTroopType(Integer tType, Long numTroops){
        tMilitary.put(tType, tMilitary.get(tType) + numTroops);
    }

    public void addBuildingType(Integer bType, Long numBuildings){
        tBuildings.put(bType, tBuildings.get(bType) + numBuildings);
    }

}
